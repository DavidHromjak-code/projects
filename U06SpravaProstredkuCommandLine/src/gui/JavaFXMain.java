/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import kolekce.LinSeznam;
import kolekce.Seznam;
import prostredky.Automobil;
import prostredky.Dodavka;
import prostredky.DodavkaTyp;
import prostredky.DopravniProstredek;
import prostredky.DopravniProstredekKlic;
import prostredky.NakladniAutomobil;
import prostredky.OsobniAutomobil;
import prostredky.ProstredekTyp;
import prostredky.Traktor;
import sprava.Ovladani;
import sprava.SpravaProstredku;
import util.Barva;

/**
 *
 * @author Midnight
 */
public class JavaFXMain extends Application {
    
    private static final String ZALOHA_FILE_NAME = "zaloha.bin";
    private static final String ZALOHA_FILE_SOUBOR = "zaloha.txt";
    private static Ovladani control = new SpravaProstredku();
    private static DopravniProstredekKlic data;
    private static Function<DopravniProstredek, ?> edit;
    private static int filtrValue = 6;
    
    private static Predicate<DopravniProstredek> filterAutomobil = s -> s.getType() == ProstredekTyp.OSOBNI_AUTOMOBIL;
    private static Predicate<DopravniProstredek> filterNakladniAutomobil = s -> s.getType() == ProstredekTyp.NAKLADNI_AUTMOBIL;
    private static Predicate<DopravniProstredek> filterTraktor = s -> s.getType() == ProstredekTyp.TRAKTOR;
    private static Predicate<DopravniProstredek> filterDodavka = s -> s.getType() == ProstredekTyp.DODAVKA;
    private static Function<? super DopravniProstredek, String> mapperUloz;
    private static Function<String, ? super DopravniProstredek> mapperNacti;
    List<DopravniProstredek> seznam = new ArrayList<DopravniProstredek>();
    ObservableList<DopravniProstredek> seznamProstredku = FXCollections.observableArrayList(seznam);
    ObservableList<ProstredekTyp> seznamTypu = FXCollections.observableArrayList(ProstredekTyp.values());
   DopravniProstredek prostredek; 
    TextField spz;
    TextField hmotnost;
    TextField pocetSedadel;
    ChoiceBox<String> barva;
    ChoiceBox<String> typ;
    TextField nosnost;
    TextField pocetNaprav;
    TextField tah;
    Button btnPrvni;
    Button btnDalsi;
    Button btnPosledni;
    Button btnEdituj;
    Button btnVyjmi;
    Button btnZobraz;
    Button btnClear;
    Button btnGeneruj;
    Button btnUloz;
    Button btnNacti;
    ChoiceBox<ProstredekTyp> choiceBoxFiltr;
    ChoiceBox choiceBoxNovy;
    ChoiceBox choiceBoxNajdi;
    Button btnZalohuj;
    Button btnObnov;
    Button btnZrus;
    ListView<DopravniProstredek> listView;
    private static final Random random = new Random();
    
