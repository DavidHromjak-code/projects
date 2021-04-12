package prostredky;

/**
 *
 * @author karel@simerda.cz
 */
public class Dodavka extends DopravniProstredek {

    private DodavkaTyp typ;

    public Dodavka() {
        super(ProstredekTyp.DODAVKA);
    }

    public Dodavka(String spz, float hmotnost, DodavkaTyp typ) {
        super(ProstredekTyp.DODAVKA, spz, hmotnost);
        this.typ = typ;
    }

   
    public DodavkaTyp getTyp() {
        return typ;
    }

    public void setTyp(DodavkaTyp typ) {
        this.typ = typ;
    }

    @Override
    public String toString() {
        return super.toString() + ", typ=" + typ.getNazev();
    }

}
