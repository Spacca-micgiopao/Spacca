package application;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.ImageView;

public class botLogic {
	public Carta giocaCarta(Mazzo mazzo, ArrayList<ImageView> tavolo) {
	    Random random = new Random();
	    int index = random.nextInt(mazzo.getCarte().size());
	    Carta cartaSelezionata = mazzo.getCarta(index);

	    // Controlla se il tavolo ha spazio prima di giocare 
	    for (ImageView imageView : tavolo) {
	        if (imageView.getImage() == null) {
	            // Imposta l'immagine dell'ImageView con quella della carta selezionata
	            imageView.setImage(cartaSelezionata.getImmagine());
	            
	            // Assegna la carta selezionata all'attributo userData dell'ImageView
	            imageView.setUserData(cartaSelezionata);
	            
	            // Rimuovi la carta dal mazzo
	            mazzo.rimuoviCarta(cartaSelezionata);
	            
	            // Restituisci la carta giocata
	            return cartaSelezionata;
	        }
	    }
	    
	    // Se non è possibile giocare la carta, restituisci null
	    return null;
	}


}