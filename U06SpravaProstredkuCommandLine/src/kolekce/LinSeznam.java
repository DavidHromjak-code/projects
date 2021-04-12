/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolekce;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rnovotny3
 */
public class LinSeznam<E> implements Seznam<E>{

    

    private void kontrolaPrvkuSeznamu()throws KolekceException {
      if(aktualni==null)throw new KolekceException("bruh");
     
    }

    private class Prvek{
        Prvek dalsi;
        E data;
        
        public Prvek(E data){
            this.data = data;
        }
        public Prvek(E data, Prvek dalsi)
        {
        this.data=data;
        this.dalsi=dalsi;
        }
    }
    
    private Prvek prvni;
    private Prvek predAktualni;
    private Prvek aktualni;
    private Prvek posledni;
    private int pocet;
    
    @Override
    public void nastavPrvni() throws KolekceException {
        if(jePrazdny())
        {   
       throw new KolekceException();
        }
        aktualni = prvni;
    }

      public void nastavPosledni() {
       aktualni=posledni;    
    }

    @Override
     public boolean dalsi() throws KolekceException {
        kontrolaPrvkuSeznamu();
        if (jePrazdny()||prvni==posledni) {

            
            return false;

        }
        predAktualni=aktualni;
        aktualni = aktualni.dalsi;
        return aktualni!=null;

    }

    @Override
    public void vlozPrvni(E data) {
        if(jePrazdny()){
            prvni = new Prvek(data,prvni);
            posledni=prvni;
           
        }else{
            Prvek novy = new Prvek(data,prvni);
            novy.dalsi = prvni;
            prvni = novy;
        }
        pocet++;
    }

    @Override
    public void vlozNaKonec(E data) {
        if(jePrazdny())
        {
            vlozPrvni(data);
            return;
        }
        else{
         Prvek novy= new Prvek(data,null);
        posledni.dalsi=novy;
        posledni=novy;
        pocet++;
        
        
        }
    }

    @Override
    public void vlozZaAktualni(E data) throws KolekceException {
       kontrolaPrvkuSeznamu();
        
        if(aktualni==posledni){
        
        aktualni.dalsi=new Prvek(data,null);
        posledni=aktualni.dalsi;
        }
        else
        {
        Prvek odpojeny=aktualni.dalsi;    
        aktualni.dalsi=new Prvek(data,odpojeny);
        }
pocet++;
    }
        
    

    @Override
    public boolean jePrazdny() {
        return prvni == null;
    }

    @Override
    public E dejPrvni() throws KolekceException {
        if(jePrazdny())
        {   
       throw new KolekceException();
        }
       return prvni.data;
    }

    @Override
    public E dejPosledni() throws KolekceException {
       
        if(posledni==null)
        {   
            nastavPosledni();
            }
        if(posledni==null)
        {   
 throw new KolekceException();
            }
       
         return posledni.data;
    }

    @Override
    public E dejAktualni() throws KolekceException {
        kontrolaPrvkuSeznamu();
        return aktualni.data;
    }

    @Override
    public E dejZaAktualnim() throws KolekceException {
        kontrolaPrvkuSeznamu();
        if(aktualni.dalsi==null)
        {
            throw new KolekceException();
        }
       return aktualni.dalsi.data;
    }

    @Override
    public E odeberAktualni() throws KolekceException {
        kontrolaPrvkuSeznamu();
        E data=aktualni.data;
        if(aktualni==prvni)
        {
        prvni=aktualni.dalsi;
        predAktualni=null;
        } else if (aktualni==posledni){
            posledni=predAktualni;
            posledni.dalsi=null;
            
        }else{
        predAktualni.dalsi=aktualni.dalsi;
        }
        pocet--;
       return data;
    
   
    }

    @Override
    public E odeberZaAktualnim() throws KolekceException {
        kontrolaPrvkuSeznamu();
        if(aktualni==posledni)
        {
        throw new KolekceException();
        }
       E data=aktualni.dalsi.data;
       aktualni.dalsi=aktualni.dalsi.dalsi;
       pocet--;
       return data;   
    }

    @Override
    public int size() {
       
        return pocet;
    }

    @Override
    public void zrus() {
        try {
            if(!jePrazdny())
            nastavPrvni();
            prvni=null;
        } catch (KolekceException ex) {
            Logger.getLogger(LinSeznam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   @Override
    public Iterator<E> iterator() {
      
            Iterator<E> it = new Iterator<E>() {
                private Prvek pom=prvni;
                @Override
                public boolean hasNext() {
                    return pom!=null;
                }
                
                @Override
                public E next() {
                    Prvek pred=pom;
                    pom=pom.dalsi;
                    return pred.data;
                }
            };
            return it;
        
    }
    
}
