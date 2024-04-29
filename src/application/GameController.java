package application;


import javafx.event.ActionEvent;

import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.media.*;
import javafx.scene.layout.*;
public class GameController {
	
	private Main main;
	private Stage stage;
	
	public String player1Name,player2Name;
	private Mazzo mazzoGiocatore1,mazzoGiocatore2,mazzoCompleto,mazzoProvenienzaCartaSelezionata;
    private Imprevisti imprevisti;
    private Carta cartaSelezionata; //selezionata da utente serve per lo spostamento
    private ImageView cartaCliccata;
    private int punteggioG1Tavolo1,punteggioG1Tavolo2,punteggioG1Tavolo3;
    private int punteggioG2Tavolo1,punteggioG2Tavolo2,punteggioG2Tavolo3;
    private static int vittoriaSuTavoloG1,vittoriaSuTavoloG2;
	private boolean turnoGiocatore1= true;  //Prima turno giocatore1: pesca poi gioca una carta poi turno giocatore2...
    private boolean tuttiTavoliPieni=false;
    private MediaPlayer player;
    //PER IL TORNEO
	private boolean Torneo;
	private static int numeroPartita;
	private List<String> partite = new ArrayList<>();
	//FXML
	@FXML
    private AnchorPane backgroundPane;
	@FXML
    private Label LabelNomePunteggioG1T1,LabelNomePunteggioG2T1;
    @FXML
    private Label LabelPunteggioG1T1,LabelPunteggioG2T1;
    @FXML
     private Label LabelNomePunteggioG1T2,LabelNomePunteggioG2T2;
    @FXML
    private Label LabelPunteggioG1T2,LabelPunteggioG2T2;
    @FXML
    private Label LabelNomePunteggioG1T3,LabelNomePunteggioG2T3;
   @FXML
    private Label LabelPunteggioG1T3,LabelPunteggioG2T3;
    @FXML
    private Label LabelIconaNomeG1,LabelIconaNomeG2;
    @FXML
    private Label turnoLabel;
    @FXML
    private ListView<Carta> listaCarteGiocatore1,listaCarteGiocatore2;
    @FXML
    private ImageView Carta1G1,Carta2G1,Carta3G1,Carta4G1; //carte giocatore1
    @FXML
    private ImageView Carta1G2,Carta2G2,Carta3G2,Carta4G2;
    @FXML
    private List<ImageView> imageViewsGiocatore1,imageViewsGiocatore2;
    @FXML
    private List<ImageView> imageViewsTavolo1,imageViewsTavolo2,imageViewsTavolo3;
    @FXML
    private Button PescaGiocatore1,PescaGiocatore2,BottoneUscita;
 
  //tavoli da gioco
    //tavolo1
    @FXML 
    private GridPane Tavolo1;
    @FXML
    private ImageView CartaT1p00,CartaT1p10,CartaT1p01,CartaT1p11; //carte tavolo1 
    //tavolo2
    @FXML 
    private GridPane Tavolo2;
    @FXML
    private ImageView CartaT2p00,CartaT2p10,CartaT2p01,CartaT2p11;
    //tavolo3
    @FXML
    private GridPane Tavolo3;
    @FXML
    private ImageView CartaT3p00,CartaT3p10,CartaT3p01,CartaT3p11;
    @FXML
    private ImageView imprevisto1;
    
