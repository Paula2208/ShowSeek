package com.example.showseek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    Button b1, b2;
    TextView salida;
    EditText entrada;
    String variable;
    QueueRef queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.b_ingresar);
        b2 = findViewById(R.id.b_refrescar);

        salida = findViewById(R.id.salida_texto);
        entrada = findViewById(R.id.entrada_texto);

        queue = new QueueRef();

        variable ="";

        refrescar();
        ingresar();

        String prueba = "Se logró la salida por consola";
        Log.d("Salida: ",prueba);
    }

    public void refrescar(){
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(variable == ""){
                    salida.setText("Se activa el botón refrescar");
                }
                else{
                    String s = "";

                    for(int i=0; i <=queue.getSize(); i++){
                        s = s + " | " + queue.dequeue();
                    }
                    salida.setText(s);
                }

            }
        });
    }

    public void ingresar(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                variable = String.valueOf(entrada.getText());
                queue.enqueue(variable);
                salida.setText(variable);
                Log.d("Dato ingresado: ", variable);
                Log.d("", queue.getMessage());
                Log.d("Dato ingresado: ", "-----------------------------------------------------------------------------------------");
                salida.setText(variable);
            }
        });
    }

}


