/**
 * Representa un arma que otorga daño extra a ciertos personajes.
 * Gestiona la lógica de equipamiento según la clase del personaje (Guerrero, Pícaro, Mago).
 *
 * @author Javier Fernández Gavino
 * @version 2.0
 */
public class Arma extends Item {
    private double danioExtra;

    /**
     * Crea un arma nueva.
     *
     * @param nombre     Nombre del arma.
     * @param danioExtra Puntos de daño que se sumarán al ataque base.
     */
    public Arma(String nombre, double danioExtra) {
        super(nombre);
        this.danioExtra = danioExtra;
    }

    // --- Getters y Setters ---
    public double getDanioExtra() {
        return this.danioExtra;
    }

    public void setDanioExtra(double danioExtra) {
        this.danioExtra = danioExtra;
    }

    /**
     * Intenta equipar el arma en el personaje objetivo.
     * Utiliza Pattern Matching de Java moderno para delegar la lógica.
     *
     * @param objetivo El personaje que intenta equiparse el arma.
     * @return true si se equipó correctamente; false si no.
     */
    @Override
    public boolean usarItem(Personaje objetivo) {
        return switch (objetivo) {
            case Guerrero g -> equiparGuerrero(g);
            case Picaro p   -> equiparPicaro(p);
            case Mago m     -> equiparMago(m);
            default         -> {
                System.out.println(objetivo.getNombre() + " no puede equipar esto.");
                yield false;
            }
        };
    }

    // --- MÉTODOS PRIVADOS DE LÓGICA ESPECÍFICA ---

    /**
     * Lógica para equipar un arma en un Pícaro.
     * @param p El pícaro que intenta equiparse el arma.
     * @return true si se equipó correctamente; false si no.
     */
    private boolean equiparPicaro(Picaro p) {
        if (p.getArma1() == null) {
            p.setArma1(this);
            System.out.println(p.getNombre() + " empuña " + getNombre() + " en la mano derecha.");
            return true;
        } else if (p.getArma2() == null) {
            p.setArma2(this);
            System.out.println(p.getNombre() + " empuña " + getNombre() + " en la mano izquierda.");
            return true;
        } else {
            System.out.println("   (Guardando " + p.getArma1().getNombre() + " en la mochila...)");
            p.recogerItem(p.getArma1());
            p.setArma1(this);
            System.out.println(p.getNombre() + " cambia su arma principal por " + getNombre() + ".");
            return true;
        }
    }

    /**
     * Lógica para equipar un arma en un Guerrero.
     * @param g El guerrero que intenta equiparse el arma.
     * @return true si se equipó correctamente; false si no.
     */
    private boolean equiparGuerrero(Guerrero g) {
        if (g.getArma() != null) {
            System.out.println("   (Guardando " + g.getArma().getNombre() + " en la mochila...)");
            g.recogerItem(g.getArma());
        }
        g.setArma(this);
        System.out.println(g.getNombre() + " se equipa " + getNombre() + ".");
        return true;
    }
    /**
     * Lógica para equipar un arma en un Mago.
     * @param m El mago que intenta equiparse el arma.
     * @return false siempre, ya que los magos no pueden usar armas.
     */
    private boolean equiparMago(Mago m) {
        System.out.println(m.getNombre() + " no tiene fuerza para usar " + getNombre() + ".");
        return false;
    }

    /**
     * Representación en cadena del arma.
     * @return Cadena con el nombre del arma y su bonificación de daño.
     */
    @Override
    public String toString() {
        return getNombre() + " [ATQ +" + (int) danioExtra + "]";
    }
}