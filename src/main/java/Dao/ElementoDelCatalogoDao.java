package Dao;

import Entities.ElementoDelCatalogo;
import Entities.Libri;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ElementoDelCatalogoDao {
    private EntityManager em;

    public ElementoDelCatalogoDao(EntityManager em) {
        this.em = em;
    }

    public void aggiungiElementoDelCatalogo(ElementoDelCatalogo el){
        em.getTransaction().begin();
        em.persist(el);
        em.getTransaction().commit();
    }
    public ElementoDelCatalogo getElementoDelCatalogoConIsbn(int isbn){
        return em.find(ElementoDelCatalogo.class, isbn );

    }
    public void rimuoviElementoPerIsbn(int isbn ){
        ElementoDelCatalogo el= getElementoDelCatalogoConIsbn(isbn);
        if(el!= null){
            em.getTransaction().begin();
            em.remove(el);
            em.getTransaction().commit();
        }
    }
    public List<ElementoDelCatalogo>getElementoPerAnnoPublicazione(int anno){
        TypedQuery<ElementoDelCatalogo>query= em.createNamedQuery("getElementoPerAnnoPubblicazione", ElementoDelCatalogo.class);
        query.setParameter("annoPubblicazione", anno );
        return query.getResultList();
    }
    public List<Libri>ricercaPerAutore(String autore){
        TypedQuery<Libri>query = em.createNamedQuery("getLibroPerAutore", Libri.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }
    public List<ElementoDelCatalogo>getElementoPerTitolo(String titolo){
        TypedQuery<ElementoDelCatalogo>query= em.createNamedQuery("getElementoPerTitolo", ElementoDelCatalogo.class);
        query.setParameter("titolo", "%" + titolo + "%");
        return query.getResultList();
    }
}