package application;

import java.io.*;

import javafx.scene.control.ListView;

public class Salvataggi implements Serializable{
	
	protected GameController gamecontroller;
	File file = new File("partita2.ser");

	public Salvataggi() {
	}
	
	//Salva in un file serializzato i dati importanti di una partita,in questo caso salva
	//lo stato di alcune parti del gamecontroller
	public void salvaPartita() {
	    try (FileOutputStream fileout = new FileOutputStream(file);
	         ObjectOutputStream out = new ObjectOutputStream(fileout)) {
	    	out.reset();
	    	//Punteggi dei tavoli
	        out.writeInt(gamecontroller.punteggioG1Tavolo1);
	        out.writeInt(gamecontroller.punteggioG1Tavolo2);
	        //Nomi dei partecipanti
	        out.writeObject(gamecontroller.player1Name);
	        out.writeObject(gamecontroller.player2Name);
	        //Carte di ogni giocatore
	        out.writeObject(gamecontroller.listaCarteGiocatore1);
	        out.writeObject(gamecontroller.listaCarteGiocatore2);
	        out.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public void caricaPartita(GameController gc) {
		try {
			FileInputStream filein = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(filein);
			gc.punteggioG1Tavolo1 = in.readInt();
			gc.punteggioG1Tavolo2 = in.readInt();
			gc.player1Name = (String) in.readObject();
			gc.player2Name = (String) in.readObject();
			gc.listaCarteGiocatore1 = (ListView<Carta>) in.readObject();
			gc.listaCarteGiocatore2 = (ListView<Carta>) in.readObject();
			in.close();
			filein.close();
		} catch (IOException  | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}