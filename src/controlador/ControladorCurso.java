/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import josueemg.SimpleAlert;
import modelo.Curso;
import util.MySQLConexion;

/**
 *
 * @author Jean Pierre
 */
public class ControladorCurso {

    public List<Curso> listarCurso(int codAlu) {
        List<Curso> lis = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select c.id_curso, nombre_curso, descripcion,id_docente from Cursos c, incripciones i where c.id_curso=i.id_curso and id_alumno=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, codAlu);
            ResultSet rs = st.executeQuery();
            //llenar el arraylist con la clase entidad
            while (rs.next()) {
                Curso c = new Curso();
                c.setId_Curso(rs.getInt(1));
                c.setNombre_Curso(rs.getString(2));
                c.setDescripcion(rs.getString(3));
                c.setId_Docente(rs.getInt(4));
                lis.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
        return lis;
    }

    public List<Curso> listarCursoDoc(int codDoc) {
        List<Curso> lis = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select c.id_curso, nombre_curso, descripcion,id_docente from Cursos c, incripciones i where c.id_curso=i.id_curso and id_docente=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, codDoc);
            ResultSet rs = st.executeQuery();
            //llenar el arraylist con la clase entidad
            while (rs.next()) {
                Curso c = new Curso();
                c.setId_Curso(rs.getInt(1));
                c.setNombre_Curso(rs.getString(2));
                c.setDescripcion(rs.getString(3));
                c.setId_Docente(rs.getInt(4));
                lis.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
        return lis;
    }

    public int obtenerIdCurso(int idD) {
        int N = 0;
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select id_curso from Cursos  where id_docente=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idD);
            //esa consulta se lleva a memoria
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                N = rs.getInt(1);
            }
            //comenzar a leer filax fila
        } catch (Exception ex) {
            SimpleAlert.showMessaje(null, true, "Error al Registar Alumno");
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
        return N;
    }
}
