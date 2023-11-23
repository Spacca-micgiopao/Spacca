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
	
	
	public void start (Stage stage)throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("ScenaGioco1.fxml"));
		Scene scene1 = new Scene(root);
		stage.setScene(scene1);
		stage.setResizable(true);
		stage.setTitle("SPACCA");
		stage.show();
		
	}
}
/*SPIEGAZIONE FILE FXML
BorderPane come contenitore principale che suddivide interfaccia in 5 regioni:
top,bottom, center,left, rigth. Nella regione center ci sono tre HBox che
rappresentano i tre tavoli da gioco(sono quindi tre matrici 2x2). I tre tavoli 
hanno rispettivamente i tre id: Tavolo1, Tavolo2, Tavolo3.
Nella parte top del BorderPane c'è un VBox che rappresenta lo spazio
dedicato al mazzo del Giocatore1 che ha id: SpazioGiocatore1. Stessa 
cosa per il VBox nella parte bottom che ha id: SpazioGiocatore2. A destra e
sinistra poi ci sono due VBox che rappresentano lo spazio dedicato al mazzo
degli imprevisti e al mazzo delle carte. Quello a sinistra ha id: SpazioImprevisti,
quello a destra ha id: spazioMazzo.
*/