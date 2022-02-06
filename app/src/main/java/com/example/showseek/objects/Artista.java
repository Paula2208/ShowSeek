package com.example.showseek.objects;

public class Artista extends Usuario implements Comparable<Artista>{

    //Atributes
    private String tipoAgrupacion;
    private String generoMusical;
    private String nombreArtistico;
    private String fotoPerfil;
    private float rating;

    private String tipo ;

    //Constructor
    public Artista(int ID, String nombre, String apellido, String correo,
                   String telefono, String ciudad, String fecha_nacimiento,
                   String tipoAgrupacion, String generoMusical, String nombreArtistico, String fotoPerfil) {
        super(ID, nombre, correo, fecha_nacimiento,"artista");
        this.tipoAgrupacion = tipoAgrupacion;
        this.generoMusical = generoMusical;
        this.nombreArtistico = nombreArtistico;
        this.fotoPerfil = fotoPerfil;
        this.rating = 0 ;
    }

    public Artista(String tipoAgrupacion, String generoMusical, String nombreArtistico, String fotoPerfil, float rating){
        super();
        this.tipoAgrupacion = tipoAgrupacion;
        this.generoMusical = generoMusical;
        this.nombreArtistico = nombreArtistico;
        this.fotoPerfil = fotoPerfil;
        this.rating = rating;
    }

    public Artista(){

    }

    public Artista(String fullname, String age, String correo){
        super(0, fullname,correo,age,"artista" );
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

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    //Methods
    @Override
    public int compareTo(Artista o) {
        int comparation = 0;
        if(rating>o.getRating()){
            comparation = 1;
        }
        else if(rating<o.getRating()){
            comparation = -1;
        }
        else{
            comparation = 0;
        }
        return comparation;
    }
}
