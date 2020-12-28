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
import modelo.Alumno;
import util.MySQLConexion;

/**
 *
 * @author jampi
 */
public class ControladorLogin {

    public boolean LoginAlumno(String Correo, String Contraseña) {
        Connection conn = null;
        String contraseña = "";
        boolean estado = false;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select contraseña from Alumno where correo=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, Correo);
            ResultSet rs = st.executeQuery();//Devuelve metodo resulset obtener la seleccion en memoria
            if (rs.next()) {
                contraseña = rs.getString(1);// Guardo la contraseña en la base de datos a memoria en la variable contraseña
            }
            if (Contraseña.equals(contraseña)) {
                estado = true;
            }
            //esa consulta se lleva a memoria
            st.executeQuery();// Obtener Datos
            //  st.executeUpdate();//Actualizar Datos
            //comenzar a leer filax fila
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
        return estado;
    }

    public boolean LoginDocente(String Correo, String Contraseña) {
        Connection conn = null;
        String contraseña = "";
        boolean estado = false;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select contraseña from Docente where correo=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, Correo);
            ResultSet rs = st.executeQuery();//Devuelve metodo resulset obtener la seleccion en memoria
            if (rs.next()) {
                contraseña = rs.getString(1);// Guardo la contraseña en la base de datos a memoria en la variable contraseña
            }
            if (Contraseña.equals(contraseña)) {
                estado = true;
            }
            //esa consulta se lleva a memoria
            st.executeQuery();// Obtener Datos
            //  st.executeUpdate();//Actualizar Datos
            //comenzar a leer filax fila
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
        return estado;
    }

}
