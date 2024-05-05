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
	private int Numeropartita;
	protected static String[] Partite;
	//Caricare il file con i dati delle partite
	
	public static void Preparazione() {
		File dir = new File("src/Salvataggi/");
		if(dir.isDirectory() && dir.exists()) {
			Partite = dir.list();
			for(int i = 0;i<Partite.length;i++) {
				Partite[i] = Partite[i].replace(".ser", "");
			}
		}
	}
	
	
	
	//Passo i valori dei nomi dei giocatori e crea un codice partita
	protected void getG(String IG1,String IG2) {
		G1 = IG1;
		G2 = IG2;
		Random rand = new Random();
		char c = (char)(rand.nextInt(26)+'a');
		IDpartita = rand.nextInt(10)+""+c;
	}
	
	//Metodo per scrivere i dati sul file
	public void ScriviDati() {
		File dir = new File("src/Salvataggi/");
		for(int i = 0;i<dir.list().length;i++)
			Numeropartita++;
		String Nome = Numeropartita+""+IDpartita+","+G1+","+G2;
		Salvataggi.setFile(Nome);
	}
}
	
	
	

	
	
	