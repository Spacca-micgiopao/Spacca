package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerPrePartita{
	
	private Stage stage;
	private Scene scene;
	private Parent root;	
	
	//FILE per salvare le informazioni
	File DatiPartita = new File("DatiPartita.txt");
	public String G1;
	public String G2;
	//istanza di infoPartita per caricare e scrivere i risultati
	InfoPartita infopartita = new InfoPartita();
	
	//TUTTO FXML
	@FXML
	private TextField InputG1;
	@FXML
	private TextField InputG2;
	@FXML
	Label LogNome;
	@FXML
	private Label Errori;
	@FXML
	Label PartitaScelta;
	@FXML
	ChoiceBox<String> ScegliPartita;
	
	
	
	//Scrivere il nome dell'Admin
	public void displayLogNome(String nome) {
		LogNome.setText(nome);
		ScegliPartita.getItems().addAll(infopartita.Partite);
	}
	
	//Costruzione di una nuova partita
	public void IniziaPartita(ActionEvent event) throws IOException {
			//Ottiene in nomi dei 2 giocatori se sono vuoti la partita non puo iniziare
			G1 = InputG1.getText();
			G2 = InputG2.getText();
			if(G1.isBlank() || G2.isBlank()) {
				Errori.setText("Almeno uno dei nomi non è stato inserito!");
			}
			//Se i nomi non sono vuoti vengono scritti sul file dei dati e viene caricata la scena del gioco
			else {
			infopartita.getG(G1,G2);
			infopartita.ScriviDati();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ScenaGioco.fxml"));
			root = loader.load();
			GameController gamecontroller = loader.getController();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			}
		}
	
}
