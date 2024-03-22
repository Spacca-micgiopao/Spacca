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
    public void applicaEffetto(Mazzo mazzo) {
        switch (tipo) {
            case RADDOppia_VERDI:
                for (Carta carta : mazzo.getCarte()) {
                    if (carta.getColore().equals("Verde")) {
                        carta.setValore(carta.getValore() * 2);
                    }
                }
                break;
            case DIMEZZA_ROSSE:
                for (Carta carta : mazzo.getCarte()) {
                    if (carta.getColore().equals("Rosso")) {
                        carta.setValore(carta.getValore() / 2);
                    }
                }
                break;
            case AUMENTA_DISPARI:
                for (Carta carta : mazzo.getCarte()) {
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

    @Override
    public boolean equals(Object o) {
        // Verifica se l'oggetto passato è lo stesso oggetto di questa istanza
        if (this == o) return true;

        // Verifica se l'oggetto passato è null o non è della stessa classe di questa istanza
        if (o == null || getClass() != o.getClass()) return false;

        // Esegue il casting dell'oggetto passato a tipo Carta
        Imprevisti imprevisto = (Imprevisti) o;

        // Confronta i tipi degli imprevisti
        return tipo == imprevisto.tipo;
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

   
    
}
