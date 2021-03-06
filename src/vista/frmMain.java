/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import modelo.*;
import controlador.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import josueemg.SimpleAlert;

/**
 *
 * @author jampi
 */
public class frmMain extends javax.swing.JFrame {

    static Alumno alumno;
    static Docente docente;
    Date dt = new Date();
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    ControladorAlumno ca = new ControladorAlumno();
    ControladorDocente cd = new ControladorDocente();
    ControladorCurso cc = new ControladorCurso();
    ControladorMensaje cm = new ControladorMensaje();

    public frmMain() {
        initComponents();
        // ListarCurso(alumno.getId_Alumno());
        txtapellido.setEditable(false);
        txtcorreo.setEditable(false);
        txtnombre.setEditable(false); // Falta completar
        if (alumno == null && docente == null) {
            frmLogin fl = new frmLogin();
            this.dispose();
            fl.setVisible(true);

        } else if (alumno != null) {
            ListarCursoAlu(alumno.getId_Alumno());
            ListarMensajesAlu(alumno.getId_Alumno());
            alulis.setVisible(false);

            System.out.println("hola" + alumno.getId_Alumno());
        } else {
            ListarCursoDoc(docente.getId_Docente());
            ListarMensajesDoc(docente.getId_Docente());
            ListarAlu(cc.obtenerIdCurso(docente.getId_Docente()));
            vermensaje.setVisible(false);
            System.out.println("asdas"+cc.obtenerIdCurso(docente.getId_Docente()));
            miscursos.setVisible(false);
            System.out.println("hola" + docente.getId_Docente());
        }
        //System.out.println("Hoa" + alumno);
        this.setLocationRelativeTo(null);
        VentanaVerPerfil.setVisible(false);
        VentanaEditarPerfil.setVisible(false);
        VerCursos.setVisible(false);
        VerMensajes.setVisible(false);
        LisAlu.setVisible(false);
    }

    void ListarCursoAlu(int codAlu) {
        DefaultTableModel dt = (DefaultTableModel) tbcurso.getModel();
        dt.setRowCount(0);
        for (Curso c : cc.listarCurso(codAlu)) {
            Object v[] = {c.getId_Curso(), c.getNombre_Curso(), c.getDescripcion(), c.getId_Docente()};
            dt.addRow(v);
        }
    }

    void ListarCursoDoc(int codDoc) {
        DefaultTableModel dt = (DefaultTableModel) tbcurso.getModel();
        dt.setRowCount(0);
        for (Curso c : cc.listarCursoDoc(codDoc)) {
            Object v[] = {c.getId_Curso(), c.getNombre_Curso(), c.getDescripcion(), c.getId_Docente()};
            dt.addRow(v);
        }
    }

    void ListarMensajesDoc(int codDoc) {
        DefaultTableModel dt = (DefaultTableModel) tbMensajes.getModel();
        dt.setRowCount(0);
        for (Mensajeria m : cm.listarMensajesDocente(codDoc)) {
            Object v[] = {m.getId_Mensajeria(), m.getMensaje()};
            dt.addRow(v);
        }
    }

    void ListarMensajesAlu(int codAlu) {
        DefaultTableModel dt = (DefaultTableModel) tbMensajes.getModel();
        dt.setRowCount(0);
        for (Mensajeria m : cm.listarMensajesAlumno(codAlu)) {
            Object v[] = {m.getId_Mensajeria(), m.getMensaje()};
            dt.addRow(v);
        }
    }

    void ListarAlu(int codc) {
        DefaultTableModel dt = (DefaultTableModel) tabLisAlu.getModel();
        dt.setRowCount(0);
        for (Alumno a : cd.listarAlumno(codc)) {
            Object v[] = {a.getId_Alumno(), a.getNombre(), a.getApellido()};
            dt.addRow(v);
        }
    }

    public String VecTipo(int id_tipo) {
        String id[] = {"Elegir", "Estudiante", "Docente", "Administrador"};
        return id[id_tipo];
    }
    //Restamos dos Fechas para obtener un entero

    public void closeIframes() {
        this.VentanaEditarPerfil.setVisible(false);
        this.VentanaEditarPerfil.setVisible(false);
    }

