/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apog2algoritmy;

import com.sun.webkit.graphics.WCImage;
import ipog_cv04_hromjak_david.Seminko;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 *
 * @author PC
 */
public class seminkovaVypln {

    public WritableImage vyplnRastr(Point puvodniSeminko, Color color, Image image) {
        
        ArrayList<Seminko> zasobnik = new ArrayList<Seminko>();
        
        Seminko prvniSeminko = new Seminko(puvodniSeminko.x, puvodniSeminko.y);
        prvniSeminko.dole = true;
        prvniSeminko.levo = true;
        prvniSeminko.pravo = true;
        prvniSeminko.nahore = true;
        int minX;
        int maxX;
        boolean konec = false;
        zasobnik.add(prvniSeminko);
        Seminko seminkoAdd;

        if (image != null) {
            WritableImage wImage = new WritableImage(image.getPixelReader(), (int) image.widthProperty().get(), (int) image.heightProperty().get());
            PixelWriter pixelWriter = wImage.getPixelWriter();
            PixelReader pixelReader = wImage.getPixelReader();
            Color puvodniBarva = pixelReader.getColor(puvodniSeminko.x, puvodniSeminko.y);
            Seminko seminko;
            pixelWriter.setColor(puvodniSeminko.x, puvodniSeminko.y, color);
            while (!zasobnik.isEmpty()) {
               seminko= zasobnik.remove(0);
               
                    
                    minX = seminko.x;
                    maxX = seminko.x;
                    if (seminko.nahore) {

                        if (pixelReader.getColor(seminko.x, seminko.y - 1).equals(puvodniBarva) && seminko.y - 1 >= 0) {
                            seminkoAdd = new Seminko(seminko.x, seminko.y - 1);
                            seminkoAdd.levo = true;
                            seminkoAdd.pravo = true;
                            if (seminko.y - 2 >= 0) {
                                if (pixelReader.getColor(seminko.x, seminko.y - 2).equals(puvodniBarva)) {

                                    seminkoAdd.nahore = true;

                                }
                            }

                            zasobnik.add(seminkoAdd);

                        }
                    }
                    if (seminko.dole) {
                        if (pixelReader.getColor(seminko.x, seminko.y + 1).equals(puvodniBarva) && seminko.y + 1 <= image.getHeight()) {
                            seminkoAdd = new Seminko(seminko.x, seminko.y + 1);
                            seminkoAdd.levo = true;
                            seminkoAdd.pravo = true;
                            if (seminko.y + 2 <= wImage.getHeight() - 1) {
                                if (pixelReader.getColor(seminko.x, seminko.y + 2).equals(puvodniBarva)) {

                                    seminkoAdd.dole = true;

                                }
                            }

                            zasobnik.add(seminkoAdd);
                        }

                    }

                    if (seminko.levo) {
                        konec = true;
                        pixelWriter.setColor(minX, seminko.y, color);
                        do {

                            if (minX - 1 == 0) {
                                konec = false;

                            } else {
                                if (seminko.y - 1 >= 0) {
                                    if (!pixelReader.getColor(minX - 1, seminko.y - 1).equals(pixelReader.getColor(minX, seminko.y - 1)) && !pixelReader.getColor(minX - 1, seminko.y - 1).equals(color) && !pixelReader.getColor(minX, seminko.y - 1).equals(color)) {
                                      

                                        if (pixelReader.getColor(minX - 1, seminko.y - 1).equals(puvodniBarva)) {
                                            seminkoAdd = new Seminko(minX - 1, seminko.y - 1);
                                            seminkoAdd.levo = true;
                                            seminkoAdd.pravo = true;
                                            seminkoAdd.nahore = true;

                                            zasobnik.add(seminkoAdd);
                                       
                                        }

                                    }
                                }
                                if (seminko.y + 1 <= wImage.getHeight()-1) {
                                    if (!(pixelReader.getColor(minX , seminko.y + 1).equals(pixelReader.getColor(minX-1, seminko.y + 1))) && !(pixelReader.getColor(minX - 1, seminko.y + 1).equals(color)) && !(pixelReader.getColor(minX, seminko.y + 1).equals(color))) {
                                    

                                        if (pixelReader.getColor(minX - 1, seminko.y + 1).equals(puvodniBarva)) {
                                            seminkoAdd = new Seminko(minX - 1, seminko.y + 1);
                                            seminkoAdd.levo = true;
                                            seminkoAdd.pravo = true;
                                            seminkoAdd.dole = true;
//
                                            zasobnik.add(seminkoAdd);
                                        
                                        }

                                    }
                                }
                            
                            if (pixelReader.getColor(minX - 1, seminko.y).equals(puvodniBarva)) {
                                pixelWriter.setColor(minX - 1, seminko.y, color);
                                minX--;

                            }
                            else {

                                konec = false;

                            }
                        }
                        
                    }
                    while (konec);
                }
                if (seminko.pravo) {
                    pixelWriter.setColor(maxX, seminko.y, color);
                    konec = true;

                    do {
                        if (maxX + 1 == wImage.getWidth()) {
                            konec = false;
                        } else {
                            if (seminko.y - 1 >= 0) {
                                if (!pixelReader.getColor(maxX + 1, seminko.y - 1).equals(pixelReader.getColor(maxX, seminko.y - 1)) && !pixelReader.getColor(maxX + 1, seminko.y - 1).equals(color) && !pixelReader.getColor(maxX, seminko.y - 1).equals(color)) {
                                   
                                    if (pixelReader.getColor(maxX + 1, seminko.y - 1).equals(puvodniBarva)) {
                                        seminkoAdd = new Seminko(maxX + 1, seminko.y - 1);
                                        seminkoAdd.levo = true;
                                        seminkoAdd.pravo = true;
                                        seminkoAdd.nahore = true;

                                        zasobnik.add(seminkoAdd);
                                     
                                    }

                                }
                            }
                            if (seminko.y + 1 <= wImage.getHeight()-1) {
                                if (!pixelReader.getColor(maxX + 1, seminko.y + 1).equals(pixelReader.getColor(maxX, seminko.y + 1)) && !pixelReader.getColor(maxX + 1, seminko.y + 1).equals(color) && !pixelReader.getColor(maxX, seminko.y + 1).equals(color)) {
                                

                                    if (pixelReader.getColor(maxX + 1, seminko.y + 1).equals(puvodniBarva)) {
                                        seminkoAdd = new Seminko(maxX + 1, seminko.y + 1);
                                        seminkoAdd.levo = true;
                                        seminkoAdd.pravo = true;
                                        seminkoAdd.dole = true;

                                        zasobnik.add(seminkoAdd);
                               
                                    }

                                }
                            }
                            if (pixelReader.getColor(maxX + 1, seminko.y).equals(puvodniBarva)) {
                                pixelWriter.setColor(maxX + 1, seminko.y, color);
                                maxX++;

                            } else {
                                konec = false;
                            }
                        }
                    } while (konec);
                }
        }
        

        return wImage;
    }



return null;
    }

}
