package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClassificaController implements Initializable{

	private Stage stage;
	private Main main;	
	
	protected ArrayList<String> Classifica = new ArrayList<String>();
	public static ArrayList<Giocatori> Giocatori = new ArrayList<Giocatori>();
	protected ArrayList<String> id = new ArrayList<String>();
	protected static File DatiGiocatori = new File("src/Data/DatiGiocatori.txt");
	
	public void setMain(Main main) {
		this.main = main;
	}
	public void setStage(Stage stage) {
		this.stage= stage;
	}
	
	//Verrà construita la classifica dai nomi in DatiGiocatori
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}
	
	
	//Per mostrare la classifica PROVVISORIO
	public void Mostraclassifia() {
		System.out.println("NOME/VITTORIE");
		for(int i = 0;i<Giocatori.size();i++) {		
			System.out.println(Giocatori.get(i).toString());
		}
	}
	
	//Prende i nomi dal controller pre partita e li scrive nella lista
	protected void getNomi(String G1,String G2) {
		try {
			FileWriter FW = new FileWriter(DatiGiocatori,true);
			BufferedWriter BW = new BufferedWriter(FW);
			PrintWriter Writer = new PrintWriter(BW);
			Writer.println(G1);
			Writer.println(G2);
			Writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
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
	
}
