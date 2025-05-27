public class Personne extends Participant {
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        super();
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }
    public String getPrenom() {
        return this.prenom;
    }
}
