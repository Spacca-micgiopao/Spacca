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
	
	public String player1Name;
	public String player2Name;
	
	private boolean botgioco = true;
	
	private Mazzo mazzoGiocatore1;
    private Mazzo mazzoGiocatore2;
    private Mazzo mazzoCompleto;
    
    private Carta cartaSelezionata; //selezionata da utente serve per lo spostamento
    private ImageView cartaCliccata;
    private Mazzo mazzoProvenienzaCartaSelezionata;
    private int punteggioG1Tavolo1;
    private int punteggioG1Tavolo2;
    private int punteggioG1Tavolo3;
    private int punteggioG2Tavolo1;
    private int punteggioG2Tavolo2;
    private int punteggioG2Tavolo3;
    private static int vittoriaSuTavoloG1;
    private static int vittoriaSuTavoloG2;
    private String nomeImprevisto1;
    private String tipoImprevisto1;
    private boolean annullatore;
    
    private boolean tuttiTavoliPieni=false;
   // private boolean partitaFinita= false; 
    
    
    @FXML
    private AnchorPane backgroundPane;
    
    private boolean turnoGiocatore1= true;  //Prima turno giocatore1: pesca poi gioca una carta poi turno giocatore2...
    @FXML
    private Label turnoLabel;
    @FXML
    private Label LabelVincitaT1;
    @FXML
    private Label LabelVincitaT2;
    @FXML
    private Label LabelVincitaT3;
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
    
 
    @FXML
    private Button PescaGiocatore1;
    @FXML
    private Button PescaGiocatore2;
    @FXML
   	private Button BottoneUscita;
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
    @FXML
    private ImageView imprevisto1;
    
    
    
    //serve per il cambio scena
    public void setMain(Main main) {
    	this.main= main;
    }
    //nomi dei giocatori
    public void setPlayersNames() {
        this.player1Name = ControllerPrePartita.getPlayer1(); 
        this.player2Name = ControllerPrePartita.getPlayer2();
      
    }

  
    public void insertSfondo() {
    	//Impostazione SFONDO
    	try {
            // Percorso del file immagine
            String filePath = "src/Sfondo/Background_gioco_prova1.jpg";

            // Creazione di un oggetto File con il percorso del file
            File file = new File(filePath);

            // Creazione di un oggetto FileInputStream per leggere il file
            FileInputStream inputStream = new FileInputStream(file);
            backgroundPane.setStyle("-fx-background-image: url('" + file.toURI().toString() + "')");
            
            visualizzaImprevisti();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //INIZIALIZZAZIONE
    public void initialize() {
    	
    	setPlayersNames();
    	turnoLabel.setText("TURNO DI "+ " "+ this.player1Name);
    	turnoLabel.setStyle("-fx-text-fill: black;");
    	
    	insertSfondo();
    	imprevisto1 = new ImageView();
    	//PREPARAZIONE GIOCO
        
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
    public void setStage(Stage stage) {
        this.stage = stage;
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
    private String getPercorsoFileCasuale(String percorsoCartella) {
        File cartella = new File(percorsoCartella);
        File[] files = cartella.listFiles();

        if (files != null && files.length > 0) {
            Random random = new Random();
            int indiceCasuale = random.nextInt(files.length);
            return files[indiceCasuale].getAbsolutePath(); // Restituisci il percorso assoluto del file
        } else {
            return null;
        }
    }

    public void visualizzaImprevisti() {
        String percorsoCartellaImprevisti = "src\\Imprevisti";

        String percorsoImprevisto1 = getPercorsoFileCasuale(percorsoCartellaImprevisti);

        if (percorsoImprevisto1 != null) {
            try (FileInputStream inputStream = new FileInputStream(percorsoImprevisto1)) {
                Image immagine1 = new Image(inputStream);

                // Visualizza le immagini nelle ImageView
                imprevisto1.setImage(immagine1);

                // Salva i nomi dei file immagine
                nomeImprevisto1 = new File(percorsoImprevisto1).getName();

                if ("raddoppia_verdi.jpg".equalsIgnoreCase(nomeImprevisto1)) {
                    tipoImprevisto1 = "raddoppia_verdi";
                } else if ("dimezza_rosse.jpg".equalsIgnoreCase(nomeImprevisto1)) {
                    tipoImprevisto1 = "dimezza_rosse";
                } else if ("dispari_piu_2.jpg".equalsIgnoreCase(nomeImprevisto1)) {
                    tipoImprevisto1 = "dispari_piu_2";
                } else if ("raddoppia_rosa.jpg".equalsIgnoreCase(nomeImprevisto1)) {
                    tipoImprevisto1 = "raddoppia_rosa";
                } else if ("raddoppia_blu.jpg".equalsIgnoreCase(nomeImprevisto1)) {
                    tipoImprevisto1 = "raddoppia_blu";
                } else if ("raddoppia_gialle.jpg".equalsIgnoreCase(nomeImprevisto1)) {
                    tipoImprevisto1 = "raddoppia_gialle";
                } else if ("raddoppia_rosse.jpg".equalsIgnoreCase(nomeImprevisto1)) {
                    tipoImprevisto1 = "raddoppia_rosse";
                } else if ("dimezza_blu.jpg".equalsIgnoreCase(nomeImprevisto1)) {
                    tipoImprevisto1 = "dimezza_blu";
                } else if ("dimezza_rosa.jpg".equalsIgnoreCase(nomeImprevisto1)) {
                    tipoImprevisto1 = "dimezza_rosa";
                } else if ("dimezza_verdi.jpg".equalsIgnoreCase(nomeImprevisto1)) {
                    tipoImprevisto1 = "dimezza_verdi";
                } else if ("dimezza_gialle.jpg".equalsIgnoreCase(nomeImprevisto1)) {
                    tipoImprevisto1 = "dimezza_gialle";
                } else {
                    tipoImprevisto1 = null; // null se non corrisponde a nessun'imprevisto riconosciuto
                }
            } catch (IOException e) {
                e.printStackTrace();
               
                
                System.err.println("Errore durante il caricamento dell'immagine dell'imprevisto 1: " + e.getMessage());
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
    //FINE PARTITA
    public void verificaFinePartita(){
    	tuttiTavoliPieni = verificaTuttiTavoliSonoPieni();
    	if(tuttiTavoliPieni== true) {
    	//	partitaFinita=true;
    		vittoriaSuTavoloG1 =0;
    		vittoriaSuTavoloG2=0;
    		turnoLabel.setText("FINE PARTITA");
    		//Verifica punteggi su tavolo1
    		if(punteggioG1Tavolo1 > punteggioG2Tavolo1) {
    			vittoriaSuTavoloG1+=1;
    			LabelVincitaT1.setText(this.player1Name+" VINCE");
    		}else {
    			if(punteggioG1Tavolo1 <  punteggioG2Tavolo1) {
    				vittoriaSuTavoloG2+=1;
    				LabelVincitaT1.setText(this.player2Name+" VINCE ");
    			}
    			else {
    				LabelVincitaT1.setText("PAREGGIO");
    			}
    		}
    		//Punteggi su tavolo2
    		if(punteggioG1Tavolo2 > punteggioG2Tavolo2) {
    			vittoriaSuTavoloG1+=1;
    			LabelVincitaT2.setText(this.player1Name+" VINCE ");
    		}else {
    			if(punteggioG1Tavolo2 <  punteggioG2Tavolo2) {
    				vittoriaSuTavoloG2+=1;
    				LabelVincitaT2.setText(this.player2Name+" VINCE");
    			}
    			else {
    				LabelVincitaT2.setText("PAREGGIO");
    			}
    		}
    		//punteggi su tavolo 3
    		if(punteggioG1Tavolo3 > punteggioG2Tavolo3) {
    			vittoriaSuTavoloG1+=1;
    			LabelVincitaT3.setText(this.player1Name+ " VINCE");
    		}else {
    			if(punteggioG1Tavolo3 <  punteggioG2Tavolo3) {
    				vittoriaSuTavoloG2+=1;
    				LabelVincitaT3.setText(this.player2Name+" VINCE");
    			}
    			else {
    				LabelVincitaT3.setText("PAREGGIO");
    			}
    		}
    		
    		//Calcolo punteggio finale
    		if(vittoriaSuTavoloG1 > vittoriaSuTavoloG2) {
    			turnoLabel.setText("VINCITORE FINALE E' "+ " " + player1Name+ " !");
    			try {
					main.showFilmatoFinale();
				} catch (Exception e) {
					System.out.println("errore nei filmati finali");
				}
				
    		}
    		else if(vittoriaSuTavoloG1 == vittoriaSuTavoloG2){
    			turnoLabel.setText("PAREGGIO FINALE");
    			try {
					main.showFilmatoFinale();
				} catch (Exception e) {
					System.out.println("errore nei filmati  finali");
				}
    		}
    		else {
    		
    			turnoLabel.setText("VINCITORE FINALE E' "+" "+ player2Name+" !");
    			try {
					main.showFilmatoFinale();
				} catch (Exception e) {
					System.out.println("errore  nei  filmati finali");
				}
    		}
    		
    		
    	}
    }
    public static  int getVittoriaSuTavoloG1() {
    	return vittoriaSuTavoloG1;
    }
    public static  int getVittoriaSuTavoloG2() {
    	return vittoriaSuTavoloG2;
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
    
    //GESTORE DEI CLICK SULLE CARTE DEI MAZZI DEI DUE GIOCATORI
    public void handleClickCartaGiocatore1(MouseEvent event) {
    	if(turnoGiocatore1) {
	        cartaCliccata = (ImageView) event.getSource();
	        
	        // Trova l'indice dell'ImageView cliccata
	        int index = imageViewsGiocatore1.indexOf(cartaCliccata);
	        if (index == -1) {
	            return;
	            
	        }
	        mazzoProvenienzaCartaSelezionata= mazzoGiocatore1;
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
    		if(!tavoloIsFull((ArrayList<ImageView>) imageViewsTavolo1)){
    			bot.giocaCarta(mazzoGiocatore2, (ArrayList<ImageView>) imageViewsTavolo1);
    			aggiornaInterfaccia();
    			passaTurno();
    			aggiornaTurnoLabel();
    			Carta cartaCasuale = mazzoCompleto.pescaCartaCasuale();
    	    	if(cartaCasuale != null && mazzoGiocatore2.getNumeroCarte()<4 ) {
    	    		mazzoGiocatore2.aggiungiCarta(cartaCasuale);
    	    		aggiornaInterfaccia();
    	    	}
	    	    cartaSelezionata= cartaCasuale;
    		}else if(!(tavoloIsFull((ArrayList<ImageView>) imageViewsTavolo2))){
    			bot.giocaCarta(mazzoGiocatore2, (ArrayList<ImageView>) imageViewsTavolo2);
    			aggiornaInterfaccia();	
    			passaTurno();
    			aggiornaTurnoLabel();
    			Carta cartaCasuale2 = mazzoCompleto.pescaCartaCasuale();
    	    	if(cartaCasuale2 != null && mazzoGiocatore2.getNumeroCarte()<4 ) {
    	    		mazzoGiocatore2.aggiungiCarta(cartaCasuale2);
    	    		aggiornaInterfaccia();
    	    	}
    	    	cartaSelezionata= cartaCasuale2;    
    	    	}else {
    			bot.giocaCarta(mazzoGiocatore2, (ArrayList<ImageView>) imageViewsTavolo3);
    			aggiornaInterfaccia();
    			passaTurno();
    			aggiornaTurnoLabel();
    			Carta cartaCasuale3 = mazzoCompleto.pescaCartaCasuale();
    		
    	    	if(cartaCasuale3 != null && mazzoGiocatore2.getNumeroCarte()<4 ) {
    	    		mazzoGiocatore2.aggiungiCarta(cartaCasuale3);
    	    		aggiornaInterfaccia();
    	    	}
    	    	cartaSelezionata= cartaCasuale3;
    	    	System.out.println("cara di bot ");
    		}
    	}
        
    }
    private int gestisciEffetto(String tipoImprevisto, Carta cartaSelezionata) {
    	 System.out.println("Tipo imprevisto: " + tipoImprevisto1);
    	 int valorecarta;

        switch (tipoImprevisto) {
            case "raddoppia_verdi":
            case "raddoppia_rosa":
            case "raddoppia_blu":
            case "raddoppia_gialle":
            case "raddoppia_rosse":
                if (cartaSelezionata.getColore().equals("verde") || cartaSelezionata.getColore().equals("rosa") ||
                    cartaSelezionata.getColore().equals("blu") || cartaSelezionata.getColore().equals("giallo") ||
                    cartaSelezionata.getColore().equals("rosso")) {
                    cartaSelezionata.setValore(cartaSelezionata.getValore() * 2);
                    System.out.println("valore aggiornato raddoppio");
                }
                break;
            case "dimezza_rosse":
            case "dimezza_blu":
            case "dimezza_rosa":
            case "dimezza_verdi":
            case "dimezza_gialle":
                if (cartaSelezionata.getColore().equals("rosso") || cartaSelezionata.getColore().equals("blu") ||
                    cartaSelezionata.getColore().equals("rosa") || cartaSelezionata.getColore().equals("verde") ||
                    cartaSelezionata.getColore().equals("giallo")) {
                    cartaSelezionata.setValore(cartaSelezionata.getValore() / 2);
                    System.out.println("valore aggiornato dimezzo");
                }
                break;
            case "dispari_piu_2":
                if (cartaSelezionata.getValore() % 2 == 1) {
                    cartaSelezionata.setValore(cartaSelezionata.getValore() + 2);
                }
                break;
            default:
                // Nessun effetto da gestire per questo tipo di imprevisto
                break;
                
        }
    	 valorecarta= cartaSelezionata.getValore();
        return valorecarta;
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
            if(cartaSelezionata.getColore().equals("annulla")) {
            	annullatore = true;
            }
            if(cartaSelezionata.getColore().equals("random")) {
            	Random rand = new Random();
            	int randomnum = rand.nextInt();       
                int valore = (randomnum == 0) ? 0 : 8;
                cartaSelezionata.setValore(valore);
                System.out.println("Il valore di random è  " + cartaSelezionata.getValore());
            }
            //controllo se la posizione del tavolo è già stata occupata
            if (posizioneTavoloCliccata.getImage() != null) {
                // La posizione è già occupata, quindi non posso posizionare la carta
                return;
            }
            int valoreCartaSelezionata = cartaSelezionata.getValore();
            valoreCartaSelezionata= gestisciEffetto(tipoImprevisto1, cartaSelezionata);
            cartaSelezionata.setValore(valoreCartaSelezionata);
            System.out.println(cartaSelezionata.getValore() + "  " + cartaSelezionata.getColore());
            //Aggiornamento punteggio
            if(mazzoProvenienzaCartaSelezionata == mazzoGiocatore1) {
            	punteggioG1Tavolo1 += cartaSelezionata.getValore();
            }else {
            	punteggioG2Tavolo1 += cartaSelezionata.getValore();
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
         
        }
        if(annullatore){
        	punteggioG1Tavolo1 = 0;
        	punteggioG2Tavolo1 =0;
        	annullatore=false;
        }
        aggiornaInterfaccia();
      //modifiche bot
        if(botgioco){
        	handleClickCartaGiocatore2(event);
        }
       
        verificaFinePartita();
    }
    //CLICK SUL SECONDO TAVOLO
    public void handleClickPosizioneTavolo2(MouseEvent event) { // metodo funziona solo se le carte sono diverse
        if (cartaSelezionata != null ) {
            ImageView posizioneTavoloCliccata = (ImageView) event.getSource();
            
            // Verifica che l'indice dell'ImageView sia valido
            int index = imageViewsTavolo2.indexOf(posizioneTavoloCliccata);
            if(cartaSelezionata.getColore().equals("annulla")) {
            	annullatore = true;
            }
            if(cartaSelezionata.getColore().equals("random")) {
            	Random rand = new Random();
            	int randomnum = rand.nextInt();       
                int valore = (randomnum == 0) ? 0 : 8;
                cartaSelezionata.setValore(valore);
                System.out.println("Il valore di random è  " + cartaSelezionata.getValore());
            }
            if (index == -1) {
                // ImageView non trovata, esci dalla funzione
                return;
            }
            
            //controllo se la posizione del tavolo è già stata occupata
            if (posizioneTavoloCliccata.getImage() != null) {
                // La posizione è già occupata, quindi non posso posizionare la carta
                return;
            }
            int valoreCartaSelezionata = cartaSelezionata.getValore();
            valoreCartaSelezionata= gestisciEffetto(tipoImprevisto1, cartaSelezionata);
            gestisciEffetto(tipoImprevisto1, cartaSelezionata);
            cartaSelezionata.setValore(valoreCartaSelezionata);
            System.out.println(cartaSelezionata.getValore() + "  " + cartaSelezionata.getColore());
          //Aggiornamento punteggio
            if(mazzoProvenienzaCartaSelezionata == mazzoGiocatore1) {
            	punteggioG1Tavolo2 += cartaSelezionata.getValore();
            }else {
            	punteggioG2Tavolo2 += cartaSelezionata.getValore();
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
         
        }
        if(annullatore){
        	punteggioG1Tavolo2 = 0;
        	punteggioG2Tavolo2 =0;
        	annullatore=false;
        }
        aggiornaInterfaccia();
      //modifiche bot
        if(botgioco){
        	handleClickCartaGiocatore2(event);
        }
        verificaFinePartita();
    }
    //CLICK SUL TERZO TAVOLO
    public void handleClickPosizioneTavolo3(MouseEvent event) { // metodo funziona solo se le carte sono diverse
        if (cartaSelezionata != null ) {
            ImageView posizioneTavoloCliccata = (ImageView) event.getSource();
            
            // Verifica che l'indice dell'ImageView sia valido
            int index = imageViewsTavolo3.indexOf(posizioneTavoloCliccata);
            if(cartaSelezionata.getColore().equals("annulla")) {
            	annullatore = true;
            }
            if(cartaSelezionata.getColore().equals("random")) {
            	Random rand = new Random();
            	int randomnum = rand.nextInt();       
                int valore = (randomnum == 0) ? 0 : 8;
                cartaSelezionata.setValore(valore);
            }
            if (index == -1) {
                // ImageView non trovata, esci dalla funzione
                return;
            }
            
            //controllo se la posizione del tavolo è già stata occupata
            if (posizioneTavoloCliccata.getImage() != null) {
                // La posizione è già occupata, quindi non posso posizionare la carta
                return;
            }
            int valoreCartaSelezionata = cartaSelezionata.getValore();
            valoreCartaSelezionata= gestisciEffetto(tipoImprevisto1, cartaSelezionata);
            cartaSelezionata.setValore(valoreCartaSelezionata);
            System.out.println(cartaSelezionata.getValore() + "  " + cartaSelezionata.getColore());
            //Aggiornamento punteggio
            if(mazzoProvenienzaCartaSelezionata == mazzoGiocatore1) {
            	punteggioG1Tavolo3 += cartaSelezionata.getValore();
            }else {
            	punteggioG2Tavolo3 += cartaSelezionata.getValore();
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
           
        }
        if(annullatore){
        	punteggioG1Tavolo3 = 0;
        	punteggioG2Tavolo3 =0;
        	annullatore=false;
        }
        aggiornaInterfaccia();
        //modifiche bot
        if(botgioco){
        	handleClickCartaGiocatore2(event);
        }
        verificaFinePartita();
    }
   
}