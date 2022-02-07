package com.example.showseek.objects;

public class Contrato implements Comparable<Contrato>{

    //Atributes
    private int ID_Contrato;
    private int ID_Artista;
    private int ID_Cliente;
    private String Aceptado;
    private String Fecha_Evento;
    private String Hora_Evento;
    private String Costo_Final;
    private String Costo_Oferta;
    private String Ciudad_evento;
    private int ID_Repertorio;
    private String Direccion_evento;

    //Constructor
    public Contrato(int ID_Contrato,int ID_Artista, int ID_Cliente, String Aceptado,
                    String Fecha_Evento, String Hora_Evento, String Costo_Final,
                    String Costo_Oferta, String Ciudad_evento, int ID_Repertorio,
                    String Direccion_evento) {
        this.ID_Contrato = ID_Contrato;
        this.ID_Artista = ID_Artista;
        this.ID_Cliente = ID_Cliente;
        this.Aceptado = Aceptado;
        this.Fecha_Evento = Fecha_Evento;
        this.Hora_Evento = Hora_Evento;
        this.Costo_Final = Costo_Final;
        this.Costo_Oferta = Costo_Oferta;
        this.Ciudad_evento = Ciudad_evento;
        this.ID_Repertorio = ID_Repertorio;
        this.Direccion_evento = Direccion_evento;
    }

    public Contrato(){

    }

    //Getters ans Setters


    public String isAceptado() {
        return Aceptado;
    }

    public void setAceptado(String aceptado) {
        Aceptado = aceptado;
    }

    public String getFecha_Evento() {
        return Fecha_Evento;
    }

    public void setFecha_Evento(String fecha_Evento) {
        Fecha_Evento = fecha_Evento;
    }

    public String getHora_Evento() {
        return Hora_Evento;
    }

    public void setHora_Evento(String hora_Evento) {
        Hora_Evento = hora_Evento;
    }

    public String getCosto_Final() {
        return Costo_Final;
    }

    public void setCosto_Final(String costo_Final) {
        Costo_Final = costo_Final;
    }

    public String getCosto_Oferta() {
        return Costo_Oferta;
    }

    public void setCosto_Oferta(String costo_Oferta) {
        Costo_Oferta = costo_Oferta;
    }

    public int getID_Contrato() {
        return ID_Contrato;
    }

    public void setID_Contrato(int ID_Contrato) {
        this.ID_Contrato = ID_Contrato;
    }

    public int getID_Artista() {
        return ID_Artista;
    }

    public void setID_Artista(int ID_Artista) {
        this.ID_Artista = ID_Artista;
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public String getCiudad_evento() {
        return Ciudad_evento;
    }

    public void setCiudad_evento(String ciudad_evento) {
        this.Ciudad_evento = ciudad_evento;
    }

    public int getID_repertorio() {
        return ID_Repertorio;
    }

    public void setID_repertorio(int ID_repertorio) {
        this.ID_Repertorio = ID_repertorio;
    }

    public String getDireccion_evento() {
        return Direccion_evento;
    }

    public void setDireccion_evento(String direccion_evento) {
        Direccion_evento = direccion_evento;
    }

    //Methods
    public Factura getFactura(){
        Factura factura = new Factura(ID_Cliente,ID_Artista,Costo_Final);
        return factura;
    }

    //Sort by the Fecha
    @Override
    public int compareTo(Contrato o) {
        int comparation = 0;
        if(ID_Cliente>o.getID_Cliente()){
            comparation = 1;
        }
        else if(ID_Cliente<o.getID_Cliente()){
            comparation = -1;
        }
        else{
            comparation = 0;
        }
        return comparation;
    }
}
