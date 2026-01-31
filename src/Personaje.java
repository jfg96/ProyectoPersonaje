    /**
     * Clase base abstracta que representa a cualquier entidad viva del juego.
     * Gestiona estadísticas comunes (vida, nivel, experiencia) e inventario.
     *
     * @author Javier Fernández Gavino
     * @version 1.0
     */
    public abstract class Personaje {
        private String nombre;
        private int nivel;
        private double puntosVida;
        private double puntosVidaMax;
        private int defensa;
        private Inventario inventario;
        private int experiencia;
        private int experienciaNecesaria;

        /**
         * Constructor base para inicializar un personaje.
         *
         * @param nombre        Nombre del personaje.
         * @param nivel         Nivel inicial.
         * @param puntosVidaMax Vida máxima inicial.
         * @param defensa       Puntos de defensa (mitigación de daño).
         */
        public Personaje(String nombre, int nivel, double puntosVidaMax, int defensa) {
            this.nombre = nombre;
            setNivel(nivel);
            this.puntosVidaMax = puntosVidaMax;
            this.puntosVida = this.puntosVidaMax;
            this.defensa = defensa;
            this.inventario = new Inventario();
            this.experiencia = 0;
            this.experienciaNecesaria = nivel * 100;
        }

        // --- Getters y Setters ---

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
            this.experienciaNecesaria = this.nivel * 100;
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
            this.puntosVidaMax = Math.max(1, Math.min(10000, puntosVidaMax));
            if (this.puntosVida > this.puntosVidaMax) this.puntosVida = this.puntosVidaMax;
        }

        public int getDefensa() {
            return defensa;
        }

        public void setDefensa(int defensa) {
            this.defensa = defensa;
        }

        public int getExperiencia() {
            return experiencia;
        }


        /**
         * Realiza una acción de ataque contra otro personaje.
         * Debe ser implementado por las subclases.
         *
         * @param objetivo El personaje que recibirá el ataque.
         */
        public abstract void atacar(Personaje objetivo);

        // --- Métodos de Acción (Lógica del Juego) ---

        /**
         * Añade experiencia al personaje y comprueba si sube de nivel.
         *
         * @param cantidad Cantidad de XP ganada.
         */
        public void ganarExperiencia(int cantidad) {
            this.experiencia += cantidad;
            System.out.println(this.nombre + " obtiene " + cantidad + " XP.");
            while (this.experiencia >= this.experienciaNecesaria) {
                this.experiencia -= this.experienciaNecesaria;
                subirNivel();
            }
        }

        /**
         * Aumenta el nivel del personaje y recalcula la experiencia necesaria.
         * Las subclases deben sobrescribir esto para mejorar sus estadísticas.
         */
        public void subirNivel() {
            setNivel(this.nivel + 1);
            System.out.println("\n*** ¡" + nombre.toUpperCase() + " SUBE AL NIVEL " + nivel + "! ***");
        }

        /**
         * Recupera un porcentaje de vida (30%) descansando.
         */
        public void descansar() {
            double vidaRecuperada = this.puntosVidaMax * 0.30;
            double vidaAntes = this.puntosVida;
            setPuntosVida(this.puntosVida + vidaRecuperada);
            System.out.println(nombre + " descansa y recupera " + (int) (this.puntosVida - vidaAntes) + " PV.");
        }

        public void moverse() {
            System.out.println(nombre + " avanza hacia la siguiente zona...");
        }

        /**
         * Verifica si el personaje sigue vivo.
         *
         * @return true si la vida es mayor que 0.
         */
        public boolean estaVivo() {
            return this.puntosVida > 0;
        }

        /**
         * Calcula y aplica daño reduciéndolo según la defensa del personaje.
         *
         * @param danio Cantidad de daño bruto recibido.
         */
        public void recibirDanio(double danio) {
            double danioReal = danio - defensa;
            if (danioReal < 0) danioReal = 0;

            setPuntosVida(puntosVida - danioReal);
            System.out.println(nombre + " recibe " + (int) danioReal + " de daño (Mitigado: " + defensa + "). [Vida: " + (int) puntosVida + "/" + (int) puntosVidaMax + "]");
        }

        // --- Gestión de Inventario ---

        /**
         * Añade un objeto al inventario.
         * Protegido contra valores null para evitar errores si un enemigo no suelta botín.
         *
         * @param item El objeto a recoger.
         */
        public void recogerItem(Item item) {
            if (item == null) {
                return; // No hace nada si no hay objeto
            }
            inventario.anadirItem(item);
            System.out.println(nombre + " guardó " + item.getNombre() + ".");
        }

        public void usarObjetoDeMochila(int indice) {
            this.inventario.usarYConsumir(indice, this);
        }

        public void mostrarInventario() {
            inventario.mostrarContenido();
        }


    }