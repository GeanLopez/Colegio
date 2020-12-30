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
public class Alumno extends Persona {

    private int Id_Alumno;

    public Alumno(String Nombre, String Apellido, String Correo, String Contraseña) {
        super(Nombre, Apellido, Correo, Contraseña);
    }

    public Alumno(int Id_Alumno, String Nombre, String Apellido, String Correo, int Telefono, String Sexo, int DNI, int Id_Tipo, String Contraseña, Date Fecha_Nacimiento, byte[] Avatar) {
        super(Nombre, Apellido, Correo, Telefono, Sexo, DNI, Id_Tipo, Contraseña, Fecha_Nacimiento, Avatar);
        this.Id_Alumno = Id_Alumno;
    }

    public Alumno() {
    }
    
    
    

    public int getId_Alumno() {
        return Id_Alumno;
    }

    public void setId_Alumno(int Id_Alumno) {
        this.Id_Alumno = Id_Alumno;
    }

}
