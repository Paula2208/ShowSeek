package com.example.showseek.objects;

public class Contrato implements Comparable<Contrato>{

    //Atributes
    private int ID_artista;
    private int ID_cliente;
    private boolean aceptado;
    private String fecha_evento;
    private String hora_evento;
    private int costo_final;
    private int costo_oferta;
    private String ciudad_evento;
    private int ID_repertorio;
    private String direccion_evento;

    //Constructor
    public Contrato(int ID_artista, int ID_cliente, boolean aceptado,
                    String fecha_evento, String hora_evento, int costo_final,
                    int costo_oferta, String ciudad_evento, int ID_repertorio,
                    String direccion_evento) {
        this.ID_artista = ID_artista;
        this.ID_cliente = ID_cliente;
        this.aceptado = aceptado;
        this.fecha_evento = fecha_evento;
        this.hora_evento = hora_evento;
        this.costo_final = costo_final;
        this.costo_oferta = costo_oferta;
        this.ciudad_evento = ciudad_evento;
        this.ID_repertorio = ID_repertorio;
        this.direccion_evento = direccion_evento;
    }

    //Getters ans Setters
    public int getID_artista() {
        return ID_artista;
    }

    public void setID_artista(int ID_artista) {
        this.ID_artista = ID_artista;
    }

    public int getID_cliente() {
        return ID_cliente;
    }

    public void setID_cliente(int ID_cliente) {
        this.ID_cliente = ID_cliente;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }

    public String getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(String fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    public String getHora_evento() {
        return hora_evento;
    }

    public void setHora_evento(String hora_evento) {
        this.hora_evento = hora_evento;
    }

    public int getCosto_final() {
        return costo_final;
    }

    public void setCosto_final(int costo_final) {
        this.costo_final = costo_final;
    }

    public int getCosto_oferta() {
        return costo_oferta;
    }

    public void setCosto_oferta(int costo_oferta) {
        this.costo_oferta = costo_oferta;
    }

    public String getCiudad_evento() {
        return ciudad_evento;
    }

    public void setCiudad_evento(String ciudad_evento) {
        this.ciudad_evento = ciudad_evento;
    }

    public int getID_repertorio() {
        return ID_repertorio;
    }

    public void setID_repertorio(int ID_repertorio) {
        this.ID_repertorio = ID_repertorio;
    }

    public String getDireccion_evento() {
        return direccion_evento;
    }

    public void setDireccion_evento(String direccion_evento) {
        this.direccion_evento = direccion_evento;
    }

    //Methods
    public Factura getFactura(){
        Factura factura = new Factura(ID_cliente,ID_artista,costo_final);
        return factura;
    }

    //Sort by the Fecha
    @Override
    public int compareTo(Contrato o) {
        int comparation = 0;
        if(ID_cliente>o.getID_cliente()){
            comparation = 1;
        }
        else if(ID_cliente<o.getID_cliente()){
            comparation = -1;
        }
        else{
            comparation = 0;
        }
        return comparation;
    }
}
