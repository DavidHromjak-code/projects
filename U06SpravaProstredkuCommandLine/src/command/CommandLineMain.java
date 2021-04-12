package command;

import generator.Generator;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import kolekce.KolekceException;
import kolekce.LinSeznam;
import kolekce.Seznam;
import perzistence.Perzistence;
import prostredky.*;
import sprava.Ovladani;
import sprava.SpravaProstredku;
import util.Barva;

/**
 * Cílem je
 * a) využívat Java 8 lambda výrazy a datové streams
 * b) návrhový vzor Fasada (viz. popis v rozhraní Ovladani)
 *  
 * 1) Použití třídy LinSeznam z předchozího cvičení do package LinSeznam
 * 2) Vytvoř třídu SpravaProstredku implementující rozhraní Ovladani v package sprava
 *      - co nejvíce využívat (java 8) lambda výrazy a datové streams
 * 3) Použití třídy Generator z předchozího cvičení v package generator
 * 4) Použití třídy Perzistence z předchozího cviření v package perzistence
 * 5) Můžete využít i třídy Keyboard z minulého cvičení v package command
 * 6) Rozšíření/úprava třídy CommandLineMain z předchozího cvičení
 * a) bude využívat třídy SpravaProstredku nikoli LinSeznam
 * b) v rámci konzolového rozhraní bude umožněno provádět následující příkazy
        help, h     - výpis příkazů
        novy,no     - vytvoř novou instanci a vlož dopravního prostředku za aktuální
        najdi,na,n  - najdi v seznamu dopravní prostředek podle státní poznávací značky
        dej         - zobraz aktuální dopravní prostředk v seznamu
        edituj,edit - edituj aktuální dopravní prostředek v seznamu
        vyjmi       - vyjmi aktuální dopravní prostředek ze seznamu
        prvni,pr    - nastav jako aktuální první dopravní prostředek v seznamu
        dalsi,da    - přejdi na další dopravni prostředek
        posledni,po - přejdi na poslední dopravní prostředek
        pocet       - zobraz počet položek v seznamu
        obnov       - obnov seznam dopravních prostředků z binárního souboru
        zalohuj     - zalohuj seznam dopravních prostředků do binárního souboru
        vypis       - zobraz seznam dopravních prostředků
        nactitext,nt- nacti seznam dopravních prostředků z textového souboru
        uloztext,ut - ulož seznam dopravních prostředků do textového souboru
        generuj,g   - generuj náhodně dopravní prostředky pro testování
        zrus        - zruš všechny dopravní prostředky v seznamu
        exit        - ukončení programu
 * c) příkaz novy bude dále umožňovat uživateli vybrat jaký dopravní prostředek chce vytvorit
        oa - osobní auto
        na - nákladní auto
        do - dodávka
        tr - traktor
 * d) příkaz najdi bude dále umožňovat uživateli vybrat komparátor
        id - porovnání podle ID dopravního prostředku
        spz - porovnání podle státní poznávací značky (SPZ)

 * 
 * @author karel@simerda.cz
 */
public class CommandLineMain {

    private static final String ZALOHA_FILE_NAME = "zaloha.bin";
    private static final String ZALOHA_FILE_SOUBOR = "zaloha.txt";
    private static Ovladani control=new SpravaProstredku();
    private static DopravniProstredekKlic data;
    private static Function<DopravniProstredek,?> edit;
    private static Function<? super DopravniProstredek, String>mapperUloz;
    private static Function<String,? super DopravniProstredek> mapperNacti;
    
   
     private static final Random random = new Random();

    public static void main(String[] args) {
        //seznam = new LinSeznam<>();
        Consumer<String> errorLog=(t)->{
            System.out.println(t);
        };
        Supplier <Seznam<DopravniProstredek>> seznam=()->
        {
            return new LinSeznam<DopravniProstredek>();
        };
        control.vytvorSeznam(seznam);
        
        run();
        System.out.println("Konec programu");
    }

