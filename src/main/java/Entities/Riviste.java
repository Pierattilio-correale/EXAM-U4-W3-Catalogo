package Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Riviste extends ElementoDelCatalogo {
    @Enumerated(EnumType.STRING)
private Periodicita periodicita;

    public Riviste( String titolo, Integer annoPubblicazione, Integer numeroPagine, Periodicita periodicita) {
        super( titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Riviste() {
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }
    @Override
    public String toString() {
        return "Rivista [ISBN=" + getIsbn() + ", Titolo=" + getTitolo() + ", Anno=" + getAnnoPubblicazione() + ", Pagine=" + getNumeroPagine() + ", Periodicità=" + periodicita + "]";
    }
}