    public int FechaDif(String cad1, String cad2) {
        //primero las cadenas convertirlos a fechas
        long dias;
        try {
            Date f1 = sd.parse(cad1);
            Date f2 = sd.parse(cad2
            );
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            //las fechas convertirlos a calendario
            c1.setTime(f1);
            c2.setTime(f2);
            dias = (c2.getTimeInMillis() - c1.getTimeInMillis()) / (1000 * 60 * 60 * 24);
        } catch (Exception ex) {
            dias = 0;
        }
        return (int) dias;
    }

    public Image generarImagenCita(byte[] bytes) throws IOException {
        BufferedImage imagen = null;
        try {
            InputStream in = new ByteArrayInputStream(bytes);
            imagen = ImageIO.read(in);
        } catch (Exception e) {
        }
        return imagen;
    }
    //Editar Perfil

    void EditarPerfil() {
        if (alumno != null) {
            try {
                img1.setIcon(new ImageIcon(generarImagenCita(alumno.getAvatar()).getScaledInstance(img1.getWidth(), img1.getHeight(), Image.SCALE_DEFAULT)));
            } catch (IOException ex) {
                Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
            }

            txtnombre1.setText(alumno.getNombre());
            txtapellido1.setText(alumno.getApellido());
            txtcorreo1.setText(alumno.getCorreo());
            txttelefono1.setText("" + alumno.getTelefono());
            txtsexo1.setText(alumno.getSexo());
            txtdni1.setText("" + alumno.getDNI());
            txttipo1.setText(VecTipo(alumno.getId_Tipo()));
            DateFechaNacimiento1.setDate(alumno.getFecha_Nacimiento());
            txtedad1.setText("" + (FechaDif(sd.format(alumno.getFecha_Nacimiento()), sd.format(dt))) / 365);
        } else {
            try {
                img1.setIcon(new ImageIcon(generarImagenCita(docente.getAvatar()).getScaledInstance(img1.getWidth(), img1.getHeight(), Image.SCALE_DEFAULT)));
            } catch (IOException ex) {
                Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtnombre1.setText(docente.getNombre());
            txtapellido1.setText(docente.getApellido());
            txtcorreo1.setText(docente.getCorreo());
            txttelefono1.setText("" + docente.getTelefono());
            txtsexo1.setText(docente.getSexo());
            txtdni1.setText("" + docente.getDNI());
            txttipo1.setText(VecTipo(docente.getId_Tipo()));
            DateFechaNacimiento1.setDate(docente.getFecha_Nacimiento());
            txtedad1.setText("" + (FechaDif(sd.format(docente.getFecha_Nacimiento()), sd.format(dt))) / 365);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        VentanaEditarPerfil = new javax.swing.JInternalFrame();
        jLabel11 = new javax.swing.JLabel();
        txtedad1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtnombre1 = new javax.swing.JTextField();
        txtapellido1 = new javax.swing.JTextField();
        txtcorreo1 = new javax.swing.JTextField();
        txttelefono1 = new javax.swing.JTextField();
        txtsexo1 = new javax.swing.JTextField();
        img1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtdni1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txttipo1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnEditarDatos = new javax.swing.JButton();
        DateFechaNacimiento1 = new com.toedter.calendar.JDateChooser();
        VentanaVerPerfil = new javax.swing.JInternalFrame();
        img = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        txtapellido = new javax.swing.JTextField();
        txtcorreo = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        txtsexo = new javax.swing.JTextField();
        txtdni = new javax.swing.JTextField();
        txttipo = new javax.swing.JTextField();
        txtedad = new javax.swing.JTextField();
        DateFechaNaciemiento = new com.toedter.calendar.JDateChooser();
        VerCursos = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbcurso = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        VerMensajes = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMensajes = new javax.swing.JTable();
        LisAlu = new javax.swing.JInternalFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabLisAlu = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        miscursos = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        vermensaje = new javax.swing.JMenuItem();
        alulis = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        VentanaEditarPerfil.setBackground(new java.awt.Color(255, 255, 255));
        VentanaEditarPerfil.setClosable(true);
        VentanaEditarPerfil.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        VentanaEditarPerfil.setVisible(true);

        jLabel11.setText("DNI:");

        jLabel12.setText("Tipo Usuario:");

        jLabel13.setText("Fecha de Nacimiento:");

        jLabel14.setText("Edad:");

        img1.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel15.setText("Nombre:");

        jLabel16.setText("Apellido:");

        jLabel17.setText("Correo:");

        jLabel18.setText("Telefono:");

        jLabel19.setText("Sexo:");

        jButton1.setText("Cambiar foto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cambiar Contraseña");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnEditarDatos.setText("Editar Datos");
        btnEditarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarDatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout VentanaEditarPerfilLayout = new javax.swing.GroupLayout(VentanaEditarPerfil.getContentPane());
        VentanaEditarPerfil.getContentPane().setLayout(VentanaEditarPerfilLayout);
        VentanaEditarPerfilLayout.setHorizontalGroup(
            VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentanaEditarPerfilLayout.createSequentialGroup()
                .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VentanaEditarPerfilLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(img1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(VentanaEditarPerfilLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jButton2))
                    .addGroup(VentanaEditarPerfilLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(VentanaEditarPerfilLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(111, 111, 111)
                        .addComponent(txtsexo1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(VentanaEditarPerfilLayout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txttipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(VentanaEditarPerfilLayout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtdni1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(VentanaEditarPerfilLayout.createSequentialGroup()
                            .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18)
                                .addComponent(jLabel17))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtcorreo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txttelefono1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(VentanaEditarPerfilLayout.createSequentialGroup()
                            .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15)
                                .addComponent(jLabel16))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtnombre1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtapellido1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VentanaEditarPerfilLayout.createSequentialGroup()
                            .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                            .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtedad1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(DateFechaNacimiento1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(99, 99, 99))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VentanaEditarPerfilLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnEditarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
        );
        VentanaEditarPerfilLayout.setVerticalGroup(
            VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentanaEditarPerfilLayout.createSequentialGroup()
                .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VentanaEditarPerfilLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(VentanaEditarPerfilLayout.createSequentialGroup()
                                    .addComponent(txtnombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel16)
                                        .addComponent(txtapellido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17)
                                        .addComponent(txtcorreo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(txttelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel19)
                                        .addComponent(txtsexo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(txtdni1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(txttipo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(img1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(DateFechaNacimiento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VentanaEditarPerfilLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(21, 21, 21)))
                .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(VentanaEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtedad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(btnEditarDatos)
                .addGap(22, 22, 22))
        );

        VentanaVerPerfil.setBackground(new java.awt.Color(255, 255, 255));
        VentanaVerPerfil.setClosable(true);
        VentanaVerPerfil.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        VentanaVerPerfil.setVisible(true);

        img.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel2.setText("Nombre:");

        jLabel3.setText("Apellido:");

        jLabel4.setText("Correo:");

        jLabel5.setText("Telefono:");

        jLabel6.setText("Sexo:");

        jLabel7.setText("DNI:");

        jLabel8.setText("Tipo Usuario:");

        jLabel9.setText("Fecha de Nacimiento:");

        jLabel10.setText("Edad:");

        javax.swing.GroupLayout VentanaVerPerfilLayout = new javax.swing.GroupLayout(VentanaVerPerfil.getContentPane());
        VentanaVerPerfil.getContentPane().setLayout(VentanaVerPerfilLayout);
        VentanaVerPerfilLayout.setHorizontalGroup(
            VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentanaVerPerfilLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(VentanaVerPerfilLayout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(111, 111, 111)
                            .addComponent(txtsexo, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(VentanaVerPerfilLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txttipo, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(VentanaVerPerfilLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(VentanaVerPerfilLayout.createSequentialGroup()
                                .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtcorreo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txttelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(VentanaVerPerfilLayout.createSequentialGroup()
                                .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtapellido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(VentanaVerPerfilLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DateFechaNaciemiento, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        VentanaVerPerfilLayout.setVerticalGroup(
            VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentanaVerPerfilLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VentanaVerPerfilLayout.createSequentialGroup()
                        .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtsexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txttipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VentanaVerPerfilLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel9))
                    .addGroup(VentanaVerPerfilLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DateFechaNaciemiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(VentanaVerPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        VerCursos.setVisible(true);

        tbcurso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id_Curso", "Cursos", "Descripcion", "Id_Docente"
            }
        ));
        jScrollPane1.setViewportView(tbcurso);

        jLabel1.setText("Buscar:");

        jButton3.setText("Ver Contenido del Curso");

        jButton4.setText("Ver Notas del Curso");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Foro de Consultas");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout VerCursosLayout = new javax.swing.GroupLayout(VerCursos.getContentPane());
        VerCursos.getContentPane().setLayout(VerCursosLayout);
        VerCursosLayout.setHorizontalGroup(
            VerCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VerCursosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VerCursosLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(VerCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        VerCursosLayout.setVerticalGroup(
            VerCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VerCursosLayout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(VerCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(VerCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VerCursosLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jButton3)
                        .addGap(39, 39, 39)
                        .addComponent(jButton4)
                        .addGap(36, 36, 36)
                        .addComponent(jButton5))
                    .addGroup(VerCursosLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(129, 129, 129))
        );

        VerMensajes.setVisible(true);

        tbMensajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id_Mensaje", "Mensaje"
            }
        ));
        jScrollPane2.setViewportView(tbMensajes);

        javax.swing.GroupLayout VerMensajesLayout = new javax.swing.GroupLayout(VerMensajes.getContentPane());
        VerMensajes.getContentPane().setLayout(VerMensajesLayout);
        VerMensajesLayout.setHorizontalGroup(
            VerMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VerMensajesLayout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        VerMensajesLayout.setVerticalGroup(
            VerMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VerMensajesLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        LisAlu.setVisible(true);

        tabLisAlu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id_Alumno", "Nombre", "Apellido"
            }
        ));
        jScrollPane3.setViewportView(tabLisAlu);

        javax.swing.GroupLayout LisAluLayout = new javax.swing.GroupLayout(LisAlu.getContentPane());
        LisAlu.getContentPane().setLayout(LisAluLayout);
        LisAluLayout.setHorizontalGroup(
            LisAluLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LisAluLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
        );
        LisAluLayout.setVerticalGroup(
            LisAluLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LisAluLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
        );

        jDesktopPane1.setLayer(VentanaEditarPerfil, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(VentanaVerPerfil, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(VerCursos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(VerMensajes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(LisAlu, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VentanaVerPerfil)
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(VentanaEditarPerfil))
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(VerCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(VerMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(LisAlu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VentanaVerPerfil)
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(VentanaEditarPerfil))
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(VerCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(VerMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(LisAlu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jMenu1.setText("Perfil");

        jMenuItem2.setText("Ver Perfil");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseClicked(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Editar Perfil");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem1.setText("Cerrar Sesión");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        miscursos.setText("Mis Cursos");

        jMenu4.setText("Ver Cursos");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        miscursos.add(jMenu4);

        jMenuBar1.add(miscursos);

        jMenu3.setText("Notas");

        jMenu5.setText("Ver Notas del Curso");
        jMenu3.add(jMenu5);

        jMenuBar1.add(jMenu3);

        jMenu6.setText("Mensajes");

        vermensaje.setText("Ver Mensaje");
        vermensaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vermensajeMouseClicked(evt);
            }
        });
        vermensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vermensajeActionPerformed(evt);
            }
        });
        jMenu6.add(vermensaje);

        alulis.setText("Listado de Alumnos");
        alulis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alulisActionPerformed(evt);
            }
        });
        jMenu6.add(alulis);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void verPerfil() {
        if (alumno != null) {
            try {
                img.setIcon(new ImageIcon(generarImagenCita(alumno.getAvatar()).getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_DEFAULT)));
            } catch (IOException ex) {
                Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
            }

            txtnombre.setText(alumno.getNombre());
            txtapellido.setText(alumno.getApellido());
            txtcorreo.setText(alumno.getCorreo());
            txttelefono.setText("" + alumno.getTelefono());
            txtsexo.setText(alumno.getSexo());
            txtdni.setText("" + alumno.getDNI());
            txttipo.setText(VecTipo(alumno.getId_Tipo()));
            DateFechaNaciemiento.setDate(alumno.getFecha_Nacimiento());
            txtedad.setText("" + (FechaDif(sd.format(alumno.getFecha_Nacimiento()), sd.format(dt))) / 365);
        } else {
            try {
                img.setIcon(new ImageIcon(generarImagenCita(docente.getAvatar()).getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_DEFAULT)));
            } catch (IOException ex) {
                Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtnombre.setText(docente.getNombre());
            txtapellido.setText(docente.getApellido());
            txtcorreo.setText(docente.getCorreo());
            txttelefono.setText("" + docente.getTelefono());
            txtsexo.setText(docente.getSexo());
            txtdni.setText("" + docente.getDNI());
            txttipo.setText(VecTipo(docente.getId_Tipo()));
            DateFechaNaciemiento.setDate(docente.getFecha_Nacimiento());
            txtedad.setText("" + (FechaDif(sd.format(docente.getFecha_Nacimiento()), sd.format(dt))) / 365);
        }
    }
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        closeIframes();
        VentanaVerPerfil.setVisible(true);
        verPerfil();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        closeIframes();
        VentanaEditarPerfil.setVisible(true);
        EditarPerfil();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        frmLogin f = new frmLogin();
        f.setVisible(true);
        docente = null;
        alumno = null;
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (alumno != null) {
            dgCambiarContraseña.alumno = alumno;
            dgCambiarContraseña cc = new dgCambiarContraseña(this, true);
            cc.setVisible(true);
            alumno = ca.obtenerAlumno(txtcorreo1.getText());
        } else {
            dgCambiarContraseña.docente = docente;
            dgCambiarContraseña cc = new dgCambiarContraseña(this, true);
            cc.setVisible(true);
            docente = cd.obtenerDocente(txtcorreo1.getText());
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    /*private static java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }*/

    private void btnEditarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarDatosActionPerformed
        if (alumno != null) {
            Alumno a = new Alumno();
            a.setNombre(txtnombre1.getText());
            a.setApellido(txtapellido1.getText());
            a.setCorreo(txtcorreo1.getText());
            a.setTelefono(Integer.parseInt(txttelefono1.getText()));
            a.setSexo(txtsexo1.getText());
            a.setDNI(Integer.parseInt(txtdni1.getText()));
            a.setFecha_Nacimiento(new java.sql.Date(DateFechaNacimiento1.getDate().getTime()));
            ca.EditarAlumno(a, alumno.getId_Alumno());
            alumno = ca.obtenerAlumno(txtcorreo1.getText());
        } else {
            Docente d = new Docente();
            d.setNombre(txtnombre1.getText());
            d.setApellido(txtapellido1.getText());
            d.setCorreo(txtcorreo1.getText());
            d.setTelefono(Integer.parseInt(txttelefono1.getText()));
            d.setSexo(txtsexo1.getText());
            d.setDNI(Integer.parseInt(txtdni1.getText()));
            d.setFecha_Nacimiento(new java.sql.Date(DateFechaNacimiento1.getDate().getTime()));
            cd.EditarDocente(d, docente.getId_Docente());
            docente = cd.obtenerDocente(txtcorreo1.getText());
        }

    }//GEN-LAST:event_btnEditarDatosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (alumno != null) {
            JFileChooser archivo = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de archivos JPEG(*.jpg;*jpeg)", "jpg", "jpeg");
            archivo.setFileFilter(filtro);
            archivo.setDialogTitle("Subir foto de perfil");
            int ventana = archivo.showOpenDialog(null);
            if (ventana == JFileChooser.APPROVE_OPTION) {
                File file = archivo.getSelectedFile();
                if (file.getName().endsWith("jpg") || file.getName().endsWith("jpeg")) {
                    String rutaName = file.getPath();
                    try {
                        FileInputStream archivoFoto = new FileInputStream(rutaName);
                        if (ca.subirFoto(archivoFoto, alumno.getId_Alumno())) {
                            Image foto = getToolkit().getImage(String.valueOf(file));
                            foto = foto.getScaledInstance(img1.getWidth(), img1.getHeight(), Image.SCALE_DEFAULT);
                            img1.setIcon(new ImageIcon(foto));
                            img.setIcon(new ImageIcon(foto));
                            SimpleAlert.showMessaje(null, true, "La foto se ha subido con exito");
                        } else {
                            SimpleAlert.showMessaje(null, true, "La foto no se ha subido");
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    SimpleAlert.showMessaje(null, true, "Archivo no soportado");
                }

            }
            alumno = ca.obtenerAlumno(txtcorreo1.getText());
        } else {
            JFileChooser archivo = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de archivos JPEG(*.jpg;*jpeg)", "jpg", "jpeg");
            archivo.setFileFilter(filtro);
            archivo.setDialogTitle("Subir foto de perfil");
            int ventana = archivo.showOpenDialog(null);
            if (ventana == JFileChooser.APPROVE_OPTION) {
                File file = archivo.getSelectedFile();
                if (file.getName().endsWith("jpg") || file.getName().endsWith("jpeg")) {
                    String rutaName = file.getPath();
                    try {
                        FileInputStream archivoFoto = new FileInputStream(rutaName);
                        if (cd.subirFoto(archivoFoto, docente.getId_Docente())) {
                            Image foto = getToolkit().getImage(String.valueOf(file));
                            foto = foto.getScaledInstance(img1.getWidth(), img1.getHeight(), Image.SCALE_DEFAULT);
                            img1.setIcon(new ImageIcon(foto));
                            img.setIcon(new ImageIcon(foto));
                            SimpleAlert.showMessaje(null, true, "La foto se ha subido con exito");
                        } else {
                            SimpleAlert.showMessaje(null, true, "La foto no se ha subido");
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    SimpleAlert.showMessaje(null, true, "Archivo no soportado");
                }

            }
            docente = cd.obtenerDocente(txtcorreo1.getText());
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        closeIframes();
        VerCursos.setVisible(true);

    }//GEN-LAST:event_jMenu4MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //Enviando Datos
        frmNotas.alumno = alumno;
        int columna = 0;//Declaro a la posicion de la columna
        try {
            int fila = tbcurso.getSelectedRow();//Llamo a la fila Seleccionada
            int id_curso = (int) tbcurso.getValueAt(fila, columna);//Llamo el valor de la tabla
            frmNotas.id_curso = id_curso;// Mando la variable al frmNotas
            frmNotas frm = new frmNotas();
            frm.setVisible(true);
        } catch (Exception e) {
            SimpleAlert.showMessaje(null, true, "Seleccione un Fila");
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (alumno != null) {
            dgMensajeria.alumno = alumno;
            int columna = 3;
            try {
                int fila = tbcurso.getSelectedRow();
                int id_docente = (int) tbcurso.getValueAt(fila, columna);
                dgMensajeria.id_docente = id_docente;
                dgMensajeria dgm = new dgMensajeria(this, true);
                dgm.setVisible(true);
                ListarMensajesAlu(alumno.getId_Alumno());
            } catch (Exception e) {
                SimpleAlert.showMessaje(null, true, "Seleccione un Fila");
            }
        } else {
            dgMensajeria.docente = docente;

            int columna = 3;
            try {
                int fila = tbcurso.getSelectedRow();
                int id_alumno = (int) tbcurso.getValueAt(fila, columna);
                dgMensajeria.id_alumno = id_alumno;
                dgMensajeria dgm = new dgMensajeria(this, true);
                dgm.setVisible(true);
                ListarMensajesDoc(docente.getId_Docente());
            } catch (Exception e) {
                SimpleAlert.showMessaje(null, true, "Seleccione un Fila");
            }
        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void vermensajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vermensajeMouseClicked
        /* closeIframes();
        VerMensajes.setVisible(true);
        System.out.println("hola");*/
    }//GEN-LAST:event_vermensajeMouseClicked

    private void vermensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vermensajeActionPerformed
        closeIframes();
        VerMensajes.setVisible(true);
        System.out.println("hola");
    }//GEN-LAST:event_vermensajeActionPerformed

    private void alulisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alulisActionPerformed
        closeIframes();
        LisAlu.setVisible(true);
    }//GEN-LAST:event_alulisActionPerformed

    private void jMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateFechaNaciemiento;
    private com.toedter.calendar.JDateChooser DateFechaNacimiento1;
    private javax.swing.JInternalFrame LisAlu;
    private javax.swing.JInternalFrame VentanaEditarPerfil;
    private javax.swing.JInternalFrame VentanaVerPerfil;
    private javax.swing.JInternalFrame VerCursos;
    private javax.swing.JInternalFrame VerMensajes;
    private javax.swing.JMenuItem alulis;
    private javax.swing.JButton btnEditarDatos;
    private javax.swing.JLabel img;
    private javax.swing.JLabel img1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenu miscursos;
    private javax.swing.JTable tabLisAlu;
    private javax.swing.JTable tbMensajes;
    private javax.swing.JTable tbcurso;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtapellido1;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtcorreo1;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtdni1;
    private javax.swing.JTextField txtedad;
    private javax.swing.JTextField txtedad1;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnombre1;
    private javax.swing.JTextField txtsexo;
    private javax.swing.JTextField txtsexo1;
    private javax.swing.JTextField txttelefono;
    private javax.swing.JTextField txttelefono1;
    private javax.swing.JTextField txttipo;
    private javax.swing.JTextField txttipo1;
    private javax.swing.JMenuItem vermensaje;
    // End of variables declaration//GEN-END:variables
}
