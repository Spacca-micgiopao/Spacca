package application;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LoginController implements Initializable,Serializable{
//Classe per controllare la pagina di Login
	
	//Per serilizzazione dei dati di accesso
	private static final long serialVersionUID = 5620015872095426512L;
	
	private Main main;
	//Crea le istanze per prendere in input Nome e Password
	@FXML
	private TextField inputnome;
	@FXML
	private PasswordField inputpassword;
	@FXML
	private Button tastoconferma;
	@FXML
	private Label errori;
	
	//Hashmap per avere username e password per l'admin
	public static HashMap<String,String> accesso = new HashMap<String,String>();
	
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
		Nome = inputnome.getText();
		Password = inputpassword.getText();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
}
	
