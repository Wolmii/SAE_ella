class Date{
    private Date date_debut;
    private Date date_cloture;

    Date(Date d, Date c){
        this.date_cloture=c;
        this.date_debut=d;
    }
}

class Gala{
    private Date date;
    private String nom;
    private String lieu;

    Gala(Date d, String n, String l){
        this.date=d;
        this.nom=n;
        this.lieu=l;
    }

    public Date getDate(){
        return this.date;
    }

    public String getNom(){
        return this.nom;
    }

    public String getLieu(){
        return this.lieu;
    }
}

class Plan{
    static int index;
    static int in=0;

    Plan(){
        index=in;
        in++;
    }

    Plan(int i){
        index=i;
    }

    public void ajouter(Personne pers, Table tbl){

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

class Table{
    private int numero;
    private int capacite;
    static int num=1;

    Table(int nb){
        this.capacite=nb;
        this.numero=num;
        num++;
    }

    public Boolean verifierDisponibilite(){
        Boolean res=false;
        return res;
    }
}

class GrandeTable extends Table{

    GrandeTable(){
        this.super(12);
    }

}

class PetiteTable extends Table{
    
    PetiteTable(){
        this.super(6);
    }
}

class GroupePersonne{
    private int nbPersonne;
    private int index;
    static int num=0;

    GroupePersonne(int nb){
        this.nbPersonne=nb;
        this.index=num;
        num++;
    }
}

class Personne{
    private String nom;
    private String prenom;
    private int index;
    static int num=0;

    Personne(String n, String p){
        this.nom=n;
        this.prenom=p;
        this.index=num;
        num++;
    }

    public String getNom(){
        return this.nom;
    }

    public String getPrenom(){
        return this.prenom;
    }
}
