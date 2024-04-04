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
	
	private Main main;
	private Stage stage;
	@FXML
	private TextField InputG1;

	@FXML
	private TextField InputG2;
	
	//FILE per salvare le informazioni
	File DatiPartita = new File("DatiPartita.txt");
	private int ContaPartite = 0000;
	public static String G1;
	public static String G2;
	
	//InfoPartita per caricare e scrivere i risultati
	InfoPartita infopartita = new InfoPartita();
	
	//Per prendere il nome dell'admin
	@FXML
	Label LogNome;
	//Servono per fare lo switch delle scene
	public void setStage(Stage stage) {
        this.stage = stage;
    }
	public void setMain(Main main) {
		this.main = main;
	}
	//NOME Admin
	public void displayLogNome(String nome) {
		LogNome.setText(nome);
	}
	
	public void IniziaPartita(ActionEvent event) throws IOException {
		FileWriter FW = new FileWriter(DatiPartita);
			G1 = InputG1.getText();
			G2 = InputG2.getText();
			ContaPartite++;
			infopartita.getG(G1);
			infopartita.ScriviDati();
			try {
			
				main.showScenaGiocoScene();
				
			} catch (Exception e) {
				System.out.println("errore visualizzazione scena gioco");
			}
	}
	public static String getPlayer1(){
		return G1;
	}
	public static String getPlayer2(){
		return G2;
	}
	
	
}
