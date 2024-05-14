package application;

import java.io.Serializable;

public class Giocatori implements Serializable{
	
	//Per serializzazione
	private static final long serialVersionUID = -4168603108386424350L;
	protected String Nome;
	protected int Vittorie = 0;
	
	
	public Giocatori(String Nome) {
		this.Nome= Nome;
	}
	
	public void setVittorie() {
		this.Vittorie += 1;
	}
	
	public int getVittorie() {
		return this.Vittorie;
	}
	
	public String toString() {
		return Nome+"/"+Vittorie;
	}
	
	public String getNome() {
		return Nome;
	}

}
