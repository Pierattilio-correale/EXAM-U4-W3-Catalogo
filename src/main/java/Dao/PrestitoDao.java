package Dao;

import Entities.ElementoDelCatalogo;
import Entities.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class PrestitoDao {
    private EntityManager em;

    public PrestitoDao(EntityManager em) {
        this.em = em;
    }

    public List<ElementoDelCatalogo> elementiAttualmenteInPrestito(int numeroDiTessera){
        TypedQuery<ElementoDelCatalogo> query = em.createQuery("select p.elementoPrestato from Prestito p where p.utente.numeroDiTessera = :numeroDiTessera and p.dataRestituzioneEffettiva is null", ElementoDelCatalogo.class);
        query.setParameter("numeroDiTessera" , numeroDiTessera);
        return query.getResultList();
    }
    public List<Prestito> ricercaPrestitiScadutiNonRestituiti() {

        TypedQuery<Prestito> query = em.createQuery("select p from Prestito p where p.dataRestituzioneEffettiva is null and p.restituzionePrevista < :oggi", Prestito.class);
        query.setParameter("oggi", LocalDate.now());

        return query.getResultList();
    }
    public void aggiungiPrestito(Prestito prestito) {

            em.getTransaction().begin();
            em.persist(prestito);
            em.getTransaction().commit();
        }
    }

