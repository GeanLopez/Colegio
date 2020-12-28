/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author jampi
 */
public class Docente extends Persona {

    private int Id_Docente;
    private int Id_Especialidad;

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
