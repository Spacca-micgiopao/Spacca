package application;

import java.io.*;

public class Salvataggi implements Serializable{
	
	protected GameController gamecontroller;
	File file = new File("partita2.ser");

	public Salvataggi() {
	}
	
	public Salvataggi(GameController gamecontroller) {
		this.gamecontroller = gamecontroller;
	}
	
    public void salvaPartita() {
        try {
            FileOutputStream fileout = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileout);
            out.writeObject(gamecontroller);
            out.close();
            fileout.close();
            }
        catch (IOException e) {
            System.out.println("Errore durante il salvataggio della partita");
        }
    }
	
	public GameController caricaPartita() {
		this.gamecontroller = null;
		try {
			FileInputStream filein = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(filein);
			this.gamecontroller = (GameController) in.readObject();
			in.close();
			filein.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return this.gamecontroller;
		
	}
}