    //METODI SET E GETTER
    //serve per il cambio scena
    public void setMain(Main main) {
    	this.main= main;
    }
    public void setStage(Stage stage) {
	        this.stage = stage;
    }
	  //nomi dei giocatori
    public void setPlayersNames() {
    	if(Torneo == false) {
	        this.player1Name = ControllerPrePartita.getPlayer1(); 
	        this.player2Name = ControllerPrePartita.getPlayer2();
    	}
    	if(Torneo==true) {
    		String partita = partite.get(numeroPartita);
    		String[] giocatori = partita.split(" vs ");

    		this.player1Name = giocatori[0];
    		this.player2Name= giocatori[1];
    	}
      
    }
    public void getTorneo() {
    	this.Torneo = MenuController.getTorneo();
    }
    public void getPartite() {
    	this.partite = PreTorneo4Controller.getPartite();
    }
    public static int getNumeroPartita() {
    	return numeroPartita;
    }
    public static  int getVittoriaSuTavoloG1() {
    	return vittoriaSuTavoloG1;
    }
    public static  int getVittoriaSuTavoloG2() {
    	return vittoriaSuTavoloG2;
    }
    
    //SFONDO E MUSICA
    public void insertSfondo() {
    	try {
            // Percorso del file immagine
           String filePath = "src/Sfondo/Background_gioco_prova2.jpeg";

            // Creazione di un oggetto File con il percorso del file
            File file = new File(filePath);

            // Creazione di un oggetto FileInputStream per leggere il file
            FileInputStream inputStream = new FileInputStream(file);
            backgroundPane.setStyle("-fx-background-image: url('" + file.toURI().toString() + "')");
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insertMusic() {
        	try {
    			 Media sound = new Media(new File("src/Musica/MusicaSottofondoGioco1.mp3").toURI().toString());
    			   player = new MediaPlayer(sound);
    			   //continua sempre a suonare
    			   player.setCycleCount(MediaPlayer.INDEFINITE);
    			   player.play();
    		}catch(Exception e ) {
    			System.out.println("errore riproduzione");
    		}
        }	
    
    //INIZIALIZZAZIONE
    public void initialize() {
    	getTorneo();
    	getPartite();
    	insertMusic();
    	setPlayersNames();
    	insertSfondo();
    	
    	//inizializzazione Label
    	LabelIconaNomeG1.setText(this.player1Name);
    	LabelIconaNomeG2.setText(this.player2Name);
    	turnoLabel.setText("Turno di "+ " "+ this.player1Name+"!");
    	turnoLabel.setStyle("-fx-text-fill: black;");
    	LabelNomePunteggioG1T1.setText(this.player1Name);
    	LabelNomePunteggioG2T1.setText(this.player2Name);
    	LabelNomePunteggioG1T2.setText(this.player1Name);
    	LabelNomePunteggioG2T2.setText(this.player2Name);
    	LabelNomePunteggioG1T3.setText(this.player1Name);
    	LabelNomePunteggioG2T3.setText(this.player2Name);
    	
    	//PREPARAZIONE GIOCO
    	 imprevisti = new Imprevisti();
    	 visualizzaImprevisti();
    	//mazzo con tutte le carte disponibili
    	mazzoCompleto = new Mazzo();
    	mazzoCompleto.CreaMazzoCompleto();
       // System.out.println("numero carte nel mazzo iniziale completo"+mazzoCompleto.getNumeroCarte());
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
    
    }
 // Metodo per visualizzare un'imprevisto
    public void visualizzaImprevisti() {
        try {
            imprevisti.caricaImprevistoCasuale();
            Image immagineImprevisto = imprevisti.caricaImmagineImprevisto();
            imprevisto1.setImage(immagineImprevisto);
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento dell'immagine dell'imprevisto: " + e.getMessage());
        }
    }

    public void caricaCarteIniziali() {
    	//carica 3 carte nel mazzo di ogni giocatore
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
    }
   
    private void aggiornaTurnoLabel() {
 
        if (turnoGiocatore1) {
            turnoLabel.setText("TURNO DI "+ " "+ this.player1Name);
            turnoLabel.setStyle("-fx-text-fill: black;");
        } else {
            turnoLabel.setText("TURNO DI "+ " "+ this.player2Name);
            turnoLabel.setStyle("-fx-text-fill: blue;");
        }
    }
    public void passaTurno() {
    	turnoGiocatore1= !turnoGiocatore1; 
    	
    }
    //FINE PARTITA con conteggio punti
    public void verificaFinePartita(){
    	tuttiTavoliPieni = verificaTuttiTavoliSonoPieni();
    	if(tuttiTavoliPieni== true) {
    		vittoriaSuTavoloG1 =0;
    		vittoriaSuTavoloG2=0;
    		turnoLabel.setText("FINE PARTITA");
    		//Verifica punteggi su tavolo1
    		if(punteggioG1Tavolo1 > punteggioG2Tavolo1) {
    			vittoriaSuTavoloG1+=1;
    		}else {
    			if(punteggioG1Tavolo1 <  punteggioG2Tavolo1) {
    				vittoriaSuTavoloG2+=1;
    			}
    		
    		}
    		//Punteggi su tavolo2
    		if(punteggioG1Tavolo2 > punteggioG2Tavolo2) {
    			vittoriaSuTavoloG1+=1;
    		}else {
    			if(punteggioG1Tavolo2 <  punteggioG2Tavolo2) {
    				vittoriaSuTavoloG2+=1;
    			}
    			
    		}
    		//punteggi su tavolo 3
    		if(punteggioG1Tavolo3 > punteggioG2Tavolo3) {
    			vittoriaSuTavoloG1+=1;
    		}else {
    			if(punteggioG1Tavolo3 <  punteggioG2Tavolo3) {
    				vittoriaSuTavoloG2+=1;
    			}
    			
    		}
    		player.stop();
    		numeroPartita++; //per il torneo
    		if(Torneo==false) {
	    		//Calcolo punteggio finale
	    		try {
	    			main.showFilmatoFinale();
	    		}catch(Exception e) {
	    			System.out.println("errore caricamento filmato finale");
	    		}
    		//se siamo dentro  torneo
	    	}else {
		    		if(numeroPartita==partite.size()) {
		    			try {
		    				main.showGameOverTorneoScene();
		    			}catch(Exception  e) {
		    				e.printStackTrace();
		    			}
		    		}
		    		else {
		    			try {
		    				main.showWinningScene();
		    			}
		    			catch(Exception e ) {
		    				System.out.println("errore nel caricamento winningscene");
		    			}
	    		
		    		}
	    	}
    	}
    		
	}
     //condizione della fine partita: tutti tavoli risultano pieni
    public boolean verificaTuttiTavoliSonoPieni() {
    	for(int i=0;i<imageViewsTavolo1.size();i++) {
    		if(imageViewsTavolo1.get(i).getImage()== null ) {
    			return false;
    		}
    	}
    	for(int i=0;i<imageViewsTavolo2.size();i++) {
    		if(imageViewsTavolo2.get(i).getImage()== null ) {
    			return false;
    		}
    	}
    	for(int i=0;i<imageViewsTavolo3.size();i++) {
    		if(imageViewsTavolo3.get(i).getImage()== null ) {
    			return false;
    		}
    	}

    	
    	return true;
    }
    //BOTTONI PESCA 
    //pesca una carta per il giocatore 1 
    public void handlePescaGiocatore1Action(ActionEvent event) {
    	if(turnoGiocatore1) {
	    	
	    	Carta cartaCasuale = mazzoCompleto.pescaCartaCasuale();
	    	
	    	if(cartaCasuale != null &&  mazzoGiocatore1.getNumeroCarte()<4) {
	    		mazzoGiocatore1.aggiungiCarta(cartaCasuale);
	    		aggiornaInterfaccia();
	    	}else {
	    		if(cartaCasuale== null) 
				{System.out.println("carta nulla");
			}
			if(mazzoGiocatore1.getNumeroCarte()>=4) {
				System.out.println("non c'è spazio");
				}
	    	}	
    	}
	
    }
    //pesca una carta per il giocatore2
    public void handlePescaGiocatore2Action(ActionEvent event) {
    	if(!turnoGiocatore1) {
	    	Carta cartaCasuale = mazzoCompleto.pescaCartaCasuale();
	    	
	    	if(cartaCasuale != null && mazzoGiocatore2.getNumeroCarte()<4 ) {
	    		mazzoGiocatore2.aggiungiCarta(cartaCasuale);
	    		aggiornaInterfaccia();
	    		
	    	}else {
	    		if(cartaCasuale== null) 
	    			{System.out.println("carta nulla");
	    		}
	    		if(mazzoGiocatore2.getNumeroCarte()>=4) {
	    			System.out.println("non c'è spazio");
	    			}
	    	}
    	}
    	
    }
    //Bottone uscita dalla finestra
    public void handleBottoneUscita(ActionEvent event) {
    	stage.close();
    }
    
   //AGGIORNA INTERFACCIA
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
    	        cartaCliccata.setEffect(null);
    	        aggiornaTurnoLabel();
    	    } else {
    	        System.out.println("Errore: carta selezionata è nulla");
    	    }
	}
    //GESTORI CLICK
    
