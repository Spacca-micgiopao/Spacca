package application;

import javafx.scene.image.Image;

public class Carta {
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
    
    public String toString() {
        return "Valore: " + valore + ", Colore: " + colore;
    }

}

