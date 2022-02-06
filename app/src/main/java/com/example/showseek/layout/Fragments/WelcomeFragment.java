package com.example.showseek.layout.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.showseek.R;


public class WelcomeFragment extends Fragment {
    private static View view;


    public WelcomeFragment() {
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

        view = inflater.inflate(R.layout.fragment_welcome, container, false);
        TextView hi = view.findViewById(R.id.txt_welcome);

        String nombre = getArguments().getString("nombre");
        String tipo = getArguments().getString("tipo");

        hi.setText("¡Hola "+nombre+"! \n Bienvenid@ a ShowSeek");
        Log.d("Tipo    ",tipo);

        return view;
    }
}