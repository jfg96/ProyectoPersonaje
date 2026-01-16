import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 2. Preparamos el teclado

        // --- 1. CREACIÓN DE ARMAS Y OBJETOS ---
        Arma espadaMaestra = new Arma("Espada Maestra", 25.0);
        Arma dagaCristal = new Arma("Daga de Cristal", 12.0);
        Arma colmilloVeneno = new Arma("Colmillo de Veneno", 10.5);
        Pocion pocionVida = new Pocion("Poción de Vida", 50.0);

        // --- 2. CREACIÓN DE PERSONAJES ---
        Personaje link = new Guerrero("Link", 10, 200, espadaMaestra);
        Personaje gandalf = new Mago("Gandalf", 15, 150, 100);
        Personaje ezio = new Picaro("Ezio", 12, 180, 50, dagaCristal, colmilloVeneno);
        Personaje paquete = new Mago("Paquete", 2, 2, 2);

        // Creamos un objetivo para las pruebas de ataque
        Guerrero sacoEntrenamiento = new Guerrero("Saco de Boxeo", 1, 1000, null);

        // --- 3. POLIMORFISMO EN ACCIÓN (SISTEMA DE ATAQUE) ---
        ArrayList<Personaje> equipo = new ArrayList<>();
        equipo.add(link);
        equipo.add(gandalf);
        equipo.add(ezio);
        equipo.add(paquete);

        System.out.println("=== DEMOSTRACIÓN DE ATAQUES (Fase 4) ===");
        for (Personaje p : equipo) {
            p.atacar(sacoEntrenamiento);
        }

        // --- 4. GESTIÓN DEL INVENTARIO (Fase 3) ---
        System.out.println("\n=== GESTIÓN DE OBJETOS CON EZIO ===");
        ezio.setPuntosVida(40);
        ezio.recogerItem(pocionVida);
        ezio.recogerItem(new Arma("Bomba de Humo", 5.0));
        ezio.mostrarInventario();

        System.out.println("\n--- Ezio usa el objeto de la posición 0 ---");
        ezio.usarObjetoDeMochila(0);

        // --- 5. SELECCIÓN DE COMBATE INTERACTIVO ---
        System.out.println("\n=== ¡BIENVENIDO A LA ARENA DE COMBATE! ===");
        System.out.println("Personajes disponibles:");
        for (int i = 0; i < equipo.size(); i++) {
            System.out.println(i + ". " + equipo.get(i).getNombre());
        }

        System.out.print("\nElige el número del luchador 1: ");
        int p1 = sc.nextInt();
        System.out.print("Elige el número del luchador 2: ");
        int p2 = sc.nextInt();

        Personaje luchador1 = equipo.get(p1);
        Personaje luchador2 = equipo.get(p2);

        System.out.println("\n--- COMIENZA EL DUELO: " + luchador1.getNombre() + " VS " + luchador2.getNombre() + " ---");

        // --- 6. BUCLE DE DUELO ---
        while (luchador1.getPuntosVida() > 0 && luchador2.getPuntosVida() > 0) {
            luchador1.atacar(luchador2);
            if (luchador2.getPuntosVida() > 0) {
                luchador2.atacar(luchador1);
            }
        }

        // --- 7. GANADOR DEL DUELO ---
        double vidaMaxima = Math.max(luchador1.getPuntosVida(), luchador2.getPuntosVida());

        System.out.println("\n============================================");
        if (vidaMaxima <= 0) {
            System.out.println("¡EMPATE FATAL! Nadie sobrevivió.");
        } else {
            // Identificamos quién es el dueño de esa vida máxima
            String nombreGanador = (vidaMaxima == luchador1.getPuntosVida()) ? luchador1.getNombre() : luchador2.getNombre();
            System.out.println("¡EL GANADOR ES: " + nombreGanador.toUpperCase() + " con " + vidaMaxima + " PV!");
        }
        System.out.println("============================================");

        sc.close(); 
    }
}