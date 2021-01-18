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
import modelo.Mensajeria;
import util.MySQLConexion;

/**
 *
 * @author Jean Pierre
 */
public class ControladorMensaje {

    public List<Mensajeria> listarMensajesDocente(int idD) {//solo se coloca cuando se necesita buscar un dato, añadir datos, eliminar
        List<Mensajeria> lis = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select Id_Mensaje, Mensaje from Mensajeria where id_docente=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idD);
            ResultSet rs = st.executeQuery();
            //llenar el arraylist con la clase entidad
            while (rs.next()) {
                Mensajeria m = new Mensajeria();
                m.setId_Mensajeria(rs.getInt(1));
                m.setMensaje(rs.getString(2));
                lis.add(m);
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

    public List<Mensajeria> listarMensajesAlumno(int idA) {
        List<Mensajeria> lis = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select Id_Mensaje, Mensaje from Mensajeria where id_Alumno=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idA);
            ResultSet rs = st.executeQuery();
            //llenar el arraylist con la clase entidad
            while (rs.next()) {
                Mensajeria m = new Mensajeria();
                m.setId_Mensajeria(rs.getInt(1));
                m.setMensaje(rs.getString(2));
                lis.add(m);
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

    public void CrearMensaje(Mensajeria m) {
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "insert into mensajeria (mensaje, id_alumno,id_docente)\n"
                    + "values (?,?,?);";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, m.getMensaje());
            st.setInt(2, m.getId_Alumno());
            st.setInt(3, m.getId_Docente());

            //esa consulta se lleva a memoria
            st.executeUpdate();
            //comenzar a leer filax fila
        } catch (Exception ex) {
            SimpleAlert.showMessaje(null, true, "Error al Enviar Mensaje");
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }

    }
}
