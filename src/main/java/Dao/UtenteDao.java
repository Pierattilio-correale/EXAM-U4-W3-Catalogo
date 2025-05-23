package Dao;

import Entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UtenteDao {
    private EntityManager em;

    public UtenteDao(EntityManager em) {
        this.em = em;
    }


    public void aggiungiUtente(Utente utente) {
        EntityTransaction transaction = em.getTransaction();

            transaction.begin();
            em.persist(utente);
            transaction.commit();

    }


    public Utente getUtenteByNumeroTessera(int numeroTessera) {
        return em.find(Utente.class, numeroTessera);
    }
}