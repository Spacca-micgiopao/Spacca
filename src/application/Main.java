package application;
	
import java.io.Serializable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application implements Serializable{

	 private Stage primaryStage;

	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        this.primaryStage = primaryStage;
	        showLoginScene();
	    }

	    public void showLoginScene() throws Exception {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	        Parent root = loader.load();
	        LoginController LoginController = loader.getController();
	        LoginController.setMain(this);
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
	    public void showMainMenuScene() throws Exception {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
	        Parent root = loader.load();
	        MenuController controller = loader.getController();
	        controller.setMain(this);
	        controller.setStage(primaryStage);
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }


	    public void showPrePartitaScene() throws Exception {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Prepartita.fxml"));
	        Parent root = loader.load();
	        ControllerPrePartita controller = loader.getController();
	        controller.setMain(this);
	        controller.setStage(primaryStage);
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    public void showScenaGiocoScene() throws Exception {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScenaGioco.fxml"));
	        Parent root = loader.load();
	        GameController controller = loader.getController();
	        controller.setMain(this);
	        controller.setStage(primaryStage);
	        Scene scene = new Scene(root);
	        
	        primaryStage.setScene(scene);
	        primaryStage.setHeight(800);
	        primaryStage.setWidth(1280);
	        primaryStage.setFullScreen(true);
	        primaryStage.show();     
	    }
	    
	    public void showScenaGiocoSceneCaricata() throws Exception {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScenaGioco.fxml"));
	        Parent root = loader.load();
	        GameController controller = loader.getController();
	        controller.setMain(this);
	        controller.setStage(primaryStage);
	        Scene scene = new Scene(root);
	        
	        primaryStage.setScene(scene);
	        primaryStage.setHeight(800);
	        primaryStage.setWidth(1280);
	        primaryStage.setFullScreen(true);
	        primaryStage.show();     
	    }

	    public void showFilmatoFinale() throws Exception{
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("FilmatiFinali.fxml"));
		    Parent root = loader.load();
	        FilmatiFinaliController controller = loader.getController();
	        controller.setMain(this);
	        controller.setStage(primaryStage);
	        Scene scene = new Scene(root);
	        primaryStage.setHeight(800);
	        primaryStage.setWidth(1280);
	        primaryStage.setScene(scene);
	        primaryStage.setFullScreen(true);
	        primaryStage.show();
    	
	    	
	    }
	    public void showClassificaScene() throws Exception {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Classifica.fxml"));
	        Parent root = loader.load();
	        ClassificaController controller = loader.getController();
	        controller.setMain(this);
	        controller.setStage(primaryStage);
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	 
}
	

