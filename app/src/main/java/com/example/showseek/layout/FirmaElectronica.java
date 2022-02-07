package com.example.showseek.layout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.showseek.R;
import com.example.showseek.estructures.hash.HashS;
import com.example.showseek.objects.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;

public class FirmaElectronica extends AppCompatActivity{

    Button act;
    EditText pass;
    HashS set;
    String conNueva;
    String key;
    ProgressBar progress;
    String nombre;
    String key2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firma_electronica);

        act = findViewById(R.id.btnCrearFirma);
        pass = findViewById(R.id.pass);
        progress = findViewById(R.id.progressBarF);
        set = new HashS();

        Bundle datos = getIntent().getExtras();

        nombre = datos.getString("nombre");
        key2 = datos.getString("clave");

        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pass.getText().toString().compareTo("") == 0) {
                    pass.setError("La contraseña es necesaria");
                    pass.requestFocus();
                    return;
                } else if (pass.getText().toString().length() < 6) {
                    pass.setError("La contraseña mínima es de 7 caracteres");
                    pass.requestFocus();
                    return;
                }

                progress.setVisibility(v.VISIBLE);
                String con = pass.getText().toString();
                conNueva = String.valueOf(set.hashFun(con));

                FirebaseDatabase.getInstance().getReference()
                        .child("Users").child(key2).child("firma").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            if(snapshot.getValue().toString().compareTo("0") == 0){



                                FirebaseDatabase.getInstance().getReference()
                                        .child("Firmas").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.exists()){
                                            boolean yes = false;
                                            for(DataSnapshot snap : snapshot.getChildren()){
                                                key = snap.getKey();
                                                if(snap.getValue().toString().compareTo(conNueva) == 0){
                                                    yes = true;
                                                }

                                            }
                                            if(!yes){
                                                int k = Integer.valueOf(key);
                                                int n = k+1;
                                                FirebaseDatabase.getInstance().getReference()
                                                        .child("Firmas").child(String.valueOf(n))
                                                        .setValue(conNueva, new DatabaseReference.CompletionListener() {
                                                            @Override
                                                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                                                                FirebaseDatabase.getInstance().getReference()
                                                                        .child("Users").child(key2).child("firma")
                                                                        .setValue(1, new DatabaseReference.CompletionListener() {
                                                                            @Override
                                                                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                                                                progress.setVisibility(v.GONE);
                                                                                Toast.makeText(getApplicationContext(),"Nueva Firma Electrónica Creada", Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        });
                                                            }
                                                        });
                                            }
                                            else{
                                                Toast.makeText(getApplicationContext(),"Error al crear la Firma Electrónica. Intente de Nuevo.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });



                            }
                            else{
                                progress.setVisibility(v.GONE);
                                Toast.makeText(getApplicationContext(),"Ya tiene una Firma creada", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        });

    }
}