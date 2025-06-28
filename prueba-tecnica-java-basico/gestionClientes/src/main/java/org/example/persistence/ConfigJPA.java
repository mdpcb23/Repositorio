package org.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConfigJPA {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();

    }

}
