package com.example.showseek.objects;

public class Factura implements Comparable<Factura>{
    //Atributes
    private int ID_Cliente;
    private int ID_artista;
    private String costo_final;

    //Constructor
    public Factura(int ID_Cliente, int ID_artista, String costo_final) {
        this.ID_Cliente = ID_Cliente;
        this.ID_artista = ID_artista;
        this.costo_final = costo_final;
    }

    //Getters and Setters
    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public int getID_artista() {
        return ID_artista;
    }

    public void setID_artista(int ID_artista) {
        this.ID_artista = ID_artista;
    }

    public String getCosto_final() {
        return costo_final;
    }

    public void setCosto_final(String costo_final) {
        this.costo_final = costo_final;
    }

    //Order by the cost
    @Override
    public int compareTo(Factura o) {
        int comparation = 0;
        /*if(costo_final>o.getCosto_final()){
            comparation = 1;
        }
        else if(costo_final<o.getCosto_final()){
            comparation = -1;
        }
        else{
            comparation = 0;
        }*/
        return comparation;
    }
}
