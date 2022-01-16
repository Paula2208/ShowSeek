package com.example.showseek.objects;

import com.example.showseek.estructures.references.single.LinkedList;

public class Cliente extends Usuario implements Comparable<Cliente>{

    //--------------------------Pruebas-------------
    //Atributes
    private LinkedList<Integer> recomendados;
    //----------------------------------------------

    //Atributes
    private String[] pref_Musical;

    //Constructor
    public Cliente(int ID_cliente, String nombre_Cliente, String apellido_Cliente,
                   String correo_Cliente, String telefono_Cliente, String ciudad_Cliente,
                   String fecha_Nacimiento_Cliente, String pref1, String pref2) {
        super(ID_cliente, nombre_Cliente,correo_Cliente,fecha_Nacimiento_Cliente);
        String[] pref_Musical = new String[2];
        pref_Musical[0] = pref1;
        pref_Musical[1] = pref2;
        this.pref_Musical = pref_Musical;
    }

    //Getters and Setters
    public String[] getPref_Musical() {
        return pref_Musical;
    }

    public void setPref_Musical(String[] pref_Musical) {
        this.pref_Musical = pref_Musical;
    }

    @Override
    public int compareTo(Cliente o) {
        int comparation = 0;
        if(super.getID()>o.getID()){
            comparation = 1;
        }
        else if(super.getID()<o.getID()){
            comparation = -1;
        }
        else{
            comparation = 0;
        }
        return comparation;
    }
}
