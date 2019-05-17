package com.example.reto2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reto2.Model.CancionModel;
import com.example.reto2.Model.PlaylistModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
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

                    runOnUiThread(() -> {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray allPlayList = jsonObject.getJSONArray("data");

                            for(int i=0;i<allPlayList.length();i++){

                                JSONObject play = allPlayList.getJSONObject(i);
                                JSONObject user = play.getJSONObject("creator");

                                PlaylistModel playModel = new PlaylistModel();
                                playModel.setId(play.getInt("id"));
                                playModel.setNombre_lista(play.getString("title"));
                                playModel.setNombre_usuario(user.getString("name"));
                                playModel.setNum_canciones(play.getInt("nb_tracks"));
                                playModel.setImagen(play.get("picture").toString());
                                if (play.has("description")) {
                                    playModel.setDescripcion(play.getString("description"));
                                }else{
                                    playModel.setDescripcion("No hay descripción");
                                }
                                playModel.setNum_fans(play.getInt("fans"));

                                adapterPlaylist.agregarPlayList(playModel);

                            }

                            lista_playlist.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            lista_playlist.setAdapter(adapterPlaylist);
                            lista_playlist.setHasFixedSize(true);

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

                if(!edit_playlist.getText().toString().equals("")&&edit_playlist.getText()!=null) {

                    new Thread(() -> {
                        new ServiceManager.SearchPlaylistGET(new ServiceManager.SearchPlaylistGET.OnResponseListener() {
                            @Override
                            public void onResponse(String response) {

                                runOnUiThread(() -> {
                                    try {

                                        JSONObject jsonObject = new JSONObject(response);
                                        JSONArray allPlayList = jsonObject.getJSONArray("data");

                                        for (int i = 0; i < allPlayList.length(); i++) {

                                            JSONObject play = allPlayList.getJSONObject(i);
                                            JSONObject user = play.getJSONObject("user");

                                            PlaylistModel playModel = new PlaylistModel();
                                            playModel.setId(play.getInt("id"));
                                            playModel.setNombre_lista(play.getString("title"));
                                            playModel.setNombre_usuario(user.getString("name"));
                                            playModel.setNum_canciones(play.getInt("nb_tracks"));
                                            playModel.setImagen(play.get("picture").toString());
                                            if (play.has("description")) {
                                                playModel.setDescripcion(play.getString("description"));
                                            }else{
                                                playModel.setDescripcion("No hay descripción");
                                            }
                                            if (play.has("fans")) {
                                                playModel.setNum_fans(play.getInt("fans"));
                                            }else {
                                                playModel.setNum_fans(0);
                                            }

                                            adapterPlaylist.agregarPlayList(playModel);

                                        }

                                        lista_playlist.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                        lista_playlist.setAdapter(adapterPlaylist);
                                        lista_playlist.setHasFixedSize(true);


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                });
                            }
                        }, edit_playlist.getText().toString());
                    }).start();
                }
            }
        });

    }


    @Override
    public void onItemClick(PlaylistModel playlist) {

        Intent i = new Intent(MainActivity.this, ListaCanciones.class);

        i.putExtra("Id", playlist.getId());
        i.putExtra("Imagen", playlist.getImagen());
        i.putExtra("NombrePlay", playlist.getNombre_lista());
        i.putExtra("Descripcion",playlist.getDescripcion());
        i.putExtra("NumCan",playlist.getNum_canciones());
        i.putExtra("NumFan",playlist.getNum_fans());

        startActivity(i);

    }

}
