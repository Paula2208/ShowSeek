package com.example.showseek.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.showseek.R;
import com.example.showseek.layout.Fragments.BuscarFragment;
import com.example.showseek.layout.Fragments.ContratoFragment;
import com.example.showseek.layout.Fragments.PerfilFragment;
import com.example.showseek.layout.Fragments.WelcomeFragment;

public class InicioActivity extends AppCompatActivity {

    FragmentTransaction nav;
    Fragment welcome, buscar, contrato, perfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        welcome = new WelcomeFragment();
        buscar = new BuscarFragment();
        contrato = new ContratoFragment();
        perfil = new PerfilFragment();

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
        }

        nav.commit();
    }
}