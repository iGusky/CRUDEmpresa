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
 * @author gusky
 */
public class PersonaDAO implements IDAOGeneral<Persona>{
    
    ConexionDB conexion;
    
    public PersonaDAO() {
        this.conexion = ConexionDB.getInstance();
    }


  
    
     private boolean personaVacia(Persona e) {
        return (e.getDireccion().compareTo("") != 0 || e.getNombre().compareTo("") != 0 || e.getTelefono().compareTo("") != 0);
    }

    @Override
    public boolean agregar(Persona pojo) {
        boolean res=false;
        if(personaVacia(pojo)){
             try{
                String sql = "INSERT INTO persona (clave,nombre,direccion,telefono) values (?,?,?,?)";
                PreparedStatement query = conexion.getConection().prepareStatement(sql);
                query.setString(1, Long.toString(pojo.getClave()));
                query.setString(2, pojo.getNombre());
                query.setString(3, pojo.getDireccion());
                query.setString(4, pojo.getTelefono());
                
                query.execute();
                res = true;
            } catch(Exception e){
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return res;
    }

    @Override
    public boolean modificar(Persona pojo) {
        if (personaVacia(pojo)) {
            try {
                String sql = "UPDATE persona SET nombre= ? ,direccion= ?, telefono= ? WHERE clave = ?";

                PreparedStatement query = conexion.getConection().prepareStatement(sql);
                query.setString(1, pojo.getNombre());
                query.setString(2, pojo.getDireccion());
                query.setString(3, pojo.getTelefono());
                query.setString(4, Long.toString(pojo.getClave()));
                query.executeUpdate();
                return true;
            } catch (Exception ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(long clave) {
        try {
            String consulta = "DELETE  FROM persona WHERE clave = ?";
            PreparedStatement sql = conexion.getConection().prepareStatement(consulta);
            sql.setString(1, Long.toString(clave));
            sql.execute();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Persona buscarPorID(long clave) {
        Persona pojo = new Persona();
        try {
            String sql = "SELECT * FROM persona WHERE clave = ?";
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
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;    
    }

    @Override
    public List<Persona> buscarTodos() {
          ResultSet rs=null;
        List<Persona> listaPersonas = new ArrayList<Persona>();
         try{
                String sql = "SELECT * FROM persona;";   
                rs = conexion.query(sql);
                while(rs.next()){
                    Departamento p = new Departamento();
                    p.setClave(rs.getLong(1));
                    p.setNombre(rs.getString(2));
                    p.setDireccion(rs.getString(3));
                    p.setTelefono(rs.getString(4));
                    
                    listaPersonas.add(p);
                }
            } catch(Exception e){
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, e);
            }
         return listaPersonas;
    }
}
