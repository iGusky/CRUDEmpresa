/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.uv.utils.ConexionDB;
import org.uv.dao.IDAOGeneral;

/**
 *
 * @author HP
 */
public class DepartamentoDAO implements IDAOGeneral<Departamento> {
    ConexionDB conexion;
    
    public DepartamentoDAO(){
        this.conexion = ConexionDB.getInstance();
        
    }

    @Override
    public boolean agregar(Departamento pojo) {
        boolean res=false;
        if(departamentoVacio(pojo)){
             try{
                String sql = "INSERT INTO departamento (clave,nombre,direccion,telefono) values (?,?,?,?)";
                PreparedStatement query = conexion.getConection().prepareStatement(sql);
                query.setString(1, Long.toString(pojo.getClave()));
                query.setString(2, pojo.getNombre());
                query.setString(3, pojo.getDireccion());
                query.setString(4, pojo.getTelefono());
                
                query.execute();
                res = true;
            } catch(Exception e){
                Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return res;
    }

    @Override
    public boolean modificar(Departamento pojo) {
        if (departamentoVacio(pojo)) {
            try {
                String sql = "UPDATE departamento SET nombre= ? ,direccion= ?, telefono= ? WHERE clave = ?";

                PreparedStatement query = conexion.getConection().prepareStatement(sql);
                query.setString(1, pojo.getNombre());
                query.setString(2, pojo.getDireccion());
                query.setString(3, pojo.getTelefono());
                query.setString(4, Long.toString(pojo.getClave()));
                query.executeUpdate();
                return true;
            } catch (Exception ex) {
                Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(long clave) {
        try {
            String consulta = "DELETE  FROM departamento WHERE clave = ?";
            PreparedStatement sql = conexion.getConection().prepareStatement(consulta);
            sql.setString(1, Long.toString(clave));
            sql.execute();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Departamento buscarPorID(long clave) {
        Departamento pojo = new Departamento();
        try {
            String sql = "SELECT * FROM departamento WHERE clave = ?";
            PreparedStatement query = conexion.getConection().prepareStatement(sql);
            query.setString(1, Long.toString(clave));
            ResultSet rs = query.executeQuery();
            
            if (rs.next()) {
                pojo.setClave(rs.getLong(1));
                pojo.setDireccion(rs.getString(3));
                pojo.setNombre(rs.getString(2));
                pojo.setTelefono(rs.getString(4));
            }
             return pojo;
            
        } catch (Exception ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;    
    }

    @Override
    public List<Departamento> buscarTodos() {
         ResultSet rs=null;
        List<Departamento> listaDepartamentos = new ArrayList<Departamento>();
         try{
                String sql = "SELECT * FROM departamento;";   
                rs = conexion.query(sql);
                while(rs.next()){
                    Departamento p = new Departamento();
                    p.setClave(rs.getLong(1));
                    p.setNombre(rs.getString(2));
                    p.setDireccion(rs.getString(3));
                    p.setTelefono(rs.getString(4));
                    
                    listaDepartamentos.add(p);
                }
            } catch(Exception e){
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, e);
            }
         return listaDepartamentos;
    }

     private boolean departamentoVacio(Departamento e) {
        return (e.getDireccion().compareTo("") != 0 || e.getNombre().compareTo("") != 0 || e.getTelefono().compareTo("") != 0);
    }


}
