package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InfoPartita {
	
	String G1;
	String G2;
	int NumeroPartita;
	
	File DatiPartita = new File("src/application/DatiPartita.txt");
	
	protected void InfoPartita(String G1,String G2,int NumeroPartita) {
		this.G1 = G1;
		this.G2 = G2;
		this.NumeroPartita = NumeroPartita;
	}
	
	protected void getG(String IG1) {
		G1 = IG1;
	}
	
	public void ScriviDati() {
		try {
			FileWriter FW = new FileWriter(DatiPartita);
			FW.write(this.G1);
			FW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
