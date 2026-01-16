public class Guerrero extends Personaje {
    //Atributos
    private Arma arma;

    //Constructor
    public Guerrero(String nombre, int nivel, double puntosVidaMax, Arma arma) {
        super(nombre, nivel, puntosVidaMax);
        this.arma = arma;
    }

    //Getters y Setters
    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    @Override
    public void atacar(Personaje objetivo) {
        double danioTotal = ((arma != null) ? arma.getDanioExtra() : 0) + (4 * getNivel());

        System.out.println(getNombre() + " golpea a " + objetivo.getNombre() + " con su "
                + (arma != null ? arma.getNombre() : "fuerza bruta") + " causando un impacto brutal.");
        objetivo.recibirDanio(danioTotal);
    }
}
