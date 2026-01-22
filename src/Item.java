/**
 * Clase abstracta que representa cualquier objeto que un personaje pueda poseer.
 * Define la estructura básica para armas y pociones.
 *
 * @author Javier Fernández Gavino
 * @version 1.0
 */
public abstract class Item {
    private String nombre;

    /**
     * Constructor del ítem.
     * @param nombre El nombre identificativo del objeto.
     */
    public Item(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método abstracto que define el uso del objeto sobre un personaje.
     * @param objetivo El personaje sobre el que se usa el objeto.
     * @return true si el objeto fue consumido o equipado con éxito; false si no tuvo efecto.
     */
    public abstract boolean usarItem(Personaje objetivo);

    @Override
    public String toString() {
        return nombre;
    }
}