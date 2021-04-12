package prostredky;

import util.Barva;

/**
 *
 * @author karel@simerda.cz
 */
public class OsobniAutomobil extends DopravniProstredek {

    private Barva barva;
    private int pocetSedadel;

    public OsobniAutomobil() {
        this(0, "");
    }

    public OsobniAutomobil(float hmotnost, String spz) {
        super(ProstredekTyp.OSOBNI_AUTOMOBIL, spz, hmotnost);
    }

    public OsobniAutomobil(String spz, Barva barva, int pocetSedadel, float hmotnost) {
        this(hmotnost, spz);
        this.barva = barva;
        this.pocetSedadel = pocetSedadel;
    }

    public Barva getBarva() {
        return barva;
    }

    public void setBarva(Barva barva) {
        this.barva = barva;
    }

    public int getPocetSedadel() {
        return pocetSedadel;
    }

    public void setPocetSedadel(int pocetSedadel) {
        this.pocetSedadel = pocetSedadel;
    }

    @Override
    public String toString() {
        return super.toString() + ",  barva=" + barva + ", pocetSedadel=" + pocetSedadel;
    }

}
