package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClassificaController implements Initializable{

	private Stage stage;
	private Main main;	
	
	protected ArrayList<String> Classifica = new ArrayList<String>();
	protected ArrayList<String> id = new ArrayList<String>();
	private InfoPartita infopartita = new InfoPartita();
	
	public void setMain(Main main) {
		this.main = main;
	}
	public void setStage(Stage stage) {
		this.stage= stage;
	}
	
	//Prendere tutti i nomi dei giocatori
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Scanner scan = new Scanner(infopartita.DatiPartita);
			StringTokenizer st = new StringTokenizer(scan.next(),",");
				for(int i=0;i<infopartita.Partite.length-1;i++) {
					st.nextToken();
					Classifica.add(st.nextToken());
					Classifica.add(st.nextToken());
					st = new StringTokenizer(scan.next(),",");
				}
			st.nextToken();
			Classifica.add(st.nextToken());
			Classifica.add(st.nextToken());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		//Rimuovere i doppioni
		for(int i=0;i<Classifica.size();i++) {
			int cont = 0;
			for(int j=0;j<Classifica.size();j++) {
				if(Classifica.get(i).equals(Classifica.get(j))) {
					cont++;
				}
				if(cont>1)
					Classifica.remove(i);
			}
		}
	}
	public void Mostraclassifia() {
		for(int i = 0;i<Classifica.size();i++) {
			System.out.println(Classifica.get(i));
		}
		
	}
	
	
	
}
