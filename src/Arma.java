/**
 * Representa un arma que otorga daño extra a ciertos personajes.
 * Gestiona la lógica de equipamiento según la clase del personaje (Guerrero, Pícaro, Mago).
 *
 * @author Javier Fernández Gavino
 * @version 1.0
 */
public class Arma extends Item {
    private double danioExtra;

    /**
     * Crea un arma nueva.
     * @param nombre Nombre del arma.
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
     * - Guerrero: Reemplaza su arma actual (la antigua va al inventario).
     * - Pícaro: Ocupa un hueco libre o reemplaza la principal si ambas manos están llenas.
     * - Mago: No puede equipar armas físicas.
     * * @param objetivo El personaje que intenta equiparse el arma.
     * @return true si se equipó correctamente (desaparece del inventario); false si no se pudo equipar.
     */
    @Override
    public boolean usarItem(Personaje objetivo) {

        // Lógica para Guerrero
        if (objetivo instanceof Guerrero) {
            Guerrero g = (Guerrero) objetivo;
            if (g.getArma() != null) {
                System.out.println("   (Guardando " + g.getArma().getNombre() + " en la mochila...)");
                g.recogerItem(g.getArma());
            }
            g.setArma(this);
            System.out.println(objetivo.getNombre() + " se equipa " + getNombre() + ".");
            return true;
        }

        // Lógica para Pícaro
        else if (objetivo instanceof Picaro) {
            Picaro p = (Picaro) objetivo;

            if (p.getArma1() == null) {
                p.setArma1(this);
                System.out.println(objetivo.getNombre() + " empuña " + getNombre() + " en la mano derecha.");
                return true;
            } else if (p.getArma2() == null) {
                p.setArma2(this);
                System.out.println(objetivo.getNombre() + " empuña " + getNombre() + " en la mano izquierda.");
                return true;
            } else {
                System.out.println("   (Guardando " + p.getArma1().getNombre() + " en la mochila...)");
                p.recogerItem(p.getArma1());
                p.setArma1(this);
                System.out.println(objetivo.getNombre() + " cambia su arma principal por " + getNombre() + ".");
                return true;
            }
        }

        // Lógica para Mago
        else if (objetivo instanceof Mago) {
            System.out.println(objetivo.getNombre() + " no tiene fuerza para usar " + getNombre() + ".");
            return false;
        }

        // Caso por defecto
        else {
            System.out.println(objetivo.getNombre() + " no puede equipar esto.");
            return false;
        }
    }

    @Override
    public String toString() {
        return getNombre() + " [ATQ +" + (int) danioExtra + "]";
    }
}