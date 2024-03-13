package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.image.*;

import java.util.ArrayList;
import java.util.List;


public class GameController {
	 

   
	private Mazzo mazzoGiocatore1;
    private Mazzo mazzoGiocatore2;
    private Mazzo mazzoCompleto;
   
    @FXML
    private ListView<Carta> listaCarteGiocatore1;
    @FXML
    private ListView<Carta> listaCarteGiocatore2;
    @FXML
    private ImageView Carta1G1;  //prima carta del mazzo del giocatore 1
    @FXML
    private ImageView Carta2G1;
    @FXML
    private ImageView Carta3G1;
    @FXML
    private ImageView Carta4G1;
    
    @FXML
    private ImageView Carta1G2; //prima carta del mazzo del giocatore 2
    @FXML
    private ImageView Carta2G2;
    @FXML
    private ImageView Carta3G2;
    @FXML
    private ImageView Carta4G2;
    
    @FXML
    private List<ImageView> imageViewsGiocatore1;
    @FXML
    private List<ImageView> imageViewsGiocatore2;
    
    @FXML
    private Button PescaGiocatore1;
    @FXML
    private Button PescaGiocatore2;
    
    //inizializzazione
    public void initialize() {
    	//mazzo con tutte le carte disponibili
    	mazzoCompleto = new Mazzo();
    	mazzoCompleto.CreaMazzoCompleto();
        
    	mazzoGiocatore1 = new Mazzo();
    	mazzoGiocatore2 = new Mazzo();
        caricaCarteIniziali();
        
        //per le ImageView: creazione di una lista contenente tutte le ImageView per facilitare 
        //l'aggiunta delle immagini in aggiorna interfaccia
        //giocatore1
        imageViewsGiocatore1 = new ArrayList<>();
        imageViewsGiocatore1.add(Carta1G1);
        imageViewsGiocatore1.add(Carta2G1);
        imageViewsGiocatore1.add(Carta3G1);
        imageViewsGiocatore1.add(Carta4G1);
        //giocatore2
        imageViewsGiocatore2 = new ArrayList<>();
        imageViewsGiocatore2.add(Carta1G2);
        imageViewsGiocatore2.add(Carta2G2);
        imageViewsGiocatore2.add(Carta3G2);
        imageViewsGiocatore2.add(Carta4G2);
        aggiornaInterfaccia();
    }

    //all'inizio vengono caricate tre(numero da rivedere)carte nel mazzo di ogni giocatore 
    public void caricaCarteIniziali() {
    	//carica tre carte nel mazzo di ogni giocatore
    	for(int i=0;i<3;i++) {
    		Carta cartaCasuale = mazzoCompleto.pescaCartaCasuale();
	    	if (cartaCasuale != null) {
	             mazzoGiocatore1.aggiungiCarta(cartaCasuale);
	         }
    	}
    	for(int i=0;i<3;i++) {
    		Carta cartaCasuale = mazzoCompleto.pescaCartaCasuale();
	    	if (cartaCasuale != null) {
	             mazzoGiocatore2.aggiungiCarta(cartaCasuale);
	         }
    	}
    	System.out.println("dimensione del mazzo giocatore 1 è "+mazzoGiocatore1.getCarte().size());
    	System.out.println("dimensione del mazzo giocatore 2 è "+mazzoGiocatore2.getCarte().size());
    }
    
    //pesca una carta per il giocatore 1 (per ora visivamente si riesce a pescare solo una carta)
    public void handlePescaGiocatore1Action(ActionEvent event) {
  
    	Carta cartaCasuale = mazzoCompleto.pescaCartaCasuale();
    	if(cartaCasuale != null) {
    		mazzoGiocatore1.aggiungiCarta(cartaCasuale);
    		System.out.println("carta aggiunta");
    		aggiornaInterfaccia();
    	}
    	
    }
    //pesca una carta per il giocatore2
    public void handlePescaGiocatore2Action(ActionEvent event) {
    	
    	Carta cartaCasuale = mazzoCompleto.pescaCartaCasuale();
    	if(cartaCasuale != null) {
    		mazzoGiocatore2.aggiungiCarta(cartaCasuale);
    		System.out.println("carta aggiunta");
    		aggiornaInterfaccia();
    	}
    	
    }
    public void aggiornaInterfaccia() {
    	//aggiorna immagini mazzo Giocatore 1
        for(int i=0;i<mazzoGiocatore1.getCarte().size()&& i < imageViewsGiocatore1.size();i++ ) {
        	Carta c = mazzoGiocatore1.getCarta(i);
        	// immagine dalla carta
            Image immagine = c.getImmagine();

         // Aggiorna l'ImageView con l'immagine della carta
            imageViewsGiocatore1.get(i).setImage(immagine);
        }
        
        //aggiorna immagini mazzo Giocatore 2
        for(int i=0;i<mazzoGiocatore2.getCarte().size() && i < imageViewsGiocatore2.size();i++) {
        	Carta c = mazzoGiocatore2.getCarta(i);
        	// immagine dalla carta
            Image immagine = c.getImmagine();

         // Aggiorna l'ImageView con l'immagine della carta
            imageViewsGiocatore2.get(i).setImage(immagine);
        }
    }
    
    
    
    
    
    
    
    
    
}
    
    	 
     
    

