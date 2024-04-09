package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.io.LineNumberReader;

public class InfoPartita {
	
	protected String G1;
	protected String G2;
	private String IDpartita;
	static int NumeroPartita = 0;
	protected static String[] Partite;
	//Caricare il file con i dati delle partite
	protected File DatiPartita = new File("src/Data/DatiPartita.txt");
	
	//Conta il numero delle partite salvate per poi elencarle in un array
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
			LN.close();
			Scan.close();
			}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//Passo i valori dei nomi dei giocatori e crea un codice partita
	protected void getG(String IG1,String IG2) {
		G1 = IG1;
		G2 = IG2;
		Random rand = new Random();
		char c = (char)(rand.nextInt(26)+'a');
		IDpartita = NumeroPartita+""+rand.nextInt(10)+""+c;
	}
	
	//Metodo per scrivere i dati sul file
	public void ScriviDati() {
		try {
			FileWriter FW = new FileWriter(DatiPartita,true);
			BufferedWriter BW = new BufferedWriter(FW);
			PrintWriter Writer = new PrintWriter(BW);
			Writer.println(IDpartita+","+G1+","+G2);
			Writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
	
	
	

	
	
	