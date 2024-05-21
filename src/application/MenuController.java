package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController{
	
	private Stage stage;
	private static boolean Torneo = false;
	private Main main;
	
	@FXML
	private Button VediClassifica, CreaPartita, CreaTorneo, Logout;

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
			e.printStackTrace();
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
	
	//Entra nella classifica
	public void VaiaClassifica(ActionEvent event) throws IOException {
		try {
			main.showClassificaScene();
		} catch (Exception e) {
			System.out.println("errore nel caricamento scene classifica");
			e.printStackTrace();
		}
	}
	
	//Vai alla creazione del torneo
	public void VaiaTorneo(ActionEvent event) throws IOException {
		Torneo=true;
		try {
			main.showPreTorneoScene();
		}
		catch(Exception e) {
			System.out.println("errore nel caricamento scena PreTorneo");
		}
	}
	
	//Per andare nel pannello di controllo dal manu principale
	public void VaiAdminPanel(ActionEvent event) throws IOException {
		try {
			main.showAdminPanel();;
		} catch (Exception e) {
			System.out.println("errore");
			e.printStackTrace();
		}
	}
	
	
	public static boolean getTorneo() {
		return Torneo;
	}
}
