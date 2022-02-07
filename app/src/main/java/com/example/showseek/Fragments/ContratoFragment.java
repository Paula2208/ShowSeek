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


public class ContratoFragment extends Fragment {

    AVLTree<Contrato> avlContratos;
    ListArray<Contrato> uwu;
    DatabaseReference database;
    EditText fecha;
    TextView aceptado, costoFinal, costoInicial;
    TextView direccion, ciudad, hora, fechaEvento;
    ImageButton search;
    String FECHA;
    View view;
    public ContratoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contrato, container, false);


        fecha = view.findViewById(R.id.fechaText);
        uwu = new ListArray<Contrato>();

        aceptado = view.findViewById(R.id.aceptado);
        costoFinal = view.findViewById(R.id.costoFinal);
        costoInicial = view.findViewById(R.id.costoInicial);
        direccion = view.findViewById(R.id.direccionEvento);
        ciudad = view.findViewById(R.id.ciudadEvento);
        hora = view.findViewById(R.id.horaEvento);
        fechaEvento = view.findViewById(R.id.fechaEvento);

        search = view.findViewById(R.id.searchFecha);
        button();
        avlContratos = busquedaBD(view);
        return view;
    }

    private void button(){
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FECHA = fecha.getText().toString();
                Log.d("T: ",FECHA);
                Contrato c = new Contrato();

                try{
                    for(int i=0; i<uwu.getSize(); i++){
                        c = uwu.get(i);
                        if(c.getFecha_Evento().compareTo(FECHA) == 0){
                            //Ni modo, por falta de tiempo tocó así
                            avlContratos.find(c,avlContratos.getRoot());

                            if(c.isAceptado().compareTo("VERDADERO") == 0){
                                aceptado.setText("¡Aceptado! Revisa tus facturas");
                            }
                            else{
                                aceptado.setText("EN ESTUDIO");
                            }

                            costoFinal.setText(c.getCosto_Final());
                            costoInicial.setText(c.getCosto_Oferta());
                            direccion.setText(c.getDireccion_evento());
                            ciudad.setText(c.getCiudad_evento());
                            hora.setText(c.getHora_Evento());
                            fechaEvento.setText(c.getFecha_Evento());

                            break;
                        }
                    }
                }
                catch (Exception e){
                    Log.d("Error ", e.toString());
                }



            }
        });
    }

    private AVLTree busquedaBD(View view) {
        AVLTree<Contrato> avl = new AVLTree<>();
        //Señalamos a la raiz de contratos
        database = FirebaseDatabase.getInstance().getReference();
        database.child("Contratos").orderByKey().limitToFirst(100).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(avl.getRoot()!= null){
                    avl.clean();
                    uwu.clear();
                }

                if(snapshot.exists()){
                    for(DataSnapshot snap : snapshot.getChildren()){
                        Contrato ar = snap.getValue(Contrato.class);
                        avl.add(ar);
                        uwu.pushBack(ar);
                        try{
                            Log.d("Contrato " , ar.getFecha_Evento());
                        }
                        catch(Exception e){
                            Log.d("Error :c  ", e.getMessage());
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return avl;

    }


}