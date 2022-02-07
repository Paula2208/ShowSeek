package com.example.showseek.layout.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.showseek.R;
import com.example.showseek.objects.Artista;
import com.example.showseek.objects.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilFragment extends Fragment {

    EditText nombre, edad;
    CheckBox artista;
    Button act;
    ProgressBar progress;
    DatabaseReference database;
    Usuario si;
    String key;
    String nom;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        nombre = v.findViewById(R.id.fullNameA);
        edad = v.findViewById(R.id.ageA);
        act = v.findViewById(R.id.actualizarUser);
        artista = v.findViewById(R.id.checkArtistaA);
        progress = v.findViewById(R.id.progressBarA);
        progress.setVisibility(v.GONE);

        nom = getArguments().getString("nombre");
        String tipo = getArguments().getString("tipo");

        nombre.setText(nom);

        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDatos(v);
                progress.setVisibility(v.VISIBLE);
            }
        });


        return v;
    }

    public void actualizar(View v){

        Log.d("Key   ",key);
        try{
            si.setFecha_nacimiento(edad.getText().toString());
            si.setNombre(nombre.getText().toString());

            if((si.getTipo().compareTo("cliente") == 0) && (artista.isChecked())){
                si.setTipo("artista");
            }

            database.child("Users").child(key).setValue(si, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    progress.setVisibility(v.GONE);
                    Toast.makeText(getContext(),"Datos de Usuario Correctamente Actualizados", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getContext(),"Error al actualizar los datos del Usuario", Toast.LENGTH_SHORT).show();
        }


    }

    public void getDatos(View v){



        database = FirebaseDatabase.getInstance().getReference();
        database.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.d("IMPORTANTE ","ESPERAR A QUE ESTO SALGA PARA OPRIMIR BOTONES");

                if(snapshot.exists()){
                    for(DataSnapshot snap : snapshot.getChildren()){
                        Usuario ar = snap.getValue(Usuario.class);

                        if(ar.getNombre().compareTo(nom) == 0){

                            key = snap.getKey();
                            edad.setText(ar.getFecha_nacimiento());
                            si = ar;

                            actualizar(v);
                            break;
                        }





                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}