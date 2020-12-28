/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author jampi
 */
public abstract class Persona {

    private String Nombre;
    private String Apellido;
    private String Correo;
    private int Telefono;
    private String Sexo;
    private int DNI;
    private int Id_Tipo;
    private String Contraseña;
    private Date Fecha_Nacimiento;
    private byte[] Avatar;

    public Persona(String Nombre, String Apellido, String Correo, String Contraseña) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Correo = Correo;

        this.Contraseña = Contraseña;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public int getId_Tipo() {
        return Id_Tipo;
    }

    public void setId_Tipo(int Id_Tipo) {
        this.Id_Tipo = Id_Tipo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public Date getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(Date Fecha_Nacimiento) {
        this.Fecha_Nacimiento = Fecha_Nacimiento;
    }

    public byte[] getAvatar() {
        return Avatar;
    }

    public void setAvatar(byte[] Avatar) {
        this.Avatar = Avatar;
    }

}
