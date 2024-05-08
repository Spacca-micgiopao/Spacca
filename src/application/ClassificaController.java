package application;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class ClassificaController implements Initializable,Serializable{
	//Questa classe gestisce tutto cio che riguarda le classifiche
	private Stage stage;
	private Main main;	
	protected ArrayList<String> Classifica = new ArrayList<String>();
	public static ArrayList<Giocatori> LSGiocatori = new ArrayList<Giocatori>();
	protected static File DatiGiocatori = new File("src/Data/DatiGiocatori.ser");
	
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
	public static void CaricaNomi() throws ClassNotFoundException, EOFException {
		try {
			FileInputStream fileIn = new FileInputStream(DatiGiocatori);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         LSGiocatori = (ArrayList<Giocatori>) in.readObject();
	         in.close();
	         fileIn.close();
	    } 
		catch (IOException e) {
		}
	}
	
	//Resetta la lista dei giocatori
	public static void elimina() {
		LSGiocatori = new ArrayList<Giocatori>();
		DatiGiocatori.delete();
	}
	
	//Temporaneo mostra la classifica
	public void Mostraclassifia() {
		System.out.println("NOME/VITTORIE");
		for(int i = 0;i<LSGiocatori.size();i++) {		
			System.out.println(LSGiocatori.get(i).toString());
		}
	}
	
	//Prende i nomi dal controller pre partita e li scrive nella lista ed esclude i doppioni
	protected void getNomi(String IG1,String IG2) throws IOException,EOFException {
		for(int i = 0;i < LSGiocatori.size();i++) {
			if(LSGiocatori.get(i).Nome.equals(IG1)) 
				IG1 = null;
			if(LSGiocatori.get(i).Nome.equals(IG2)) 
				IG2 = null;
		}
			if(IG1 !=null)
				LSGiocatori.add(new Giocatori(IG1));
			if(IG2 !=null)
				LSGiocatori.add(new Giocatori(IG2));
		try {
			FileOutputStream fileOut = new FileOutputStream(DatiGiocatori);
		    ObjectOutputStream out = new ObjectOutputStream(fileOut);
		    out.writeObject(LSGiocatori);
		    out.close();
		    fileOut.close();
		    } 
		catch (IOException i) {
		         i.printStackTrace();
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
