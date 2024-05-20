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
    public int gestisciEffetto(String tipoImprevisto, Carta cartaSelezionata) {
    	
  
   	 int valorecarta;

       switch (tipoImprevisto) {
           case "raddoppia_verdi":
           case "raddoppia_rosa":
           case "raddoppia_blu":
           case "raddoppia_gialle":
           case "raddoppia_rosse":
               if (cartaSelezionata.getColore().equals("verde") || cartaSelezionata.getColore().equals("rosa") ||
                   cartaSelezionata.getColore().equals("blu") || cartaSelezionata.getColore().equals("giallo") ||
                   cartaSelezionata.getColore().equals("rosso")) {
                   cartaSelezionata.setValore(cartaSelezionata.getValore() * 2);
                   System.out.println("valore aggiornato raddoppio");
               }
               break;
           case "dimezza_rosse":
           case "dimezza_blu":
           case "dimezza_rosa":
           case "dimezza_verdi":
           case "dimezza_gialle":
               if (cartaSelezionata.getColore().equals("rosso") || cartaSelezionata.getColore().equals("blu") ||
                   cartaSelezionata.getColore().equals("rosa") || cartaSelezionata.getColore().equals("verde") ||
                   cartaSelezionata.getColore().equals("giallo")) {
                   cartaSelezionata.setValore(cartaSelezionata.getValore() / 2);
                   System.out.println("valore aggiornato dimezzo");
               }
               break;
           case "dispari_piu_2":
               if (cartaSelezionata.getValore() % 2 == 1) {
                   cartaSelezionata.setValore(cartaSelezionata.getValore() + 2);
               }
               break;
           default:
               // Nessun effetto da gestire per questo tipo di imprevisto
               break;
               
       }
   	 valorecarta= cartaSelezionata.getValore();
       return valorecarta;
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
    public void carteimprevisto(Carta cartaSelezionata ){
    	 if (cartaSelezionata.getColore().equals("random")) {
    	        Random rand = new Random();
    	        int valore = rand.nextBoolean() ? 0 : 8;
    	        cartaSelezionata.setValore(valore);
    	        System.out.println(valore);
    	    }
    
 	
    	
    }
 

    public Image caricaImmagineImprevisto() throws IOException {
        String percorsoImprevisto = "src\\Imprevisti\\" + nomeImprevisto;
        File fileImprevisto = new File(percorsoImprevisto);
        return new Image(new FileInputStream(fileImprevisto));
    }

}

