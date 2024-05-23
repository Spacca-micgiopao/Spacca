package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.image.ImageView;

public class botLogic {
	private int posizioneTavolo;
	
	public Carta giocaCarta(Mazzo mazzo, ArrayList<ImageView> tavolo,List<ImageView> imageViewsGiocatore2) {
	    Random random = new Random();
	    int index = random.nextInt(mazzo.getCarte().size());
	    Carta cartaSelezionata = mazzo.getCarta(index);

	    // Controlla se il tavolo ha spazio prima di giocare 
	    for (ImageView imageView : tavolo) {
	        if (imageView.getImage() == null) {
	        	posizioneTavolo=tavolo.indexOf(imageView);
	            // Imposta l'immagine dell'ImageView con quella della carta selezionata
	            imageView.setImage(cartaSelezionata.getImmagine());
	       
	            for(int i=0;i < imageViewsGiocatore2.size();i++ ) {
	            	if(imageViewsGiocatore2.get(i).getImage()==cartaSelezionata.getImmagine()) {
	            		imageViewsGiocatore2.get(i).setImage(null);
	            		break;
	            	}
	            }
	            // Rimuovi la carta dal mazzo
	            mazzo.rimuoviCarta(cartaSelezionata);
	            
	            // Restituisci la carta giocata
	            return cartaSelezionata;
	        }
	    }
	    
	    // Se non è possibile giocare la carta
	    return null;
	}
	public  int getPosizioneTavolo() {
		return posizioneTavolo;
	}
	
}