package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
	//Questa classe gestisce tutto cio che riguarda le classifiche
	private Stage stage;
	private Main main;	
	protected ArrayList<String> Classifica = new ArrayList<String>();
	public static ArrayList<Giocatori> LSGiocatori = new ArrayList<Giocatori>();
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
	
	//Carica in memoria dal file i nomi dei giocatori
	public static void CaricaNomi() {
		try {
			Scanner scan = new Scanner(DatiGiocatori);
			while(scan.hasNextLine()) {
				LSGiocatori.add(new Giocatori(scan.nextLine()));
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Resetta la lista dei giocatori
	public static void elimina() {
		LSGiocatori = new ArrayList<Giocatori>();
	}
	
	//Temporaneo mostra la classifica
	public void Mostraclassifia() {
		System.out.println("NOME/VITTORIE");
		for(int i = 0;i<LSGiocatori.size();i++) {		
			System.out.println(LSGiocatori.get(i).toString());
		}
	}
	
	//Prende i nomi dal controller pre partita e li scrive nella lista ed esclude i doppioni
	protected void getNomi(String IG1,String IG2) throws IOException {
		for(int i = 0;i < LSGiocatori.size();i++) {
			if(LSGiocatori.get(i).getNome() == IG1) {
				IG1 = null;
			}
			else if(LSGiocatori.get(i).getNome() == IG2)
				IG2 = null;
		}
		FileWriter FW = new FileWriter(DatiGiocatori,true);
		BufferedWriter BW = new BufferedWriter(FW);
		PrintWriter Writer = new PrintWriter(BW);
			if(IG1 != null) {
				LSGiocatori.add(new Giocatori(IG1));
				Writer.println(IG1);
			}
			if(IG2 != null) {
				LSGiocatori.add(new Giocatori(IG2));
				Writer.println(IG2);
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
