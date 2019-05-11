package com.example.reto2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class Cancion extends AppCompatActivity {

    private Button btn_atras_cancion;
    private TextView txt_ver_cancion;

    private ImageView image_cancion;
    private TextView txt_nombre_cancion;
    private TextView txt_artista_cancion;
    private TextView txt_album_cancion;
    private TextView txt_dura_cancion;
    private TextView txt_linea_cancion;
    private Button btn_escuchar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancion);

        btn_atras_cancion = findViewById(R.id.btn_atras_cancion);
        txt_ver_cancion = findViewById(R.id.txt_ver_cancion);

        image_cancion = findViewById(R.id.image_cancion);
        txt_nombre_cancion = findViewById(R.id.txt_nombre_cancion);
        txt_artista_cancion = findViewById(R.id.txt_art_cancion);
        txt_album_cancion = findViewById(R.id.txt_album_cancion);
        txt_dura_cancion = findViewById(R.id.txt_dura_cancion);
        txt_linea_cancion = findViewById(R.id.txt_linea_cancion);
        btn_escuchar = findViewById(R.id.btn_escuchar);

        btn_atras_cancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Cancion.this, ListaCanciones.class);
                startActivity(i);
                finish();
            }
        });

        btn_escuchar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
