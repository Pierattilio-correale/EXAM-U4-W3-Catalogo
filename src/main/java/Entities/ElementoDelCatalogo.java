package Entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
@NamedQuery(name = "getElementoPerAnnoPubblicazione" ,query = "select el from ElementoDelCatalogo el where el.annoPubblicazione =:annoPubblicazione")
@NamedQuery(name = "getElementoPerTitolo", query = "select el from ElementoDelCatalogo el where el.titolo like :titolo")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "elementi_del_catalogo")


public abstract class ElementoDelCatalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int isbn;
    private String titolo;
    @Column(name = "anno_di_pubblicazione")
    private Integer annoPubblicazione;
    @Column(name = "numero_di_pagine")
    private Integer numeroPagine;
    @OneToMany(mappedBy = "elementoPrestato")
    private List<Prestito>elementiPrestati;

    public ElementoDelCatalogo(String titolo, Integer annoPubblicazione, Integer numeroPagine) {
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public ElementoDelCatalogo() {
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public List<Prestito> getElementiPrestati() {
        return elementiPrestati;
    }

    public void setElementiPrestati(List<Prestito> elementiPrestati) {
        this.elementiPrestati = elementiPrestati;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(Integer annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public Integer getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(Integer numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ElementoDelCatalogo that = (ElementoDelCatalogo) o;
        return Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }

    @Override
    public String toString() {
        return "ElementoDelCatalogo{" +
                "isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                ", elementiPrestati=" + elementiPrestati +
                '}';
    }
}
