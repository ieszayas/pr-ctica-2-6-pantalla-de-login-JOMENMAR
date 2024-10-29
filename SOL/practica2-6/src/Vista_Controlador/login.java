/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista_Controlador;

import Modelo.IO.BBDD;
import Modelo.Usuario;
import static Modelo.Usuario.crearUsuarios;
import static Modelo.Usuario.getUsuarios;

/**
 * Clase para el formulario de inicio de sesión.
 * Permite a los usuarios autenticarse y acceder a la aplicación.
 * Puede mostrar contraseñas y redirigir a una pantalla para crear cuentas.
 */
public class login extends javax.swing.JFrame {

    private static boolean BBDD_creada = false;

    public login() {
        initComponents();
        if (!BBDD_creada) {
            BBDD.crearBaseDeDatos();
            BBDD.crearTablaUsuarios();
            crearUsuarios();
            BBDD_creada = true;
        }
        Password.setEchoChar('*');
    }

    /**
     * Código autogenerado para inicializar componentes de la interfaz.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Usuario_Intr = new javax.swing.JTextField();
        Mostrar_Contrasenha = new javax.swing.JCheckBox();
        Loguear = new javax.swing.JButton();
        Password = new javax.swing.JPasswordField();
        Crear_Usuario_Texto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");

        jLabel1.setText("Por favor introduzca sus credenciales para acceder:");

        jLabel2.setText("Usuario");
        jLabel3.setText("Password");

        Mostrar_Contrasenha.setText("Mostrar");
        Mostrar_Contrasenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mostrar_ContrasenhaActionPerformed(evt);
            }
        });

        Loguear.setText("Loguear");
        Loguear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                LoguearMouseReleased(evt);
            }
        });

        Crear_Usuario_Texto.setText("Haz clic para crear una cuenta nueva");
        Crear_Usuario_Texto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Crear_Usuario_TextoMouseClicked(evt);
            }
        });

        // Configuración del layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Usuario_Intr, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(Loguear, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                                .addGap(18, 18, 18))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(Password)
                                                .addGap(18, 18, 18)))
                                        .addComponent(Mostrar_Contrasenha))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(Crear_Usuario_Texto)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Usuario_Intr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Mostrar_Contrasenha)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(Loguear)
                .addGap(26, 26, 26)
                .addComponent(Crear_Usuario_Texto)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void Mostrar_ContrasenhaActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        if (Mostrar_Contrasenha.isSelected()) {
            Password.setEchoChar((char) 0);
        } else {
            Password.setEchoChar('*');
        }
    }                                                   

    private void LoguearMouseReleased(java.awt.event.MouseEvent evt) {                                      
        String nombre = Usuario_Intr.getText();
        String password = String.valueOf(Password.getPassword());

        Usuario u = new Usuario(nombre, password);

        if (!u.comparar()) {
            vibrarPantalla();
            return;
        }
        this.dispose();
        new Usuario_Logueado(u.getNombre()).setVisible(true);
    }                                     

    private void Crear_Usuario_TextoMouseClicked(java.awt.event.MouseEvent evt) {                                                 
        this.dispose();
        new CrearUsuario(null).setVisible(true);
    }                                                

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    public void vibrarPantalla() {
        final int originalX = this.getLocationOnScreen().x;  
        final int originalY = this.getLocationOnScreen().y;  

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    this.setLocation(originalX + (int) (Math.random() * 10 - 5),
                            originalY + (int) (Math.random() * 10 - 5));
                    Thread.sleep(20);  
                }
                this.setLocation(originalX, originalY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Variables declaration                    
    private javax.swing.JLabel Crear_Usuario_Texto;
    private javax.swing.JButton Loguear;
    private javax.swing.JCheckBox Mostrar_Contrasenha;
    private javax.swing.JPasswordField Password;
    private javax.swing.JTextField Usuario_Intr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration                   
}
