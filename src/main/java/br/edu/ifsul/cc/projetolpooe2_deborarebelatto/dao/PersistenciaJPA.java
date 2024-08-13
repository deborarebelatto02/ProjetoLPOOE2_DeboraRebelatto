/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.projetolpooe2_deborarebelatto.dao;

import br.edu.ifsul.cc.projetolpooe2_deborarebelatto.model.Emprestimo;
import br.edu.ifsul.cc.projetolpooe2_deborarebelatto.model.Leitor;
import br.edu.ifsul.cc.projetolpooe2_deborarebelatto.model.Livro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author debor
 */
public class PersistenciaJPA {

    EntityManager entity;
    EntityManagerFactory factory;

    public PersistenciaJPA() {
        factory = Persistence.createEntityManagerFactory("pu_projetolpooe2_deborarebelatto");
        entity = factory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        if (entity == null || !entity.isOpen()) {
            entity = factory.createEntityManager();
        }
        return entity;
    }

    public Boolean conexaoAberta() {
        if (entity == null || !entity.isOpen()) {
            entity = factory.createEntityManager();
        }
        return entity.isOpen();
    }

    public void fecharConexao() {
        if (entity != null && entity.isOpen()) {
            entity.close();
        }
    }

    public Object find(Class c, Object id) throws Exception {
        EntityManager em = getEntityManager();
        return em.find(c, id);
    }

    public void persist(Object o) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void remover(Object o) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (!em.contains(o)) {
                o = em.merge(o);
            }
            em.remove(o);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public List<Livro> listLivro() {
        TypedQuery<Livro> query1 = entity.createQuery("SELECT l FROM Livro l", Livro.class);
        return query1.getResultList();
    }
    
    public List<Leitor> listLeitor() {
        TypedQuery<Leitor> query2 = entity.createQuery("SELECT l FROM Leitor l", Leitor.class);
        return query2.getResultList();
    }
    
    public List<Emprestimo> listEmprestimo() {
        TypedQuery<Emprestimo> query3 = entity.createQuery("SELECT l FROM Emprestimo l", Emprestimo.class);
        return query3.getResultList();
    }
    
    // Método para buscar ID
    public <T> T buscarPorId(Class<T> clazz, Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(clazz, id);
        } finally {
            em.close();
        }
    }

    // Método para buscar todos os registros de uma classe
    public <T> List<T> buscarTodos(Class<T> clazz) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT e FROM " + clazz.getSimpleName() + " e";
            return em.createQuery(jpql, clazz).getResultList();
        } finally {
            em.close();
        }
    }
}
