package application;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import javafx.scene.image.Image;
public class Imprevisti {
	 private String tipoImprevisto;
    private String nomeImprevisto;

    public void caricaImprevistoCasuale() {
        String[] imprevisti = {
            "dimezza_blu",
            "dimezza_gialle",
            "dimezza_rosa", 
            "dimezza_rosse", 
            "dimezza_verdi",
            "dispari_piu_2", 
            "raddoppia_blu", 
            "raddoppia_gialle", 
            "raddoppia_rosa", 
            "raddoppia_rosse",
            "raddoppia_verdi"  
        };
        Random random = new Random();
        tipoImprevisto = imprevisti[random.nextInt(imprevisti.length)];
        nomeImprevisto = tipoImprevisto + ".jpg"; // Immagine associata all'imprevisto
        try {
			caricaImmagineImprevisto();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void applicaEffettoCarta(Carta carta) {
    	int valoreCarta = carta.getValore();
        String coloreCarta = carta.getColore();

        switch (tipoImprevisto) {
            case "raddoppia_verdi":
                if (coloreCarta.equals("verde")) {
                    valoreCarta *= 2;
                }
                break;
            case "dimezza_rosse":
                if (coloreCarta.equals("rosso")) {
                    valoreCarta /= 2;
                }
                break;
            case "dispari_piu_2":
                if (valoreCarta % 2 == 1) {
                    valoreCarta += 2;
                }
                break;
            case "raddoppia_rosa":
                if (coloreCarta.equals("rosa")) {
                    valoreCarta *= 2;
                }
                break;
            case "raddoppia_blu":
                if (coloreCarta.equals("blu")) {
                    valoreCarta *= 2;
                }
                break;
            case "raddoppia_gialle":
                if (coloreCarta.equals("giallo")) {
                    valoreCarta *= 2;
                }
                break;
            case "dimezza_blu":
                if (coloreCarta.equals("blu")) {
                    valoreCarta /= 2;
                }
                break;
            case "dimezza_rosa":
                if (coloreCarta.equals("rosa")) {
                    valoreCarta /= 2;
                }
                break;
            case "dimezza_verdi":
                if (coloreCarta.equals("verde")) {
                    valoreCarta /= 2;
                }
                break;
            case "dimezza_gialle":
                if (coloreCarta.equals("giallo")) {
                    valoreCarta /= 2;
                }
                break;
            default:
                // Nessun effetto
                break;
        }

        carta.setValore(valoreCarta);
    }
    public void carteimprevisto(Carta cartaSelezionata, boolean annullatore){
    	if(cartaSelezionata.getColore().equals("annulla")) {
        	annullatore = true;
        } else {
        	annullatore = false;
        }
        if(cartaSelezionata.getColore().equals("random")) {
        	Random rand = new Random();
        	int randomnum = rand.nextInt();       
            int valore = (randomnum == 0) ? 0 : 8;
            cartaSelezionata.setValore(valore);
        }
    	
    }

    public Image caricaImmagineImprevisto() throws IOException {
        String percorsoImprevisto = "src/Imprevisti/" + nomeImprevisto;
        File fileImprevisto = new File(percorsoImprevisto);
        return new Image(new FileInputStream(fileImprevisto));
    }

}