package com.example.showseek.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.showseek.R;

public class DetailArtista extends AppCompatActivity implements View.OnClickListener{

    private ImageView foto;
    private TextView nombre, tipo, genero;
    private RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail_artista);


        Bundle datos = getIntent().getExtras();

        String nombreS = datos.getString("nombre");
        String tipoS = datos.getString("tipo");
        String generoS = datos.getString("genero");
        Float ratingS = datos.getFloat("rating");
        String fotoS = datos.getString("foto");

        foto = findViewById(R.id.pictureArtista);
        nombre = findViewById(R.id.nombreArtisticoInfo);
        tipo = findViewById(R.id.tipoAgrupacionInfo);
        genero = findViewById(R.id.generoMusicalInfo);
        rating = findViewById(R.id.ratingBarInfo);

        nombre.setText(nombreS);
        tipo.setText(tipoS);
        genero.setText(generoS);
        rating.setRating(ratingS);

        Log.d("foto URL", fotoS);

        if(fotoS != null){
            Glide.with(this).load(fotoS).centerCrop().into(foto);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCrearContrato:
                //startActivity(new Intent(this, FormularioContrato.class));
                break;
        }
    }
}