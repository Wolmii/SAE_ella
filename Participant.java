public abstract class Participant {
    private static int counter = -1;
    protected int numero;

    public Participant() {
        this.numero = counter++;
    }

}