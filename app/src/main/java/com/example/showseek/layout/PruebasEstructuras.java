package com.example.showseek.layout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.showseek.R;
import com.example.showseek.estructures.array.ListArray;
import com.example.showseek.estructures.array.QueueArray;
import com.example.showseek.estructures.array.StackArray;
import com.example.showseek.estructures.hash.HashM;
import com.example.showseek.estructures.hash.HashS;
import com.example.showseek.estructures.nonLineal.AVLTree;
import com.example.showseek.estructures.nonLineal.ColaPrioridad;
import com.example.showseek.estructures.references.single.IDs;
import com.example.showseek.objects.Artista;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PruebasEstructuras extends AppCompatActivity implements View.OnClickListener {

    public StackArray<Artista> pila;
    public ColaPrioridad<Artista> cola;
    public AVLTree<Artista> avl;
    public HashS password;
    public HashS firma;
    public HashM factura;

    public ListArray<Artista> colabora;
    public DatabaseReference database;
    public int size;
    public EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas_estructuras);

        creacion();

        cargarDatos();

        text = findViewById(R.id.editTextNumber);

    }

    private void cargarDatos(){

        //Señalamos a la raiz de artistas y llenamos la cola que nos va a colaborar
        database = FirebaseDatabase.getInstance().getReference();
        database.child("Artistas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                colabora.clear();
                Log.d("IMPORTANTE ","ESPERAR A QUE ESTO SALGA PARA OPRIMIR BOTONES");

                if(snapshot.exists()){
                    for(DataSnapshot snap : snapshot.getChildren()){
                        Artista ar = snap.getValue(Artista.class);
                        colabora.pushBack(ar);


                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void creacion() {

        long inicio = System.nanoTime();
        pila = new StackArray<Artista>();
        long fin = System.nanoTime();
        long tiempofinal = fin - inicio;

        String prueba = Long.toString(tiempofinal);
        Log.d("Creación pila: ",prueba);

        inicio = System.nanoTime();
        cola = new ColaPrioridad<Artista>();
        fin = System.nanoTime();
        tiempofinal = fin - inicio;

        prueba = Long.toString(tiempofinal);
        Log.d("Creación Cola: ",prueba);

        inicio = System.nanoTime();
        avl = new AVLTree<Artista>();
        fin = System.nanoTime();
        tiempofinal = fin - inicio;

        prueba = Long.toString(tiempofinal);
        Log.d("Creación AVL: ",prueba);

        inicio = System.nanoTime();
        password = new HashS();
        fin = System.nanoTime();
        tiempofinal = fin - inicio;

        prueba = Long.toString(tiempofinal);
        Log.d("Creación Tabla Hash: ",prueba);

        firma = new HashS();
        factura = new HashM(100000);
        colabora = new QueueArray<Artista>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPila:
                Pila();
                break;

            case R.id.btnCola:
                Cola();
                break;

            case R.id.btnAVL:
                AVL();
                break;

            case R.id.btnHashContraseña:
                HashCon();
                break;

            case R.id.btnHashFirma:
                HashFirma();
                break;

            case R.id.btnHashFactura:
                HashFactura();
                break;

            case R.id.btnSet:
                Numero();
                break;
        }
    }

    private void Numero() {

        String num = text.getText().toString().trim();
        size = Integer.parseInt(num);
        Log.d("Tamaño de Datos: ",num);
    }

    private void Cola() {

        long inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            cola.enqueue(colabora.get(i));
        }

        long fin = System.nanoTime();
        long tiempofinal = fin - inicio;

        String prueba = Long.toString(tiempofinal);
        Log.d("Insert Cola: ",prueba);


        inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            cola.dequeue();
        }

        fin = System.nanoTime();
        tiempofinal = fin - inicio;

        prueba = Long.toString(tiempofinal);
        Log.d("Delete Cola: ",prueba);
    }

    private void Pila() {

        long inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            pila.push(colabora.get(i));
        }

        long fin = System.nanoTime();
        long tiempofinal = fin - inicio;

        String prueba = Long.toString(tiempofinal);
        Log.d("Insert Pila: ",prueba);


        inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            pila.pop();
        }

        fin = System.nanoTime();
        tiempofinal = fin - inicio;

        prueba = Long.toString(tiempofinal);
        Log.d("Delete Pila: ",prueba);
    }

    private void AVL() {

        long inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            avl.add(colabora.get(i));
        }

        long fin = System.nanoTime();
        long tiempofinal = fin - inicio;

        String prueba = Long.toString(tiempofinal);
        Log.d("Insert AVL: ",prueba);


        inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            avl.find(colabora.get(i), avl.getRoot());
        }

        fin = System.nanoTime();
        tiempofinal = fin - inicio;

        prueba = Long.toString(tiempofinal);
        Log.d("Find AVL: ",prueba);



        inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            avl.delete(colabora.get(i));
        }

        fin = System.nanoTime();
        tiempofinal = fin - inicio;

        prueba = Long.toString(tiempofinal);
        Log.d("Delete AVL: ",prueba);

    }

    private void HashCon() {

        long inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            int correo = password.hashFun(colabora.get(i).getCorreo());
            int contra = password.hashFun(colabora.get(i).getNombreArtistico());
            String a = String.valueOf(correo + contra);
            password.add(a);
        }

        long fin = System.nanoTime();
        long tiempofinal = fin - inicio;

        String prueba = Long.toString(tiempofinal);
        Log.d("Insert HashContra: ",prueba);





        inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            int correo = password.hashFun(colabora.get(i).getCorreo());
            int contra = password.hashFun(colabora.get(i).getNombreArtistico());
            String a = String.valueOf(correo + contra);
            password.find(a);
        }

        fin = System.nanoTime();
        tiempofinal = fin - inicio;

        prueba = Long.toString(tiempofinal);
        Log.d("Find HashContra: ",prueba);


        inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            int correo = password.hashFun(colabora.get(i).getCorreo());
            int contra = password.hashFun(colabora.get(i).getNombreArtistico());
            String a = String.valueOf(correo + contra);
            password.delete(a);
        }

        fin = System.nanoTime();
        tiempofinal = fin - inicio;

        prueba = Long.toString(tiempofinal);
        Log.d("Delete HashContra: ",prueba);
    }

    private void HashFirma() {

        long inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            firma.add(colabora.get(i).getNombreArtistico());
        }

        long fin = System.nanoTime();
        long tiempofinal = fin - inicio;

        String prueba = Long.toString(tiempofinal);
        Log.d("Insert HashFirma: ",prueba);





        inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            firma.find(colabora.get(i).getNombreArtistico());
        }

        fin = System.nanoTime();
        tiempofinal = fin - inicio;

        prueba = Long.toString(tiempofinal);
        Log.d("Find HashFirma: ",prueba);


        inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            firma.delete(colabora.get(i).getNombreArtistico());
        }

        fin = System.nanoTime();
        tiempofinal = fin - inicio;

        prueba = Long.toString(tiempofinal);
        Log.d("Delete HashFirma: ",prueba);
    }

    private void HashFactura() {

        long inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            IDs p = new IDs(colabora.get(i).getID(),colabora.get(i).getID());
            factura.add(p);
        }

        long fin = System.nanoTime();
        long tiempofinal = fin - inicio;

        String prueba = Long.toString(tiempofinal);
        Log.d("Insert HashFactura: ",prueba);





        inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            factura.findEmpById(colabora.get(i).getID());
        }

        fin = System.nanoTime();
        tiempofinal = fin - inicio;

        prueba = Long.toString(tiempofinal);
        Log.d("Find HashFactura: ",prueba);


        inicio = System.nanoTime();

        for(int i = 0; i < size; i++){
            factura.delete(colabora.get(i).getID());
        }

        fin = System.nanoTime();
        tiempofinal = fin - inicio;

        prueba = Long.toString(tiempofinal);
        Log.d("Delete HashFactura: ",prueba);
    }
}