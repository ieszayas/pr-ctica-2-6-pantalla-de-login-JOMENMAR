/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import static Modelo.IO.BBDD.insertarUsuario;
import java.util.*;

/**
 *
 * @author DAM2_05
 */
public class Usuario {

    private String nombre_usuario = "Sin nombre";
    private String password = "";
    private String nombre = "";
    private String apellido = "";
    private Date fecha_nac;
    private String correo = "";

    private static ArrayList<Usuario> usuarios = new ArrayList();
    
    public Usuario(){
        
    }

    public Usuario(String nombre_usuario, String password) {
        this.nombre_usuario = nombre_usuario;
        this.password = password;
    }

    public Usuario(String nombre_usuario, String password, String nombre, String apellido, Date fecha_nac, String correo) {
        this.nombre_usuario = nombre_usuario;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nac = fecha_nac;
        this.correo = correo;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }



    public static void crearUsuarios() {
        usuarios.add(new Usuario("Kevin", "1234"));
        usuarios.add(new Usuario("Paul", "1234"));
        usuarios.add(new Usuario("Alba", "1234"));
        usuarios.add(new Usuario("Ignacio", "1234"));
        
        for (Usuario usuario : usuarios) {
            insertarUsuario(usuario);
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
