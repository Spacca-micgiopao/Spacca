package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;
public class FilmatiFinaliController {
	private Main main;
	private Stage stage;
	private int vittoriaSuTavoloG1;
	private int vittoriaSuTavoloG2;
	private String player1Name;
	private String player2Name;
	
	@FXML
	MediaView MediaViewFinale;
	@FXML
	Label LabelGiocatore;
	
	
	public void setMain(Main main) {
	    	this.main= main;
    }
	public void getVittoriaSuTavolo() {
		this.vittoriaSuTavoloG1= GameController.getVittoriaSuTavoloG1();
		this.vittoriaSuTavoloG2= GameController.getVittoriaSuTavoloG2();
	}
	public void getPlayerName() {
		this.player1Name = ControllerPrePartita.getPlayer1();
		this.player2Name =  ControllerPrePartita.getPlayer2();
	}
	
	public void initialize() {
		getPlayerName();
		getVittoriaSuTavolo();
		//VIDEO NEL CASO  DI VINCITA
		
        
        
        if(vittoriaSuTavoloG1>vittoriaSuTavoloG2) {
        	String videoPath1 = "src/AnimazioniVincita/AnimazioneVincita.mp4";
			Media media = new Media(new File(videoPath1).toURI().toString());
			// Crea il media player
	        MediaPlayer mediaPlayer = new MediaPlayer(media);
	
	        // Crea la vista media
	        MediaView mediaView = new MediaView(mediaPlayer);
	        
	        MediaViewFinale.setMediaPlayer(mediaPlayer);
	
	        // Imposta le dimensioni della vista media
	        mediaView.setFitWidth(1280); // Imposta la larghezza desiderata
	        mediaView.setFitHeight(800); // Imposta l'altezza desiderata
	        LabelGiocatore.setText( this.player1Name+" VINCE !");
	        LabelGiocatore.setStyle("-fx-background-color: transparent;");
	        LabelGiocatore.setVisible(false);
	        // Listener per monitorare il tempo di riproduzione del video
	        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
	            // Definire il punto (in secondi) in cui mostrare la Label
	            double tempoDiVisualizzazione = 10; // Esempio: mostrare la Label a 10 secondi nel video
	
	            // Se il tempo corrente di riproduzione supera il punto desiderato, mostra la Label
	            if (newValue.toSeconds() >= tempoDiVisualizzazione) {
	                LabelGiocatore.setVisible(true);
	            }
	        });
	
	        
	        
	        // Riproduci il video
	        mediaPlayer.play();
        }
        if(vittoriaSuTavoloG2>vittoriaSuTavoloG1) {
        	String videoPath1 = "src/AnimazioniVincita/AnimazioneVincita.mp4";
        	Media media = new Media(new File(videoPath1).toURI().toString());
			// Crea il media player
	        MediaPlayer mediaPlayer = new MediaPlayer(media);
	
	        // Crea la vista media
	        MediaView mediaView = new MediaView(mediaPlayer);
	        
	        MediaViewFinale.setMediaPlayer(mediaPlayer);
	
	        // Imposta le dimensioni della vista media
	        mediaView.setFitWidth(1280); // Imposta la larghezza desiderata
	        mediaView.setFitHeight(800); // Imposta l'altezza desiderata
        	LabelGiocatore.setText( this.player2Name+" VINCE !");
 	        LabelGiocatore.setStyle("-fx-background-color: transparent;");
 	        LabelGiocatore.setVisible(false);
 	        // Listener per monitorare il tempo di riproduzione del video
 	        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
 	            //punto (in secondi) in cui mostrare la Label
 	            double tempoDiVisualizzazione = 10; // mostrare la Label a 10 secondi nel video
 	
 	            // Se il tempo corrente di riproduzione supera il punto desiderato, mostra la Label
 	            if (newValue.toSeconds() >= tempoDiVisualizzazione) {
 	                LabelGiocatore.setVisible(true);
 	            }
 	        });
 	
 	    
 	        
 	        // Riproduci il video
 	        mediaPlayer.play();
        }
        //VIDEO  NEL  CASO DI PAREGGIO
        
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
        
        
        LabelGiocatore.setText( "PAREGGIO");
        LabelGiocatore.setStyle("-fx-background-color: transparent;");
	    LabelGiocatore.setLayoutX(420);
	    LabelGiocatore.setLayoutY(369);
	        
	        
	     // Riproduci il video
	     mediaPlayer.play();
        }
	}

		


	public void setStage(Stage stage) {
		this.stage =stage;
		
	}
	public void handleBottoneUscita(ActionEvent event) {
    	stage.close();
    }
}
       