package app.service;

import java.util.ArrayList;

/**
 * Classe abstraite représentant une table dans un plan de salle.
 * Chaque table possède un numéro unique, une capacité, et une liste de participants.
 * 
 * Les sous-classes (comme PetiteTable, GrandeTable) doivent hériter de cette classe.
 */
public abstract class Table implements HasNumero {

    /** Numéro unique de la table (généré automatiquement par type de classe) */
    protected final int numero;

    /** Capacité maximale de la table (nombre de places disponibles) */
    private int capacite;

    /** Liste des participants actuellement assis à cette table */
    private ArrayList<Participant> listParticipant = new ArrayList<Participant>();

    /**
     * Constructeur de la classe Table.
     * Initialise la capacité et génère automatiquement un numéro unique par type de table.
     *
     * @param capacite La capacité maximale de la table
     */
    Table(int capacite){
        this.numero = PerClassCounter.getNextNumero(this.getClass());
        this.capacite = capacite;
    }

    /**
     * Ajoute un participant à cette table.
     *
     * @param participant Le participant à ajouter
     */
    public void ajouter(Participant participant) {
        this.listParticipant.add(participant);
    }

    /**
     * Enlève un participant de cette table.
     *
     * @param participant Le participant à enlever
     */
    public void enlever(Participant participant) {
        for (Participant p : this.listParticipant) {
            if (p instanceof Groupe) {
                if (((Groupe) p).getMembres().contains(participant)) {
                    ((Groupe)p).supprimer((Personne) participant);
                    return;
                }
            }
        }
        this.listParticipant.remove(participant);
    }

    /**
     * Déplace un participant de cette table vers une autre table.
     *
     * @param participant Le participant à déplacer
     * @param table La table de destination
     */
    public void deplacer(Participant participant, Table table) {
        this.enlever(participant);
        table.ajouter(participant);
    }

    /**
     * Vérifie si cette table peut accueillir un certain nombre de personnes supplémentaires.
     *
     * @param nb Le nombre de personnes à accueillir
     * @return true si la place est suffisante, false sinon
     */
    public boolean estDisponible(int nb) {
        int nbPersonne = 0;
        for (Participant participant : this.listParticipant) {
            if (participant instanceof Personne) {
                nbPersonne++;
            } else if (participant instanceof Groupe) {
                nbPersonne += ((Groupe) participant).getMembres().size();
            }
        }
        return nb <= (this.capacite - nbPersonne);
    }

    /**
     * Recherche une personne (nom + prénom) parmi les participants à la table,
     * y compris dans les groupes.
     *
     * @param nom Le nom de la personne recherchée
     * @param prenom Le prénom de la personne recherchée
     * @return true si une personne correspondante est trouvée, false sinon
     */
    public boolean rechercheParNom(String nom, String prenom) {
        ArrayList<Personne> listPersonne = new ArrayList<Personne>();

        // Recherche directe des Personne individuelles
        this.listParticipant.stream()
            .filter(p -> p.getClass() == Personne.class)
            .forEach(p -> listPersonne.add((Personne) p));

        // Recherche parmi les membres des groupes
        this.listParticipant.stream()
            .filter(p -> p.getClass() == Groupe.class)
            .forEach(g -> listPersonne.addAll(((Groupe) g).getMembres()));

        // Vérifie s'il existe une personne avec le nom et prénom donnés
        return listPersonne.stream()
            .anyMatch(p -> p.getNom().equals(nom) && p.getPrenom().equals(prenom));
    }

    /**
     * Retourne le numéro unique de cette table.
     *
     * @return Le numéro de la table
     */
    public int getNumero() {
        return this.numero;
    }

    /**
     * Retourne la capacité maximale de cette table.
     *
     * @return La capacité de la table
     */
    public int getCapacite() {
        return this.capacite;
    }

    /**
     * Retourne la liste des participants actuellement présents à la table.
     *
     * @return Liste des participants
     */
    public ArrayList<Participant> getListParticipant() {
        return this.listParticipant;
    }

    /**
     * Redéfinition de la méthode equals basée sur le numéro de table.
     * Deux tables sont considérées égales si elles ont le même numéro.
     *
     * @param obj L'objet à comparer
     * @return true si les tables ont le même numéro, false sinon
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Table) {
            Table table = (Table) obj;
            return this.getNumero() == table.getNumero();
        } else {
            return false;
        }
    }
}
