import java.util.ArrayList;

public class Plan {

    private final int nbPetiteTable = 10;
    private final int nbGrandeTable = 20;
    private final int nbTable = nbPetiteTable + nbGrandeTable;
    private ArrayList<Table> listTable = new ArrayList<Table>(nbTable);

    Plan(){
        for (int i = 0; i < nbPetiteTable; i++) {
            this.listTable.add(new PetiteTable());
        }
        for (int i = 0; i < nbGrandeTable; i++) {
            this.listTable.add(new GrandeTable());
        }
    }

    public void ajouter(Participant participant, Table table){
        this.listTable.get(this.listTable.indexOf(table)).ajouter(participant);
    }

    public void supprimer(Participant participant, Table table){

    }

    public void deplacer(Participant participant, Table table){

    }

    public boolean verifierDisponibilite(Table table){
        boolean verif=false;
        return verif;
    }

    public Table rechercheParNom(String nom, String prenom){
        Table tab;
        return tab;
    }
}