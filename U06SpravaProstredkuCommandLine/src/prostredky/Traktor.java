package prostredky;

/**
 *
 * @author karel@simerda.cz
 */
public class Traktor extends DopravniProstredek {

    private long tah;

    public Traktor() {
        super(ProstredekTyp.TRAKTOR);
    }

    public Traktor(String spz, float hmotnost, long tah) {
        super(ProstredekTyp.TRAKTOR, spz, hmotnost);
        this.tah = tah;
    }

    public long getTah() {
        return tah;
    }

    public void setTah(long tah) {
        this.tah = tah;
    }

    @Override
    public String toString() {
        return super.toString() + ", tah=" + tah;
    }

}
