
        package perzistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Objects;
import kolekce.KolekceException;
import kolekce.Seznam;
import prostredky.DopravniProstredek;
import util.Klon;


/**
 *
 * @author karel@simerda.cz
 */
public final class Perzistence {

    private Perzistence() {

    }

    public static <T extends Klon> void uloz(String jmenoSouboru, Seznam<DopravniProstredek> seznam)
       throws IOException {
        try {
 Objects.requireNonNull(seznam);

 ObjectOutputStream vystup =
 new ObjectOutputStream(
 new FileOutputStream(jmenoSouboru));

 //vystup.reset();

 vystup.writeInt(seznam.size());

 Iterator<DopravniProstredek> it = seznam.iterator();
 while (it.hasNext()) {
 vystup.writeObject(it.next());
 }
 vystup.close();
 } catch (IOException ex) {
 throw new IOException(ex);
 }
 }


    public static <T extends Klon> Seznam<DopravniProstredek> nacti(String jmenoSouboru, Seznam<DopravniProstredek> seznam)
       throws IOException, KolekceException {
 try {
 Objects.requireNonNull(seznam);
 ObjectInputStream vstup =
 new ObjectInputStream(
 new FileInputStream(jmenoSouboru));
 seznam.zrus();

 int pocet = vstup.readInt();
 for (int i = 0; i < pocet; i++) {
 seznam.vlozNaKonec((DopravniProstredek) vstup.readObject());
 }
 vstup.close();
 } catch (IOException|ClassNotFoundException ex) {
 throw new IOException(ex);
 } finally {
 }
 return seznam;

                
            }
}
    

