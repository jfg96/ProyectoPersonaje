/**
 * Subclase de Personaje especializada en el uso de magia.
 * Utiliza 'Maná' como recurso para realizar ataques potentes.
 *
 * @author Javier Fernández Gavino
 * @version 1.0
 */
public class Mago extends Personaje {
    /**
     * Puntos de maná actuales del mago.
     */
    private double puntosMana;

    /**
     * Puntos de maná máximos del mago.
     */
    private double puntosManaMax;

    /**
     * Crea un Mago a nivel 1 con estadísticas base bajas pero capacidad mágica.
     * @param nombre Nombre del mago.
     */
    public Mago(String nombre) {
        super(nombre, 1, 80, 1); // Vida baja, defensa baja
        this.puntosManaMax = 100;
        this.puntosMana = 100;
    }

    /**
     * Obtiene los puntos de maná actuales.
     * @return Puntos de maná.
     */
    public double getPuntosMana() { return puntosMana; }

    /**
     * Establece los puntos de maná actuales, asegurando que no excedan el máximo ni sean negativos.
     * @param puntosMana Nuevos puntos de maná.
     */
    public void setPuntosMana(double puntosMana) {
        this.puntosMana = Math.max(0, Math.min(puntosManaMax, puntosMana));
    }

    /**
     * Restaura puntos de maná sin exceder el máximo.
     * @param cantidad Cantidad de maná a recuperar.
     */
    public void recuperarMana(double cantidad) {
        double manaAntes = this.puntosMana;
        this.puntosMana += cantidad;
        if (this.puntosMana > this.puntosManaMax) this.puntosMana = this.puntosManaMax;

        System.out.println(getNombre() + " recupera " + (int)(this.puntosMana - manaAntes) + " maná.");
    }

    /**
     * Ataque especial del mago que consume maná para infligir daño.
     * Si no hay suficiente maná, realiza un ataque débil.
     * @param objetivo El personaje que recibe el ataque.
     */
    @Override
    public void atacar(Personaje objetivo) {
        if (!this.estaVivo()) return;

        if (puntosMana >= 10) {
            int inteligencia = 35 + (5 * getNivel());
            puntosMana -= 10;
            System.out.println(getNombre() + " lanza una bola de fuego! (Maná restante: " + (int)puntosMana + ")");
            objetivo.recibirDanio(inteligencia);
        } else {
            System.out.println(getNombre() + " no tiene maná y golpea torpemente con su bastón.");
            objetivo.recibirDanio(2 + getNivel());
        }
    }

    /**
     * Sube de nivel al mago, aumentando su vida y maná máximos.
     */
    @Override
    public void subirNivel() {
        super.subirNivel();
        setPuntosVidaMax(getPuntosVidaMax() + 10);
        setPuntosVida(getPuntosVidaMax());
        this.puntosManaMax += 25;
        this.puntosMana = this.puntosManaMax;
        System.out.println("¡" + getNombre() + " aumenta su poder arcano! (+Maná)");
    }

    /**
     * Descansa para recuperar vida y maná completamente.
     */
    @Override
    public void descansar() {
        super.descansar(); // Recupera vida
        this.puntosMana = this.puntosManaMax; // Recupera maná completamente
        System.out.println("   [Mago]: Maná restaurado.");
    }
}