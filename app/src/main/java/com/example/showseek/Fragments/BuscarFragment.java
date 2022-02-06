package com.example.showseek.layout.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.showseek.R;
import com.example.showseek.estructures.nonLineal.ColaPrioridad;
import com.example.showseek.objects.Artista;
import com.example.showseek.otros.artistasAdaptador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class BuscarFragment extends Fragment{

    //Atributos
    RecyclerView recyclerArtistas;
    artistasAdaptador adapter;
    DatabaseReference database;
    ProgressBar progress;

    public BuscarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);
        inicializarRecycler(view);
        return view;
    }

    private void inicializarRecycler(View view) {
        recyclerArtistas = view.findViewById(R.id.recyclerArtistas);
        recyclerArtistas.setLayoutManager(new LinearLayoutManager(getContext()));

        progress = view.findViewById(R.id.progressBar2);
        progress.setVisibility(View.VISIBLE);

        ColaPrioridad<Artista> queue = llenadoCola();
        int size = queue.getSize();

        adapter = new artistasAdaptador(queue, getContext(), size, getActivity());
        recyclerArtistas.setAdapter(adapter);
    }

    public ColaPrioridad llenadoCola(){


        ColaPrioridad<Artista> queue = new ColaPrioridad<Artista>();

        //Señalamos a la raiz de artistas
        database = FirebaseDatabase.getInstance().getReference();
        database.child("Artistas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                queue.clean();
                if(snapshot.exists()){
                    for(DataSnapshot snap : snapshot.getChildren()){
                        Artista ar = snap.getValue(Artista.class);
                        queue.enqueue(ar);

                    }
                }


                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return queue;
    }
}