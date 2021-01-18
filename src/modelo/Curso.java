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
public class Curso {
    private int Id_Curso;
    private String Nombre_Curso;
    private String Descripcion;
    private int Id_Docente;

    public int getId_Curso() {
        return Id_Curso;
    }

    public void setId_Curso(int Id_Curso) {
        this.Id_Curso = Id_Curso;
    }

    public String getNombre_Curso() {
        return Nombre_Curso;
    }

    public void setNombre_Curso(String Nombre_Curso) {
        this.Nombre_Curso = Nombre_Curso;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getId_Docente() {
        return Id_Docente;
    }

    public void setId_Docente(int Id_Docente) {
        this.Id_Docente = Id_Docente;
    }
    
}
