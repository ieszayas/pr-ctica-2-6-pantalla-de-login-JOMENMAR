/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.IO;

import Modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAM2_05
 */
public class BBDD {

    static Scanner sc = new Scanner(System.in);

    static Connection con = Conexion.getConexion();
    private static String NOMBRE_BBDD = "Login";
    private static boolean creado = false;

    public static void crearBaseDeDatos() {

        if (!creado) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                if (con != null) {

                    boolean existeBbdd = false;
                    String sqlInterrogation = "SELECT SCHEMA_NAME FROM information_schema.SCHEMATA WHERE SCHEMA_NAME = ?";
                    pstmt = con.prepareStatement(sqlInterrogation);
                    pstmt.setString(1, NOMBRE_BBDD);
                    rs = pstmt.executeQuery();
                    existeBbdd = rs.next();

                    if (existeBbdd) {
                        if (!continuarBBDD()) {
                            System.out.println("Eliminando la base de datos...");
                            String sqlDelete = "DROP DATABASE IF EXISTS " + NOMBRE_BBDD;
                            pstmt = con.prepareStatement(sqlDelete);
                            pstmt.executeUpdate();

                            System.out.println("Creando la base de datos...");
                            String sqlCreate = "CREATE DATABASE " + NOMBRE_BBDD;
                            pstmt = con.prepareStatement(sqlCreate);
                            pstmt.executeUpdate();
                        }

                    } else {
                        System.out.println("Creando la base de datos...");
                        String sqlCreate = "CREATE DATABASE " + NOMBRE_BBDD;
                        pstmt = con.prepareStatement(sqlCreate);
                        pstmt.executeUpdate();
                    }

                    String sqlUse = "USE " + NOMBRE_BBDD;
                    pstmt = con.prepareStatement(sqlUse);
                    pstmt.executeUpdate();

                    System.out.println("Conectado a la base de datos " + NOMBRE_BBDD);
                }

            } catch (SQLException ex) {
                Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        crearTablaUsuarios();

    }

    public static boolean continuarBBDD() {
        while (true) {
            System.out.println("""
                           ¿Quieres continuar con la base de datos anterior?
                                              Si o No
                           """);
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("si")) {
                return true;
            }

            if (respuesta.equalsIgnoreCase("no")) {
                return false;
            }

            System.out.println("Escriba su respuesta de nuevo");
        }

    }

    // Método para crear la tabla 'usuarios'
    public static void crearTablaUsuarios() {
        String query = "CREATE TABLE IF NOT EXISTS " + NOMBRE_BBDD + ".usuarios ("
                + "nombre_usuario VARCHAR(50) PRIMARY KEY, " // Clave primaria
                + "password VARCHAR(255) NOT NULL, "
                + "fecha_nacimiento DATE , "
                + "nombre VARCHAR(50) , "
                + "apellidos VARCHAR(255) , " // Almacenar la lista de apellidos como una cadena
                + "correo VARCHAR(100) "
                + ")";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();
            System.out.println("Tabla 'usuarios' creada con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public static boolean nombreUsuarioExiste(String nombre_usuario) {
        String query = "SELECT COUNT(*) FROM usuarios WHERE nombre_usuario = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, nombre_usuario);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;  // Devuelve true si el COUNT(*) > 0 (el usuario ya existe)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Si no se encuentra el usuario o hay un error, devuelve false
    }

    // Método para insertar un usuario en la tabla 'usuarios' utilizando PreparedStatement
    public static void insertarUsuario(Usuario usuario) {
        // Primero verificamos si el nombre de usuario ya existe
        if (nombreUsuarioExiste(usuario.getNombre_usuario())) {
            System.out.println("Error: El nombre de usuario '" + usuario.getNombre_usuario() + "' ya está registrado.");
            return;  // Si el nombre de usuario ya existe, se sale del método sin insertar
        }

        // Si el nombre de usuario no existe, procedemos con la inserción
        String query = "INSERT INTO usuarios (nombre_usuario, password, fecha_nacimiento, nombre, apellidos, correo) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        // Convertimos la lista de apellidos en una única cadena separada por comas
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            // Asignar los valores del objeto 'Usuario' a los parámetros del PreparedStatement
            pstmt.setString(1, usuario.getNombre_usuario());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setDate(3, (java.sql.Date) usuario.getFecha_nac());  // Asumimos que la fecha está en formato 'yyyy-mm-dd'
            pstmt.setString(4, usuario.getNombre());
            pstmt.setString(5, usuario.getApellido());
            pstmt.setString(6, usuario.getCorreo());

            // Ejecutar la consulta
            pstmt.executeUpdate();
            System.out.println("Usuario insertado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modificarUsuarioPassword(Usuario usuario) {
        // Primero verificamos si el nombre de usuario existe
        if (!nombreUsuarioExiste(usuario.getNombre_usuario())) {
            System.out.println("Error: El nombre de usuario '" + usuario.getNombre_usuario() + "' no está registrado.");
            return;  // Si el nombre de usuario no existe, se sale del método sin modificar
        }

        // Si el nombre de usuario existe, procedemos con la modificación
        String query = "UPDATE usuarios SET password = ?"
                + "WHERE nombre_usuario = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            // Asignar los valores del objeto 'Usuario' a los parámetros del PreparedStatement
            pstmt.setString(1, usuario.getPassword());
            pstmt.setString(2, usuario.getNombre_usuario());  // Usamos el nombre de usuario como identificador

            // Ejecutar la actualización
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario modificado con éxito.");
            } else {
                System.out.println("Error: No se pudo modificar el usuario.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