    private static void run() {
        String cmd;
        do {

            cmd = Keyboard.getStringItem("Zadej příkaz: ");
            switch (cmd) {
                case "help":
                case "h":
                   vypisHelp();
                    break;
                case "novy":
                case "no":
                   cmd = Keyboard.getStringItem("Vyber oa/na/do/tr: ");
                   switch(cmd)
                           {
                               case "oa":
                               control.vlozPolozku(Generator.generujProstredek(0));
                               break;
                               case "na":
                                   control.vlozPolozku(Generator.generujProstredek(1));
                               break;
                               case "do":
                                   control.vlozPolozku(Generator.generujProstredek(2));
                               break;
                               case "tr":
                                   control.vlozPolozku(Generator.generujProstredek(3));
                               break;    
                               
                           }
                    break;
                case "najdi":
                case "na":
                case "n":
                   cmd = Keyboard.getStringItem("Zadej SPZ: ");
                   control.najdiPolozku((Klon) new DopravniProstredekKlic(cmd));
                    break;
                case "dej":
                case "de":
            {
                System.out.println(control.dejKopiiAktualniPolozky().toString());
            }
                    break;
                case "edituj":
                case "edit":
                    edit= (line)->{
         
         line.setSpz(Keyboard.getStringItem("SPZ (" + line.getSpz()+"):"));
         line.setHmotnost(Keyboard.getFloatItem("Hmotnost:",line.getHmotnost()));
         switch (line.getType()) {
           case OSOBNI_AUTOMOBIL:
              OsobniAutomobil auto = (OsobniAutomobil) line;
                auto.setPocetSedadel(Keyboard.getIntItem("Pocet sedadel:", auto.getPocetSedadel()));
                auto.setBarva(Keyboard.getBarvaItem("Barva:", auto.getBarva()));
                break;
            case DODAVKA:
                Dodavka dodavka=(Dodavka) line;
                
                
                        
                break;
            case NAKLADNI_AUTMOBIL:
                NakladniAutomobil nakladak= (NakladniAutomobil) line;
                nakladak.setNosnost(Keyboard.getFloatItem("Nosnost", nakladak.getNosnost()));
                nakladak.setPocetNaprav(Keyboard.getIntItem("Počet Nápprav", nakladak.getPocetNaprav()));
                break;
            case TRAKTOR:
                Traktor traktor = (Traktor) line;
                traktor.setTah(Keyboard.getIntItem("Tah:",(int)traktor.getTah() ));
                break;
            default:
            }
         return line;
        
     };
                   control.editujAktualniPolozku(edit);
                    break;
                case "vyjmi":

            {
                control.vyjmiAktualniPolozku();
            }
                    break;
                case "prvni":
                case "pr":
            {
                control.prejdiNaPrvniPolozku();
            }
                    break;
                case "dalsi":
                case "da":
            {
                control.prejdiNaDalsiPolozku();
            }
                    break;
                case "posledni":
                case "po":
                    control.prejdiNaPosledniPolozku();
                    break;
                case "pocet":

                    System.out.println(control.dejAktualniPocetPolozek());
                    break;

                case "obnov":
                    control.nactiZeSouboru(ZALOHA_FILE_NAME);
                    break;
                case "zalohuj":
            {
                control.ulozDoSouboru(ZALOHA_FILE_NAME);
            }
                    break;
                case "vypis":
                case "v":
            {

                System.out.println(control.stream().map( Object::toString )
            .collect( Collectors.joining( "\n") ));
            }
                    
                    break;
                    case "nactitext":
                case "nt":
                    mapperNacti= (line) -> {
       DopravniProstredek prostredek = null;
       if (line.length() == 0) { return prostredek; }
       String[] items = line.split(",");
       String spz = items[1].trim();
       float hmotnost = Float.valueOf(items[2]);
       switch (items[0].trim().toLowerCase(Locale.US)) {
          case "oa":
            int pocetSedadel = Integer.valueOf(items[3].trim());
            Barva barva = Barva.decode(items[4].trim());
            prostredek = new OsobniAutomobil(spz, barva, pocetSedadel, hmotnost);
            break;
          case "do":
            DodavkaTyp typ = DodavkaTyp.decode(items[3].trim());
            prostredek = new Dodavka(spz, hmotnost, typ);
          break;
         case "na":
            long nosnost = Long.valueOf(items[3].trim());
           prostredek = new NakladniAutomobil(spz, hmotnost, nosnost);
            break;
          case "tr":
            int tah = Integer.valueOf(items[3].trim());
            prostredek = new Traktor(spz, hmotnost, tah);
            break;
        }
        return prostredek;
      };
                    control.nactiTextSoubor(ZALOHA_FILE_SOUBOR, mapperNacti);
                    break;
                    case "uloztext":
                case "ut":
                          mapperUloz=(line) -> 
        
        {
        String text="";
        switch (line.getType()) {
          case OSOBNI_AUTOMOBIL:
              OsobniAutomobil oa=(OsobniAutomobil) line;
              text="oa, "+ oa.getSpz()+ ", "+ oa.getHmotnost()+", "+oa.getBarva();
                break;
            case DODAVKA:
            Dodavka dodavka=(Dodavka) line;
            text="do, "+ dodavka.getSpz()+ ", "+ dodavka.getHmotnost()+", "+dodavka.getTyp();
                break;
            case NAKLADNI_AUTMOBIL:
              NakladniAutomobil na=(NakladniAutomobil) line;  
              text="na, "+ na.getSpz()+ ", "+ na.getHmotnost()+", "+na.getPocetNaprav();
                break;
            case TRAKTOR:
              Traktor tr=(Traktor) line;  
              text="tr, "+ tr.getSpz()+ ", "+ tr.getHmotnost()+", "+tr.getTah();
                break;
        }
        return text;
        };
                    control.nactiTextSoubor(ZALOHA_FILE_SOUBOR, mapperUloz);
                    break;
                case "generuj":
                case "g":
                    
                    control.generujData(random.nextInt(100));
                    break;
                case "zrus":

                    control.zrus();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Neznamy prikaz:" + cmd.length());
            }
        } while (true);
    }

