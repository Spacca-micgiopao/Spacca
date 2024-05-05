package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
//GESTISCE ANNUNCIO VITTORIA DELLE SINGOLE PARTITE
public class FilmatiFinaliController {
	private Main main;
	private Stage stage;
	private int vittoriaSuTavoloG1;
	private int vittoriaSuTavoloG2;
	private String player1Name;
	private String player2Name;
	private String vincitore;
	@FXML
	MediaView MediaViewFinale;
	@FXML
	Label LabelGiocatore;
	
	//METODI SET E GET
	public void setMain(Main main) {
	    	this.main= main;
    }
	public void setStage(Stage stage) {
		this.stage =stage;
		
	}
	public void getVittoriaSuTavolo() {
		this.vittoriaSuTavoloG1= GameController.getVittoriaSuTavoloG1();
		this.vittoriaSuTavoloG2= GameController.getVittoriaSuTavoloG2();
	}
	
	public void getPlayerName() {
	    	
	        this.player1Name = ControllerPrePartita.getPlayer1(); 
	        this.player2Name = ControllerPrePartita.getPlayer2();
	}
	    	
	public void initialize() {
		getVittoriaSuTavolo(); 
		getPlayerName();
		//se pareggio
		 if(vittoriaSuTavoloG1 ==  vittoriaSuTavoloG2) {
		        String videoPathPareggio = "src/AnimazioniVincita/AnimazionePareggio.mp4";
				Media media = new Media(new File(videoPathPareggio).toURI().toString());
				// Crea il media player
		        MediaPlayer mediaPlayer = new MediaPlayer(media);

		        // Crea la vista media
		        MediaView mediaView = new MediaView(mediaPlayer);
		        
		        MediaViewFinale.setMediaPlayer(mediaPlayer);

		        // Imposta le dimensioni della vista media
		        mediaView.setFitWidth(1280); // Imposta la larghezza desiderata
		        mediaView.setFitHeight(800); // Imposta l'altezza desiderata
		        
			     // Riproduci il video
			     mediaPlayer.play();
        }else {
	
			String videoPath1 = "src/AnimazioniVincita/AnimazioneVincita .mp4";
			Media media = new Media(new File(videoPath1).toURI().toString());
			// Crea il media player
	        MediaPlayer mediaPlayer = new MediaPlayer(media);
	
	        // Crea la vista media
	        MediaView mediaView = new MediaView(mediaPlayer);
	        
	        MediaViewFinale.setMediaPlayer(mediaPlayer);
	
	        // Imposta le dimensioni della vista media
	        mediaView.setFitWidth(1280); // Imposta la larghezza desiderata
	        mediaView.setFitHeight(800); // Imposta l'altezza desiderata
	        if(vittoriaSuTavoloG1>vittoriaSuTavoloG2) {
	        	vincitore= this.player1Name;
	        }
	        if(vittoriaSuTavoloG2>vittoriaSuTavoloG1) {
	        	vincitore = this.player2Name;
	        }
	        LabelGiocatore.setText(vincitore+" VINCE !");
	        mediaPlayer.play();
        }
	
	
        
	}

	public void handleBottoneUscita(ActionEvent event) {
    	stage.close();
    }
}
       