package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.LineNumberReader;

public class InfoPartita {
	
	String G1;
	String G2;
	static int NumeroPartita = 0;
	protected static String[] Partite;
	protected String[] partite;
	//protected String[] partite = new String[Partite.size()];
	//Caricare il file con i dati delle partite
	File DatiPartita = new File("src/application/DatiPartita.txt");
	
	//Creazione dell'oggetto che contiene le informazioni della partita
	protected void InfoPartita(String G1,String G2) {
		this.G1 = G1;
		this.G2 = G2;
	}
	
	//Questo Metodo parte ogni volta che viene fatto il login,tiene conto del numero di partite
	//E salva in un array la lista di tutte le partite per farla scegliere all'admin
	protected void Preparazione() {
		try {
			Scanner Scan = new Scanner(DatiPartita);
			FileReader FR = new FileReader(DatiPartita);
			LineNumberReader LN = new LineNumberReader(FR);
				while(LN.readLine() !=null) {
					NumeroPartita++;
				}
				Partite = new String[NumeroPartita];
				for(int i =0;i<NumeroPartita;i++) 
					Partite[i]= Scan.nextLine();
				partite = Partite;
			}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//Passo i valori dei nomi dei giocatori,se presenti
	protected void getG(String IG1,String IG2) {
		G1 = IG1;
		G2 = IG2;
	}
	
	//Metodo per scrivere i dati sul file
	public void ScriviDati() {
		try {
			FileWriter FW = new FileWriter(DatiPartita,true);
			BufferedWriter BW = new BufferedWriter(FW);
			PrintWriter Writer = new PrintWriter(BW);
			Scanner Scan = new Scanner(DatiPartita);
			Writer.println(NumeroPartita+","+G1+","+G2);
			Writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
	
	
	

	
	
	