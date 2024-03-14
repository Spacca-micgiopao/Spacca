package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


import java.util.ArrayList;
import java.util.List;


public class GameController {
	 
	private Mazzo mazzoGiocatore1;
    private Mazzo mazzoGiocatore2;
    private Mazzo mazzoCompleto;
    
    private Carta cartaSelezionata; //selezionata da utente serve per lo spostamento
   
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
    private List<ImageView> imageViewsTavolo1;
    @FXML
    private List<ImageView> imageViewsTavolo2;
    @FXML
    private List<ImageView> imageViewsTavolo3;
    /*
    @FXML
    private Button PescaGiocatore1;
    @FXML
    private Button PescaGiocatore2;
    */
  //tavoli da gioco
    //tavolo1
    @FXML 
    private GridPane Tavolo1;
    @FXML
    private ImageView CartaT1p00; //Carta nel tavolo1 in posizione 0,0
    @FXML
    private ImageView CartaT1p10;
    @FXML
    private ImageView CartaT1p01;
    @FXML
    private ImageView CartaT1p11;
    //tavolo2
    @FXML 
    private GridPane Tavolo2;
    @FXML
    private ImageView CartaT2p00; 
    @FXML
    private ImageView CartaT2p10;
    @FXML
    private ImageView CartaT2p01;
    @FXML
    private ImageView CartaT2p11;
    //tavolo3
    @FXML
    private GridPane Tavolo3;
    @FXML
    private ImageView CartaT3p00; 
    @FXML
    private ImageView CartaT3p10;
    @FXML
    private ImageView CartaT3p01;                        
    @FXML               
    private ImageView CartaT3p11;
    
    //inizializzazione
    public void initialize() {
    	//mazzo con tutte le carte disponibili
    	mazzoCompleto = new Mazzo();
    	mazzoCompleto.CreaMazzoCompleto();
        System.out.println("numero carte nel mazzo iniziale completo"+mazzoCompleto.getNumeroCarte());
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
        //tavolo1
        imageViewsTavolo1 = new ArrayList<>();
        imageViewsTavolo1.add(CartaT1p00); //Carta nel tavolo1 in posizione 0,0
        imageViewsTavolo1.add(CartaT1p10);
        imageViewsTavolo1.add(CartaT1p01);
        imageViewsTavolo1.add(CartaT1p11);
        //tavolo2
        imageViewsTavolo2 = new ArrayList<>();
        imageViewsTavolo2.add(CartaT2p00); //Carta nel tavolo1 in posizione 0,0
        imageViewsTavolo2.add(CartaT2p10);
        imageViewsTavolo2.add(CartaT2p01);
        imageViewsTavolo2.add(CartaT2p11);
        //tavolo3
        imageViewsTavolo3 = new ArrayList<>();
        imageViewsTavolo3.add(CartaT3p00); //Carta nel tavolo1 in posizione 0,0
        imageViewsTavolo3.add(CartaT3p10);
        imageViewsTavolo3.add(CartaT3p01);
        imageViewsTavolo3.add(CartaT3p11);
        
        // Associa gestori eventi alle ImageView per effettuare spostamento carte
        Carta1G1.setOnMouseClicked(this::handleClickCartaGiocatore1);
        Carta2G1.setOnMouseClicked(this::handleClickCartaGiocatore1);
        Carta3G1.setOnMouseClicked(this::handleClickCartaGiocatore1);
        Carta4G1.setOnMouseClicked(this::handleClickCartaGiocatore1);
        
        Carta1G2.setOnMouseClicked(this::handleClickCartaGiocatore2);
        Carta2G2.setOnMouseClicked(this::handleClickCartaGiocatore2);
        Carta3G2.setOnMouseClicked(this::handleClickCartaGiocatore2);
        Carta4G2.setOnMouseClicked(this::handleClickCartaGiocatore2);
        
        CartaT1p00.setOnMouseClicked(this::handleClickPosizioneTavolo1);
        CartaT1p01.setOnMouseClicked(this::handleClickPosizioneTavolo1);
        CartaT1p10.setOnMouseClicked(this::handleClickPosizioneTavolo1);
        CartaT1p11.setOnMouseClicked(this::handleClickPosizioneTavolo1);
       
        CartaT2p00.setOnMouseClicked(this::handleClickPosizioneTavolo2);
        CartaT2p01.setOnMouseClicked(this::handleClickPosizioneTavolo2);
        CartaT2p10.setOnMouseClicked(this::handleClickPosizioneTavolo2);
        CartaT2p11.setOnMouseClicked(this::handleClickPosizioneTavolo2);
        
        CartaT3p00.setOnMouseClicked(this::handleClickPosizioneTavolo3);
        CartaT3p01.setOnMouseClicked(this::handleClickPosizioneTavolo3);
        CartaT3p10.setOnMouseClicked(this::handleClickPosizioneTavolo3);
        CartaT3p11.setOnMouseClicked(this::handleClickPosizioneTavolo3);
    }

