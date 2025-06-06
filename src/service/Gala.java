import java.sql.Date;
import java.util.ArrayList;

/**
 * La classe Gala représente un événement de type gala.
 * Un gala possède un nom, une date, un lieu, et une liste des personnes invitées.
 * 
 * Cette classe permet également d'ajouter ou de retirer des personnes inscrites à l'événement.
 */
public class Gala {

    /** Nom de l'événement gala */
    private String nom;

    /** Date de l'événement */
    private Date date;

    /** Lieu où se tient le gala */
    private String lieu;

    /** Liste des personnes inscrites à ce gala */
    private ArrayList<Personne> listPersonne = new ArrayList<Personne>();

    /**
     * Constructeur de la classe Gala sans plan.
     * Initialise le nom, la date et le lieu du gala.
     *
     * @param nom  Le nom du gala
     * @param date La date à laquelle le gala a lieu
     * @param lieu Le lieu de l'événement
     */
    public Gala(String nom, Date date, String lieu) {
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
    }

    /**
     * Ajoute une personne à la liste des invités du gala.
     *
     * @param personne La personne à ajouter
     */
    public void ajouter(Personne personne) {
        this.listPersonne.add(personne);
    }

    /**
     * Supprime une personne de la liste des invités du gala.
     *
     * @param personne La personne à retirer
     */
    public void supprimer(Personne personne) {
        this.listPersonne.remove(personne);
    }

    /**
     * Retourne le nom du gala.
     *
     * @return Le nom de l'événement
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Retourne la date du gala.
     *
     * @return La date de l'événement
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Retourne le lieu du gala.
     *
     * @return Le lieu de l'événement
     */
    public String getLieu() {
        return this.lieu;
    }

    /**
     * Retourne la liste des personnes inscrites à ce gala.
     *
     * @return Une liste d'objets Personne
     */
    public ArrayList<Personne> getListPersonne() {
        return this.listPersonne;
    }
}
