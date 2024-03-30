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
