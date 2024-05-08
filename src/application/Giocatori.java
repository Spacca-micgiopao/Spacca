package application;

import java.io.Serializable;

public class Giocatori implements Serializable{
	
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
