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
public class Nota {
  private int Id_Notas;
  private int Id_Alumno;
  private int Id_Curso;
  private double PC1;
  private double PC2;
  private double EP;
  private double PC3;
  private double EF;
  
  public double Promedio(){
      return (PC1+PC2+EP+PC3+EF)/5;
  }

    public int getId_Notas() {
        return Id_Notas;
    }

    public void setId_Notas(int Id_Notas) {
        this.Id_Notas = Id_Notas;
    }

    public int getId_Alumno() {
        return Id_Alumno;
    }

    public void setId_Alumno(int Id_Alumno) {
        this.Id_Alumno = Id_Alumno;
    }

    public int getId_Curso() {
        return Id_Curso;
    }

    public void setId_Curso(int Id_Curso) {
        this.Id_Curso = Id_Curso;
    }

    public double getPC1() {
        return PC1;
    }

    public void setPC1(double PC1) {
        this.PC1 = PC1;
    }

    public double getPC2() {
        return PC2;
    }

    public void setPC2(double PC2) {
        this.PC2 = PC2;
    }

    public double getEP() {
        return EP;
    }

    public void setEP(double EP) {
        this.EP = EP;
    }

    public double getPC3() {
        return PC3;
    }

    public void setPC3(double PC3) {
        this.PC3 = PC3;
    }

    public double getEF() {
        return EF;
    }

    public void setEF(double EF) {
        this.EF = EF;
    }
  
  
}
