package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SalvaTorneo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7423889382853862983L;
	File dir = new File("src/SalvataggiTorneo");
	static File file = new File("");
	
	//Crea la cartella
	protected void Preparazione() {
		if(!dir.exists())
			dir.mkdir();
	}
	
	//Per salvare il torneo quando si esce
	protected static void Salvataggio(PreTorneo4Controller PTC) {
		try {
			file = new File("src/SalvataggioTorneo"+PTC.c+".ser");
			FileOutputStream fileout = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			out.reset();
			//Parametri da salvare
			out.writeObject(PTC.partite);
			out.writeObject(PTC.G);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
