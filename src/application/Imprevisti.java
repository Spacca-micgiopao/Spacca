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
             "Scambia punti tavolo 1",
             "Scambia punti tavolo 2",
             "Scambia punti tavolo 3",
       };
	private String Imprevistoscelto;
	private GameController gc;
	
	public Imprevisti(GameController gc) {
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
            case "Raddoppia punti carte Verdi":
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
            case "Dimezza punti carte Verdi":
                if (coloreCarta.equals("verde")) {
                    valoreCarta /= 2;
                }
                break;
            case "Dimezza punti carte gialle":
                if (coloreCarta.equals("giallo")) {
                    valoreCarta /= 2;
                }
                break;
            case "Scambia punti tavolo 1":
            	int s = gc.punteggioG1Tavolo1;
            	gc.punteggioG1Tavolo1 = gc.punteggioG2Tavolo1;
            	gc.punteggioG2Tavolo1 = s;
            	gc.LabelPunteggioG1T1.setText(gc.punteggioG1Tavolo1+"");
            	gc.LabelPunteggioG2T1.setText(gc.punteggioG2Tavolo1+"");
            	break;
            case "Scambia punti tavolo 2":
            	int c = gc.punteggioG1Tavolo2;
            	gc.punteggioG1Tavolo2 = gc.punteggioG2Tavolo2;
            	gc.punteggioG2Tavolo2 = c;
            	gc.LabelPunteggioG1T2.setText(gc.punteggioG1Tavolo2+"");
            	gc.LabelPunteggioG2T2.setText(gc.punteggioG2Tavolo2+"");
            	break;
            case "Scambia punti tavolo 3":
            	int k = gc.punteggioG1Tavolo3;
            	gc.punteggioG1Tavolo3 = gc.punteggioG2Tavolo3;
            	gc.punteggioG2Tavolo3 = k;
            	gc.LabelPunteggioG1T3.setText(gc.punteggioG1Tavolo3+"");
            	gc.LabelPunteggioG2T3.setText(gc.punteggioG2Tavolo3+"");
            	break;
            default:
                // Nessun effetto
                break;
        }

        carta.setValore(valoreCarta);
    }
    public void carteimprevisto(Carta cartaSelezionata) {
        
        if (cartaSelezionata.getColore().equals("random")) {
            Random rand = new Random();
            int randomnum = rand.nextInt(2);  // Per ottenere 0 o 1
            int valore = (randomnum == 0) ? 0 : 8;
            cartaSelezionata.setValore(valore);
            System.out.println("valore rand = " + cartaSelezionata.getValore());
        }
    }

}
