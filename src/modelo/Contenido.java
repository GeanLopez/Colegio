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
public class Contenido {
private int Id_Contenido;
private byte[] Contenido;
private int Id_Curso;

    public int getId_Contenido() {
        return Id_Contenido;
    }

    public void setId_Contenido(int Id_Contenido) {
        this.Id_Contenido = Id_Contenido;
    }

    public byte[] getContenido() {
        return Contenido;
    }

    public void setContenido(byte[] Contenido) {
        this.Contenido = Contenido;
    }

    public int getId_Curso() {
        return Id_Curso;
    }

    public void setId_Curso(int Id_Curso) {
        this.Id_Curso = Id_Curso;
    }

}
