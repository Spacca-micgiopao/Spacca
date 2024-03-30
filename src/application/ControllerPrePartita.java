package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerPrePartita {
	
	private Stage stage;
	private Scene scene;
	private Parent root;	
	
	@FXML
	private TextField InputG1;

	@FXML
	private TextField InputG2;
	
	//FILE per salvare le informazioni
	File DatiPartita = new File("DatiPartita.txt");
	public String G1;
	public String G2;
	
	//InfoPartita per caricare e scrivere i risultati
	InfoPartita infopartita = new InfoPartita();
	
	//Per prendere il nome dell'admin
	@FXML
	Label LogNome;
	
	//NOME Admin
	public void displayLogNome(String nome) {
		LogNome.setText(nome);
	}
	
	public void IniziaPartita(ActionEvent event) throws IOException {
			//Ottiene in nomi dei 2 giocatori se sono vuoti la partita non puo iniziare
			G1 = InputG1.getText();
			G2 = InputG2.getText();
			if(G1.isBlank() || G2.isBlank()) {
				System.out.println("Nomi vuoti");
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
