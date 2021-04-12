/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipog_cv05_hromjak_david;

import ipog2algoritmy.OrezAlg;
import java.awt.Point;
import java.awt.Polygon;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 *
 * @author PC
 */
public class FXMLDocumentController implements Initializable {
    
     ArrayList <Polygon> seznam = new ArrayList <Polygon> ();
    Polygon polygon=new Polygon();
    @FXML
    private Canvas canvas;
    GraphicsContext gc;
    @FXML
    private RadioButton obecny2;
    @FXML
    private ToggleGroup generator;
    @FXML
    private RadioButton obecny1;
    @FXML
    private RadioButton konvex;
    @FXML
    private ToggleGroup hledani;
    @FXML
    private RadioButton konvexHledani;
    @FXML
    private RadioButton obecneHledani;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc=canvas.getGraphicsContext2D();
     
    }    

    @FXML
    private void ZjistiPolohu(MouseEvent event) {
        for (Polygon polygon1 : seznam) {
             double[] xpoints=new double[polygon1.npoints];
            double[] ypoints=new double[polygon1.npoints];
            for (int i = 0; i < polygon1.npoints; i++) {
                xpoints[i]=(double)polygon1.xpoints[i];
                ypoints[i]=(double)polygon1.ypoints[i];
            
            }
          if(obecneHledani.isSelected())  
           if(OrezAlg.jeBodVObecnemPolygonu(new Point((int)event.getX(),(int)event.getY()), polygon1, true))
           {
           gc.setStroke(Color.RED);
           }
           else
           {
           gc.setStroke(Color.BLACK);
           }
          if(konvexHledani.isSelected())
               if(OrezAlg.jeBodVKonvexPolygonu(new Point((int)event.getX(),(int)event.getY()), polygon1))
           {
           gc.setStroke(Color.GREEN);
           }
           else
           {
           gc.setStroke(Color.BLACK);
           }
           gc.strokePolygon(xpoints, ypoints, polygon1.npoints);
        }
    }

    @FXML
    private void generuj(ActionEvent event) {
           
      for (int i = 0; i < 10; i++) {
      if(konvex.isSelected())
      seznam.add(OrezAlg.generujPolygonKonvexni());
      if(obecny1.isSelected())
      seznam.add(OrezAlg.generujPolygoObecny1());
      if(obecny2.isSelected())
      seznam.add(OrezAlg.generujPolygoObecny2());
        }
        
        for (Polygon polygon : seznam) {
            double[] xpoints=new double[polygon.npoints];
            double[] ypoints=new double[polygon.npoints];
            for (int i = 0; i < polygon.npoints; i++) {
                xpoints[i]=(double)polygon.xpoints[i];
                ypoints[i]=(double)polygon.ypoints[i];
           
            }
            
            gc.setStroke(Color.BLACK);
            
            gc.strokePolygon(xpoints, ypoints, polygon.npoints);
        }
    }

    @FXML
    private void removPolygons(ActionEvent event) {
        seznam.removeAll(seznam);
        gc.clearRect(0, 0, canvas.getWidth()-1, canvas.getHeight()-1);
    }
    
}
