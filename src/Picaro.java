public class Picaro extends Personaje {
    //Atributos
    private int energia;
    private int energiaMaxima;
    private Arma arma1;
    private Arma arma2;

    //Constructor
    public Picaro(String nombre, int nivel, double puntosVidaMax, int energiaMaxima, Arma arma1, Arma arma2) {
        super(nombre, nivel, puntosVidaMax);
        setEnergiaMaxima(energiaMaxima);
        this.energia = energiaMaxima; // Inicialmente, la energía es máxima
        this.arma1 = arma1;
        this.arma2 = arma2;
    }

    //Getters y Setters
    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = Math.max(0, Math.min(energiaMaxima, energia));
    }

    public int getEnergiaMaxima() {
        return energiaMaxima;
    }

    public void setEnergiaMaxima(int energiaMaxima) {
        this.energiaMaxima = Math.max(0, Math.min(300, energiaMaxima));
        if (this.energia > this.energiaMaxima) {
            this.energia = this.energiaMaxima;
        }
    }

    public Arma getArma1() {
        return arma1;
    }

    public void setArma1(Arma arma1) {
        this.arma1 = arma1;
    }

    public Arma getArma2() {
        return arma2;
    }

    public void setArma2(Arma arma2) {
        this.arma2 = arma2;
    }

    @Override
    public void atacar(Personaje objetivo) {
        if (energia >= 15) {
            double danioTotal = (arma1.getDanioExtra() + arma2.getDanioExtra()) + (4 * getNivel());
            System.out.println(getNombre() + " se desliza entre las sombras y apuñala a " + objetivo.getNombre());

            // El Pícaro le aplica el daño al objetivo
            objetivo.recibirDanio(danioTotal);

            setEnergia(energia - 15);
        } else {
            System.out.println(getNombre() + " intenta atacar pero no tiene energía suficiente.");
        }
    }
}



