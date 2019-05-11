package com.example.reto2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.reto2.Model.CancionModel;
import com.example.reto2.Model.PlaylistModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterPlaylist.OnItemClickListener {

    private TextView txt_buscar_playlist;
    private EditText edit_playlist;
    private Button btn_buscar;

    private RecyclerView lista_playlist;
    private AdapterPlaylist adapterPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_buscar_playlist = findViewById(R.id.txt_buscar_playlist);
        edit_playlist = findViewById(R.id.edit_playlist);
        btn_buscar = findViewById(R.id.btn_buscar);
        lista_playlist = findViewById(R.id.lista_playlist);

        adapterPlaylist = new AdapterPlaylist();
        adapterPlaylist.setListener(this);

        new Thread(() -> {
            new ServiceManager.PlaylistGET(new ServiceManager.PlaylistGET.OnResponseListener() {
                @Override
                public void onResponse(String response) {
                   // response = "https://api.deezer.com/playlist/908622995";
                    runOnUiThread(() -> {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray allPlayList = jsonObject.getJSONArray("data");

                            for(int i=0;i<allPlayList.length();i++){

                                JSONObject play = allPlayList.getJSONObject(i);
                                JSONObject user = play.getJSONObject("creator");

                                PlaylistModel playModel = new PlaylistModel();
                                playModel.setNombre_lista(play.getString("title"));
                                playModel.setNombre_usuario(user.getString("name"));
                                playModel.setNum_canciones(play.getInt("nb_tracks"));
                                playModel.setImagen(play.get("picture").toString());
                                //playModel.setDescripcion(play.getString("description"));
                                playModel.setNum_fans(play.getInt("fans"));

                                adapterPlaylist.agregarPlayList(playModel);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    });
                }
            });
        }).start();

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapterPlaylist = new AdapterPlaylist();
                adapterPlaylist.setListener(MainActivity.this);
                String busqueda = edit_playlist.getText().toString();

                new Thread(() -> {
                    new ServiceManager.SearchPlaylistGET(new ServiceManager.SearchPlaylistGET.OnResponseListener() {
                        @Override
                        public void onResponse(String response) {
                            // response = "https://api.deezer.com/playlist/908622995";
                            runOnUiThread(() -> {
                                try {
                                    String resp = response + edit_playlist.getText().toString();
                                    JSONObject jsonObject = new JSONObject(resp);
                                    JSONArray allPlayList = jsonObject.getJSONArray("data");

                                    for(int i=0;i<allPlayList.length();i++){

                                        JSONObject play = allPlayList.getJSONObject(i);
                                        JSONObject user = play.getJSONObject("user");

                                        PlaylistModel playModel = new PlaylistModel();
                                        playModel.setNombre_lista(play.getString("title"));
                                        playModel.setNombre_usuario(user.getString("name"));
                                        playModel.setNum_canciones(play.getInt("nb_tracks"));
                                        playModel.setImagen(play.get("picture").toString());
                                        //playModel.setDescripcion(play.getString("description"));
                                        playModel.setNum_fans(0);

                                        adapterPlaylist.agregarPlayList(playModel);

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            });
                        }
                    },busqueda);
                }).start();
            }
        });

        lista_playlist.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        lista_playlist.setAdapter(adapterPlaylist);
        lista_playlist.setHasFixedSize(true);

    }


    @Override
    public void onItemClick(PlaylistModel playlist) {

    }

    @Override
    public void onChat(PlaylistModel playlist) {

    }
}
