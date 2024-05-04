package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.nio.file.Files;
import java.nio.file.Path;

public class MenuController {
	
	
	private Stage stage;
	private Main main;
	
	@FXML
	private Button VediClassifica,CreaPartita,CreaTorneo,Logout;
	
	InfoPartita infopartita = new InfoPartita();
	
	public void setMain(Main main) {
    	this.main= main;
    }
	
	public void setStage(Stage stage) {
			this.stage= stage;
	}
	
	//per creare una partita,il metodo preparazione prepara la lista delle partite salvate
	public void VaiaPrePartita(ActionEvent event) throws IOException {
		try {
			main.showPrePartitaScene();
		} catch (Exception e) {
			System.out.println("errore nel caricamento scena prePartita");
		}
	}
	
	//per fare il logout e tornare alla pagina di login
	public void Logout(ActionEvent event) throws IOException {
		try {
			main.showLoginScene();
		}
		catch(Exception e) {
			System.out.println("errore nel caricamento scena Login");
		}
	}
	
	//per fare il logout e tornare alla pagina di login infopartita serve per creare la lista dei giocatori la prima volta
	public void VaiaClassifica(ActionEvent event) throws IOException {
		try {
			main.showClassificaScene();
		} catch (Exception e) {
			System.out.println("errore nel caricamento scene classifica");
			e.printStackTrace();
		}
	}
		
	//Per cancellare tutti i dati salvati sui file e i file di salvataggio
	//La prima parte cancella la directory che contiene i salvataggi
	public void Pulisci(ActionEvent event) throws IOException {
		File dir = new File("src/Salvataggi/");
		String[] n = dir.list();
			if(dir.isDirectory() && dir.exists()) {
				for(int i = 0;i<n.length;i++) {
					File f = new File("src/Salvataggi/"+n[i]);
					f.delete();
				}
			}
		//Resetta il file che contiene le informazioni dei giocatori
			ClassificaController.elimina();
		}
}