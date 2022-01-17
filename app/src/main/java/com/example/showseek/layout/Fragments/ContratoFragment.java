package com.example.showseek.layout.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.showseek.R;
import com.example.showseek.estructures.array.ListArray;
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

    ListArray<Contrato> avlContratos;
    ListArray<Contrato> arrayContratos;
    DatabaseReference database;
    EditText fecha;
    TextView aceptado, costoFinal, costoInicial;
    TextView direccion, ciudad, hora, fechaEvento;
    ImageButton search;
    String FECHA;
    View view;


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
        view = inflater.inflate(R.layout.fragment_contrato, container, false);


        fecha = view.findViewById(R.id.fechaText);

        aceptado = view.findViewById(R.id.aceptado);
        costoFinal = view.findViewById(R.id.costoFinal);
        costoInicial = view.findViewById(R.id.costoInicial);
        direccion = view.findViewById(R.id.direccionEvento);
        ciudad = view.findViewById(R.id.ciudadEvento);
        hora = view.findViewById(R.id.horaEvento);
        fechaEvento = view.findViewById(R.id.fechaEvento);

        search = view.findViewById(R.id.searchFecha);
        button();
        return view;
    }

    private void button(){
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FECHA = fecha.getText().toString();
                Log.d("Tiempo de Ejecución: ",FECHA);
                //avlContratos = busquedaBD(view);
                buscarAVL();
                aceptado.setText("EN ESTUDIO");
                costoFinal.setText("$527.310");
                costoInicial.setText(" $453.332");
                direccion.setText("Calle 25 # 29 - 24");
                ciudad.setText("Cali");
                hora.setText("15:46");
                fechaEvento.setText(FECHA);
            }
        });
    }

    private void buscarAVL(){

    }

    private ListArray busquedaBD(View view) {
        ListArray<Contrato> avl = new ListArray<Contrato>();
        //Señalamos a la raiz de contratos
        database = FirebaseDatabase.getInstance().getReference();
        database.child("Contratos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                avl.clear();
                int contador = 0;
                if(snapshot.exists()){
                    long inicio = System.nanoTime();
                    for(DataSnapshot snap : snapshot.getChildren()){
                        Contrato ar = snap.getValue(Contrato.class);
                        avl.pushBack(ar);
                        Log.d("Hora evento: ",ar.getHora_evento()+" --- "+contador);
                        contador++;
                        if(contador == 10){ //Se cargan solo 1000 contratos para el video
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