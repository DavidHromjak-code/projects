/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprava;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import kolekce.KolekceException;
import kolekce.LinSeznam;
import kolekce.Seznam;
import prostredky.DopravniProstredek;
import prostredky.Klon;

/**
 *
 * @author PC
 */
public class SpravaProstredku implements Ovladani{
private Seznam<DopravniProstredek> seznam;
private Comparator<? super DopravniProstredek> comp;
private Consumer<String> errorlog;

    @Override
    public void vytvorSeznam(Supplier supplier) {
       Objects.requireNonNull(supplier);
       seznam=(Seznam<DopravniProstredek>) supplier.get();
    }

    @Override
    public void vytvorSeznam(Function function, int size) throws KolekceException {
        Objects.requireNonNull(function);
        seznam=(Seznam<DopravniProstredek>) function.apply(size);
        if(seznam==null)
        {
            throw new KolekceException();
        }
        
        
    }

    @Override
    public void nastavKomparator(Comparator comparator) {
        Objects.requireNonNull(comparator);
        this.comp=(Comparator<? super DopravniProstredek>) comparator;
            }

    @Override
    public void nastavErrorLog(Consumer errorLog) {
        this.errorlog=errorLog;
    }

    @Override
    public void vlozPolozku(Klon data) throws NullPointerException {
        try
        {
            seznam.vlozNaKonec((DopravniProstredek)data);
        }
        catch (Exception e)
        {
        
        }
    }

    @Override
    public Klon najdiPolozku(Klon klic) {
        Klon pom;
        
        pom =(Klon) seznam
                .stream()
                .filter((DopravniProstredek l)->comp.compare(l,(DopravniProstredek) klic)==0)
                .findFirst()
                .get();
        
        return pom;
    }

    @Override
    public void prejdiNaPrvniPolozku() {
        try
        {
        seznam.nastavPrvni();
        }catch(KolekceException ex)
        {
        }
    }

    @Override
    public void prejdiNaPosledniPolozku() {
        seznam.nastavPosledni();
    }

    @Override
    public void prejdiNaDalsiPolozku() {
       try{
       seznam.dalsi();
       }
       catch(KolekceException ex)
       {
       }
    }

    @Override
    public void prejdiNaPredchoziPolozku() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Klon nastavAktualniPolozku(Klon klic) {
        
  try
        {   if(!seznam.jePrazdny())
        {
            
            seznam.nastavPrvni();
            do {
                DopravniProstredek prostredek= seznam.dejAktualni();
                DopravniProstredek pom=(DopravniProstredek) klic;

                if(comp.compare(prostredek, pom)==0) {
                    System.out.println("pruh");
                    return (Klon) prostredek;
                } 
                seznam.dalsi();
            } while (true);
           } else{
            System.out.println("pruh");
            return null;
                    }
            
        
        }catch(Exception ex)
        {
        System.out.println("ksaná smetana");
        }
        
        return null;
    }

    @Override
    public Klon vyjmiAktualniPolozku() {
        Klon pom;
        try{
        pom=(Klon) seznam.odeberAktualni();
       
        } catch(KolekceException ex)
        {
            pom=null;
        }
        return pom;
    }

    @Override
    public Klon dejKopiiAktualniPolozky() {
        try
        {
            return (Klon) seznam.dejAktualni().clone();
        }
        catch(KolekceException ex)
        {
        }
        catch(CloneNotSupportedException ex)
                {}
        return null;
    }

    @Override
    public void editujAktualniPolozku(Function editor) {
        try
        {
        editor.apply((Klon) seznam.dejAktualni());

        }catch(KolekceException ex)
        {
            System.out.println("kruhovina");
        }
    }

    @Override
    public void ulozDoSouboru(String soubor) {
        try{
        perzistence.Perzistence.uloz(soubor, seznam);
        }
        catch(IOException ex)
        {
        
        }
    }

    @Override
    public void nactiZeSouboru(String soubor) {
        seznam.zrus();
        
    try {
        seznam=perzistence.Perzistence.nacti(soubor, seznam);
    } catch (IOException ex) {
        
    } catch (KolekceException ex) {
       
    }
        
       
    }

    @Override
    public void vypis(Consumer writer) {
        seznam.stream().forEach((Consumer<? super DopravniProstredek>)writer);
    }

    @Override
    public void nactiTextSoubor(String soubor, Function mapper) {
       seznam.zrus();
    try {
        Files.lines(Paths.get(soubor), Charset.defaultCharset()).filter(t->t!=null).map(mapper).forEach(data-> seznam.vlozNaKonec((DopravniProstredek) data));
    } catch (IOException ex) {
        Logger.getLogger(SpravaProstredku.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public void ulozTextSoubor(String soubor, Function mapper) {
        try(PrintWriter pw= new PrintWriter(soubor,"UTF-8"))
        {
        seznam.stream().map((Function<? super DopravniProstredek, String>)mapper).forEachOrdered(pw::println);
        } catch (FileNotFoundException ex) {
        Logger.getLogger(SpravaProstredku.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedEncodingException ex) {
        Logger.getLogger(SpravaProstredku.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public void generujData(int pocetProstredku) {
        generator.Generator.generuj(pocetProstredku).forEach((t)->{seznam.vlozNaKonec((DopravniProstredek)t);});
    }

    @Override
    public int dejAktualniPocetPolozek() {
        return seznam.size();
    }

    @Override
    public void zrus() {
        seznam.zrus();
    }

    @Override
    public Iterator iterator() {
        return  seznam.iterator();
    }

    
}
