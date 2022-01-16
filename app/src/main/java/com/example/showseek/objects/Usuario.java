package com.example.showseek.objects;

public class Usuario {
    //Atributes
    private int ID;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String ciudad;
    private String fecha_nacimiento;

    // Implementar un nickname

    //Constructor
    public Usuario(int ID, String nombre, String apellido,
                   String correo, String telefono, String ciudad,
                   String fecha_nacimiento) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Usuario(){
        this.ID = 0;
        this.nombre = "";
        this.apellido = "";
        this.correo = "";
        this.telefono = "";
        this.ciudad = "";
        this.fecha_nacimiento = "";
    }

    public Usuario(String fullname, String age, String correo){
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
}
