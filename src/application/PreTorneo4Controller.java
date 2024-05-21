package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PreTorneo4Controller {
	private Main main;
	private Stage stage;
	private SalvaTorneo salvatorneo;
	protected int c;
	protected static String[] G;  //contenente tutti i giocatori del Torneo
	protected static List<String> partite = new ArrayList<>(); //contenente tutte partite torneo in formato "giocatore1 vs giocatore2"
	MediaPlayer player;
	@FXML
	private TextField InputG1;
	@FXML
	private TextField InputG2;
	@FXML
	private TextField InputG3;
	@FXML
	private TextField InputG4;
	
	 @FXML
	private TextField[] InsiemeTextField;
	@FXML 
	private Label G1P1;
	@FXML 
	private Label G2P1;
	@FXML 
	private Label G1P2;
	@FXML 
	private Label G2P2;
	@FXML 
	private Label G1P3;
	@FXML 
	private Label G2P3;
	@FXML 
	private Label G1P4;
	@FXML 
	private Label G2P4;
	@FXML 
	private Label G1P5;
	@FXML 
	private Label G2P5;
	@FXML 
	private Label G1P6;
	@FXML 
	private Label G2P6;
	@FXML
	private Label[] labelGiocatori;
	//METODI SET E GET
	public void setStage(Stage stage) {
        this.stage = stage;
    }
	public void setMain(Main main) {
		this.main = main;
	}
	public static String[] getG() {
		return G;
	}
	public static List<String> getPartite(){
		return  partite;
	}
	
	public void insertMusic() {
    	//MUSICA
        	try {
    			 Media sound = new Media(new File("src/Musica/marioMusic.mp3").toURI().toString());
    			   player = new MediaPlayer(sound);
    			   //continua sempre a suonare
    			   player.setCycleCount(MediaPlayer.INDEFINITE);
    			   player.play();
    		}catch(Exception e ) {
    			System.out.println("errore riproduzione");
    		}
     }
	//Inizializzazione
	public void initialize() {
		insertMusic();
		 InsiemeTextField = new TextField[]{InputG1,InputG2,InputG3,InputG4};
		 G = new String[InsiemeTextField.length];
		 labelGiocatori = new Label[] {G1P1, G2P1,G1P2,G2P2,G1P3,G2P3,G1P4,G2P4,G1P5,G2P5,G1P6,G2P6}; //per  visualizzare i giocatori nell'elenco  partite
	}
	public void HandleConfermaGiocatori(ActionEvent event) throws IOException {
		for(int i=0;i<4;i++) {
			G[i]= InsiemeTextField[i].getText().replaceAll("\\s+", "");
		}
		//DA INSERIRE CONTROLLI PER VERIFICARE CORRETTEZZA NOMI INSERITI
		inserimentoPartite();
		//quando si confermano i nomi giocatori viene visualizzato elenco di tutte le partite
	}
	public void inserimentoPartite() {
		 partite.add(G[0] + " vs " + G[1]);
		 partite.add(G[2] + " vs " + G[3]);
		 partite.add(G[1] + " vs " + G[2]);
		 partite.add(G[3] + " vs " + G[0]);
		 partite.add(G[0] + " vs " + G[2]);
		 partite.add(G[1] + " vs " + G[3]);
		 int partitaIndex =0;
	     for(int i=0;i<6;i++) {
	    	String partita= partite.get(i);
	    	String[] giocatori = partita.split(" vs ");
	    	String giocatore1= giocatori[0];
	    	String giocatore2= giocatori[1];
	    	labelGiocatori[partitaIndex].setText(giocatore1);
            labelGiocatori[partitaIndex + 1].setText(giocatore2);
            partitaIndex+=2;
	      }
	}
	public void handleBottoneIniziaAGiocare(ActionEvent event) {
    	player.stop();
    	try {
    		main.showScenaGiocoScene();
    	}catch(Exception e) {
    		System.out.println("Errore nel caricamento scenaGioco");
    	}
    }
	public void handleBottoneUscita(ActionEvent event) {
    	stage.close();
    }
	public void handleBottoneMenu(ActionEvent event) {
		player.stop();
    	try {
    		main.showMainMenuScene();
    	}catch(Exception e) {
    		System.out.println("errore nel caricamento main menu");
    	}
    }




}


