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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.media.*;
import javafx.scene.layout.*;
public class GameController  implements Serializable{
	
	private Main main;
	private Stage stage;
	
	//Per gestire il caricamento delle partite
	public static int flag = 0;
	private Salvataggi salvataggio = new Salvataggi(this);
	private boolean botgioco = false;
	public static String player1Name;
	public static String player2Name;
	protected Mazzo mazzoGiocatore1,mazzoGiocatore2,mazzoCompleto,mazzoProvenienzaCartaSelezionata;
	protected String[] carteTavolo1,carteTavolo2,carteTavolo3; //servono per tenere traccia delle carte nei tavoli (nel formato "colore_valore")
	//Imprevisti alfa e beta sono 2 oggetti della classe imprevisti che li gestiscono
    public Imprevisti imprevistiAlfa = new Imprevisti(this);
    public Imprevisti imprevistiBeta = new Imprevisti(this);
    private Carta cartaSelezionata; //selezionata da utente serve per lo spostamento
    private ImageView cartaCliccata;
    protected int punteggioG1Tavolo1,punteggioG1Tavolo2,punteggioG1Tavolo3;
    protected int punteggioG2Tavolo1,punteggioG2Tavolo2,punteggioG2Tavolo3;
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
	protected Label LabelPunteggioG1T1, LabelPunteggioG1T2,LabelPunteggioG1T3,LabelPunteggioG2T1,LabelPunteggioG2T2,
	LabelPunteggioG2T3, LabelIconaNomeG1, LabelIconaNomeG2, turnoLabel,imprevistiAlfaLabel ,imprevistiBetaLabel;
    @FXML
    protected ListView<Carta> listaCarteGiocatore1,listaCarteGiocatore2;
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
    //tavolo1---------------------------------------------------
    @FXML 
    private GridPane Tavolo1;
    @FXML
    private ImageView CartaT1p00,CartaT1p10,CartaT1p01,CartaT1p11; //carte tavolo1 
    //tavolo2-------------------------------------------------------
    @FXML 
    private GridPane Tavolo2;
    @FXML
    private ImageView CartaT2p00,CartaT2p10,CartaT2p01,CartaT2p11;
    //tavolo3-----------------------------------------------------
    @FXML
    private GridPane Tavolo3;
    @FXML
    private ImageView CartaT3p00,CartaT3p10,CartaT3p01,CartaT3p11;
    @FXML
    private Pane CampoBasso,CampoAlto;
    @FXML
    private Rectangle rettangolo1;
    
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
	        player1Name = ControllerPrePartita.getPlayer1(); 
	        player2Name = ControllerPrePartita.getPlayer2();
    	}
    	if(Torneo==true) {
    		String partita = partite.get(numeroPartita);
    		String[] giocatori = partita.split(" vs ");

    		player1Name = giocatori[0];
    		player2Name= giocatori[1];
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
    
    public void insertMusic() {
        	try {
    			 Media sound = new Media(new File("src/Musica/MusicaSottofondoGioco1.mp3").toURI().toString());
    			   player = new MediaPlayer(sound);
    			   //continua sempre a suonare
    			   player.setCycleCount(MediaPlayer.INDEFINITE);
    			   player.play();
    			   player.setVolume(0.02);
    		}catch(Exception e ) {
    			System.out.println("errore riproduzione");
    		}
        }	
    
    //INIZIALIZZAZIONE se il flag = 1 la partita è stata caricata e inizierà in modo diverso da una partita iniziata da 0
    public void initialize() {
    	insertMusic();
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
        
    	if(flag == 0) {
	    	getTorneo();
	    	getPartite();
	    	setPlayersNames();
    	}
    	
    	LabelIconaNomeG1.setText(player1Name);
    	LabelIconaNomeG2.setText(player2Name);
    	if(flag == 1) {
    		salvataggio.caricaPartita(this);
    		//Come per i nomi vanno aggiornati i punteggi nel caso siano stati caricati
    		LabelPunteggioG1T1.setText(this.punteggioG1Tavolo1+"");
    		LabelPunteggioG1T2.setText(this.punteggioG1Tavolo2+"");
    		LabelPunteggioG1T3.setText(this.punteggioG1Tavolo3+"");
    		LabelPunteggioG2T1.setText(this.punteggioG2Tavolo1+"");
    		LabelPunteggioG2T2.setText(this.punteggioG2Tavolo2+"");
    		LabelPunteggioG2T3.setText(this.punteggioG2Tavolo3+"");
    		//caricamento anche delle immagini
    		Salvataggi.associazioneImmaginiAMazzo(this.mazzoGiocatore1);
    		Salvataggi.associazioneImmaginiAMazzo(this.mazzoGiocatore2);
    		Salvataggi.associazioneImmaginiATavolo(this.carteTavolo1,this.imageViewsTavolo1);
    		Salvataggi.associazioneImmaginiATavolo(this.carteTavolo2,this.imageViewsTavolo2);
    		Salvataggi.associazioneImmaginiATavolo(this.carteTavolo3,this.imageViewsTavolo3);
    	}
    	
    	//inizializzazione Label
    	turnoLabel.setText("Turno di "+ " "+ player1Name+"!");
    	turnoLabel.setStyle("-fx-text-fill: black;");
    	 visualizzaImprevisti();
	 // rettanoglo bot
   	if (botgioco) {
   	    	rettangolo1.setVisible(true); 	
   	    } else {
   	    	rettangolo1.setVisible(false);
   	    }
    	 
    	//mazzo con tutte le carte disponibili
    	mazzoCompleto = new Mazzo();
    	mazzoCompleto.CreaMazzoCompleto();
       
    	if(flag == 0) {
    	mazzoGiocatore1 = new Mazzo();
    	mazzoGiocatore2 = new Mazzo();
    	carteTavolo1 = new String[4]; carteTavolo2 = new String[4]; carteTavolo3= new String[4];
        mazzoGiocatore1.caricaCarteIniziali(3,mazzoCompleto); //carica tre carte iniziali
        mazzoGiocatore2.caricaCarteIniziali(3, mazzoCompleto);
    	}
        aggiornaInterfaccia();
    }
 // Metodo per visualizzare un'imprevisto
    public void visualizzaImprevisti() {
    	imprevistiAlfa.caricaImprevistoCasuale();
    	imprevistiBeta.caricaImprevistoCasuale();
    	//Di base non c'è davvero un motivo per cui 2 imprevisti uguali non dovrebbero essere usati insieme
    	//Detto questo vorrei che le probabilità siano davvero basse quindi deve succedere 2 volte di fila per
    	//Funzionare davvero il che è quasi impossibile
    	if(imprevistiAlfa.scelto() == imprevistiBeta.scelto()) {
    		imprevistiAlfa.caricaImprevistoCasuale();
    		imprevistiBeta.caricaImprevistoCasuale();
    	}
        imprevistiAlfaLabel.setText(imprevistiAlfa.scelto());
        imprevistiBetaLabel.setText(imprevistiBeta.scelto());
    }
    
    private void aggiornaTurnoLabel() {
 
        if (turnoGiocatore1) {
            turnoLabel.setText("TURNO DI "+ " "+ player1Name);
            turnoLabel.setStyle("-fx-text-fill: black;");
        } else {
            turnoLabel.setText("TURNO DI "+ " "+ player2Name);
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
	    		visualizzaImprevisti();
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
	    		visualizzaImprevisti();
	    	}
	    		
    	}
    	
    }
    //Bottone uscita dalla finestra
    public void handleBottoneUscita(ActionEvent event) {
    	player.stop();
    	salvataggio.salvaPartita();
    	flag = 0;
    	try {
			main.showPrePartitaScene();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
  //controlla se tavolo è pieno
    public boolean tavoloIsFull(ArrayList<ImageView> tavolo) {
        for (ImageView imageView : tavolo) {
            if (imageView.getImage() == null) {
                // Se almeno un'immagine nel tavolo è vuota, il tavolo non è pieno
                return false;
            }
        }
        // Se tutte le immagini nel tavolo sono piene, il tavolo è pieno
        return true;
    }
 // giocatore 2 più bot
    public void handleClickCartaGiocatore2(MouseEvent event) {
    	if(!turnoGiocatore1&& !botgioco) {
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
	      //modifiche bot
    	}else {
    		botLogic bot = new botLogic();
    		
    		
    		if(!tavoloIsFull((ArrayList<ImageView>) imageViewsTavolo1) ){
    			bot.giocaCarta(mazzoGiocatore2, (ArrayList<ImageView>) imageViewsTavolo1);
    			
    			aggiornaInterfaccia();
    			passaTurno();
    			aggiornaTurnoLabel();
    			Carta cartaCasuale = mazzoCompleto.pescaCartaCasuale();
    	    	if(cartaCasuale != null && mazzoGiocatore2.getNumeroCarte()<4 ) {
    	    		mazzoGiocatore2.aggiungiCarta(cartaCasuale);
    	    		aggiornaInterfaccia();
    	    		cartaSelezionata= cartaCasuale;
    	    		int valoreCartaSelezionata = cartaSelezionata.getValore();
    	           // valoreCartaSelezionata= imprevisti.gestisciEffetto(tipoImprevisto1, cartaSelezionata);
    	            cartaSelezionata.setValore(valoreCartaSelezionata);
    	    		punteggioG2Tavolo1 += cartaSelezionata.getValore();
    	    	}
    	    	
	    	   
    		}else if(!(tavoloIsFull((ArrayList<ImageView>) imageViewsTavolo2))){
    			bot.giocaCarta(mazzoGiocatore2, (ArrayList<ImageView>) imageViewsTavolo2);
    			
    			aggiornaInterfaccia();	
    			passaTurno();
    			aggiornaTurnoLabel();
    			Carta cartaCasuale2 = mazzoCompleto.pescaCartaCasuale();
    	    	if(cartaCasuale2 != null && mazzoGiocatore2.getNumeroCarte()<4 ) {
    	    		mazzoGiocatore2.aggiungiCarta(cartaCasuale2);
    	    		aggiornaInterfaccia();
    	    		cartaSelezionata= cartaCasuale2;
    	    		int valoreCartaSelezionata = cartaSelezionata.getValore();
    	            //valoreCartaSelezionata= imprevisti.gestisciEffetto(tipoImprevisto1, cartaSelezionata);
    	            cartaSelezionata.setValore(valoreCartaSelezionata);
    	    		punteggioG2Tavolo2 += cartaSelezionata.getValore();
    	    	}
    	    	

    		}else {
    			bot.giocaCarta(mazzoGiocatore2, (ArrayList<ImageView>) imageViewsTavolo3);
    			aggiornaInterfaccia();
    			passaTurno();
    			aggiornaTurnoLabel();
    			Carta cartaCasuale3 = mazzoCompleto.pescaCartaCasuale();
    		
    	    	if(cartaCasuale3 != null && mazzoGiocatore2.getNumeroCarte()<4 ) {
    	    		mazzoGiocatore2.aggiungiCarta(cartaCasuale3);
    	    		aggiornaInterfaccia();
    	    		cartaSelezionata= cartaCasuale3;
    	    		int valoreCartaSelezionata = cartaSelezionata.getValore();
    	            //valoreCartaSelezionata= imprevisti.gestisciEffetto(tipoImprevisto1, cartaSelezionata);
    	            cartaSelezionata.setValore(valoreCartaSelezionata);
    	    		punteggioG2Tavolo3 += cartaSelezionata.getValore();
    	    	}
	    		
    		}
    		}
        
    }
        
   
    //GESTORE DEI CLICK SU UNA POSIZIONE DEI TAVOLI
    private void handleClickPosizioneTavolo(MouseEvent event, ImageView posizioneTavolo, int tavoloNumero) {
        if (cartaSelezionata != null) {
            // Verifica che l'indice dell'ImageView sia valido
            int index = -1;
            int valoreCartaSelezionata = cartaSelezionata.getValore();
            String colore = cartaSelezionata.getColore();
             if (tavoloNumero == 1) {
                index = imageViewsTavolo1.indexOf(posizioneTavolo);
                carteTavolo1[index]= colore+"_"+valoreCartaSelezionata;
            } else if (tavoloNumero == 2) {
                index = imageViewsTavolo2.indexOf(posizioneTavolo);
                carteTavolo2[index]= colore+"_"+valoreCartaSelezionata;
            } else if (tavoloNumero == 3) {
                index = imageViewsTavolo3.indexOf(posizioneTavolo);
                carteTavolo3[index]= colore+"_"+valoreCartaSelezionata;
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
            
            // Applica l'effetto dell'imprevisto sulla carta selezionata
            cartaSelezionata.setValore(valoreCartaSelezionata);
            imprevistiAlfa.applicaEffettoCarta(cartaSelezionata);
            imprevistiBeta.applicaEffettoCarta(cartaSelezionata);

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
            //modifiche bot
            if(botgioco){
            	handleClickCartaGiocatore2(event);
            }
            
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
  
