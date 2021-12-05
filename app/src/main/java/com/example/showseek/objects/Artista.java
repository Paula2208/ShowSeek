package com.example.showseek.objects;

public class Artista extends Usuario{

    //Atributes
    private String tipoAgrupacion;
    private String generoMusical;

    //Constructor
    public Artista(int ID, String nombre, String apellido, String correo,
                   int telefono, String ciudad, String fecha_nacimiento,
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
}
