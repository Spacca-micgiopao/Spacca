package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
//SCHERMATA DI VINCITA DELLE SINGOLE PARTITE DURANTE TORNEO
public class WinningController {
	private Main main;
	private Stage stage;
	public String player1Name;
	public String player2Name;
	private int vittoriaSuTavoloG1;
	private int vittoriaSuTavoloG2;
	private int numeroPartita;
	private List<String> partite = new ArrayList<>();//giocatorex vs giocatorey
	private String[] G;//tutti giocatori del  torneo
	private String[]  giocatori; //giocatori singole partite
	private static List<String> vincitori = new ArrayList<>(); //vincitori delle singole partite
	private static int[] numeroVittorieG= {0,0,0,0};//punteggio di  ogni giocatore
	private MediaPlayer player;
	@FXML
	private Label  LabelVincita;
	//METODI SET E GET
	public void setMain(Main main) {
    	this.main= main;
	}
	public void setStage(Stage stage) {
		this.stage =stage;
		
	}
	public void setPartite() {
    	this.partite = PreTorneo4Controller.getPartite();
    }
	public void setVittoriaSuTavolo() {
		this.vittoriaSuTavoloG1= GameController.getVittoriaSuTavoloG1();
		this.vittoriaSuTavoloG2= GameController.getVittoriaSuTavoloG2();
	}
	public void setNumeroPartita() {
		this.numeroPartita = GameController.getNumeroPartita();
	
	}
	private void setG() {
		this.G= PreTorneo4Controller.getG();
		
	}
	public void setPlayersNames() {
		
		String partita = partite.get(numeroPartita-1);
		giocatori = partita.split(" vs ");

		this.player1Name = giocatori[0];
		this.player2Name= giocatori[1];
    	
	}
	public static List<String> ritornaVincitori() {
		return vincitori;
	}
	public static int[] getNumeroVittorieG() {
		return numeroVittorieG;
	}
	public void insertMusic() {
    	//MUSICA
        	try {
    			 Media sound = new Media(new File("Musica/WinningMusic.mpeg").toURI().toString());
    			   player = new MediaPlayer(sound);
    			   //continua sempre a suonare
    			   player.setCycleCount(MediaPlayer.INDEFINITE);
    			   player.setVolume(0.1);
    			   //player.play();
    		}catch(Exception e ) {
    			System.out.println("errore riproduzione");
    		}
        }
	
	public void initialize() {
		
		insertMusic();
		setVittoriaSuTavolo(); 
		setNumeroPartita();
		setG();
		setPartite();
		setPlayersNames();
		
		
		if(vittoriaSuTavoloG1>vittoriaSuTavoloG2) {
			LabelVincita.setText(this.player1Name+" vince !");
			for(int i=0;i<G.length;i++) {
				if(this.player1Name.equals(G[i])) {
					numeroVittorieG[i]+=3;
					vincitori.add(this.player1Name);
				}
			}
		}
		if(vittoriaSuTavoloG1<vittoriaSuTavoloG2) {
			LabelVincita.setText(this.player2Name+" vince !");
			for(int i=0;i<G.length;i++) {
				if(this.player2Name.equals(G[i])) {
					numeroVittorieG[i]+=3;
					vincitori.add(this.player2Name);
				}
			}
		}
		if(vittoriaSuTavoloG1 == vittoriaSuTavoloG2) {
			for(int i=0;i<G.length;i++){
				if(this.player1Name.equals(G[i])) {
					numeroVittorieG[i]++;
				}
			}
			for(int i=0;i<G.length;i++) {
				if(this.player2Name.equals(G[i])) {
					numeroVittorieG[i]++;
				}
			}
			
			LabelVincita.setText(" PAREGGIO ! ");
			vincitori.add("Pareggio");
		}
	
		 
	}
	public void VaiAPartitaSuccessiva(ActionEvent event) throws IOException {
		//player.stop();
		try {
			main.showScenaGiocoScene();
		} catch (Exception e) {
			System.out.println("errore nel caricamento scene Gioco");
		}
	}
	public void handleBottoneUscita(ActionEvent event) {
    	stage.close();
    }
	
}
	
	
	
	
	
	
	
	

