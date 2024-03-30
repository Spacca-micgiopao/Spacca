package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.LineNumberReader;

public class InfoPartita {
	
	String G1;
	String G2;
	int NumeroPartita;
	//Caricare il file con i dati delle partite
	File DatiPartita = new File("src/application/DatiPartita.txt");
	
	//Imposto lo scheletro per i vari writer e scanner che userò per gestire
	//La lettura e la scrittura dei dati
	private FileWriter FW = null;
	private BufferedWriter BW = null;
	private PrintWriter Writer = null;
	
	//Creazione dell'oggetto che contiene le informazioni della partita
	protected void InfoPartita(String G1,String G2) {
		this.G1 = G1;
		this.G2 = G2;
		this.NumeroPartita = 0;
	}
	
	//Passo i valori dei nomi dei giocatori,se presenti
	protected void getG(String IG1,String IG2) {
		G1 = IG1;
		G2 = IG2;
	}
	
	//Metodo per scrivere i dati sul file
	public void ScriviDati() {
		try {
			//Prima Parte del codice conta le righe nel file sapendo dunque quante righe ci sono
			//Posso sapere quante partite ci sono
			FileReader FR = new FileReader(DatiPartita);
			LineNumberReader LN = new LineNumberReader(FR);
				while(LN.readLine() !=null) {
					NumeroPartita++;
				}
			//Seconda parte per segnare il numero della partita attuale e i suoi giocatori
			FW = new FileWriter(DatiPartita,true);
			BW = new BufferedWriter(FW);
			Writer = new PrintWriter(BW);
			Writer.println(NumeroPartita+","+G1+","+G2);
			Writer.close();
			}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
	
	
	

	
	
	