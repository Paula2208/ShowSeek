package com.example.showseek.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.showseek.R;

public class CreacionContrato extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_contrato);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button2:
                Toast.makeText(CreacionContrato.this, "Cotización enviada. Se te notificará cuando hayan cambios en tu contrato.", Toast.LENGTH_LONG).show();

                break;
        }
    }
}