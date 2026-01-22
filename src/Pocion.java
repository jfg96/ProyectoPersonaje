/**
 * Item consumible que restaura estadísticas del personaje.
 * Puede ser de tipo "VIDA" o "MANA".
 *
 * @author Javier Fernández Gavino
 * @version 1.0
 */
public class Pocion extends Item {
    private double cantidad;
    private String tipo; // "VIDA" o "MANA"

    /**
     * Crea una poción.
     * @param nombre Nombre (ej: "Poción Menor").
     * @param cantidad Puntos que restaura.
     * @param tipo Tipo de efecto ("VIDA" o "MANA").
     */
    public Pocion(String nombre, double cantidad, String tipo) {
        super(nombre);
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    /**
     * Usa la poción sobre un personaje.
     * - VIDA: Cura a cualquier personaje.
     * - MANA: Solo funciona en Magos.
     * @param objetivo Personaje que consume la poción.
     * @return true si la poción tuvo efecto (y debe gastarse), false si no.
     */
    @Override
    public boolean usarItem(Personaje objetivo) {
        if (tipo.equalsIgnoreCase("VIDA")) {
            double vidaAntes = objetivo.getPuntosVida();
            objetivo.setPuntosVida(vidaAntes + cantidad);
            System.out.println(objetivo.getNombre() + " bebe " + getNombre() + " y se cura.");
            return true; // ÉXITO
        }
        else if (tipo.equalsIgnoreCase("MANA")) {
            if (objetivo instanceof Mago) {
                Mago m = (Mago) objetivo;
                m.recuperarMana(cantidad);
                System.out.println(objetivo.getNombre() + " bebe una poción de maná.");
                return true; // ÉXITO
            } else {
                System.out.println(objetivo.getNombre() + " intenta beber el maná pero no tiene efecto (No es mago).");
                return false; // FALLO: No gastar
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getNombre() + " (+" + (int) cantidad + " " + tipo + ")";
    }
}