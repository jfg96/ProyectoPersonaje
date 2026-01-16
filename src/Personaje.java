public abstract class Personaje {
    // Atributos
    private String nombre;
    private int nivel;
    private double puntosVida;
    private double puntosVidaMax;
    private Inventario inventario;


    // Constructor
    public Personaje(String nombre, int nivel, double puntosVidaMax) {
        this.nombre = nombre;
        setNivel(nivel);
        setPuntosVidaMax(puntosVidaMax);
        this.puntosVida = this.puntosVidaMax; // Inicialmente, los puntos de vida son máximos
        this.inventario = new Inventario();
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = Math.max(1, Math.min(80, nivel));

    }

    public double getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(double puntosVida) {
        this.puntosVida = Math.max(0, Math.min(puntosVidaMax, puntosVida));
    }

    public double getPuntosVidaMax() {
        return puntosVidaMax;
    }

    public void setPuntosVidaMax(double puntosVidaMax) {
        this.puntosVidaMax = Math.max(50, Math.min(5000, puntosVidaMax));
        if (this.puntosVida > this.puntosVidaMax) {
            this.puntosVida = this.puntosVidaMax;
        }
    }

    public abstract void atacar(Personaje objetivo);

    public void recogerItem(Item item) {
        inventario.anadirItem(item);
        System.out.println(nombre + " ha recogido el item: " + item.getNombre());
    }

    public void usarObjetoDeMochila(int indice) {
        this.inventario.usarYConsumir(indice, this);
    }

    public void mostrarInventario() {
        System.out.println("Inventario de " + nombre + ":");
        inventario.mostrarContenido();
    }

    public void recibirDanio(double danio) {
        setPuntosVida(puntosVida - danio);
        System.out.println(nombre + " ha recibido " + danio + " puntos de daño. Puntos de vida restantes: " + puntosVida);
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "nombre='" + nombre + '\'' +
                ", nivel=" + nivel +
                ", puntosVida=" + puntosVida +
                ", puntosVidaMax=" + puntosVidaMax +
                '}';
    }
}
