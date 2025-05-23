import Dao.ElementoDelCatalogoDao;
import Dao.PrestitoDao;
import Dao.UtenteDao;
import Entities.ElementoDelCatalogo;
import Entities.Prestito;
import Entities.Utente;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Archivio archivio = new Archivio();

        while (true) {
            System.out.println("Seleziona un'opzione:");
            System.out.println("1. Inserisci un libro");
            System.out.println("2. Inserisci una rivista");
            System.out.println("3. Cerca un elemento per ISBN");
            System.out.println("4. Rimuovi un elemento per ISBN");
            System.out.println("5. Cerca per anno di pubblicazione");
            System.out.println("6. Cerca libri per autore");
            System.out.println("7. Cerca elemento per il titolo");
            System.out.println("8. Cerca gli elementi attualmente in prestito per numero di tessera");
            System.out.println("9. Cerca i prestiti scaduti e non restituiti");
            System.out.println("10. Aggiungi un utente");
            System.out.println("11. Registra un nuovo prestito");
            System.out.println("12. Esci");


            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    inserisciLibro(archivio.getElementoDAO(), scanner);
                    break;
                case 2:
                    inserisciRivista(archivio.getElementoDAO(), scanner);
                    break;
                case 3:
                    cercaPerIsbn(archivio.getElementoDAO(), scanner);
                    break;
                case 4:
                    rimuoviPerIsbn(archivio.getElementoDAO(), scanner);
                    break;
                case 5:
                    cercaPerAnno(archivio.getElementoDAO(), scanner);
                    break;
                case 6:
                    cercaPerAutore(archivio.getElementoDAO(), scanner);
                    break;
                case 7:
                    cercaPerTitolo(archivio.getElementoDAO(), scanner);
                    break;
                case 8:
                    cercaElementiInPrestito(archivio.getPrestitoDAO(), scanner);
                    break;
                case 9:
                    cercaPrestitiScadutiNonRestituiti(archivio.getPrestitoDAO());
                    break;
                case 10:
                    inserisciUtente(archivio.getUtenteDao(), scanner);
                    break;
                case 11:
                    registraPrestito(archivio.getPrestitoDAO(), archivio.getUtenteDao(), archivio.getElementoDAO(), scanner);
                    break;
                case 12:
                    System.out.println("Grazie per essere stato con noi alla prossima!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opzione non valida, riprova.");
            }

        }
    }

    private static void inserisciLibro(ElementoDelCatalogoDao elementoDAO, Scanner scanner) {
        System.out.print("Inserisci il titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Inserisci l'anno di pubblicazione: ");
        int anno = scanner.nextInt();
        System.out.print("Inserisci il numero di pagine: ");
        int numeroPagine = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Inserisci l'autore del libro: ");
        String autore = scanner.nextLine();
        System.out.print("Inserisci il genere del libro: ");
        String genere = scanner.nextLine();

        Entities.Libri libro = new Entities.Libri(titolo, anno, numeroPagine, autore, genere);
        elementoDAO.aggiungiElementoDelCatalogo(libro);
        System.out.println("Libro inserito nel catalogo.");

    }


    private static void inserisciRivista(ElementoDelCatalogoDao elementoDAO, Scanner scanner) {

        System.out.print("Inserisci il titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Inserisci l'anno di pubblicazione: ");
        int anno = scanner.nextInt();
        System.out.print("Inserisci il numero di pagine: ");
        int numeroPagine = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Inserisci la periodicità della rivista (SEMESTRALE, MENSILE, SETTIMANALE): ");
        String periodicitaStr = scanner.nextLine();
        Entities.Periodicita periodicita = Entities.Periodicita.valueOf(periodicitaStr.toUpperCase());

        Entities.Riviste rivista = new Entities.Riviste(titolo, anno, numeroPagine, periodicita);
        elementoDAO.aggiungiElementoDelCatalogo(rivista);
        System.out.println("Rivista inserita nel catalogo.");
    }


private static void cercaPerIsbn(ElementoDelCatalogoDao elementoDAO, Scanner scanner) {
//        try {
    System.out.print("Inserisci l'ISBN da cercare: ");
    int isbn = scanner.nextInt();
    Entities.ElementoDelCatalogo elemento = elementoDAO.getElementoDelCatalogoConIsbn(isbn);
    System.out.println("Elemento trovato: " + elemento);
//        } catch (ElementoNonTrovatoException e) {
//            System.out.println(e.getMessage());
//        }
}
//
private static void rimuoviPerIsbn(ElementoDelCatalogoDao elementoDAO, Scanner scanner) {
//        try {
    System.out.print("Inserisci l'ISBN da rimuovere: ");
    int isbn = scanner.nextInt();
    elementoDAO.rimuoviElementoPerIsbn(isbn);
    System.out.println("Elemento rimosso dal catalogo.");
//        } catch (ElementoNonTrovatoException e) {
//            System.out.println(e.getMessage());
//        }
}