    private static void vypisHelp() {
        System.out.println(""
        +"help, h     - výpis příkazů \n"
        +"novy,no     - vytvoř novou instanci a vlož dopravního prostředku za aktuální \n"
        +"najdi,na,n  - najdi v seznamu dopravní prostředek podle státní poznávací značky \n"
        +"dej         - zobraz aktuální dopravní prostředk v seznamu \n"
        +"edituj,edit - edituj aktuální dopravní prostředek v seznamu \n"
        +"vyjmi       - vyjmi aktuální dopravní prostředek ze seznamu \n"
        +"prvni,pr    - nastav jako aktuální první dopravní prostředek v seznamu \n"
        +"dalsi,da    - přejdi na další dopravni prostředek \n"
        +"posledni,po - přejdi na poslední dopravní prostředek \n"
        +"pocet       - zobraz počet položek v seznamu \n"
        +"obnov       - obnov seznam dopravních prostředků z binárního souboru \n"
        +"zalohuj     - zalohuj seznam dopravních prostředků do binárního souboru \n"
        +"vypis       - zobraz seznam dopravních prostředků \n"
        +"nactitext,nt- nacti seznam dopravních prostředků z textového souboru \n"
        +"uloztext,ut - ulož seznam dopravních prostředků do textového souboru \n"
        +"generuj,g   - generuj náhodně dopravní prostředky pro testování \n" 
        +"zrus        - zruš všechny dopravní prostředky v seznamu \n"
        +"exit        - ukončení programu \n"
        );
    }

}
