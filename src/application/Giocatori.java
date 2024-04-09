package application;

public class Giocatori {
	
	protected String Nome;
	protected int Vittorie;
	
	public Giocatori(String Nome) {
		this.Nome= Nome;
		this.Vittorie = 0;
	}
	
	public String toString() {
		return Nome+"/"+Vittorie;
	}
	
	public String getNome() {
		return Nome;
	}
	
	
	

}
