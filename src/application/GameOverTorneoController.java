package application;

import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
//SCHERMATA FINALE PER DICHIARARE VINCITORE TORNEO
public class GameOverTorneoController {
	private Main main;
	private Stage stage;
	private int vittoriaSuTavoloG1;
	private int vittoriaSuTavoloG2;
	private String player1Name;
	private String player2Name;
	private List<String> partite = new ArrayList<>();
	private String[] G; //tutti  giocatori torneo
	private int[] numeroVittorieG;
	private List<String> vincitori;
	private ArrayList<String> VincitoriPari;
	private int numeroPartita;
	private String vincitore;
	private MediaPlayer player;
	@FXML
	private Label LabelVincitorFinale;
	@FXML 
	private Label G1P1,G2P1,G1P2,G2P2,G1P3,G2P3,G1P4,G2P4,G1P5,G2P5,G1P6,G2P6;
	@FXML
	private Label[] InsiemeGiocatoriPartite;
	@FXML
	private Label Pareggiante1,Pareggiante2,Pareggiante3,Pareggiante4;
	@FXML 
	private Label[] LabelPareggianti;
	@FXML
	private Label VincitoreP1,VincitoreP2,VincitoreP3,VincitoreP4,VincitoreP5,VincitoreP6;
	@FXML
	private Label[] LabelVincitoriSingole;
	@FXML
	private Label G1,G2,G3,G4;
	@FXML
	private Label[] LabelNomiTuttiGiocatori;
	@FXML
	private Label PunteggioVittoriaG1,PunteggioVittoriaG2,PunteggioVittoriaG3,PunteggioVittoriaG4;
	@FXML
	private  Label[] LabelPunteggiVittorie;
	//METODI SET E GET
	public void setMain(Main main) {
    	this.main= main;
	}
	public void setStage(Stage stage) {
		this.stage =stage;
		
	}
	public void setVittoriaSuTavolo() {
		this.vittoriaSuTavoloG1= GameController.getVittoriaSuTavoloG1();
		this.vittoriaSuTavoloG2= GameController.getVittoriaSuTavoloG2();
	}
	public void setPartite() {
		
		this.partite = PreTorneo4Controller.getPartite();
    	
	}
	public void setNumeroPartita() {
		
		this.numeroPartita = GameController.getNumeroPartita();
		
	
	}
	public void setNumeroVittorieG() {
		this.numeroVittorieG= WinningController.getNumeroVittorieG();
	}
	private void setG() {
		this.G= PreTorneo4Controller.getG();
	}
	private void setVincitori() {
		this.vincitori= WinningController.ritornaVincitori();
	}
	public void setPlayerName() {
		String partita = partite.get(numeroPartita-1);
		String[] giocatori = partita.split(" vs ");

		this.player1Name = giocatori[0];
		this.player2Name= giocatori[1];
	    	
	      
	}
	//Prima di dichiarare vincitore calcolare punteggio ultima partita
	public void calcoloPunteggioUltimaPartita() {
		if(vittoriaSuTavoloG1>vittoriaSuTavoloG2) {
			for(int i=0;i<G.length;i++) {
				if(this.player1Name.equals(G[i])) {
					numeroVittorieG[i]+=3;
					vincitori.add(player1Name);
				}
			}
		}
		if(vittoriaSuTavoloG1<vittoriaSuTavoloG2) {
			for(int i=0;i<G.length;i++) {
				if(this.player2Name.equals(G[i])) {
					numeroVittorieG[i]+=3;
					vincitori.add(player2Name);
				}
			}
		}
		if(vittoriaSuTavoloG1==vittoriaSuTavoloG2) {
			vincitori.add("Pareggio");
			for(int i=0;i<G.length;i++) {
				if(this.player1Name.equals(G[i])) {
					numeroVittorieG[i]++;
				}
				if(this.player2Name.equals(G[i])) {
					numeroVittorieG[i]++;
				}
			}
		}
	}
	public void insertMusic() {
    	//MUSICA
        	try {
    			 Media sound = new Media(new File("src/Musica/WinningMusic.mpeg").toURI().toString());
    			   player = new MediaPlayer(sound);
    			   //continua sempre a suonare
    			   player.setCycleCount(MediaPlayer.INDEFINITE);
    			   player.play();
    		}catch(Exception e ) {
    			System.out.println("errore riproduzione");
    		}
    }
	public  void  initialize() {
		insertMusic();
		LabelNomiTuttiGiocatori= new Label[] {G1,G2,G3,G4};
		InsiemeGiocatoriPartite = new Label[] {G1P1,G2P1,G1P2,G2P2,G1P3,G2P3,G1P4,G2P4,G1P5,G2P5,G1P6,G2P6};
		LabelVincitoriSingole  =  new Label[] {VincitoreP1, VincitoreP2, VincitoreP3,VincitoreP4,VincitoreP5,VincitoreP6};
		LabelPunteggiVittorie = new Label[] {PunteggioVittoriaG1,PunteggioVittoriaG2,PunteggioVittoriaG3,PunteggioVittoriaG4};
		LabelPareggianti =  new  Label[] {Pareggiante1,Pareggiante2,  Pareggiante3,  Pareggiante4};
		
		setVittoriaSuTavolo(); 
		setPartite();
		setNumeroPartita();
		setPlayerName();
		setVincitori();
		setG();
		setNumeroVittorieG();
		calcoloPunteggioUltimaPartita();
		VincitoriPari = new ArrayList<>();
		
		boolean pareggio=false;
		int max= numeroVittorieG[0];
		
		for(int i=1;i<numeroVittorieG.length;i++) {
			if(numeroVittorieG[i]>max) {
				max=numeroVittorieG[i];
			}else if(numeroVittorieG[i]== max) {
				pareggio=true;
			}
		}
		if(pareggio==true) {
			LabelVincitorFinale.setText("VINCITORI A PARIMERITO");
			for(int i=0;i<numeroVittorieG.length;i++) {
				if(numeroVittorieG[i]==max) {
					VincitoriPari.add(G[i].toUpperCase());
				}
			}
			for(int  i=0;i<VincitoriPari.size();i++) {
				LabelPareggianti[i].setText(VincitoriPari.get(i));
			}
		}
		if(pareggio==false) {
			LabelVincitorFinale.setText(" VINCITORE :");
			for(int i=0;i<numeroVittorieG.length;i++) {
				if(max==numeroVittorieG[i]) {
					vincitore=G[i].toUpperCase();
				}
			}
			Pareggiante2.setText(vincitore);
		}
			
	    for(int i=0;i<partite.size();i++) {
			String  partita=  partite.get(i);
			String[] giocatori = partita.split(" vs ");
			InsiemeGiocatoriPartite[i*2].setText(giocatori[0]);
			InsiemeGiocatoriPartite[i*2+1].setText(giocatori[1]);
		}
		
		for(int  i=0;i<vincitori.size();i++) {
			LabelVincitoriSingole[i].setText(vincitori.get(i));
		}
		
		visualizzaClassifica(G,numeroVittorieG,LabelPunteggiVittorie,LabelNomiTuttiGiocatori);
	}
	 public void visualizzaClassifica(String[] G, int[] numeroVittorieG, Label[] LabelPunteggiVittorie, Label[] LabelNomiTuttiGiocatori) {
	        // mappa per associare punteggi a lista di nomi
	        Map<Integer, List<String>> punteggioToNomi = new HashMap<>();

	        // Associazione dei nomi ai punteggi
	        for (int j = 0; j < G.length; j++) {
	            int punteggio = numeroVittorieG[j];
	            String nomeGiocatore = G[j];

	            // se il punteggio è già presente nella mappa
	            if (punteggioToNomi.containsKey(punteggio)) {
	                // Se presente allora nome del giocatore aggiunto alla lista dei nomi
	                punteggioToNomi.get(punteggio).add(nomeGiocatore);
	            } else {
	                // Se non presente nuova lista con il nome del giocatore
	                List<String> nomiGiocatori = new ArrayList<>();
	                nomiGiocatori.add(nomeGiocatore);
	                punteggioToNomi.put(punteggio, nomiGiocatori);
	            }
	        }

	        //punteggi in ordine decrescente
	        int[] vittorieOrdinate = Arrays.copyOf(numeroVittorieG, numeroVittorieG.length);
	        Arrays.sort(vittorieOrdinate);

	        // Visualizzazione dei punteggi e dei nomi nella classifica
	        int posizione = 0;
	        for (int i = vittorieOrdinate.length - 1; i >= 0; i--) {
	            int punteggio = vittorieOrdinate[i];

	            //nomi dei giocatori associati a questo punteggio
	            List<String> nomiGiocatori = punteggioToNomi.get(punteggio);
	            if (nomiGiocatori != null && !nomiGiocatori.isEmpty()) {
	                int numNomi = nomiGiocatori.size();
	                for (int j = 0; j < numNomi; j++) {
	                    String nome = nomiGiocatori.get(j);
	                    if (posizione < LabelPunteggiVittorie.length && posizione < LabelNomiTuttiGiocatori.length) {
	                      
	                        LabelPunteggiVittorie[posizione].setText(punteggio + " ");
	                  
	                        LabelNomiTuttiGiocatori[posizione].setText(nome);
	                        posizione++;
	                    } else {
	                        break; // Esci se assegnato tutti i punteggi e nomi disponibili alle label
	                    }
	                }
	            }
	        }
	 }
	public void handleBottoneUscita(ActionEvent event) {
    	stage.close();
    }
}
	
	
	
	
	
	
	
	
	

