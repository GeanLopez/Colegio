/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
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
                d = new Docente(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getDate(11), rs.getBytes(12));
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

    public void EditarDocente(Docente d, int id) {
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "update Docente set nombre=?, apellido=?,correo=?,telefono=?,dni=?,sexo=?,Fecha_Nacimiento=? where id_Docente=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, d.getNombre());
            st.setString(2, d.getApellido());
            st.setString(3, d.getCorreo());
            st.setInt(4, d.getTelefono());
            st.setString(5, d.getSexo());
            st.setInt(6, d.getDNI());
            st.setDate(7, (Date) d.getFecha_Nacimiento());
            st.setInt(8, id);
            //esa consulta se lleva a memoria
            st.executeUpdate();
            //comenzar a leer filax fila
        } catch (Exception ex) {
            SimpleAlert.showMessaje(null, true, "DNI o Correo Existentes");
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
    }

    public void CambiarContraseña(String Contraseña, int idDocente) {
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "update Docente set contraseña=? where id_Docente=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, Contraseña);
            st.setInt(2, idDocente);
            //esa consulta se lleva a memoria
            st.executeUpdate();
            //comenzar a leer filax fila
        } catch (Exception ex) {
            SimpleAlert.showMessaje(null, true, "Ingrese Bien datos Mierda");
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
    }
    /*Subir Imagen*/
    public boolean subirFoto(FileInputStream foto, int id) {
        boolean state = true;
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "update Docente set avatar = ? where id_Docente =? ";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setBinaryStream(1, foto);
            st.setInt(2, id);
            st.executeUpdate();
            //llenar el arraylist con la clase entidad
        } catch (Exception ex) {
            SimpleAlert.showMessaje(null, true, "Eliga una foto mierda");
            state = false;
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
        return state;
    }
    
}