    public void caricaCarteIniziali() {
    	//carica 4 carte nel mazzo di ogni giocatore
    	for(int i=0;i<4;i++) {
    		Carta cartaCasuale = mazzoCompleto.pescaCartaCasuale();
	    	if (cartaCasuale != null) {
	             mazzoGiocatore1.aggiungiCarta(cartaCasuale);
	         }
    	}
    	for(int i=0;i<4;i++) {
    		Carta cartaCasuale = mazzoCompleto.pescaCartaCasuale();
	    	if (cartaCasuale != null) {
	             mazzoGiocatore2.aggiungiCarta(cartaCasuale);
	         }
    	}
    }
    /*BOTTONI PESCA FACOLTATIVI
    //pesca una carta per il giocatore 1 (per ora visivamente si riesce a pescare solo una carta)
    public void handlePescaGiocatore1Action(ActionEvent event) {
    	System.out.println("bottone pesca cliccato per G1");
    	Carta cartaCasuale = mazzoCompleto.pescaCartaCasuale();
    	
    	if(cartaCasuale != null &&  mazzoGiocatore1.getNumeroCarte()<=4 && imageViewsGiocatore1.size()<4) {
    		mazzoGiocatore1.aggiungiCarta(cartaCasuale);
    		System.out.println("carta aggiunta per giocatore1");
    		aggiornaInterfaccia();
    	}else {
    		if(cartaCasuale== null) 
			{System.out.println("carta nulla");
		}
		if(mazzoGiocatore1.getNumeroCarte()>4) {
			System.out.println("non c'è spazio");
			}
    	}	
	
    	
    }
    //pesca una carta per il giocatore2
    public void handlePescaGiocatore2Action(ActionEvent event) {
    	System.out.println("bottone pesca cliccato per g2");
    	Carta cartaCasuale = mazzoCompleto.pescaCartaCasuale();
    	
    	if(cartaCasuale!=null) {
    	//if(cartaCasuale != null && mazzoGiocatore2.getNumeroCarte()<=4 && imageViewsGiocatore2.size()<4 ) {
    		mazzoGiocatore2.aggiungiCarta(cartaCasuale);
    		System.out.println("carta aggiunta per giocatore 2");
    		aggiornaInterfaccia();
    		
    	}else {
    		if(cartaCasuale== null) 
    			{System.out.println("carta nulla");
    		}
    		if(mazzoGiocatore2.getNumeroCarte()>4) {
    			System.out.println("non c'è spazio");
    			}
    	}
    	
    	
    }
    
   */
    public void aggiornaInterfaccia() {
        // Aggiorna le immagini del mazzo del giocatore 1
        for (int i = 0; i < imageViewsGiocatore1.size(); i++) {
            if (i < mazzoGiocatore1.getCarte().size()) {
                Carta c = mazzoGiocatore1.getCarta(i);
                if (c != null) {
                    Image immagine = c.getImmagine();
                    imageViewsGiocatore1.get(i).setImage(immagine);
                } else {
                    // Se la carta è null
                    imageViewsGiocatore1.get(i).setImage(null);
                }
            } else {
                // Se non ci sono più carte nel mazzo del giocatore l'immagine dell'ImageView diventa null
                imageViewsGiocatore1.get(i).setImage(null);
            }
        }

        // Aggiorna le immagini del mazzo del giocatore 2
        for (int i = 0; i < imageViewsGiocatore2.size(); i++) {
            if (i < mazzoGiocatore2.getCarte().size()) {
                Carta c = mazzoGiocatore2.getCarta(i);
                if (c != null) {
                    Image immagine = c.getImmagine();
                    imageViewsGiocatore2.get(i).setImage(immagine);
                } else {
                    // Se la carta è null
                    imageViewsGiocatore2.get(i).setImage(null);
                }
            } else {
                // Se non ci sono più carte nel mazzo del giocatore l'immagine dell'ImageView diventa null
                imageViewsGiocatore2.get(i).setImage(null);
            }
        }
    }


