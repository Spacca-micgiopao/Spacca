package application;

public class Giocatori {
	
	protected String Nome;
	protected int Vittorie;
	
	
	public Giocatori(String Nome) {
		this.Nome= Nome;
	}
	
	public String toString() {
		return Nome;
	}
	
	public String getNome() {
		return Nome;
	}
	
	
	

}
