/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name="empleados")
public class Empleado{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clave;
    
    @Column
    private String nombre;
    
    @Column
    private String telefono;
    
    @Column
    private String direccion;
    
    @Column
    private String usuario;
    
    @Column
    private String password;
    
    @Column 
    private int clave_departamento;

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getClave_departamento() {
        return clave_departamento;
    }

    public void setClave_departamento(int clave_departamento) {
        this.clave_departamento = clave_departamento;
    }

   
    
    
}
