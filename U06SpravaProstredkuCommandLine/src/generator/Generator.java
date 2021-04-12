package generator;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import kolekce.KolekceException;
import kolekce.LinSeznam;
import kolekce.Seznam;
import prostredky.Dodavka;
import prostredky.DodavkaTyp;
import prostredky.DopravniProstredek;
import prostredky.NakladniAutomobil;
import prostredky.OsobniAutomobil;
import prostredky.ProstredekTyp;
import prostredky.Traktor;
import util.Barva;

/**
 *
 * @author kasi0004
 */
public final class Generator {

    private static final Random random = new Random();

    private Generator() {
    }

    public static Seznam<DopravniProstredek> generuj(int pocetProstredku) {
        Seznam<DopravniProstredek> seznam = new LinSeznam<DopravniProstredek>();
        for (int i = 0; i < pocetProstredku; i++) {
            if (seznam.jePrazdny()) {
                seznam.vlozPrvni(generujProstredek());
                
            } else {
                seznam.vlozNaKonec(generujProstredek());
            }
        }

        return seznam;
    }
     public static DopravniProstredek generujProstredek(int typ) {
        DopravniProstredek prostredek = null;
        
        switch (ProstredekTyp.values()[typ]) {
            case OSOBNI_AUTOMOBIL:
                prostredek = new OsobniAutomobil(novaSPZ(), novaBarva(), novyPocetSedadel(), novaHmotnost(1000));
                break;
            case DODAVKA:
                prostredek = new Dodavka(novaSPZ(), novaHmotnost(1000), novyTypDodavky());
                break;
            case NAKLADNI_AUTMOBIL:
                prostredek = new NakladniAutomobil(novaSPZ(), novaHmotnost(1000), novaNosnost());
                break;
            case TRAKTOR:
                prostredek = new Traktor(novaSPZ(), novaHmotnost(1000), novyTahTraktoru());
               
                break;
        }
        return prostredek;
    }

    private static DopravniProstredek generujProstredek() {
        DopravniProstredek prostredek = null;
        int typ = random.nextInt(ProstredekTyp.getProstredky().length);
        switch (ProstredekTyp.values()[typ]) {
            case OSOBNI_AUTOMOBIL:
                prostredek = new OsobniAutomobil(novaSPZ(), novaBarva(), novyPocetSedadel(), novaHmotnost(1000));
                break;
            case DODAVKA:
                prostredek = new Dodavka(novaSPZ(), novaHmotnost(1000), novyTypDodavky());
                break;
            case NAKLADNI_AUTMOBIL:
                prostredek = new NakladniAutomobil(novaSPZ(), novaHmotnost(1000), novaNosnost());
                break;
            case TRAKTOR:
                prostredek = new Traktor(novaSPZ(), novaHmotnost(1000), novyTahTraktoru());
                break;
                
          
        }
        return prostredek;
    }

    public static String novaSPZ() {
        StringBuilder spz = new StringBuilder();
        spz.append(random.nextInt(10))
                .append((char) ('A' + random.nextInt(27)))
                .append((char) ('0' + random.nextInt(10)))
                //  .append(" ")
                .append((char) ('0' + random.nextInt(10)))
                .append((char) ('0' + random.nextInt(10)))
                //                .append("-")
                .append((char) ('0' + random.nextInt(10)))
                .append((char) ('0' + random.nextInt(10)));

        return spz.toString();
    }

    public static Barva novaBarva() {
        Barva barva;
        switch (random.nextInt(5)) {
            case 0:
                barva = Barva.BILA;
                break;
            case 1:
                barva = Barva.CERNA;
                break;
            case 2:
                barva = Barva.CEVENA;
                break;
            case 3:
                barva = Barva.MODRA;
                break;
            case 4:
                barva = Barva.ZELENA;
                break;

            default:
                barva = Barva.BILA;
        }
        return barva;
    }

    public static int novyPocetSedadel() {
        return random.nextInt(5);
    }

    public static float novaHmotnost(int max) {
        return 0 + random.nextFloat() * (max - 0);
    }

    public static DodavkaTyp novyTypDodavky() {
        DodavkaTyp typ;
        switch (random.nextInt(3)) {
            case 0:
                typ = DodavkaTyp.DVOJ_KABINA;
                break;
            case 1:
                typ = DodavkaTyp.NASTAVBA;
                break;
            case 2:
                typ = DodavkaTyp.VALNIK;
                break;

            default:
                typ = DodavkaTyp.DVOJ_KABINA;
        }
        return typ;
    }

    public static int novaNosnost() {
        return random.nextInt(10000);
    }

    public static long novyTahTraktoru() {
        return  random.nextLong();
    }

}
