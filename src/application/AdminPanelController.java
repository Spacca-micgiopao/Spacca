package application;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class AdminPanelController {
	
	private Stage stage;
	private Main main;
	
	public void setMain(Main main) {
    	this.main= main;
    }
	
	public void setStage(Stage stage) {
			this.stage= stage;
	}
	
	//Per eliminare tutti i dati salvati
		public void Pulisci(ActionEvent event) throws IOException {
			File dir = new File("src/Salvataggi/");
			String[] n = dir.list();
				if(dir.isDirectory() && dir.exists()) {
					for(int i = 0;i<n.length;i++) {
						File f = new File("src/Salvataggi/"+n[i]);
						f.delete();
					}
				}
			//Resetta il file che contiene le informazioni dei giocatori
			ClassificaController.elimina();
		}
	
	//Per tornare al menuprincipale
	public void Backtomenu(ActionEvent event) throws IOException {
		try {
			main.showMainMenuScene();;
		} catch (Exception e) {
			System.out.println("errore nel caricamento del main menu");
			e.printStackTrace();
		}
	}
}
