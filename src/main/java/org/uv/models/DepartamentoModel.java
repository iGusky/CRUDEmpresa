/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.models;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.uv.entities.Departamento;

/**
 *
 * @author Gustavo
 */
public class DepartamentoModel {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public boolean create(Departamento departamento) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(departamento);
            transaction.commit();
        } catch (Exception e) {
            result = false;
            System.err.println("Error: " + e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public List<Departamento> findAldd() {
        List<Departamento> result = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Departamento");
            result = (List<Departamento>) query.list();
        } catch (Exception e) {
            result = null;
            System.err.println("Error: " + e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return result;
    }

}
