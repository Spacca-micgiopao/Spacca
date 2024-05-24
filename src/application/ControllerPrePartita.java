package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerPrePartita implements Initializable{
	
	private Main main;
	private Stage stage;
	public static String G1,G2;
	private static boolean botGioco=false;
	//FILE per salvare le informazioni
		//istanza di infoPartita per caricare e scrivere i risultati
		InfoPartita infopartita = new InfoPartita();
		ClassificaController classficacontroller = new ClassificaController();
		//TUTTO FXML
		@FXML
		private TextField InputG1,InputG2;
		@FXML
		private TextField InputG1ConBot;
		@FXML
		private Label Errori;
		@FXML
		private Button Back;
		@FXML
		Label PartitaScelta;
		@FXML
		ChoiceBox<String> ScegliPartita;
		
		//Carica la lista delle partite salvate nella checkbox

	public void setStage(Stage stage) {
        this.stage = stage;
    }
	public void setMain(Main main) {
		this.main = main;
	}
	//Carica la lista delle partite salvate nella checkbox
	//La aggiorna ogni volta che viene aperta la pagina
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ScegliPartita.getItems().clear();
		ScegliPartita.getItems().removeAll();
		if(InfoPartita.Partite != null)
		ScegliPartita.getItems().addAll(infopartita.Partite);
	}
	
	//Costruzione di una nuova partita
	public void IniziaPartita(ActionEvent event) throws IOException {
		//Ottiene in nomi dei 2 giocatori se sono vuoti la partita non puo iniziare
		G1 = InputG1.getText().replaceAll("\\s+","");
		G2 = InputG2.getText().replaceAll("\\s+","");
		if(G1.isBlank() || G2.isBlank())
			Errori.setText("Almeno uno dei nomi non è stato inserito!");
		else if(G1.length() > 20 || G2.length() > 20) {
			Errori.setText("Almeno uno dei nomi è troppo lungo!");
		}
		else if(G1.equalsIgnoreCase(G2)) {
			Errori.setText("Non puoi giocare contro te stesso!");
		}
		//Se i nomi sono consoni agli standard del gioco posso creare la partita
		else {
			infopartita.getG(G1,G2);
			infopartita.ScriviDati();
			classficacontroller.getNomi(G1, G2);
			try {
				GameController.flag = 0;
				main.showScenaGiocoScene();
			} catch (Exception e) {
				e.printStackTrace();;
			}
		}	
	}
	//costruzione nuova partita con bot
	public void IniziaPartitaBot(ActionEvent event) throws IOException {
		//Ottiene in nomi dei 2 giocatori se sono vuoti la partita non puo iniziare
		G1 = InputG1ConBot.getText().replaceAll("\\s+","");
		G2 = "Bot";
		if(G1.isBlank() )
			Errori.setText("Il nome non è stato inserito");
		else if(G1.length() > 20 ) {
			Errori.setText("Nome inserito  è troppo lungo!");
		}
		//Se i nomi sono consoni agli standard del gioco posso creare la partita
		else {
			botGioco=true;
			infopartita.getG(G1,G2);
			infopartita.ScriviDati();
			classficacontroller.getNomi(G1, G2);
			try {
				main.showScenaGiocoScene();
			} catch (Exception e) {
				e.printStackTrace();;
			}
		}	
	}
	//per tornare alla schermata del menu
	public void BackToMenu(ActionEvent event) throws IOException {
		try {
			main.showMainMenuScene();
		} catch (Exception e) {
			System.out.println("errore nel caricamento scene Main Menu");
		}
	}
	
	//Legge dalla ChoiceBox quale partita caricare con la stringa di ricerca
	//Dato che ogni partita ha un id diverso anche se i giocatori sono gli stessi
	//Sono create partite diverse
	public void CaricaPartita(ActionEvent event) throws IOException {
		try {
			if(ScegliPartita.getValue() == null) {
				Errori.setText("Devi scegliere una partita");
			}
			else {
				Salvataggi.setFile(ScegliPartita.getValue()); 
				GameController.flag = 1;
				main.showScenaGiocoScene();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean getBotGioco() {
		return botGioco;
	}
	public static String getPlayer1(){
		return G1;
	}
	public static String getPlayer2(){
		return G2;
	}
	
}
