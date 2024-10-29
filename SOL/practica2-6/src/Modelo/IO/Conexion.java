/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.IO;

/**
 *
 * @author DAM2_05
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
 
public class Conexion {
      private static Connection conexion = null;
 
    private Conexion() {
    }
 
    public static Connection getConexion() {
 
        
        if (conexion == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/";
                String usuario = "root";
                conexion = DriverManager.getConnection(url, usuario, null);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al conectar al servidor MySQL.");
            }
        }
 
        return conexion;
    }
 
    public static void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
 
        } catch (SQLException ex) {
            System.out.println("No se ha podidoncerrarla conexion.");
        }
 
    }
}
