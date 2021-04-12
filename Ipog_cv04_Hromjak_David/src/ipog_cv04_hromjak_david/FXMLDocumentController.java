/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipog_cv04_hromjak_david;

import apog2algoritmy.seminkovaVypln;
import java.awt.Point;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


/**
 *
 * @author PC
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    
    @FXML
    private ColorPicker cpColor;
 
    @FXML
    private ImageView imageView;
     seminkovaVypln seminko=new seminkovaVypln();
    FileChooser fileChooser = new FileChooser();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         fileChooser.setTitle("Open Resource File");
         fileChooser.getExtensionFilters().addAll(
         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif","*.bmp"));

 File file =  fileChooser.showOpenDialog(null);
 System.out.println(file);
Image image= new Image(file.toURI().toString());

    imageView.setFitHeight(image.getHeight());
    imageView.setFitWidth(image.getWidth());
    imageView.setImage(image);
WritableImage writableImage = new WritableImage(image.getPixelReader(), (int) image.getWidth(), (int) image.getHeight());
PixelWriter pixelWriter = writableImage.getPixelWriter();
PixelReader pixelReader = writableImage.getPixelReader();
//for (int i = 0; i < writableImage.getHeight(); i++) {
//    for (int j = 0; j < writableImage.getWidth(); j++) {
//        Color c = pixelReader.getColor(j, i);
//        if (c.getRed() > 0.5 || c.getGreen() > 0.5 || c.getBlue() > 0.5) {
//            pixelWriter.setColor(j, i, Color.WHITE);
//        
//        }
//        else if (c.getRed() > 0 || c.getGreen() > 0 || c.getBlue() > 0) {
//          
//            pixelWriter.setColor(j, i, Color.BLACK);
//        }
//    }
//}
imageView.setImage(writableImage);
    }    
    


    @FXML
    private void vyplnRastr(MouseEvent event) {
       System.out.println(imageView.getImage());
    imageView.setImage(seminko.vyplnRastr(new Point((int)event.getSceneX(),(int) event.getSceneY()), cpColor.getValue(), imageView.getImage()));
        
    
    }


}