private static void cercaPerAnno(ElementoDelCatalogoDao elementoDAO , Scanner scanner) {
    System.out.print("Inserisci l'anno da cercare: ");
    int anno = scanner.nextInt();
    scanner.nextLine();
    List<ElementoDelCatalogo> risultati = elementoDAO.getElementoPerAnnoPublicazione(anno);
    if (risultati.isEmpty()) {
        System.out.println("Nessun elemento trovato per l'anno " + anno);
    } else {
        System.out.println("Elementi trovati:");
        risultati.forEach(System.out::println);
    }
}

private static void cercaPerAutore(ElementoDelCatalogoDao elementoDAO, Scanner scanner) {
    System.out.print("Inserisci il nome dell'autore: ");
    String autore = scanner.nextLine();
    List<Entities.Libri> risultati = elementoDAO.ricercaPerAutore(autore);
    if (risultati.isEmpty()) {
        System.out.println("Nessun libro trovato per l'autore " + autore);
    } else {
        System.out.println("Entities.Libri trovati:");
        risultati.forEach(System.out::println);
    }

}
private static void cercaPerTitolo(ElementoDelCatalogoDao elementoDAO, Scanner scanner) {
    System.out.print("Inserisci il nome dell'autore: ");
    String titolo = scanner.nextLine();
    List<Entities.ElementoDelCatalogo> risultati = elementoDAO.getElementoPerTitolo(titolo);
    if (risultati.isEmpty()) {
        System.out.println("Nessun libro trovato per il titolo " + titolo);
    } else {
        System.out.println("Libri trovati:");
        risultati.forEach(System.out::println);
    }
}

private static void cercaElementiInPrestito(PrestitoDao prestitoDAO, Scanner scanner) {
    System.out.print("Inserisci il numero di tessera utente: ");
    int numeroDiTessera = scanner.nextInt();
    List<Prestito> prestitiAttivi = prestitoDAO.elementiAttualmenteInPrestito(numeroDiTessera);

    if (prestitiAttivi.isEmpty()) {
        System.out.println("Nessun prestito attivo trovato per il numero di tessera " + numeroDiTessera);
    } else {
        System.out.println("Elementi attualmente in prestito:");
        prestitiAttivi.forEach(System.out::println);
    }
}


private static void cercaPrestitiScadutiNonRestituiti(PrestitoDao prestitoDAO) {
    List<Prestito> prestitiScaduti = prestitoDAO.ricercaPrestitiScadutiNonRestituiti();

    if (prestitiScaduti.isEmpty()) {
        System.out.println("Nessun prestito scaduto e non restituito trovato.");
    } else {
        System.out.println("Prestiti scaduti e non restituiti:");
        prestitiScaduti.forEach(System.out::println);
    }
    }
    private static void inserisciUtente(UtenteDao utenteDao, Scanner scanner) {
        System.out.print("Inserisci il nome: ");
        String nome = scanner.nextLine();

        System.out.print("Inserisci il cognome: ");
        String cognome = scanner.nextLine();

        System.out.print("Inserisci la data di nascita : ");
        String dataNascitaStr = scanner.nextLine();
        LocalDate dataNascita = LocalDate.parse(dataNascitaStr);

        Utente utente = new Utente();
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setDataDiNascita(dataNascita);

        utenteDao.aggiungiUtente(utente);
        System.out.println("Utente aggiunto correttamente!");
    }
    private static void registraPrestito(PrestitoDao prestitoDao, UtenteDao utenteDao, ElementoDelCatalogoDao elementoDao, Scanner scanner) {
        System.out.print("Inserisci il numero di tessera dell'utente: ");
        int numeroTessera = scanner.nextInt();
        scanner.nextLine(); // Consuma newline
        Utente utente = utenteDao.getUtenteByNumeroTessera(numeroTessera);

        if (utente == null) {
            System.out.println("Utente non trovato.");
            return;
        }

        System.out.print("Inserisci l'ISBN dell'elemento da prestare: ");
        int isbn = scanner.nextInt();
        scanner.nextLine();
        ElementoDelCatalogo elemento = elementoDao.getElementoDelCatalogoConIsbn(isbn);

        if (elemento == null) {
            System.out.println("Elemento non trovato.");
            return;
        }

        LocalDate dataInizio = LocalDate.now();
        LocalDate dataFine = dataInizio.plusDays(30);

        Prestito prestito = new Prestito();
        prestito.setUtente(utente);
        prestito.setElementoPrestato(elemento);
        prestito.setDataInizioPrestito(dataInizio);
        prestito.setRestituzionePrevista(dataFine);

        prestitoDao.aggiungiPrestito(prestito);
        System.out.println("Prestito registrato con successo.");
    }
}

