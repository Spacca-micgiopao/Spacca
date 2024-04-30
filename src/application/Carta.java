package application;

import java.io.Serializable;
import java.util.Objects;

import javafx.scene.image.Image;

public class Carta implements Serializable{
	private int valore;
    private String colore;
    private Image immagine;

    public Carta(int valore, String colore,Image immagine) {
        this.valore = valore;
        this.colore = colore;
        this.immagine = immagine;
       
    }
    
    public Image getImmagine() {
        return immagine;
    }
    
    public int getValore() {
        return valore;
    }

    public String getColore() {
        return colore;
    }
   public void setValore(int valore) {
		this.valore=valore;
	}
    @Override
    public boolean equals(Object o) {
        // Verifica se l'oggetto passato è lo stesso oggetto di questa istanza
        if (this == o) return true;
        
        // Verifica se l'oggetto passato è null o non è della stessa classe di questa istanza
        if (o == null || getClass() != o.getClass()) return false;
        
        // Esegue il casting dell'oggetto passato a tipo Carta
        Carta carta = (Carta) o;
        
        // Confronta i valori, il colore e l'immagine delle due carte
        return valore == carta.valore &&
                Objects.equals(colore, carta.colore) &&
                Objects.equals(immagine, carta.immagine);
    }
    
    public String toString() {
        return "Valore: " + valore + ", Colore: " + colore;
    }

}

