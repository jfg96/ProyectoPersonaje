public class Main {

    public static void main(String[] args) {
        System.out.println("=== INICIO DE LAS PRUEBAS PERSONAJES ===\n");

        // 1. CREACIÓN DE PERSONAJES
        System.out.println("--- 1. Creando Héroes y Enemigos ---");
        Arma espadaOxidada = new Arma("Espada Oxidada", 5);
        Guerrero conan = new Guerrero("Conan", espadaOxidada);
        Mago merlin = new Mago("Merlín");

        // Creamos un enemigo de nivel 2
        Personaje trasgo = new Enemigo("Trasgo Feo", 2);

        System.out.println("Héroe creado: " + conan.getNombre() + " (HP: " + conan.getPuntosVida() + ")");
        System.out.println("Héroe creado: " + merlin.getNombre() + " (Mana: 100)");
        System.out.println("Enemigo aparece: " + trasgo.getNombre() + " (HP: " + trasgo.getPuntosVida() + ")\n");

        // 2. PRUEBA DE COMBATE Y POLIMORFISMO
        System.out.println("--- 2. Prueba de Combate (Polimorfismo) ---");
        // El guerrero ataca (usa fuerza + arma)
        conan.atacar(trasgo);

        // El mago ataca (usa maná + inteligencia)
        merlin.atacar(trasgo);

        // El enemigo contraataca (IA básica)
        if (trasgo.estaVivo()) {
            trasgo.atacar(conan);
        }
        System.out.println("");

        // 3. PRUEBA DE INVENTARIO Y OBJETOS
        System.out.println("--- 3. Gestión de Inventario y Loot ---");
        // Simulamos que encontramos una poción en el suelo
        Pocion pocionVida = new Pocion("Poción Roja", 50, TipoPocion.VIDA);

        // Conan la recoge
        conan.recogerItem(pocionVida);
        conan.mostrarInventario();

        // Conan recibe daño para probar la curación
        System.out.println(conan.getNombre() + " se tropieza y pierde vida...");
        conan.setPuntosVida(10); // Le bajamos la vida artificialmente
        System.out.println("Vida actual: " + conan.getPuntosVida());

        // Usamos la poción (está en el índice 0 del inventario)
        System.out.println("Usando objeto del inventario...");
        conan.usarObjetoDeMochila(0);
        System.out.println("Vida después de poción: " + conan.getPuntosVida());
        conan.mostrarInventario(); // Debería estar vacío
        System.out.println("");

        // 4. PRUEBA DE EQUIPAMIENTO (LOGICA DE CLASE)
        System.out.println("--- 4. Restricciones de Clase (Armas) ---");
        Arma baculo = new Arma("Bastón Supremo", 20);

        // El mago intenta equiparlo (Fallará porque Arma dice que Mago no puede, o no tiene lógica específica y devuelve false)
        // Nota: Según tu código, Mago devuelve false en Arma.java.
        System.out.println("Merlín intenta equipar un arma física:");
        merlin.recogerItem(baculo);
        merlin.usarObjetoDeMochila(0); // Intentar equipar

        // El guerrero encuentra una espada mejor
        Arma excalibur = new Arma("Excalibur", 50);
        System.out.println("\nConan encuentra " + excalibur.getNombre());
        conan.recogerItem(excalibur);
        // Al usarla, debería equiparse Excalibur y guardar la Espada Oxidada en la mochila
        conan.usarObjetoDeMochila(0);
        System.out.println("Arma actual de Conan: " + conan.getArma().getNombre());
        System.out.println("Inventario de Conan (debería tener la vieja):");
        conan.mostrarInventario();
        System.out.println("");

        // 5. PRUEBA DE EXPERIENCIA Y LEVEL UP
        System.out.println("--- 5. Subida de Nivel ---");
        // Le damos XP suficiente para subir
        System.out.println("Conan mata al Dragón (Cheat de XP)...");
        conan.ganarExperiencia(200); // Necesita 100 para nivel 2

        System.out.println("Nivel actual: " + conan.getNivel());
        System.out.println("Vida Máxima (aumentada): " + conan.getPuntosVidaMax());
        System.out.println("Defensa (aumentada): " + conan.getDefensa());

        System.out.println("\n=== PRUEBAS FINALIZADAS CON ÉXITO ===");
    }
}