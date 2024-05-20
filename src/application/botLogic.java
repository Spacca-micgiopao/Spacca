package application;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.ImageView;

public class botLogic {
	public boolean giocaCarta(Mazzo mazzo, ArrayList<ImageView> tavolo) {
        Random random = new Random();
        int index = random.nextInt(mazzo.getCarte().size());
        Carta cartaSelezionata = mazzo.getCarta(index);

        // Controlla se il tavolo ha spazio prima di giocare 
        for (ImageView imageView : tavolo) {
            if (imageView.getImage() == null) {
                imageView.setImage(cartaSelezionata.getImmagine());
                mazzo.rimuoviCarta(cartaSelezionata);
                
                return true;
            }
        
      
    } 
      return false;
}
}