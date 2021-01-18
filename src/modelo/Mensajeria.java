/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Jean Pierre
 */
public class Mensajeria {
private int Id_Mensajeria;
private int Id_Alumno;
private int Id_Docente;
private String Mensaje;

    public int getId_Mensajeria() {
        return Id_Mensajeria;
    }

    public void setId_Mensajeria(int Id_Mensajeria) {
        this.Id_Mensajeria = Id_Mensajeria;
    }

    public int getId_Alumno() {
        return Id_Alumno;
    }

    public void setId_Alumno(int Id_Alumno) {
        this.Id_Alumno = Id_Alumno;
    }

    public int getId_Docente() {
        return Id_Docente;
    }

    public void setId_Docente(int Id_Docente) {
        this.Id_Docente = Id_Docente;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

}
