package com.example.showseek.objects;

public class Artista extends Usuario implements Comparable<Artista>{

    //Atributes
    private String tipoAgrupacion;
    private String generoMusical;
    private int totalRecomendacion=0;

    //Constructor
    public Artista(int ID, String nombre, String apellido, String correo,
                   String telefono, String ciudad, String fecha_nacimiento,
                   String tipoAgrupacion, String generoMusical) {
        super(ID, nombre, apellido, correo, telefono, ciudad, fecha_nacimiento);
        this.tipoAgrupacion = tipoAgrupacion;
        this.generoMusical = generoMusical;
    }

    //Getters and Setters
    public String getTipoAgrupacion() {
        return tipoAgrupacion;
    }

    public void setTipoAgrupacion(String tipoAgrupacion) {
        this.tipoAgrupacion = tipoAgrupacion;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    //Methods
    @Override
    public int compareTo(Artista o) {
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