    private void spostaCartaSuTavolo(Carta carta, ImageView posizioneTavolo) {
        // Imposta l'immagine della carta sulla posizione del tavolo
    	 if (carta != null) {
    	        // Imposta l'immagine della carta sulla posizione del tavolo
    	        posizioneTavolo.setImage(carta.getImmagine());
    	    } else {
    	        System.out.println("Errore: carta selezionata è nulla");
    	    }
	}
    
    //GESTORE DEI CLICK SULLE CARTE DEI MAZZI DEI DUE GIOCATORI
    public void handleClickCartaGiocatore1(MouseEvent event) {
        ImageView cartaCliccata = (ImageView) event.getSource();
        
        // Trova l'indice dell'ImageView cliccata
        int index = imageViewsGiocatore1.indexOf(cartaCliccata);
        if (index == -1) {
            return;
            
        }

        // Ottiene la carta associata all'immagine cliccata
        if (index != -1) {
            if (index < mazzoGiocatore1.getCarte().size()) {
                cartaSelezionata = mazzoGiocatore1.getCarta(index);
            } else {
                return;
            }
        }
        
    }
    public void handleClickCartaGiocatore2(MouseEvent event) {
        ImageView cartaCliccata = (ImageView) event.getSource();
        // Trova l'indice dell'ImageView cliccata
        int index = imageViewsGiocatore2.indexOf(cartaCliccata);
        if (index == -1) {
            return;
        }

        // Ottiene la carta associata all'immagine cliccata
        if (index != -1) {
           if (index < mazzoGiocatore2.getCarte().size()) {
        		cartaSelezionata = mazzoGiocatore2.getCarta(index);
            } else {
                return;
            }
        }
        
    }
   
