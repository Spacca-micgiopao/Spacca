package application;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Loginalertcontroller {

	@FXML
	private TextField username,password;
	@FXML
	private Button conferma;
	private Stage stage;
	private File DatiAccesso = new File("SPACCA/src/Data/DatiAccesso.txt");
	
	public void conferma(ActionEvent event) throws IOException {
		LoginController.accesso.clear();
		String User = username.getText();
		String PW = password.getText();
		if(!User.isBlank() && !PW.isBlank()) {
			LoginController.SalvaAccesso(DatiAccesso, User, PW);
			stage = (Stage) conferma.getScene().getWindow();
			stage.close();
		}
	}
}
