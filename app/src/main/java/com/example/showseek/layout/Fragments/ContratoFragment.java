package com.example.showseek.layout.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.showseek.R;
import com.example.showseek.estructures.nonLineal.AVLTree;
import com.example.showseek.objects.Artista;
import com.example.showseek.objects.Contrato;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContratoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContratoFragment extends Fragment {

    AVLTree<Contrato> avlContratos;
    DatabaseReference database;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContratoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContratoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContratoFragment newInstance(String param1, String param2) {
        ContratoFragment fragment = new ContratoFragment();
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
        View view = inflater.inflate(R.layout.fragment_contrato, container, false);
        avlContratos = busquedaBD(view);

        return view;
    }

    private AVLTree busquedaBD(View view) {
        AVLTree<Contrato> avl = new AVLTree<Contrato>();
        //Señalamos a la raiz de contratos
        database = FirebaseDatabase.getInstance().getReference();
        database.child("Contratos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                avl.clean();
                int contador = 0;
                if(snapshot.exists()){
                    long inicio = System.nanoTime();
                    for(DataSnapshot snap : snapshot.getChildren()){
                        Contrato ar = snap.getValue(Contrato.class);
                        avl.add(ar);
                        contador++;
                        if(contador == 30){
                            break;
                        }
                    }
                    long fin = System.nanoTime();
                    long tiempofinal = fin - inicio;

                    String prueba = "--------------------------------------------------------------";
                    String rating = Long.toString(tiempofinal);
                    Log.d("Tiempo de Ejecución: ",prueba);
                    Log.d("","");
                    Log.d("","");
                    Log.d("","");
                    Log.d("","");
                    Log.d("Tiempo final ",rating);
                    Log.d("","");
                    Log.d("","");
                    Log.d("","");
                    Log.d("","");
                    Log.d("","");
                    Log.d("","");
                    Log.d("","");
                    Log.d("","----------------------------------------------------------------");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return avl;

    }


}