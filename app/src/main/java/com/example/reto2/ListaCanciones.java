package com.example.reto2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reto2.Model.CancionModel;

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
        lista_canciones.setLayoutManager(new LinearLayoutManager(this));
        lista_canciones.setAdapter(adapterCanciones);
        lista_canciones.setHasFixedSize(true);

        btn_atras_playlists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaCanciones.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onItemClick(CancionModel cancion) {

    }

    @Override
    public void onChat(CancionModel cancion) {

    }
}
