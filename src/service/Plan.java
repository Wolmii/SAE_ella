import java.util.ArrayList;

/**
 * La classe Plan représente un agencement fixe de tables pour un événement de type Gala.
 * Elle gère les actions sur les participants (ajout, suppression, déplacement),
 * ainsi que la vérification de disponibilité des tables.
 */
public class Plan {

    /** Nombre fixe de petites tables dans un plan */
    private final int nbPetiteTable = 10;

    /** Nombre fixe de grandes tables dans un plan */
    private final int nbGrandeTable = 20;

    /** Nombre total de tables attendues dans un plan */
    private final int nbTable = nbPetiteTable + nbGrandeTable;

    /** Le gala auquel ce plan est associé */
    private Gala gala;

    /** Liste des tables composant ce plan (10 petites + 20 grandes) */
    private ArrayList<Table> listTable;

    /**
     * Constructeur de la classe Plan.
     * Vérifie que la liste de tables contient exactement 10 petites et 20 grandes tables.
     *
     * @param gala L'événement Gala auquel ce plan est rattaché
     * @param listTable La liste complète des tables à utiliser dans le plan
     * @throws IllegalArgumentException si le nombre de tables n'est pas conforme
     */
    Plan(Gala gala, ArrayList<Table> listTable) {
        int petiteTableCount = 0;
        int grandeTableCount = 0;

        // Compte les petites et grandes tables
        for (Table t : listTable) {
            if (t instanceof PetiteTable) {
                petiteTableCount++;
            } else if (t instanceof GrandeTable) {
                grandeTableCount++;
            }
        }

        // Vérifie le nombre exact de tables
        if (petiteTableCount != nbPetiteTable) {
            throw new IllegalArgumentException("Un plan compte exactement " + nbPetiteTable + " petites tables");
        }
        if (grandeTableCount != nbGrandeTable) {
            throw new IllegalArgumentException("Un plan compte exactement " + nbGrandeTable + " grandes tables");
        }
        if (listTable.size() != nbTable) {
            throw new IllegalArgumentException("Un plan compte exactement " + nbTable + " tables");
        }

        this.listTable = listTable;
        this.gala = gala;
    }

    /**
     * Ajoute un participant à une table spécifique du plan.
     *
     * @param participant Le participant à ajouter
     * @param table La table ciblée
     */
    public void ajouter(Participant participant, Table table) {
        this.getTable(table).ajouter(participant);
    }

    /**
     * Enlève un participant d'une table spécifique.
     *
     * @param participant Le participant à enlever
     * @param table La table concernée
     */
    public void enlever(Participant participant, Table table) {
        this.getTable(table).enlever(participant);
    }

    /**
     * Supprime une personne d'une table et la supprime aussi du gala.
     *
     * @param personne La personne à Supprimer
     * @param table La table concernée
     */
    public void supprimer(Personne personne, Table table) {
        this.getTable(table).enlever(personne);
        this.gala.supprimer(personne); // Méthode supposée existante dans Gala
    }

    /**
     * Supprime un groupe d'une table et supprime chaque membre du gala.
     *
     * @param groupe Le groupe à supprimer
     * @param table La table concernée
     */
    public void supprimer(Groupe groupe, Table table) {
        this.getTable(table).enlever(groupe);
        for (Personne personne : groupe.getMembres()) {
            this.gala.supprimer(personne); // Méthode supposée existante dans Gala
        }
    }

    /**
     * Déplace un participant d'une table à une autre.
     *
     * @param participant Le participant à déplacer
     * @param ancienneTable La table d'origine
     * @param nouvelleTable La table de destination
     */
    public void deplacer(Participant participant, Table ancienneTable, Table nouvelleTable) {
        this.getTable(ancienneTable).deplacer(participant, nouvelleTable);
    }

    /**
     * Vérifie si une table peut accueillir un nombre de personnes donné.
     *
     * @param table La table ciblée
     * @param nbPersonne Le nombre de personnes à vérifier
     * @return true si la capacité est suffisante, sinon false
     */
    public boolean estDisponible(Table table, int nbPersonne) {
        return this.getTable(table).estDisponible(nbPersonne);
    }

    /**
     * Vérifie si une table a au moins une place disponible.
     *
     * @param table La table ciblée
     * @return true si au moins une place est libre, sinon false
     */
    public boolean estDisponible(Table table) {
        return this.estDisponible(table, 1);
    }

    /**
     * Recherche la table à laquelle est assigné un participant donné par nom et prénom.
     *
     * @param nom Le nom du participant
     * @param prenom Le prénom du participant
     * @return La table où se trouve le participant, ou null s'il n'est pas trouvé
     */
    public Table rechercheParNom(String nom, String prenom) {
        return this.listTable.stream()
            .filter(t -> t.rechercheParNom(nom, prenom))
            .findFirst()
            .orElse(null);
    }

    /**
     * Retourne le gala associé à ce plan.
     *
     * @return L'événement Gala
     */
    public Gala getGala() {
        return this.gala;
    }

    /**
     * Définit le gala auquel ce plan est associé.
     *
     * @param gala Le nouveau gala
     */
    public void setGala(Gala gala) {
        this.gala = gala;
    }

    /**
     * Retourne l'instance exacte de la table contenue dans le plan (selon equals).
     *
     * @param table Une table utilisée comme clé de recherche
     * @return L'objet Table correspondant contenu dans listTable
     */
    private Table getTable(Table table) {
        return this.listTable.get(this.listTable.indexOf(table));
    }
}
