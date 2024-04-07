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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Scanner scan = new Scanner(infopartita.DatiPartita);
			StringTokenizer st = new StringTokenizer(scan.next(),",");
			//for(int i=0;i<2;i++) {
				st.nextToken();
				Classifica.add(st.nextToken());
				//Classifica.add(st.nextToken());
				st = new StringTokenizer(scan.next(),",");
			//}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void Mostraclassifia() {
		for(int i = 0;i<Classifica.size();i++) {
			System.out.println(Classifica.get(i));
		}
		
	}
	
	
	
}
