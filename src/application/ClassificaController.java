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
	
	//Carica i dati dei giocatori sia nella memoria che nella classifica stessa
	//Questi dati vengono caricati in 2 istanze,quando si visualizza la partita e quando si va al menu principale
	public static void Preparazione() {
		try {
			if(DatiGiocatori.exists()) {
				Scanner scan = new Scanner(DatiGiocatori);
					while(scan.hasNextLine()) {
						{
						Giocatori.add(new Giocatori(scan.nextLine()));
						}
					}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Per mostrare la classifica PROVVISORIO
	public void Mostraclassifia() {
		System.out.println("NOME/VITTORIE");
		for(int i = 0;i<Giocatori.size();i++) {		
			System.out.println(Giocatori.get(i).toString());
		}
	}
	
	//Prende i nomi dal controller pre partita e li scrive nella lista ed esclude i doppioni
	protected void getNomi(String IG1,String IG2) {
		try {
			String G1 = IG1;
			String G2 = IG2;
			FileWriter FW = new FileWriter(DatiGiocatori,true);
			BufferedWriter BW = new BufferedWriter(FW);
			PrintWriter Writer = new PrintWriter(BW);
			for(int i = 0;i<Giocatori.size();i++) {
					if(Giocatori.get(i).Nome == G1)
						G1 = null;
					else if(Giocatori.get(i).Nome == G2)
						G2 = null;
			}
			if(G1 != null)
			Writer.println(G1);
			if(G2 != null)
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
