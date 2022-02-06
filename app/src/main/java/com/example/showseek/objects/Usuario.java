package com.example.showseek.objects;

public class Usuario {
    //Atributes
    private int ID;
    private String nombre;
    private String correo;
    private String fecha_nacimiento;
    private String tipo ;

    //Constructor
    public Usuario(int ID, String nombre, String correo, String fecha_nacimiento, String tipo) {
        this.ID = ID;
        this.nombre = nombre;
        this.correo = correo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.tipo = tipo;
    }

    public Usuario(){
        this.ID = 0;
        this.nombre = "";
        this.correo = "";
        this.fecha_nacimiento = "";
    }

    public Usuario(String fullname, String age, String correo){
        this.ID = 0;
        this.nombre=fullname;
        this.fecha_nacimiento = age;
        this.correo = correo;

    }

    public Usuario(int ID,String fullname, String age, String correo){
        this.ID = ID;
        this.nombre=fullname;
        this.fecha_nacimiento = age;
        this.correo = correo;
    }

    //Getters and Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
