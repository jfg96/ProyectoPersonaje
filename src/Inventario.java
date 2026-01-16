import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> listaItems;

    public Inventario() {
        this.listaItems = new ArrayList<>();
    }

    public void anadirItem(Item i) {
        listaItems.add(i);
    }


    public void mostrarContenido() {
        if (listaItems.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("Contenido del inventario:");
            for (int i = 0; i < listaItems.size(); i++) {
                System.out.println((i + ". " + listaItems.get(i).toString()));


            }
        }
    }

    public void usarYConsumir(int indice, Personaje objetivo) {

        if (indice >= 0 && indice < listaItems.size()) {
            Item item = listaItems.get(indice);
            item.usarItem(objetivo);
            if (item instanceof Pocion) {
                listaItems.remove(indice); // Lo borramos de la lista
                System.out.println("El objeto " + item.getNombre() + " se ha consumido.");
            }
        } else {
            System.out.println("No hay ningún objeto en la posición " + indice);
        }
    }

}