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

import com.example.showseek.R;
import com.example.showseek.estructures.array.ListArray;
import com.example.showseek.estructures.nonLineal.ColaPrioridad;
import com.example.showseek.objects.Artista;
import com.example.showseek.otros.artistasAdaptador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuscarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuscarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Atributos
    RecyclerView recyclerArtistas;
    artistasAdaptador adapter;
    DatabaseReference database;

    public BuscarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuscarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuscarFragment newInstance(String param1, String param2) {
        BuscarFragment fragment = new BuscarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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

        ColaPrioridad<Artista> queue = llenadoCola();

        int size = queue.getSize();

        adapter = new artistasAdaptador(queue, getContext(), size);
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
                int contador = 0;
                String prueba ="Se insertó el item con nombre artístico :";
                if(snapshot.exists()){
                    for(DataSnapshot snap : snapshot.getChildren()){
                        Artista ar = snap.getValue(Artista.class);
                        queue.enqueue(ar);
                        contador++;
                        if(contador == 1000){
                            break;
                        }
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return queue;
    }
}