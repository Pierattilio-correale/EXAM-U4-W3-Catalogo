import Dao.ElementoDelCatalogoDao;
import Dao.PrestitoDao;
import Dao.UtenteDao;
import Entities.ElementoDelCatalogo;
import Entities.Libri;
import Entities.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Archivio {
    private EntityManager em;


    private ElementoDelCatalogoDao elementoDAO;
    private PrestitoDao prestitoDAO;
    private  UtenteDao utenteDao;
    public Archivio() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        em = emf.createEntityManager();
        elementoDAO = new ElementoDelCatalogoDao(em);
        prestitoDAO = new PrestitoDao(em);
        this.utenteDao = new UtenteDao(em);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ElementoDelCatalogoDao getElementoDAO() {
        return elementoDAO;
    }

    public void setElementoDAO(ElementoDelCatalogoDao elementoDAO) {
        this.elementoDAO = elementoDAO;
    }

    public PrestitoDao getPrestitoDAO() {
        return prestitoDAO;
    }

    public void setPrestitoDAO(PrestitoDao prestitoDAO) {
        this.prestitoDAO = prestitoDAO;
    }

    public UtenteDao getUtenteDao() {
        return utenteDao;
    }

    public void setUtenteDao(UtenteDao utenteDao) {
        this.utenteDao = utenteDao;
    }

    @Override
    public String toString() {
        return "Archivio{" +
                "em=" + em +
                ", elementoDAO=" + elementoDAO +
                ", prestitoDAO=" + prestitoDAO +
                ", utenteDao=" + utenteDao +
                '}';
    }
}


