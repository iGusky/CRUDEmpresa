/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.uv.entities.Empleado;
import org.uv.models.EmpleadoModel;

/**
 *
 * @author Gustavo
 */
@Named(value = "empleadoBean")
@SessionScoped
public class EmpleadoBean implements Serializable {

    private Empleado empleado = new Empleado();
    private EmpleadoModel empleadoModel = new EmpleadoModel();
    List<Empleado> empleados = new ArrayList<>();
    
    /**
     * Creates a new instance of EmpleadosBean
     */
    public EmpleadoBean() {
        obtenerEmpleados();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
    

    public void guardar() {
//        EmpleadoModel empleadoModel = new EmpleadoModel();
        empleadoModel.create(empleado);
        addMessage(FacesMessage.SEVERITY_INFO, "Se guardo", empleado.getNombre());
        empleados.add(empleado);
    }

    public void obtenerEmpleados() {
        System.out.println("desde obtener empleados");
        this.empleados = empleadoModel.findAll();
       
    }
    

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    

}
