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
import org.uv.entities.Departamento;
import org.uv.models.DepartamentoModel;

/**
 *
 * @author Gustavo
 */
@Named(value = "departamentoBean")
@SessionScoped
public class DepartamentoBean implements Serializable {
    
    private Departamento departamento;
    private DepartamentoModel departamentoModel;
    List<Departamento> departamentos = new ArrayList<>();
    

    /**
     * Creates a new instance of DepartamentoBean
     */
    public DepartamentoBean() {
        departamento = new Departamento();
        departamentoModel = new DepartamentoModel();
        obtenerDepartamentos();
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }   
    
    public void guardar() {
        departamentoModel.create(departamento);
        addMessage(FacesMessage.SEVERITY_INFO, "Se guardo", departamento.getDepartamento());
        this.departamentos.add(departamento);
    }
     
    public void obtenerDepartamentos(){
        System.out.println("Desde obtener departamentos");
        this.departamentos = departamentoModel.findAldd();
        
    }
    
     
      public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
}
