public class Gala {
    private String nom;
    private Date date;
    private String lieu;

    public Gala(String nom, Date date, String lieu) {
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
    }

    public String getNom() {
        return this.nom;
    }

    public Date getDate() {
        return this.date;
    }

    public String lieu() {
        return this.lieu;
    }
}
