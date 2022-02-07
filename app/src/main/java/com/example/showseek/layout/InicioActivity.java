package com.example.showseek.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.showseek.R;
import com.example.showseek.layout.Fragments.BuscarFragment;
import com.example.showseek.layout.Fragments.ContratoFragment;
import com.example.showseek.layout.Fragments.PerfilFragment;
import com.example.showseek.layout.Fragments.WelcomeFragment;

public class InicioActivity extends AppCompatActivity {

    private boolean clicked = false;
    FragmentTransaction nav;
    Fragment welcome, buscar, contrato, perfil;
    String tipo;
    String nombre;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Bundle datos = getIntent().getExtras();

        nombre = datos.getString("nombre");
        tipo = datos.getString("tipo");
        key = datos.getString("clave");

        Bundle args = new Bundle();

        // Colocamos el String
        args.putString("nombre", nombre);
        args.putString("tipo", tipo);
        args.putString("clave", key);

        welcome = new WelcomeFragment();
        welcome.setArguments(args);
        buscar = new BuscarFragment();
        contrato = new ContratoFragment();
        perfil = new PerfilFragment();
        perfil.setArguments(args);

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor_inicio,welcome).commit();
    }

    public void onClick(View view){
        String prueba ="";
        nav = getSupportFragmentManager().beginTransaction();

        switch(view.getId()){
            case R.id.search:
                nav.replace(R.id.contenedor_inicio,buscar);
                nav.addToBackStack(null);
                prueba = "Se entra a buscar artista";
                Log.d("Fragment al que entra: ",prueba);
                break;
            case R.id.files:
                nav.replace(R.id.contenedor_inicio,contrato);
                nav.addToBackStack(null);
                prueba = "Se entra a ver contratos";
                Log.d("Fragment al que entra: ",prueba);
                break;
            case R.id.account:
                nav.replace(R.id.contenedor_inicio,perfil);
                nav.addToBackStack(null);
                prueba = "Se entra a perfil";
                Log.d("Fragment al que entra: ",prueba);
                break;

            case R.id.boton_flotante_menu:
                onBotonMenuClicked();
                break;

            case R.id.boton_flotante_perfil:

                //Perfil Artista
                Intent arPerfil = new Intent(InicioActivity.this, FirmaElectronica.class);
                arPerfil.putExtra("tipo",tipo);
                arPerfil.putExtra("nombre",nombre);
                arPerfil.putExtra("clave",key);
                startActivity(arPerfil);
                break;

            case R.id.boton_flotante_clave:

                //Crear Clave Electrónica
                Intent clave = new Intent(InicioActivity.this, FirmaElectronica.class);
                clave.putExtra("tipo",tipo);
                clave.putExtra("nombre",nombre);
                startActivity(clave);
                break;

            case R.id.boton_flotante_recibo:

                //Crear Facturas
                Intent facturas = new Intent(InicioActivity.this, VistaFacturacion.class);
                facturas.putExtra("tipo",tipo);
                facturas.putExtra("nombre",nombre);
                facturas.putExtra("clave",key);
                startActivity(facturas);
                break;
        }

        nav.commit();
    }

    private void onBotonMenuClicked(){
        setVisibiliy(clicked);
        clicked = !clicked;
    }

    private void setVisibiliy(Boolean clicked){
        if(!clicked){


            View botonClave = findViewById(R.id.boton_flotante_clave);
            botonClave.setVisibility(View.VISIBLE);
            View botonRecibo = findViewById(R.id.boton_flotante_recibo);
            botonRecibo.setVisibility(View.VISIBLE);

            if(tipo.compareTo("artista") == 0){
                View botonPerfil = findViewById(R.id.boton_flotante_perfil);
                botonPerfil.setVisibility(View.VISIBLE);
            }

        }else{
            View botonPerfil = findViewById(R.id.boton_flotante_perfil);
            botonPerfil.setVisibility(View.INVISIBLE);
            View botonClave = findViewById(R.id.boton_flotante_clave);
            botonClave.setVisibility(View.INVISIBLE);
            View botonRecibo = findViewById(R.id.boton_flotante_recibo);
            botonRecibo.setVisibility(View.INVISIBLE);
        }
    }
}