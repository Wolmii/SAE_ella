import java.util.ArrayList;

public class Plan {

    private ArrayList<ArrayList<Table>> listTable = new ArrayList<ArrayList<Table>>();

    Plan(){
        this.listTable.add(new ArrayList<PetiteTable>());
    }

    public void ajouter(Personne personne, Table table){
        this.listTable.get(table).ajouter(personne);
    }

    public void ajouter(GroupePersonne grp, Table tbl){

    }

    public void supprimer(Personne pers, Table tbl){

    }

    public void supprimer(GroupePersonne grp, Table tbl){

    }

    public void enlever(Personne pers, Table tbl){

    }

    public void enlever(GroupePersonne grp, Table tbl){

    }

    public void deplacer(Personne pers, Table tbl){

    }

    public void deplacer(GroupePersonne grp, Table tbl){

    }

    public boolean verifierDisponibilite(Table tbl){
        boolean verif=false;
        return verif;
    }

    public Table rechercheParNom(String nom, String prenom){
        Table tab;
        return tab;
    }
}