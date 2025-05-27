import java.util.ArrayList;

public class Groupe extends Participant {

    private ArrayList<Personne> membres = new ArrayList<Personne>();

    public Groupe(ArrayList<Personne> membres) {
        super();
        if (membres.size() < 2) {
            throw new IllegalArgumentException("Un groupe doit être composé d'au moins 2 personnes");
        } else {
            this.membres.addAll(membres);
        }
    }

    public void ajouter(Personne personne) {
        this.membres.add(personne);
    }
}