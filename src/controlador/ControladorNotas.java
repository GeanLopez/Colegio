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
import util.MySQLConexion;
import modelo.Nota;

/**
 *
 * @author Jean Pierre
 */
public class ControladorNotas {

    public List<Nota> listarNotas(int codAlu, int codCur) {
        List<Nota> lis = new ArrayList<>();
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "select Id_Notas,pc1,pc2,ep,pc3,ef from notas where id_curso=? and id_alumno=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, codCur);
            st.setInt(2, codAlu);
            ResultSet rs = st.executeQuery();
            //llenar el arraylist con la clase entidad
            while (rs.next()) {
                Nota n = new Nota();
                n.setId_Notas(rs.getInt(1));
                n.setPC1(rs.getDouble(2));
                n.setPC2(rs.getDouble(3));
                n.setEP(rs.getDouble(4));
                n.setPC3(rs.getDouble(5));
                n.setEF(rs.getDouble(6));

                lis.add(n);
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

    public void CrearNota(Nota n) {
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "insert into Notas (pc1,pc2,ep,pc3,ef,id_alumno,id_curso)\n"
                    + "values (?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setDouble(1, n.getPC1());
            st.setDouble(2, n.getPC2());
            st.setDouble(3, n.getEP());
            st.setDouble(4, n.getPC3());
            st.setDouble(6, n.getEF());
            st.setInt(7, n.getId_Alumno());
            st.setInt(8, n.getId_Curso());

            //esa consulta se lleva a memoria
            st.executeUpdate();
            //comenzar a leer filax fila
        } catch (Exception ex) {
            SimpleAlert.showMessaje(null, true, "Error al Registrar Notas");
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }

    }
    public void EditarNota(Nota a, int id) {
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "update Notas set pc1=?,pc2=?,ep=?,pc3=?,ef=? where id_notas=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setDouble(1, a.getPC1());
            st.setDouble(2, a.getPC2());
            st.setDouble(3, a.getEP());
            st.setDouble(4, a.getPC3());
            st.setDouble(5, a.getEF());
            st.setInt(6, id);
            //esa consulta se lleva a memoria
            st.executeUpdate();
            //comenzar a leer filax fila
        } catch (Exception ex) {
            SimpleAlert.showMessaje(null, true, "Error al Editar Nota");
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
