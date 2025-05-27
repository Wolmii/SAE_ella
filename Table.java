public abstract class Table {
    private static int index = 0;
    private int numero;
    private int capacite;

    Table(int capacite){
        this.capacite = capacite;
        this.numero = index++;
    }

    public boolean verifierDisponibilite(){
        return true;
    }
}
