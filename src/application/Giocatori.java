package application;

import java.io.Serializable;

public class Giocatori implements Serializable{
	
	//Per serializzazione
	private static final long serialVersionUID = -4168603108386424350L;
	protected String Nome;
	protected int Vittorie;
	
	
	public Giocatori(String Nome) {
		this.Nome= Nome;
	}
	
	public String toString() {
		return Nome+"/"+Vittorie;
	}
	
	public String getNome() {
		return Nome;
	}

}