    //GESTORE DEI CLICK SULLE CARTE DEI MAZZI DEI DUE GIOCATORI
    public void handleClickCartaGiocatore1(MouseEvent event) {
    	if(turnoGiocatore1) {
	        cartaCliccata = (ImageView) event.getSource();
	        
	        // Trova l'indice dell'ImageView cliccata
	        int index = imageViewsGiocatore1.indexOf(cartaCliccata);
	        if (index == -1) {
	            return;
	            
	        }
	        mazzoProvenienzaCartaSelezionata = mazzoGiocatore1;
	        // Ottiene la carta associata all'immagine cliccata
	        if (index != -1) {
	            if (index < mazzoGiocatore1.getCarte().size()) {
	                cartaSelezionata = mazzoGiocatore1.getCarta(index);
	                cartaCliccata.setEffect(new DropShadow());
	            } else {
	                return;
	            }
	        }
	        passaTurno();
    	}
    	
	        
    }
    public void handleClickCartaGiocatore2(MouseEvent event) {
    	if(!turnoGiocatore1) {
	        cartaCliccata = (ImageView) event.getSource();
	        // Trova l'indice dell'ImageView cliccata
	        int index = imageViewsGiocatore2.indexOf(cartaCliccata);
	        if (index == -1) {
	            return;
	        }
	        mazzoProvenienzaCartaSelezionata = mazzoGiocatore2;
	        // Ottiene la carta associata all'immagine cliccata
	        if (index != -1) {
	           if (index < mazzoGiocatore2.getCarte().size()) {
	        		cartaSelezionata = mazzoGiocatore2.getCarta(index);
	        		cartaCliccata.setEffect(new DropShadow());
	            } else {
	                return;
	            }
	        }
	        passaTurno();
    	}
        
    }
   
