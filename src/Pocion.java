public class Pocion extends Item {
    // Atributos
    private double puntosVidaRecuperar;

    // Constructor
    public Pocion(String nombre, double puntosVidaRecuperar) {
        super(nombre);
        this.puntosVidaRecuperar = puntosVidaRecuperar;
    }

    // Getters y Setters
    public double getPuntosVidaRecuperar() {
        return puntosVidaRecuperar;
    }

    public void setPuntosVidaRecuperar(double puntosVidaRecuperar) {
        this.puntosVidaRecuperar = puntosVidaRecuperar;
    }

    @Override
    public void usarItem(Personaje objetivo) {
        double vidaAntes = objetivo.getPuntosVida();
        objetivo.setPuntosVida(vidaAntes + puntosVidaRecuperar);
        System.out.println(objetivo.getNombre() + " usa la poción " + getNombre() + " y recupera " + (objetivo.getPuntosVida() - vidaAntes) + " puntos de vida.");
    }

    @Override
    public String toString() {
        return "Pocion{" +
                "nombre='" + getNombre() + '\'' +
                ", puntosVidaRecuperar=" + puntosVidaRecuperar +
                '}';
    }
}
