package app.service;

/**
 * La classe Personne représente un individu participant à un gala.
 * Elle hérite de la classe Participant et possède un nom et un prénom.
 */
public class Personne extends Participant {

    /** Le nom de la personne */
    private String nom;

    /** Le prénom de la personne */
    private String prenom;

    /**
     * Constructeur de la classe Personne.
     * Initialise les attributs nom et prénom, et attribue un numéro unique.
     *
     * @param nom Le nom de la personne
     * @param prenom Le prénom de la personne
     */
    public Personne(String nom, String prenom) {
        // Génère un identifiant unique à l'aide de PerClassCounter
        this.numero = PerClassCounter.getNextNumero(this.getClass());
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Retourne le nom de la personne.
     *
     * @return Le nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Retourne le prénom de la personne.
     *
     * @return Le prénom
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Compare deux personnes sur la base de leur numéro unique.
     *
     * @param obj L'objet à comparer
     * @return true si les deux personnes ont le même numéro, sinon false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Personne) {
            Personne personne = (Personne) obj;
            return this.getNumero() == personne.getNumero();
        } else {
            return false;
        }
    }
}