    @Override
    public void start(Stage primaryStage) {
        Supplier<Seznam<DopravniProstredek>> seznam2 = ()
                -> {
            return new LinSeznam<DopravniProstredek>();
        };
        control.vytvorSeznam(seznam2);
        control.nastavKomparator(new Comparator<DopravniProstredek>() {
        @Override
        public int compare(DopravniProstredek h1, DopravniProstredek h2) {
            if(h1.getId()==h2.getId())
                return 0;
            return 1;
        }
    });
        btnPrvni = new Button();
        btnPrvni.setText("prvni");
        btnPrvni.setLayoutX(910);
        btnPrvni.setLayoutY(50);
        btnPrvni.setMinWidth(80);
        
        btnPrvni.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                handleBtnPrvni();
            }
        });
        
        btnDalsi = new Button();
        btnDalsi.setText("dalsi");
        btnDalsi.setLayoutX(910);
        btnDalsi.setLayoutY(80);
        btnDalsi.setMinWidth(80);
        
        btnDalsi.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        btnPosledni = new Button();
        btnPosledni.setText("posledni");
        btnPosledni.setLayoutX(910);
        btnPosledni.setLayoutY(110);
        btnPosledni.setMinWidth(80);
        
        btnPosledni.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        
        Label labelProchazeni = new Label();
        labelProchazeni.setText("Procházení");
        labelProchazeni.setLayoutX(910);
        labelProchazeni.setLayoutY(20);
        
        Label labelPrikazy = new Label();
        labelPrikazy.setText("Příkazy");
        labelPrikazy.setLayoutX(910);
        labelPrikazy.setLayoutY(140);
        
        btnEdituj = new Button();
        btnEdituj.setText("Edituj");
        btnEdituj.setLayoutX(910);
        btnEdituj.setLayoutY(170);
        btnEdituj.setMinWidth(80);
        
        btnEdituj.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                handleBtnEdituj();
            }
        });
        
        btnVyjmi = new Button();
        btnVyjmi.setText("Vyjmi");
        btnVyjmi.setLayoutX(910);
        btnVyjmi.setLayoutY(200);
        btnVyjmi.setMinWidth(80);
        
        btnVyjmi.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                handleBtnVyjmi();
                
            }
        });
        
        btnZobraz = new Button();
        btnZobraz.setText("Zobraz");
        btnZobraz.setLayoutX(910);
        btnZobraz.setLayoutY(230);
        btnZobraz.setMinWidth(80);
        
        btnZobraz.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        
        btnClear = new Button();
        btnClear.setText("Clear");
        btnClear.setLayoutX(910);
        btnClear.setLayoutY(260);
        btnClear.setMinWidth(80);
        
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        
        btnGeneruj = new Button();
        btnGeneruj.setText("Generuj");
        btnGeneruj.setLayoutX(10);
        btnGeneruj.setLayoutY(530);
        btnGeneruj.setMinWidth(60);
        
        btnGeneruj.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                handleBtnGeneruj();
                
            }
        });
        
        btnUloz = new Button();
        btnUloz.setText("Ulož");
        btnUloz.setLayoutX(80);
        btnUloz.setLayoutY(530);
        btnUloz.setMinWidth(60);
        
        btnUloz.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                handleBtnUloz();
                
            }
        });
        btnNacti = new Button();
        btnNacti.setText("Načti");
        btnNacti.setLayoutX(150);
        btnNacti.setLayoutY(530);
        btnNacti.setMinWidth(60);
        
        btnNacti.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                handleBtnNacti();
                
            }
        });
        Label labelNovy = new Label();
        labelNovy.setText("Nový:");
        labelNovy.setLayoutX(220);
        labelNovy.setLayoutY(530);
        
        choiceBoxNovy = new ChoiceBox();
        choiceBoxNovy.getItems().addAll("osobní automobil", "nákladní automobil", "dodávka", "traktor");
        choiceBoxNovy.setLayoutX(260);
        choiceBoxNovy.setLayoutY(530);
        choiceBoxNovy.getSelectionModel().selectedIndexProperty().addListener(
                (obs, oldValue, newValue) -> {
                    handleDialogNovy(choiceBoxNovy.getSelectionModel().getSelectedIndex());
                });
        
        Label labelFiltr = new Label();
        labelFiltr.setText("Filtr:");
        labelFiltr.setLayoutX(390);
        labelFiltr.setLayoutY(530);
        
        choiceBoxFiltr = new ChoiceBox(seznamTypu);
        choiceBoxFiltr.setValue(ProstredekTyp.NON_FILTER);
        choiceBoxFiltr.setLayoutX(420);
        choiceBoxFiltr.setLayoutY(530);
        choiceBoxFiltr.getSelectionModel().selectedIndexProperty().addListener(
                (obs, oldValue, newValue) -> {
                    
                    switch (choiceBoxFiltr.getSelectionModel().getSelectedIndex()) {
                        case 0:
                            filtrValue = 0;
                            
                            break;
                        case 1:
                            filtrValue = 1;
                            
                            break;
                        case 2:
                            filtrValue = 2;
                            
                            break;
                        
                        case 3:
                            filtrValue = 3;
                            
                            break;
                        case 6:
                            filtrValue = 6;
                            
                            break;
                    }
                    handleObnovPrvkyVListView();
                    
                });
        
        btnZalohuj = new Button();
        btnZalohuj.setText("Zálohuj");
        btnZalohuj.setLayoutX(680);
        btnZalohuj.setLayoutY(530);
        btnZalohuj.setMinWidth(60);
        
        btnZalohuj.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                handleBtnZalohuj();
                
            }
        });
        btnObnov = new Button();
        btnObnov.setText("Obnov");
        btnObnov.setLayoutX(750);
        btnObnov.setLayoutY(530);
        btnObnov.setMinWidth(60);
        
        btnObnov.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                handleBtnObnov();
            }
        });
        btnZrus = new Button();
        btnZrus.setText("Zruš");
        btnZrus.setLayoutX(820);
        btnZrus.setLayoutY(530);
        btnZrus.setMinWidth(60);
        
        btnZrus.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                handleBtnZrus();
            }
        });
        
        listView = new ListView<DopravniProstredek>();
        listView.setEditable(true);
        
        listView.setMaxSize(900, 520);
        listView.setMinSize(900, 520);
        
        Pane root = new Pane();
        
        root.getChildren().addAll(listView, btnPrvni, labelProchazeni, btnDalsi, btnPosledni, labelPrikazy, btnEdituj, btnVyjmi, btnZobraz, btnClear, btnGeneruj, btnUloz, btnNacti, labelNovy, choiceBoxNovy,
                labelFiltr, choiceBoxFiltr, btnZalohuj, btnObnov, btnZrus);
        
        Scene scene = new Scene(root, 1000, 570);
        
        listView.setLayoutX(0);
        listView.setLayoutY(0);
        primaryStage.setTitle("Správa dopravních prostředků");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public void handleBtnPrvni() {
        control.prejdiNaPrvniPolozku();
        
    }
    
    public void handleDialogNovy(int i) {
        System.out.println("jur");
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Novy");
        ButtonType novy = new ButtonType("Novy", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(novy, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        System.out.println(listView.getSelectionModel().getSelectedIndex());
        spz = new TextField();
        hmotnost = new TextField();
        grid.add(new Label("Spz"), 0, 0);
        grid.add(spz, 1, 0);
        grid.add(new Label("Hmotnost"), 0, 1);
        grid.add(hmotnost, 1, 1);
        switch (i) {
            case 0:
                pocetSedadel = new TextField();
                grid.add(new Label("Počet sedadel"), 0, 2);
                grid.add(pocetSedadel, 1, 2);
                break;
            case 1:
                nosnost = new TextField();
                grid.add(new Label("Nosnost"), 0, 2);
                grid.add(nosnost, 1, 2);
                break;
            case 2:
                
                typ = new ChoiceBox();
                typ.getItems().addAll("dvojkabina", "nástavba", "valník");
                grid.add(typ, 1, 2);
                grid.add(new Label("typ dodávky"), 0, 2);
                break;
            case 3:
                tah = new TextField();
                grid.add(new Label("Tah"), 0, 2);
                grid.add(tah, 1, 2);
                break;
        }
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter((ButtonType dialogButton) -> {
            if (dialogButton == novy) {
                
                switch (i) {
                    case 0:
                        
                        control.vlozPolozku(new OsobniAutomobil(spz.getText(), Barva.BILA, Integer.valueOf(pocetSedadel.getText()), Integer.valueOf(hmotnost.getText())));
                        break;
                    case 1:
                        control.vlozPolozku(new NakladniAutomobil(spz.getText(), Integer.valueOf(hmotnost.getText()), Integer.valueOf(nosnost.getText())));
                        break;
                    case 2:
                        
                        control.vlozPolozku(new Dodavka(spz.getText(), Integer.valueOf(hmotnost.getText()), DodavkaTyp.decode(typ.getSelectionModel().getSelectedItem())));
                        break;
                    case 3:
                        control.vlozPolozku(new Traktor(spz.getText(), Integer.valueOf(hmotnost.getText()), Long.valueOf(tah.getText())));
                        break;
                }
            }
            
            return "ok";
        });
        Optional<String> result = dialog.showAndWait();
           
            handleObnovPrvkyVListView();
    }
    
    public void handleBtnDalsi() {
        control.prejdiNaDalsiPolozku();
    }
    
    public void handleBtnPosledni() {
        control.prejdiNaPosledniPolozku();
        
    }
    
    public void handleBtnEdituj() {
                  Dialog<String> dialog = new Dialog<>();
dialog.setTitle("Editace");




// Set the button types.
ButtonType edituj = new ButtonType("Edituj", ButtonData.OK_DONE);
dialog.getDialogPane().getButtonTypes().addAll(edituj, ButtonType.CANCEL);

// Create the spz and hmotnost labels and fields.
GridPane grid = new GridPane();
grid.setHgap(10);
grid.setVgap(10);
grid.setPadding(new Insets(20, 150, 10, 10));
prostredek=listView.getSelectionModel().getSelectedItem();
System.out.println(prostredek.toString());
System.out.println(listView.getSelectionModel().getSelectedIndex());
spz = new TextField();
spz.setText(prostredek.getSpz());
hmotnost = new TextField();
hmotnost.setText(Float.toString(prostredek.getHmotnost()));

grid.add(new Label("Spz"), 0, 0);
grid.add(spz, 1, 0); 
grid.add(new Label("Hmotnost"), 0, 1);
grid.add(hmotnost, 1, 1);
switch(prostredek.getType()){
       case OSOBNI_AUTOMOBIL:
         OsobniAutomobil oa=(OsobniAutomobil) prostredek;  
               pocetSedadel = new TextField();
               pocetSedadel.setText(Integer.toString(oa.getPocetSedadel()));

barva= new ChoiceBox();
barva.getItems().addAll("bílá", "černá", "modrá", "zelená", "červená");
barva.setValue(oa.getBarva().toString());

grid.add(new Label("Počet sedadel"), 0, 2);
grid.add(pocetSedadel, 1, 2);
grid.add(new Label("Barva"), 0, 3);
grid.add(barva,1,3);
               
                break;
            case DODAVKA:
               typ= new ChoiceBox();
typ.getItems().addAll("dvojkabina", "nástavba", "valník");
              grid.add(typ,1,2); 
              grid.add(new Label("typ dodávky"), 0, 2);
               
                break;
            case NAKLADNI_AUTMOBIL:
                nosnost = new TextField();
                pocetNaprav = new TextField();
               grid.add(new Label("Nosnost"), 0, 2);
grid.add(nosnost, 1, 2);
grid.add(new Label("Počet naprav"), 0, 3);
grid.add(pocetNaprav, 1, 3);
                break;
            case TRAKTOR:
                 tah = new TextField();
                grid.add(new Label("Tah"), 0, 2);
grid.add(tah, 1, 2);
                break; 
}

dialog.getDialogPane().setContent(grid);



// Convert the result to a spz-hmotnost-pair when the login button is clicked.
dialog.setResultConverter(dialogButton -> {
    if (dialogButton == edituj) {
        edit= (line)->{
         System.out.println("jur");
         line.setSpz(spz.getText());
         line.setHmotnost(0);
         switch (line.getType()) {
           case OSOBNI_AUTOMOBIL:
              OsobniAutomobil auto = (OsobniAutomobil) line;
                auto.setPocetSedadel(Integer.valueOf(pocetSedadel.getText()));
                auto.setBarva(Barva.BILA);
                break;
            case DODAVKA:
                Dodavka dodavka=(Dodavka) line;
                dodavka.setTyp(DodavkaTyp.VALNIK);
                
                        
                break;
            case NAKLADNI_AUTMOBIL:
                NakladniAutomobil nakladak= (NakladniAutomobil) line;
                nakladak.setNosnost(Integer.valueOf(nosnost.getText()));
                nakladak.setPocetNaprav(Integer.valueOf(pocetNaprav.getText()));
                break;
            case TRAKTOR:
                Traktor traktor = (Traktor) line;
                traktor.setTah(Long.valueOf(tah.getText()));
                break;
            default:
            }
         return line;
     };
        System.out.println(listView.getSelectionModel().getSelectedIndex());
     System.out.println(prostredek.toString());
     control.nastavAktualniPolozku(prostredek);
   control.editujAktualniPolozku(edit);
   handleObnovPrvkyVListView();
        return null;
    }
    return null;
});

//Optional<Pair<String, String>> result = dialog.showAndWait();
Optional<String> result = dialog.showAndWait();
control.editujAktualniPolozku(edit);
        }
    
    public void handleBtnVyjmi() {
        control.nastavAktualniPolozku(listView.getSelectionModel().getSelectedItem());
        System.out.println(listView.getSelectionModel().getSelectedItem().toString());
        control.prejdiNaPrvniPolozku();
        System.out.println(control.vyjmiAktualniPolozku().toString());
        
        listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
        
    }
    
    public void handleBtnGeneruj() {
        control.zrus();
        control.generujData(20);
        handleObnovPrvkyVListView();
    }
    
    public void handleBtnUloz() {
        mapperUloz = (line)
                -> {
            String text = "";
            switch (line.getType()) {
                case OSOBNI_AUTOMOBIL:
                    OsobniAutomobil oa = (OsobniAutomobil) line;
                    text = "oa, " + oa.getSpz() + ", " + oa.getHmotnost() + ", " + oa.getBarva();
                    break;
                case DODAVKA:
                    Dodavka dodavka = (Dodavka) line;
                    text = "do, " + dodavka.getSpz() + ", " + dodavka.getHmotnost() + ", " + dodavka.getTyp();
                    break;
                case NAKLADNI_AUTMOBIL:
                    NakladniAutomobil na = (NakladniAutomobil) line;
                    text = "na, " + na.getSpz() + ", " + na.getHmotnost() + ", " + na.getPocetNaprav();
                    break;
                case TRAKTOR:
                    Traktor tr = (Traktor) line;
                    text = "tr, " + tr.getSpz() + ", " + tr.getHmotnost() + ", " + tr.getTah();
                    break;
            }
            return text;
        };
        control.ulozTextSoubor(ZALOHA_FILE_SOUBOR, mapperUloz);
        
    }
    
    public void handleBtnNacti() {
        mapperNacti = (line) -> {
            DopravniProstredek prostredek = null;
            if (line.length() == 0) {
                return prostredek;
            }
            String[] items = line.split(",");
            System.out.println(items[0]);
            String spz = items[1].trim();
            float hmotnost = Float.valueOf(items[2]);
            switch (items[0].trim().toLowerCase(Locale.US)) {
                case "oa":
                    Barva barva = Barva.decode(items[3].trim());
                    prostredek = new OsobniAutomobil(spz, barva, random.nextInt(10), hmotnost);
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
                    long tah = Long.valueOf(items[3].trim());
                    prostredek = new Traktor(spz, hmotnost, tah);
                    break;
            }
            return prostredek;
        };
        control.nactiTextSoubor(ZALOHA_FILE_SOUBOR, mapperNacti);
        handleObnovPrvkyVListView();
        
    }
    
    public void handleBtnZalohuj() {
        control.ulozDoSouboru(ZALOHA_FILE_NAME);
        
    }
    
    public void handleBtnObnov() {
        control.nactiZeSouboru(ZALOHA_FILE_NAME);
        handleObnovPrvkyVListView();
    }
    
    public void handleBtnZrus() {
        control.zrus();
        handleObnovPrvkyVListView();
    }
    
    public void handleObnovPrvkyVListView() {
        listView.getItems().removeAll(seznam);
        seznam.removeAll(seznam);
        switch (ProstredekTyp.values()[filtrValue]) {
            case OSOBNI_AUTOMOBIL:
                control.stream()
                        .filter(filterAutomobil)
                        .forEach(s -> seznam.add((DopravniProstredek) s));
                
                break;
            case DODAVKA:
                control.stream()
                        .filter(filterDodavka)
                        .forEach(s -> seznam.add((DopravniProstredek) s));
                
                break;
            case NAKLADNI_AUTMOBIL:
                control.stream()
                        .filter(filterNakladniAutomobil)
                        .forEach(s -> seznam.add((DopravniProstredek) s));
                
                break;
            case TRAKTOR:
                control.stream()
                        .filter(filterTraktor)
                        .forEach(s -> seznam.add((DopravniProstredek) s));
                break;
            case NON_FILTER:
                control.stream()
                        .forEach(s -> seznam.add((DopravniProstredek) s));
                break;
            
        }
        
        seznamProstredku = FXCollections.observableArrayList(seznam);
        
        listView.setItems(seznamProstredku);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
