package com.example.reto2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reto2.Model.CancionModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListaCanciones extends AppCompatActivity implements AdapterCanciones.OnItemClickListener {

    private Button btn_atras_playlists;
    private TextView txt_lista_canciones;

    private ImageView image_playlist;
    private TextView txt_nombre_play;
    private TextView txt_descrip_play;
    private TextView txt_num_canciones;
    private TextView txt_num_fans;

    private RecyclerView lista_canciones;
    private AdapterCanciones adapterCanciones;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_canciones);

        btn_atras_playlists = findViewById(R.id.btn_atras);
        txt_lista_canciones = findViewById(R.id.txt_canciones);
        image_playlist = findViewById(R.id.image_playlist);
        txt_nombre_play = findViewById(R.id.txt_nombre_playlist);
        txt_descrip_play = findViewById(R.id.txt_desc_playlist);
        txt_num_canciones = findViewById(R.id.txt_num_canciones);
        txt_num_fans = findViewById(R.id.txt_num_fans);

        lista_canciones = findViewById(R.id.lista_canciones);

        adapterCanciones = new AdapterCanciones();
        adapterCanciones.setListener(this);

        Intent i = getIntent();
        Picasso.get()
                .load(i.getStringExtra("Imagen"))
                .into(image_playlist);
        txt_nombre_play.setText(i.getStringExtra("NombrePlay"));
        txt_descrip_play.setText(i.getStringExtra("Descripcion"));
        txt_num_canciones.setText("# canciones: "+i.getIntExtra("NumCan",0));
        txt_num_fans.setText("# fans: "+i.getIntExtra("NumFan",0));

        int id = i.getIntExtra("Id",0);

        new Thread(() -> {
            new ServiceManager.PlaylistId(new ServiceManager.PlaylistId.OnResponseListener() {
                @Override
                public void onResponse(String response) {

                    runOnUiThread(() -> {
                        try {
                            JSONObject jsonObjPlay = new JSONObject(response);
                            JSONObject tracks = jsonObjPlay.getJSONObject("tracks");
                            JSONArray allcanciones = tracks.getJSONArray("data");

                            for(int i=0;i<allcanciones.length();i++){

                                JSONObject jsonCan = allcanciones.getJSONObject(i);
                                JSONObject artista = jsonCan.getJSONObject("artist");
                                JSONObject album = jsonCan.getJSONObject("album");

                                int id = jsonCan.getInt("id");

                                CancionModel cancion = new CancionModel();
                                cancion.setId(id);
                                cancion.setArtista(artista.getString("name"));
                                if(album.has("cover")){
                                    cancion.setImagen(album.get("cover").toString());
                                }else{
                                    cancion.setImagen("https://api.deezer.com/album/302127/image");
                                }

                                cancion.setAlbum(album.getString("title"));
                                cancion.setDuracion(jsonCan.getInt("duration"));
                                cancion.setNombre(jsonCan.getString("title"));

                                new Thread(() -> {
                                    new ServiceManager.CancionGETId(new ServiceManager.CancionGETId.OnResponseListener() {
                                        @Override
                                        public void onResponse(String response) {

                                            runOnUiThread(() -> {
                                                try {
                                                    JSONObject jsonObjCan = new JSONObject(response);
                                                    if(jsonObjCan.has("release_date")){
                                                        cancion.setAnio(jsonObjCan.get("release_date").toString());
                                                    }else{
                                                        cancion.setAnio("No esta registrado");
                                                    }

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }

                                            });
                                        }
                                    }, id);
                                }).start();

                                adapterCanciones.agregarCancion(cancion);

                            }

                            lista_canciones.setLayoutManager(new LinearLayoutManager(ListaCanciones.this));
                            lista_canciones.setAdapter(adapterCanciones);
                            lista_canciones.setHasFixedSize(true);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    });
                }
            }, id);
        }).start();

        btn_atras_playlists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onItemClick(CancionModel cancion) {

        Intent i = new Intent(ListaCanciones.this, Cancion.class);

        i.putExtra("Id", cancion.getId());
        i.putExtra("Imagen", cancion.getImagen());
        i.putExtra("Nombre", cancion.getNombre());
        i.putExtra("Artista", cancion.getArtista());
        i.putExtra("Album", cancion.getAlbum());
        i.putExtra("Duracion", cancion.getDuracion());

        startActivity(i);
    }

}
