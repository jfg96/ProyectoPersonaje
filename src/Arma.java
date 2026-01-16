public class Arma extends Item {
    // Atributos

    private double danioExtra;

    // Constructor
    public Arma(String nombre, double danioExtra) {
        super(nombre);
        this.danioExtra = danioExtra;
    }

    // Getters y Setters
    public double getDanioExtra() {
        return danioExtra;
    }

    public void setDanioExtra(double danioExtra) {
        this.danioExtra = danioExtra;
    }

    @Override
    public void usarItem(Personaje objetivo) {
        System.out.println(objetivo.getNombre() + " equipa el arma " + getNombre() + " que añade " + danioExtra + " de daño extra.");
    }

    @Override
    public String toString() {
        return "Arma{" +
                "nombre='" + getNombre() + '\'' +
                ", daño=" + danioExtra +
                '}';
    }
}