    //GESTORE DEI CLICK SU UNA POSIZIONE DEI TAVOLI
    //CLICK SU PRIMO TAVOLO
    public void handleClickPosizioneTavolo1(MouseEvent event) { // metodo funziona solo se le carte sono diverse
        if (cartaSelezionata != null ) {
            ImageView posizioneTavoloCliccata = (ImageView) event.getSource();
            
            // Verifica che l'indice dell'ImageView sia valido
            int index = imageViewsTavolo1.indexOf(posizioneTavoloCliccata);
            if (index == -1) {
                // ImageView non trovata, esci dalla funzione
                return;
            }
            
            //controllo se la posizione del tavolo è già stata occupata
            if (posizioneTavoloCliccata.getImage() != null) {
                // La posizione è già occupata, quindi non posso posizionare la carta
                return;
            }
            
            // Sposta la carta selezionata nella posizione del tavolo cliccata
            spostaCartaSuTavolo(cartaSelezionata, posizioneTavoloCliccata);
            // Rimuovi la carta dal mazzo del giocatore
            if (mazzoGiocatore1.contieneCarta(cartaSelezionata)) {
                mazzoGiocatore1.rimuoviCarta(cartaSelezionata);
            } else if (mazzoGiocatore2.contieneCarta(cartaSelezionata)) {
                mazzoGiocatore2.rimuoviCarta(cartaSelezionata);
            }
            //svuota imageView dal mazzoGiocatore1
            for(int i=0;i < imageViewsGiocatore1.size();i++ ) {
            	
            	if(imageViewsGiocatore1.get(i).getImage()==cartaSelezionata.getImmagine()) {
            		imageViewsGiocatore1.get(i).setImage(null);
            		break;
            	}
            }
          //svuota imageView dal mazzoGiocatore2
            for(int i=0;i < imageViewsGiocatore2.size();i++ ) {
            	
            	if(imageViewsGiocatore2.get(i).getImage()==cartaSelezionata.getImmagine()) {
            		imageViewsGiocatore2.get(i).setImage(null);
            		break;
            	}
            }
            // Resetta la carta selezionata
            cartaSelezionata = null;
         
            System.out.println("dimensione del mazzo giocatore 1 è "+mazzoGiocatore1.getCarte().size());
            System.out.println("dimensione del mazzo giocatore 2 è "+mazzoGiocatore2.getCarte().size());
            
        }
        aggiornaInterfaccia();

    }
    //CLICK SUL SECONDO TAVOLO
    public void handleClickPosizioneTavolo2(MouseEvent event) { // metodo funziona solo se le carte sono diverse
        if (cartaSelezionata != null ) {
            ImageView posizioneTavoloCliccata = (ImageView) event.getSource();
            
            // Verifica che l'indice dell'ImageView sia valido
            int index = imageViewsTavolo2.indexOf(posizioneTavoloCliccata);
            if (index == -1) {
                // ImageView non trovata, esci dalla funzione
                return;
            }
            
            //controllo se la posizione del tavolo è già stata occupata
            if (posizioneTavoloCliccata.getImage() != null) {
                // La posizione è già occupata, quindi non posso posizionare la carta
                return;
            }
            
            // Sposta la carta selezionata nella posizione del tavolo cliccata
            spostaCartaSuTavolo(cartaSelezionata, posizioneTavoloCliccata);
            // Rimuovi la carta dal mazzo del giocatore
            if (mazzoGiocatore1.contieneCarta(cartaSelezionata)) {
                mazzoGiocatore1.rimuoviCarta(cartaSelezionata);
            } else if (mazzoGiocatore2.contieneCarta(cartaSelezionata)) {
                mazzoGiocatore2.rimuoviCarta(cartaSelezionata);
            }
            //svuota imageView dal mazzoGiocatore1
            for(int i=0;i < imageViewsGiocatore1.size();i++ ) {
            	
            	if(imageViewsGiocatore1.get(i).getImage()==cartaSelezionata.getImmagine()) {
            		imageViewsGiocatore1.get(i).setImage(null);
            		break;
            	}
            }
          //svuota imageView dal mazzoGiocatore2
            for(int i=0;i < imageViewsGiocatore2.size();i++ ) {
            	
            	if(imageViewsGiocatore2.get(i).getImage()==cartaSelezionata.getImmagine()) {
            		imageViewsGiocatore2.get(i).setImage(null);
            		break;
            	}
            }
            // Resetta la carta selezionata
            cartaSelezionata = null;
           
            System.out.println("dimensione del mazzo giocatore 1 è "+mazzoGiocatore1.getCarte().size());
            System.out.println("dimensione del mazzo giocatore 2 è "+mazzoGiocatore2.getCarte().size());
            
        }
        aggiornaInterfaccia();
    }
    //CLICK SUL TERZO TAVOLO
    public void handleClickPosizioneTavolo3(MouseEvent event) { // metodo funziona solo se le carte sono diverse
    	System.out.println("cliccato posizione nel tavolo3");
        if (cartaSelezionata != null ) {
            ImageView posizioneTavoloCliccata = (ImageView) event.getSource();
            
            // Verifica che l'indice dell'ImageView sia valido
            int index = imageViewsTavolo3.indexOf(posizioneTavoloCliccata);
            if (index == -1) {
                // ImageView non trovata, esci dalla funzione
                return;
            }
            
            //controllo se la posizione del tavolo è già stata occupata
            if (posizioneTavoloCliccata.getImage() != null) {
                // La posizione è già occupata, quindi non posso posizionare la carta
                return;
            }
            
            // Sposta la carta selezionata nella posizione del tavolo cliccata
            spostaCartaSuTavolo(cartaSelezionata, posizioneTavoloCliccata);
            // Rimuovi la carta dal mazzo del giocatore
            if (mazzoGiocatore1.contieneCarta(cartaSelezionata)) {
                mazzoGiocatore1.rimuoviCarta(cartaSelezionata);
            } else if (mazzoGiocatore2.contieneCarta(cartaSelezionata)) {
                mazzoGiocatore2.rimuoviCarta(cartaSelezionata);
            }
            //svuota imageView dal mazzoGiocatore1
            for(int i=0;i < imageViewsGiocatore1.size();i++ ) {
            	
            	if(imageViewsGiocatore1.get(i).getImage()==cartaSelezionata.getImmagine()) {
            		imageViewsGiocatore1.get(i).setImage(null);
            		System.out.println("svuotamento iamgeView da lista giocatore1");
            		break;
            	}
            }
          //svuota imageView dal mazzoGiocatore2
            for(int i=0;i < imageViewsGiocatore2.size();i++ ) {
            	
            	if(imageViewsGiocatore2.get(i).getImage()==cartaSelezionata.getImmagine()) {
            		imageViewsGiocatore2.get(i).setImage(null);
            		break;
            	}
            }
            // Resetta la carta selezionata
            cartaSelezionata = null;
           
            System.out.println("dimensione del mazzo giocatore 1 è "+mazzoGiocatore1.getCarte().size());
            System.out.println("dimensione del mazzo giocatore 2 è "+mazzoGiocatore2.getCarte().size());
            
        }
        aggiornaInterfaccia();
    }
   
    
}
    

