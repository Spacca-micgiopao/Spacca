package application;
import java.util.Random;
public class Imprevisti {
	   
	 //Lista degli imprevisti
	 private String[] Lsimprevisti = {
             "Dimezza punti carte blu",
             "Dimezza punti carte gialle",
             "Dimezza punti carte rosa", 
             "Dimezza punti carte rosse", 
             "Dimezza punti carte Verdi",
             "Carte Dispari +2 punti", 
             "Raddoppia punti carte blu", 
             "Raddoppia punti carte gialle", 
             "Raddoppia punti carte rosa", 
             "Raddoppia punti carte rosse",
             "Raddoppia punti carte Verdi",
             "Scambia Punti tavolo 1",
             //"Tira i dadi!",
       };
	private String Imprevistoscelto;
	private GameController gc;
	
	public Imprevisti(GameController gc) {
       this.Imprevistoscelto = Imprevistoscelto;
       this.gc = gc;
	}
	
	public String scelto() {
		return this.Imprevistoscelto;
	}
    public void caricaImprevistoCasuale() {
        Random random = new Random();
        this.Imprevistoscelto = Lsimprevisti[random.nextInt(Lsimprevisti.length)];
    	}
    
    public void applicaEffettoCarta(Carta carta) {
    	int valoreCarta = carta.getValore();
        String coloreCarta = carta.getColore();

        switch (Imprevistoscelto) {
            case "Raddoppia punti carte verdi":
                if (coloreCarta.equals("verde")) {
                    valoreCarta *= 2;
                }
                break;
            case "Dimezza punti carte rosse":
                if (coloreCarta.equals("rosso")) {
                    valoreCarta /= 2;
                }
                break;
            case "Carte Dispari +2 punti":
                if (valoreCarta % 2 == 1) {
                    valoreCarta += 2;
                }
                break;
            case "Raddoppia punti carte rosa":
                if (coloreCarta.equals("rosa")) {
                    valoreCarta *= 2;
                }
                break;
            case "Raddoppia punti carte blu":
                if (coloreCarta.equals("blu")) {
                    valoreCarta *= 2;
                }
                break;
            case "Raddoppia punti carte gialle":
                if (coloreCarta.equals("giallo")) {
                    valoreCarta *= 2;
                }
                break;
            case "Dimezza punti carte blu":
                if (coloreCarta.equals("blu")) {
                    valoreCarta /= 2;
                }
                break;
            case "Dimezza punti carte rosa":
                if (coloreCarta.equals("rosa")) {
                    valoreCarta /= 2;
                }
                break;
            case "Dimezza punti carte verdi":
                if (coloreCarta.equals("verde")) {
                    valoreCarta /= 2;
                }
                break;
            case "Dimezza punti carte gialle":
                if (coloreCarta.equals("giallo")) {
                    valoreCarta /= 2;
                }
                break;
            case "Scambia Punti tavolo 1":
            	int s = gc.punteggioG1Tavolo1;
            	gc.punteggioG1Tavolo1 = gc.punteggioG2Tavolo1;
            	gc.punteggioG2Tavolo1 = s;
            	gc.LabelPunteggioG1T1.setText(gc.punteggioG1Tavolo1+"");
            	gc.LabelPunteggioG2T1.setText(gc.punteggioG2Tavolo1+"");
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

}