public class Mago extends Personaje {
    //Atributos
    private int manaMax;
    private int mana;

    //Constructor
    public Mago(String nombre, int nivel, double puntosVidaMax, int manaMax) {
        super(nombre, nivel, puntosVidaMax);
        setManaMax(manaMax);
        this.mana = manaMax; // Inicialmente, el mana es máximo
    }

    //Getters y Setters
    public int getManaMax() {
        return manaMax;
    }

    public void setManaMax(int manaMax) {
        this.manaMax = Math.max(50, Math.min(1000, manaMax));
        if (this.mana > this.manaMax) {
            this.mana = this.manaMax;
        }
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = Math.max(0, Math.min(manaMax, mana));
    }

    @Override
    public void atacar(Personaje objetivo) {
        if (this.getMana() >= 20) {
            double danioMagico = 30 + (5 * getNivel());
            System.out.println(getNombre() + " lanza un hechizo mágico a " + objetivo.getNombre());
            objetivo.recibirDanio(danioMagico);
            System.out.println(getNombre() + " inflige " + danioMagico + " puntos de daño mágico a " + objetivo.getNombre() + ".");
            // El Mago consume mana
            this.setMana(mana - 20);
        } else {
            System.out.println(getNombre() + " no tiene suficiente mana para atacar.");
        }
    }
}
