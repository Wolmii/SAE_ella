package app.service;

import java.util.ArrayList;

/**
 * La classe Groupe représente un groupe de personnes invitées à un gala.
 * Un groupe est un type de Participant, et doit contenir au minimum deux personnes.
 */
public class Groupe extends Participant {

    /** Liste des personnes composant le groupe */
    private ArrayList<Personne> membres = new ArrayList<Personne>();

    /**
     * Constructeur de la classe Groupe.
     * Initialise un groupe avec une liste de personnes.
     *
     * @param membres Liste des personnes formant le groupe
     * @throws IllegalArgumentException si le groupe contient moins de 2 personnes
     */
    public Groupe(ArrayList<Personne> membres) {
        // Attribution d'un numéro unique au groupe via PerClassCounter
        this.numero = PerClassCounter.getNextNumero(this.getClass());

        // Vérification de la taille minimale du groupe
        if (membres.size() < 2) {
            throw new IllegalArgumentException("Un groupe doit être composé d'au moins 2 personnes");
        } else {
            this.membres.addAll(membres);
        }
    }

    /**
     * Ajoute une personne au groupe.
     *
     * @param personne La personne à ajouter
     */
    public void ajouter(Personne personne) {
        this.membres.add(personne);
    }

    /**
     * Supprime une personne du groupe.
     *
     * @param personne La personne à supprimer
     */
    public void supprimer(Personne personne) {
        this.membres.remove(personne);
    }

    /**
     * Retourne la liste des membres du groupe.
     *
     * @return Une liste d'objets Personne
     */
    public ArrayList<Personne> getMembres() {
        return this.membres;
    }

    /**
     * Compare deux groupes sur la base de leur numéro unique.
     *
     * @param obj L’objet à comparer
     * @return true si les numéros sont identiques, sinon false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Groupe) {
            Groupe groupe = (Groupe) obj;
            return this.getNumero() == groupe.getNumero();
        } else {
            return false;
        }
    }
}
