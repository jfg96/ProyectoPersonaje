public abstract class Item {
    // Atributos
    private String nombre;

    // Constructor
    public Item(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract void usarItem(Personaje objetivo);

    @Override
    public String toString() {
        return nombre;
    }
}
