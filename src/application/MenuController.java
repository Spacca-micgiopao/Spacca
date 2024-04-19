package application;

import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {
	
	
	private Stage stage;
	private Main main;
	
	@FXML
	private Button VediClassifica;
	@FXML
	private Button CreaPartita;
	@FXML
	private Button CreaTorneo;
	@FXML
	private Button Logout;
	
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
			}
		}
		
		//Per cancellare tutti i dati salvati sui file
		public void Pulisci(ActionEvent event) throws IOException {
			if(InfoPartita.DatiPartita.exists() && ClassificaController.DatiGiocatori.exists()) {
				FileWriter writer = new FileWriter(InfoPartita.DatiPartita);	
				writer.write("");
				writer.close();
				writer = new FileWriter(ClassificaController.DatiGiocatori);
				writer.write("");
				writer.close();
				//Per ricaricare i dati ora vuoti
				InfoPartita.Preparazione();
				ClassificaController.Preparazione();
			}
		}
		
}
