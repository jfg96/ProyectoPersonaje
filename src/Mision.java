/**
 * Clase abstracta que representa una misión y sus características.
 * Para gestionar misiones específicas, se debe usar la clase GestorMisiones.
 *
 * @author Juan María Alanís Rodríguez
 * @version 1.2
 */
public abstract class Mision {

    // Atributos
    /**
     *  Título de la misión.
     */
    private String titulo;
    /**
     * Descripción de la misión.
     */
    private String descripcion;
    /**
     * Cantidad objetivo para completar la misión.
     */
    private int objetivoCantidad;
    /**
     * Progreso actual de la misión.
     */
    private int progresoActual;
    /**
     * Experiencia otorgada al completar la misión.
     */
    private int xpRecompensa;
    /**
     * Objeto otorgado como recompensa al completar la misión.
     */
    private Item itemRecompensa;
    /**
     * Estado actual de la misión.
     */
    private EstadoMision estado;
    /**
     * Sala asociada a la misión.
     */
    private Sala sala;

    // Constructores
    /**
     * Crea una misión con los detalles proporcionados.
     *
     * @param titulo      Título de la misión.
     * @param descripcion Descripción de la misión.
     * @param objetivo    Cantidad objetivo para completar la misión.
     * @param xp          Experiencia otorgada al completar la misión.
     * @param item        Objeto otorgado como recompensa al completar la misión.
     */
    public Mision(String titulo, String descripcion, int objetivo, int xp, Item item, Sala sala) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.objetivoCantidad = objetivo;
        this.xpRecompensa = xp;
        this.itemRecompensa = item;
        this.estado = EstadoMision.DISPONIBLE;
        this.progresoActual = 0;
        this.sala = sala;
    }

    // Getters
    public String getTitulo() {
        return this.titulo;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    public int getObjetivoCantidad() {
        return this.objetivoCantidad;
    }
    public int getProgresoActual() {
        return this.progresoActual;
    }
    public int getXpRecompensa() {
        return this.xpRecompensa;
    }
    public Item getItemRecompensa() {
        return this.itemRecompensa;
    }
    public EstadoMision getEstado() {
        return this.estado;
    }
    public Sala getSala() {
        return this.sala;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setProgresoActual(int progreso) {
        this.progresoActual = progreso;
    }
    public void setObjetivoCantidad(int objetivoCantidad) {
        this.objetivoCantidad = objetivoCantidad;
    }
    public void setXpRecompensa(int xpRecompensa) {
        this.xpRecompensa = xpRecompensa;
    }
    public  void setItemRecompensa(Item itemRecompensa) {
        this.itemRecompensa = itemRecompensa;
    }
    public void setEstado(EstadoMision estado) {
        this.estado = estado;
    }
    public  void setSala(Sala sala) {
        this.sala = sala;
    }

    // Métodos
    //
    /**
     * Actualiza el progreso de la misión basado en los datos proporcionados.
     * Se requiere implementación específica en las subclases.
     * -
     * Solo ha de ser sobreescrita por las clases que heredan de Misión.
     *
     * @param datos Datos relevantes para actualizar el progreso.
     */
    public abstract void actualizarProgreso(Object datos);

    //
    // ------- Métodos que se usan UNICAMENTE para la clase GestorMisiones -------
    //
    /**
     * Verifica si la misión está lista para entregar la recompensa.
     * -
     * Aplicable solo en clase GestorMisiones.
     *
     * @return true si la misión está completa y lista para entregar, false en caso contrario.
     */
    protected boolean estaListaParaEntregar() {
        return (progresoActual >= objetivoCantidad) &&
                (estado == EstadoMision.EN_CURSO || estado == EstadoMision.COMPLETADA);
    }
    /**
     * Entrega la recompensa al jugador si la misión está completa.
     * -
     * Aplicable solo en clase GestorMisiones.
     *
     * @param jugador Personaje que recibe la recompensa.
     */
    protected void entregarRecompensa(Personaje jugador) {
        if (estaListaParaEntregar()) {
            System.out.println("\n[MISION COMPLETADA: " + titulo + "]");
            jugador.ganarExperiencia(xpRecompensa);
            if (itemRecompensa != null) {
                jugador.recogerItem(itemRecompensa);
                System.out.println("   [+] Has recibido: " + itemRecompensa.getNombre());
            }
            this.estado = EstadoMision.RECOMPENSA_ENTREGADA;
        }
    }
    /**
     * Acepta la misión para el jugador.
     * -
     * Aplicable solo en clase GestorMisiones.
     *
     * @param jugador Personaje que acepta la misión.
     */
    protected void aceptar(Personaje jugador) {
        if (this.estado == EstadoMision.DISPONIBLE) {
            this.estado = EstadoMision.EN_CURSO;
            System.out.println("\n[!] Has aceptado la misión: " + titulo);
        }
    }
    /**
     * Procesa un evento relevante para la misión.
     * -
     * Aplicable solo en clase GestorMisiones.
     *
     * @param evento Evento que ocurrió en el juego.
     */
    protected void procesarEvento(Object evento) {
        if (this.estado == EstadoMision.EN_CURSO) {
            actualizarProgreso(evento);
            if (progresoActual >= objetivoCantidad)
                this.estado = EstadoMision.COMPLETADA;
        }
    }
}
