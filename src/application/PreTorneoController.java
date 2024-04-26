package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class PreTorneoController implements Initializable {

	private Main main;
	private Stage stage;
	private String[] tipiTorneo= {"Torneo con 4 giocatori","Torneo con robot"};
	private int numeroGiocatori;
	MediaPlayer player;
	@FXML
	private ChoiceBox<String> ScegliTipoTorneo;
	
	public void setStage(Stage stage) {
        this.stage = stage;
    }
	public void setMain(Main main) {
		this.main = main;
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		insertMusic();
		// TODO Auto-generated method stub
		ScegliTipoTorneo.getItems().addAll(tipiTorneo);
		ScegliTipoTorneo.setOnAction(this::getTipoTorneo);
	}
	public void getTipoTorneo(ActionEvent event) {
		String tipoTorneo = ScegliTipoTorneo.getValue();
		if(tipoTorneo=="Torneo con 4 giocatori") {
			numeroGiocatori =4;
		}
		
		if(tipoTorneo=="Torneo con robot") {
			//TorneoConRobot=true;
		}
	}
	public void handleConfermaTipoTorneo(ActionEvent event) {
    	if(numeroGiocatori==4) {
    		player.stop();
    		try {
				main.showPreTorneo4Scene();
			}catch(Exception e) {
				System.out.println("errore caricamento preTorneo4");
			}
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
