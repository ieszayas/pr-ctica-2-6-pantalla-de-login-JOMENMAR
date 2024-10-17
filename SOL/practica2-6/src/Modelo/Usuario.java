/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.*;

/**
 *
 * @author DAM2_05
 */
public class Usuario {

    private String nombre = "Sin nombre";
    private String password = "";

    private static ArrayList<Usuario> usuarios = new ArrayList();

    public Usuario(String nombre_v, String password_v) {
        nombre = nombre_v;
        password = password_v;
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

    public static void crearUsuarios() {
        usuarios.add(new Usuario("Kevin", "1234"));
        usuarios.add(new Usuario("Paul", "1234"));
        usuarios.add(new Usuario("Alba", "1234"));
        usuarios.add(new Usuario("Ignacio", "1234"));
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

}
