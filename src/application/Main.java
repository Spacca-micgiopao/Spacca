package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public void start(Stage stage) throws Exception {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	        Parent root = loader.load();
	        Scene scene1 = new Scene(root);
	        stage.setScene(scene1);
	        stage.setResizable(true);
	        stage.setTitle("SPACCA");
	        stage.show();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
/*
FILE FXML
Il file è composto per adesso da 8 ImageView e due bottoni,
le 4 imageView in alto sono quelle che servono per visualizzare 
le carte del mazzo del giocatore1 , mentre quelle in basso sono del giocatore2.
Ogni giocatore ha anche un bottone pesca che aggiunge una carta al proprio 
mazzo( per ora visivamente si può aggiungere una sola carta).
*/