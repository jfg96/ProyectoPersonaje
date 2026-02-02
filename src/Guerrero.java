/**
 * Clase especializada en fuerza y defensa física.
 * Utiliza un arma principal para maximizar su daño.
 *
 * @author Javier Fernández Gavino
 * @version 1.0
 */
public class Guerrero extends Personaje {
    /**
     * Arma principal del guerrero.
     */
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

    /**
     * Obtiene el arma equipada por el guerrero.
     * @return El arma del guerrero.
     */
    public Arma getArma() {
        return arma;
    }

    /**
     * Equipa un arma al guerrero.
     * @param arma El arma a equipar.
     */
    public void setArma(Arma arma) {
        this.arma = arma;
    }

    /**
     * Ataca a otro personaje, infligiendo daño basado en su fuerza y el arma equipada.
     * @param objetivo El personaje que recibirá el ataque.
     */
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

    /**
     * Sube de nivel, mejorando las estadísticas del guerrero.
     * Gana más vida y defensa que otras clases.
     */
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