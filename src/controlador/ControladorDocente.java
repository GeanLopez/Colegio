/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import josueemg.SimpleAlert;
import modelo.Docente;
import util.MySQLConexion;

/**
 *
 * @author jampi
 */
public class ControladorDocente {

    public void CrearDocente(Docente d) {
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "insert into Docente (id_especialidad,nombre, apellido,correo,id_tipo,contraseña) \n"
                    + "values (?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, d.getId_Especialidad());
            st.setString(2, d.getNombre());
            st.setString(3, d.getApellido());
            st.setString(4, d.getCorreo());
            st.setInt(5, 2);
            st.setString(6, d.getContraseña());

            //esa consulta se lleva a memoria
            st.executeUpdate();
            //comenzar a leer filax fila
        } catch (Exception ex) {
            SimpleAlert.showMessaje(null, true, "Error al Registar Docente");
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }

    }
    public Docente obtenerDocente(String correo) {
       Docente d = null;
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select * from docente where correo = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, correo);
            //esa consulta se lleva a memoria
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                d = new Docente(rs.getInt(1), rs.getInt(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getDate(11), rs.getBytes(12));
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
        return d;
    }
}
