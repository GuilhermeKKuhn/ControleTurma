package util;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Factory {
    private static final EntityManagerFactory CRIADOR = Persistence.createEntityManagerFactory("postgres");

    public static EntityManager getEntityManager(){
        return CRIADOR.createEntityManager();
    }
}
