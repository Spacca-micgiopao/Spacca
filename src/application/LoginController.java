package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class LoginController {
//Classe per controllare la pagina di Login
	
	private Main main;
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
	
	//Circa funziona,da fare : salvare tutti i dati in un file da cui leggerli
	//si può in teoria accedere con qualsiasi combinazione di username e password quindi va risolto
	public void setMain(Main main) {
		this.main = main;
	}
	//Per confermare le credenziali e passare al menu principale

	public void conferma(ActionEvent event) throws IOException, InterruptedException {
		//Modificare qui per decidere nome utente e password dell'admin
		accesso.put("", "");
		Nome = inputnome.getText();
		Password = inputpassword.getText();
		HashMap<String,String> login = new HashMap<String,String>();
		login.put(Nome,Password);
		if(accesso.containsValue(Password) && accesso.containsKey(Nome)) {
			 try {
				main.showMainMenuScene();
				InfoPartita.Preparazione();
			} catch (Exception e) {
				System.out.println("errore nel caricamento MainMenu");
				e.printStackTrace();
			}
			
		}else
			errori.setText("USERNAME O PASSWORD ERRATI");
	}
}
	
