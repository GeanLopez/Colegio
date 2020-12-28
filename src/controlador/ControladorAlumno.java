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
import static josueemg.SimpleAlert.parent;
import util.MySQLConexion;
import modelo.Alumno;

/**
 *
 * @author jampi
 */
public class ControladorAlumno {

    public void CrearAlumno(Alumno a) {
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "insert into Alumno (nombre, apellido,correo,id_tipo,contraseña) \n"
                    + "values (?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, a.getNombre());
            st.setString(2, a.getApellido());
            st.setString(3, a.getCorreo());
            st.setInt(4, 1);
            st.setString(5, a.getContraseña());

            //esa consulta se lleva a memoria
            st.executeUpdate();
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

    }
    
    public Alumno obtenerAlumno(String correo) {
        Alumno a = null;
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select * from alumno where correo = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, correo);
            //esa consulta se lleva a memoria
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getDate(10), rs.getBytes(11));
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
        return a;
    }
}
