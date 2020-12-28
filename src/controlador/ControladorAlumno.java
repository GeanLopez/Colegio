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
import util.MySQLConexion;
import modelo.Alumno;
/**
 *
 * @author jampi
 */
public class ControladorAlumno {

    public List<Alumno> getEsp() {
        List<Especial> lis = new ArrayList();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select codesp, nomesp from tabesp";
            PreparedStatement st = conn.prepareStatement(sql);
            //  st.setString(1, cod);
            //esa consulta se lleva a memoria
            ResultSet rs = st.executeQuery();
            //comenzar a leer filax fila
            while (rs.next()) {
                Especial a = new Especial();
                a.setCode(rs.getString(1));
                a.setNome(rs.getString(2));
                lis.add(a);
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

}
