package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminPanelController implements Initializable{
	
	private Stage stage;
	private Main main;
	@FXML
	private Label LabelStatistiche;
	@FXML
	private TextField Usernamechange;
	@FXML
	private TextField Passwordchange;
	@FXML
	private ChoiceBox<String> ScegliPartita,ScegliTorneo;
	
	private File DatiAccesso = new File("src/Data/DatiAccesso.txt");
	public void setMain(Main main) {
    	this.main= main;
    }
	
	public void setStage(Stage stage) {
			this.stage= stage;
	}
	
	private int Npartite;
	
	//Per eliminare tutti i dati salvati
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
		DatiAccesso.delete();
		LoginController.accesso.put("admin", "");
		Npartite = 0;
		Scrivistats();
		SalvaTorneo.Cancella();
		try {
			main.showLoginScene();
			main.showAlert();
		}
		catch(Exception e) {
			System.out.println("errore nel caricamento scena Login");
		}
	}
	
	//Per scrivere le statistiche e al caso aggiornarle
	public void Scrivistats() {
		LabelStatistiche.setText("Numero di giocatori registrati: "+ClassificaController.LSGiocatori.size()+"\n"+
				 "Numero di partite salvate: "+Npartite+"\n");
	}
	
	//Modifica username e password e fa il logout
	public void CambioAccesso(ActionEvent event) throws IOException {
		String username = Usernamechange.getText();
		String password = Passwordchange.getText();
		if(!username.isBlank() && !password.isBlank()) {
				LoginController.SalvaAccesso(DatiAccesso, username+"", password+"");
				try {
					main.showLoginScene();
				}
				catch(Exception e) {
					System.out.println("errore nel caricamento scena Login");
				}
		}
	}
	
	//Cancella un torneo
	public void Deletetorneo(ActionEvent event) throws IOException {
		if(ScegliTorneo.getValue() != null) {
			SalvaTorneo.setFile(ScegliTorneo.getValue());
			SalvaTorneo.Elimina();
			ScegliTorneo.getItems().clear();
			SalvaTorneo.Preparazione();
			if(SalvaTorneo.Tornei != null)
				ScegliTorneo.getItems().addAll(SalvaTorneo.Tornei);
		}
	}
	
	//Cancella una partita
	public void Deletepartita(ActionEvent event) throws IOException {
		if(ScegliPartita.getValue() != null) {
			Salvataggi.setFile(ScegliPartita.getValue());
			Salvataggi.Elimina();
			ScegliPartita.getItems().clear();
			InfoPartita.Preparazione();
			if(InfoPartita.Partite != null)
				ScegliPartita.getItems().addAll(InfoPartita.Partite);
		}
	}
	
	//Per tornare al menuprincipale
	public void Backtomenu(ActionEvent event) throws IOException {
		try {
			main.showMainMenuScene();
		} catch (Exception e) {
			System.out.println("errore nel caricamento del main menu");
			e.printStackTrace();
		}
	}
	public void bottoneUscita(ActionEvent event) {
		stage.close();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Scrivistats();
		ScegliPartita.getItems().clear();
		ScegliTorneo.getItems().clear();
		if(InfoPartita.Partite != null)
			ScegliPartita.getItems().addAll(InfoPartita.Partite);
		if(SalvaTorneo.Tornei != null)
			ScegliTorneo.getItems().addAll(SalvaTorneo.Tornei);
	}
}
