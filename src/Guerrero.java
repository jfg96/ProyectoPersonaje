/**
 * Clase especializada en fuerza y defensa física.
 * Utiliza un arma principal para maximizar su daño.
 *
 * @author Javier Fernández Gavino
 * @version 1.0
 */
public class Guerrero extends Personaje {
    private Arma arma;

    /**
     * Crea un guerrero. Comienza siempre a nivel 1.
     * @param nombre Nombre del personaje.
     * @param arma Arma inicial.
     */
    public Guerrero(String nombre, Arma arma) {
        super(nombre, 1, 100, 5);
        this.arma = arma;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    @Override
    public void atacar(Personaje objetivo) {
        if (!this.estaVivo()) return;

        int fuerza = 15 + (3 * getNivel());
        int danioTotal = fuerza;

        if (this.arma != null) {
            danioTotal += this.arma.getDanioExtra();
            System.out.println(getNombre() + " ataca con " + arma.getNombre() + ".");
        } else {
            System.out.println(getNombre() + " golpea con los puños.");
        }
        objetivo.recibirDanio(danioTotal);
    }

    @Override
    public void subirNivel() {
        super.subirNivel();
        // El guerrero escala mejor en vida (+25) y defensa (+2)
        setPuntosVidaMax(getPuntosVidaMax() + 25);
        setPuntosVida(getPuntosVidaMax());
        setDefensa(getDefensa() + 2);
        System.out.println("¡" + getNombre() + " se siente más resistente! (+Vida, +Defensa)");
    }
}