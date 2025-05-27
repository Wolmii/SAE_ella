import java.util.ArrayList;

public abstract class Table {
    private static int counter = -1;
    protected int numero;
    private int capacite;
    private ArrayList<Participant> listParticipant = new ArrayList<Participant>();

    Table(int capacite){
        this.numero = counter++;
        this.capacite = capacite;
    }

    public boolean verifierDisponibilite(int nb) {
        return nb <= (this.capacite - this.listParticipant.size());
    }

    public void ajouter(Participant participant) {
        this.listParticipant.add(participant);
    }

    public void supprimer(Participant participant) {
        this.listParticipant.remove(participant);
    }

    public void deplacer(Participant participant, Table table) {
        this.listParticipant.add(
            this.listParticipant.remove(this.listParticipant.indexOf(participant))
        );
    }
}
