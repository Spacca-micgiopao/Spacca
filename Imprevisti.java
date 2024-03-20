package application;

public class Imprevisti {

    private TipoImprevisti tipo;

    public Imprevisti(TipoImprevisti tipo) {
        this.tipo = tipo;
    }

    public TipoImprevisti getTipo() {
        return tipo;
    }

    // Implementa l'effetto dell'imprevisto in base al tipo
    public void applicaEffetto(Gioco gioco) {
        switch (tipo) {
            case RADDOppia_VERDI:
                for (Carta carta : gioco.getGiocatore().getMano()) {
                    if (carta.getColore().equals("Verde")) {
                        carta.setValore(carta.getValore() * 2);
                    }
                }
                break;
            case DIMEZZA_ROSSE:
                for (Carta carta : gioco.getGiocatore().getMano()) {
                    if (carta.getColore().equals("Rosso")) {
                        carta.setValore(carta.getValore() / 2);
                    }
                }
                break;
            case AUMENTA_DISPARI:
                for (Carta carta : gioco.getGiocatore().getMano()) {
                    if (carta.getValore() % 2 == 1) {
                        carta.setValore(carta.getValore() + 2);
                    }
                }
                break;
            default:
                // Ignora l'imprevisto
                break;
        }
    }

    public String toString() {
        switch (tipo) {
            case RADDOppia_VERDI:
                return "Raddoppia il valore delle carte verdi";
            case DIMEZZA_ROSSE:
                return "Dimezza il valore delle carte rosse";
            case AUMENTA_DISPARI:
                return "Aumenta di due il valore delle carte dispari";
            default:
                return "Imprevisto sconosciuto";
        }
    }
    public enum TipoImprevisti {
        RADDOppia_VERDI,
        DIMEZZA_ROSSE,
        AUMENTA_DISPARI
    }


}

