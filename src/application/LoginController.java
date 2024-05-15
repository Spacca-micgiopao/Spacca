package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LoginController {
//Classe per controllare la pagina di Login
	
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
	
	public static void caricaAccesso(File f){
		try {
			Scanner scan = new Scanner(f);
			accesso.put(scan.nextLine(), scan.nextLine());
			scan.close();
		} 
		catch (FileNotFoundException e) {
			accesso.put("admin", "");
		}
		catch (NoSuchElementException e) {
			accesso.put("admin", "");
		}
	}
}
	
