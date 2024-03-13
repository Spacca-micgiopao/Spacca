package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.util.HashMap;


public class LoginController {
//Classe per controllare la pagina di Login
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	//Crea le istanze per prendere in input Nome e Password
	@FXML
	private TextField inputnome;
	@FXML
	private TextField inputpassword;
	@FXML
	private Button tastoconferma;
	
	String Nome;
	String Password;
	//per poter usare i dati salvati nella classe Datiaccesso
	Datiaccesso DatiAccesso = new Datiaccesso();
	
	
	//Circa funziona,da fare : salvare tutti i dati in un file da cui leggerli
	//si può in teoria accedere con qualsiasi combinazione di username e password quindi va risolto
	public void conferma(ActionEvent event) throws IOException {
		Nome = inputnome.getText();
		Password = inputpassword.getText();
		HashMap<String,String> login = new HashMap<String,String>();
		login.put(Nome,Password);
		if(DatiAccesso.accesso.containsValue(Password) && DatiAccesso.accesso.containsKey(Nome)) {
			Parent root = FXMLLoader.load(getClass().getResource("ScenaGioco.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		

			
				
	}
	
	//Tasto temporaneo per saltare direttamente al tabellone senza dover fare login
	//Ho tolto il loader del game controller non so potrebbe dover essere rimesso
	public void switchtoTabellone(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ScenaGioco.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	
}