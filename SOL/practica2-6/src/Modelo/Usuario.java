/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.IO.BBDD;
import java.util.*;

/**
 *
 * @author DAM2_05
 */
public class Usuario {

    private String nombre_usuario = "Sin nombre";
    private String password = "";
    private String fecha_nacimiento = "";
    private String nombre = "";
    private String apellidos = "";
    private String correo = "";

    private static ArrayList<Usuario> usuarios = new ArrayList();

    public Usuario(String nombre_v, String password_v) {
        nombre = nombre_v;
        password = password_v;
    }

    public Usuario(String nombre_usuario, String password, String nombre, String fecha_nacimiento, String apellidos, String correo) {
        this.nombre_usuario = nombre_usuario;
        this.password = password;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nombre = nombre;
        this.correo = correo;
        this.apellidos = apellidos.trim();
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    

    public static void crearUsuarios() {
        usuarios.add(new Usuario("Kevin", "1234"));
        usuarios.add(new Usuario("Paul", "1234"));
        usuarios.add(new Usuario("Alba", "1234"));
        usuarios.add(new Usuario("Ignacio", "1234"));
       
        for (Usuario usuario : usuarios) {
            BBDD.insertarUsuario(usuario);
        }
    }

    public boolean comparar() {
        boolean correcto = false;
        for (Usuario usuario : usuarios) {
            if (this.nombre.equals(usuario.nombre)) {
                if (this.password.equals(usuario.password)) {
                    correcto = true;
                }
            }
        }
        return correcto;
    }

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

}
