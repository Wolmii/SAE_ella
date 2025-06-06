package app.service;

import java.sql.Date;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // === Création des participants ===
        Personne alice = new Personne("Dupont", "Alice");
        Personne bob = new Personne("Martin", "Bob");
        Personne clara = new Personne("Durand", "Clara");
        Personne david = new Personne("Lemoine", "David");

        // === Création du groupe Clara + David ===
        ArrayList<Personne> membresGroupe = new ArrayList<>();
        membresGroupe.add(clara);
        membresGroupe.add(david);
        Groupe groupe = new Groupe(membresGroupe);

        // === Création des tables ===
        ArrayList<Table> tables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tables.add(new PetiteTable());
        }
        for (int i = 0; i < 20; i++) {
            tables.add(new GrandeTable());
        }

        // === Création du gala et du plan ===
        Gala gala = new Gala("Gala Étudiant", Date.valueOf("2025-12-15"), "Palais des Congrès");
        Plan plan = new Plan(gala, tables);
        plan.setGala(gala);

        // === Ajout des personnes et groupes au gala ===
        gala.ajouter(alice);
        gala.ajouter(bob);
        gala.ajouter(clara);
        gala.ajouter(david);

        // === Ajout aux tables ===
        plan.ajouter(alice, tables.get(0));    // Table 0
        plan.ajouter(bob, tables.get(1));      // Table 1
        plan.ajouter(groupe, tables.get(2));   // Table 2

        // === État initial ===
        System.out.println("=== ÉTAT INITIAL ===");
        System.out.println("Table 0 (Alice) disponible pour 1 personne ? " + plan.estDisponible(tables.get(0)));
        System.out.println("Table 1 (Bob) disponible ? " + plan.estDisponible(tables.get(1)));
        System.out.println("Table 2 (Groupe Clara+David) disponible pour 2 personnes ? " + plan.estDisponible(tables.get(2), 2));

        boolean bobATable1 = plan.rechercheParNom("Martin", "Bob").equals(tables.get(1));
        boolean claraATable2 = plan.rechercheParNom("Durand", "Clara").equals(tables.get(2));

        System.out.println("Bob est bien à la table 1 ? " + bobATable1);           // attendu : true
        System.out.println("Clara est bien à la table 2 ? " + claraATable2);       // attendu : true

        // === Déplacement de Bob vers table 3 ===
        plan.deplacer(bob, tables.get(1), tables.get(3));
        System.out.println("\nDéplacement de Bob vers la table 3 effectué.");

        // Vérification post-déplacement
        boolean bobATable3 = plan.rechercheParNom("Martin", "Bob").equals(tables.get(3));
        System.out.println("Bob est maintenant à la table 3 ? " + bobATable3);     // attendu : true

        // === Suppression de Clara ===
        Table tableDeClara = plan.rechercheParNom("Durand", "Clara");
        plan.enlever(clara, tableDeClara);
        System.out.println("\nClara supprimée du plan et du gala.");

        boolean claraEstDansLePlan = plan.rechercheParNom("Durand", "Clara") != null;
        System.out.println("Clara est toujours présente ? " + claraEstDansLePlan); // attendu : false

        // === Suppression du groupe ===
        plan.enlever(groupe, tables.get(2));
        System.out.println("Groupe supprimé du plan et du gala.");

        boolean davidEstDansLePlan = plan.rechercheParNom("Lemoine", "David") != null;
        System.out.println("David (du groupe supprimé) est toujours présent ? " + davidEstDansLePlan); // attendu : false

        // === Résumé final ===
        System.out.println("\n=== ÉTAT FINAL ===");
        System.out.println("Alice est à la table : " + plan.rechercheParNom("Dupont", "Alice").getNumero()); // attendu : table 0
        System.out.println("Bob est à la table : " + plan.rechercheParNom("Martin", "Bob").getNumero());     // attendu : table 3
    }
}