    //GESTORE DEI CLICK SU UNA POSIZIONE DEI TAVOLI
    private void handleClickPosizioneTavolo(MouseEvent event, ImageView posizioneTavolo, int tavoloNumero) {
        if (cartaSelezionata != null) {
            // Verifica che l'indice dell'ImageView sia valido
            int index = -1;
            if (tavoloNumero == 1) {
                index = imageViewsTavolo1.indexOf(posizioneTavolo);
            } else if (tavoloNumero == 2) {
                index = imageViewsTavolo2.indexOf(posizioneTavolo);
            } else if (tavoloNumero == 3) {
                index = imageViewsTavolo3.indexOf(posizioneTavolo);
            }
            if (index == -1) {
                // ImageView non trovata
                return;
            }
            // Controllo se la posizione del tavolo è già stata occupata
            if (posizioneTavolo.getImage() != null) {
                // La posizione è già occupata
                return;
            }
            int valoreCartaSelezionata = cartaSelezionata.getValore();
            // Applica l'effetto dell'imprevisto sulla carta selezionata
            cartaSelezionata.setValore(valoreCartaSelezionata);
            imprevisti.applicaEffettoCarta(cartaSelezionata);

            // Aggiornamento punteggio
            if (tavoloNumero == 1) {
            	if(mazzoProvenienzaCartaSelezionata ==mazzoGiocatore1) {
	            	punteggioG1Tavolo1 += cartaSelezionata.getValore();
	                LabelPunteggioG1T1.setText(String.valueOf(punteggioG1Tavolo1));	
            	}else {
            		punteggioG2Tavolo1 += cartaSelezionata.getValore();
	                LabelPunteggioG2T1.setText(String.valueOf(punteggioG2Tavolo1));
            	}
                
            } else if (tavoloNumero == 2) {
            	if(mazzoProvenienzaCartaSelezionata ==mazzoGiocatore1) {
	            	punteggioG1Tavolo2 += cartaSelezionata.getValore();
	                LabelPunteggioG1T2.setText(String.valueOf(punteggioG1Tavolo2));	
            	}else {
            		punteggioG2Tavolo2 += cartaSelezionata.getValore();
	                LabelPunteggioG2T2.setText(String.valueOf(punteggioG2Tavolo2));
            	}
            } else if (tavoloNumero == 3) {
            	if(mazzoProvenienzaCartaSelezionata ==mazzoGiocatore1) {
	            	punteggioG1Tavolo3 += cartaSelezionata.getValore();
	                LabelPunteggioG1T3.setText(String.valueOf(punteggioG1Tavolo3));	
            	}else {
            		punteggioG2Tavolo3 += cartaSelezionata.getValore();
	                LabelPunteggioG2T3.setText(String.valueOf(punteggioG2Tavolo3));
            	}
            }

            // Sposta la carta selezionata nella posizione del tavolo cliccata
            spostaCartaSuTavolo(cartaSelezionata, posizioneTavolo);

            // Rimuovi la carta dal mazzo del giocatore
            if (mazzoGiocatore1.contieneCarta(cartaSelezionata)) {
                mazzoGiocatore1.rimuoviCarta(cartaSelezionata);
            } else if (mazzoGiocatore2.contieneCarta(cartaSelezionata)) {
                mazzoGiocatore2.rimuoviCarta(cartaSelezionata);
            }

            // Svuota imageView dal mazzoGiocatore1
            for (int i = 0; i < imageViewsGiocatore1.size(); i++) {
                ImageView imageView = imageViewsGiocatore1.get(i);
                if (imageView.getImage() == cartaSelezionata.getImmagine()) {
                    imageView.setImage(null);
                    break;
                }
            }
            // Svuota imageView dal mazzoGiocatore2
            for (int i = 0; i < imageViewsGiocatore2.size(); i++) {
                ImageView imageView = imageViewsGiocatore2.get(i);
                if (imageView.getImage() == cartaSelezionata.getImmagine()) {
                    imageView.setImage(null);
                    break;
                }
            }

            // Resetta la carta selezionata
            cartaSelezionata = null;

            // Aggiorna interfaccia e verifica fine partita
            aggiornaInterfaccia();
            verificaFinePartita();
        }
    }

    // Gestore del click sul primo tavolo
    public void handleClickPosizioneTavolo1(MouseEvent event) {
        ImageView posizioneTavoloCliccata = (ImageView) event.getSource();
        handleClickPosizioneTavolo(event, posizioneTavoloCliccata, 1);
    }

    // Gestore del click sul secondo tavolo
    public void handleClickPosizioneTavolo2(MouseEvent event) {
        ImageView posizioneTavoloCliccata = (ImageView) event.getSource();
        handleClickPosizioneTavolo(event, posizioneTavoloCliccata, 2);
    }

    // Gestore del click sul terzo tavolo
    public void handleClickPosizioneTavolo3(MouseEvent event) {
        ImageView posizioneTavoloCliccata = (ImageView) event.getSource();
        handleClickPosizioneTavolo(event, posizioneTavoloCliccata, 3);
    }
}
  