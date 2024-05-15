package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminPanelController implements Initializable{
	
	private Stage stage;
	private Main main;
	@FXML
	private Label LabelStatistiche;
	@FXML
	private TextField Usernamechange,Passwordchange;
	
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
		Npartite = 0;
		Scrivistats();
	}
	
	//Per scrivere le statistiche e al caso aggiornarle
	public void Scrivistats() {
		LabelStatistiche.setText("Numero di giocatori registrati: "+ClassificaController.LSGiocatori.size()+"\n"+
				 "Numero di partite salvate: "+Npartite+"\n");
	}
	
	public void CambioAccesso(ActionEvent event) {
		String Newusername, Newpassword;
		Newusername = Usernamechange.getText();
		Newpassword = Passwordchange.getText();
			LoginController.accesso.clear();
			LoginController.accesso.put("1", "2");
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Scrivistats();
	}
}
