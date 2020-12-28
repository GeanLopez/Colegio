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
public class Docente extends Persona {

    private int Id_Docente;
    private int Id_Especialidad;

    public Docente(int Id_Especialidad, String Nombre, String Apellido, String Correo, String Contraseña) {
        super(Nombre, Apellido, Correo, Contraseña);
        this.Id_Especialidad = Id_Especialidad;
    }

    public Docente(int Id_Docente, int Id_Especialidad, String Nombre, String Apellido, String Correo, int Telefono, String Sexo, int DNI, int Id_Tipo, String Contraseña, Date Fecha_Nacimiento, byte[] Avatar) {
        super(Nombre, Apellido, Correo, Telefono, Sexo, DNI, Id_Tipo, Contraseña, Fecha_Nacimiento, Avatar);
        this.Id_Docente = Id_Docente;
        this.Id_Especialidad = Id_Especialidad;
    }

    
    public int getId_Docente() {
        return Id_Docente;
    }

    public void setId_Docente(int Id_Docente) {
        this.Id_Docente = Id_Docente;
    }

    public int getId_Especialidad() {
        return Id_Especialidad;
    }

    public void setId_Especialidad(int Id_Especialidad) {
        this.Id_Especialidad = Id_Especialidad;
    }

}
