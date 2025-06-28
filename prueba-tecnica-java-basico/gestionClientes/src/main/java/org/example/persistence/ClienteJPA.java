package org.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entities.Cliente;

import java.util.List;

public class ClienteJPA {

    public void guardar(Cliente cliente){
        EntityManager em = ConfigJPA.getEntityManager();
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.close();
    }

    public List<Cliente> listarTodos() {
        EntityManager em = ConfigJPA.getEntityManager();
        List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c",Cliente.class).getResultList();
        em.close();
        return clientes;
    }

    public Cliente buscarPorId(Long id) {
        EntityManager em = ConfigJPA.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        em.close();
        return cliente;
    }

    public void actualizar(Cliente cliente) {
        EntityManager em = ConfigJPA.getEntityManager();
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminar(Long id) {
        EntityManager em = ConfigJPA.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) {
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        }
        em.close();
    }

    public List<Cliente> buscarPorCiudad(String ciudad){
        EntityManager em = ConfigJPA.getEntityManager();
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.ciudad = :ciudad", Cliente.class);
        query.setParameter("ciudad", ciudad);
        List<Cliente> clientes = query.getResultList();
        em.close();
        return clientes;
    }


}

