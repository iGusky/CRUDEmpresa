/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.dao;

import java.util.List;

/**
 *
 * @author HP
 */
public interface IDAOGeneral<T> {
    
    public T agregar(T pojo);
    public boolean modificar (T pojo);
    public boolean eliminar (long clave);
    public T buscarPorID(long clave);
    public List<T> buscarTodos();
    
}
