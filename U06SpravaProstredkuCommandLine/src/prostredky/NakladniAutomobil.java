package prostredky;

/**
 *
 * @author karel@simerda.cz
 */
public class NakladniAutomobil extends DopravniProstredek {

    private int pocetNaprav;
    private float nosnost;

    public NakladniAutomobil(String spz, float hmotnost, float nosnost) {
        super(ProstredekTyp.NAKLADNI_AUTMOBIL, spz, hmotnost);
    }

    public NakladniAutomobil(String spz, int pocetNaprav, float hmotnost, float nosnost) {
        this(spz, hmotnost, nosnost);
        this.pocetNaprav = pocetNaprav;
    }

    public NakladniAutomobil() {
        this("",0,0);
    }

    public int getPocetNaprav() {
        return pocetNaprav;
    }

    public void setPocetNaprav(int pocetNaprav) {
        this.pocetNaprav = pocetNaprav;
    }

    public float getNosnost() {
        return nosnost;
    }

    public void setNosnost(float nosnost) {
        this.nosnost = nosnost;
    }
    
    

    @Override
    public String toString() {
        return super.toString() + ", pocetNaprav=" + pocetNaprav + " nosnost=" + nosnost;
    }

}
