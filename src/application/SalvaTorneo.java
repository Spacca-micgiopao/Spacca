package application;

import static application.PreTorneo4Controller.G;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class SalvaTorneo implements Serializable {

	private static final long serialVersionUID = 7423889382853862983L;
	static File dir = new File("src/SalvataggiTorneo");
	static File file = new File("src/SalvataggiTorneo/Torneo1.ser");
	protected static String[] Tornei;
	
	//Crea cartelle e carica la lista dei tornei,da usare allo start
	public static void Preparazione() throws IOException {
		if(!dir.exists())
			dir.mkdir();
		if(!file.exists())
			file.createNewFile();
		if(dir.list() != null)
		Tornei = dir.list();
	}
	
	//Per salvare il torneo quando si esce
	protected void Salvataggio(PreTorneo4Controller PTC) {
		try {
			//file = new File("src/SalvataggioTorneo"+PTC.c+".ser");
			FileOutputStream fileout = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			out.reset();
			//Parametri da salvare
			out.writeObject(PTC.partite);
			out.writeObject(G);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Per caricare un nuovo torneo
	protected static void CaricaTorneo(PreTorneo4Controller PTC) throws FileNotFoundException {
		try {
			FileInputStream filein = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(filein);
			PTC.partite = (List<String>) in.readObject();
			PTC.G = (String[])in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
