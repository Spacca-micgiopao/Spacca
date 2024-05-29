package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
	public static int flag=0;
	
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
	    	
	       	this.player1Name = GameController.player1Name;
	        this.player2Name = GameController.player2Name;
	}
	    	
	public void initialize() {
		
		getVittoriaSuTavolo(); 
		getPlayerName();
		
		//se pareggio
		 if(vittoriaSuTavoloG1 ==  vittoriaSuTavoloG2) {
			   String videoPathPareggio = "SPACCA/src/AnimazioniVincita/AnimazionePareggio.mp4";
	

		       
			     // Riproduci il video
			     //mediaPlayer.play();
        }else {
	
			String videoPath1 = "src/AnimazioniVincita/AnimazioneVincita .mp4";
	
	

	        if(vittoriaSuTavoloG1>vittoriaSuTavoloG2) {
	        	vincitore= this.player1Name;
	        }
	        if(vittoriaSuTavoloG2>vittoriaSuTavoloG1) {
	        	vincitore = this.player2Name;
	        }
	        LabelGiocatore.setText(vincitore+" VINCE !");
	        //Aggiunge una vittoria nella classifica
	        for(int i = 0;i<ClassificaController.LSGiocatori.size();i++) {
	        	if(ClassificaController.LSGiocatori.get(i).getNome().equals(vincitore)) {
	        		ClassificaController.LSGiocatori.get(i).setVittorie();
	        	}
	        }
	        Salvataggi.file.delete();
	        ClassificaController.salva();
	        //mediaPlayer.play();
        }
	
	
        
	}

	public void handleBottoneUscita(ActionEvent event) {
    	stage.close();
    }
	
	public void handleBottoneMenu(ActionEvent event) {
		try {
			main.showMainMenuScene();
		} catch (Exception e) {
			System.out.println("errore nel caricamento del main menu");
			e.printStackTrace();
		}
    }
}
       