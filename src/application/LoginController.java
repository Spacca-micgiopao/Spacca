package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	@FXML
	private Label errori;
	
	//Hashmap per avere username e password per l'admin
	HashMap<String,String> accesso = new HashMap<String,String>();
	
	String Nome;
	String Password;
	
	//Per confermare le credenziali e passare al menu principale
	public void conferma(ActionEvent event) throws IOException {
		//Modificare qui per decidere nome utente e password dell'admin
		accesso.put("", "");
		Nome = inputnome.getText();
		Password = inputpassword.getText();
		HashMap<String,String> login = new HashMap<String,String>();
		login.put(Nome,Password);
		if(accesso.containsValue(Password) && accesso.containsKey(Nome)) {
			Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else
			errori.setText("Username o password errati,in caso di problemi rivolgersi all'assistenza");
	}
	
	//Tasto temporaneo per saltare direttamente al tabellone senza dover fare login
	//Ho tolto il loader del game controller non so potrebbe dover essere rimesso
	public void switchtoTabellone(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("GameController.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
}
	
