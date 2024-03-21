package application;

import java.util.ArrayList;
import java.util.Random;

public class GeneratoreImprevisti {

    private ArrayList<Imprevisti> listaImprevisti;

    public GeneratoreImprevisti() {
        listaImprevisti = new ArrayList<>();
    }

    // Metodo per riempire l'ArrayList con tutti e tre i tipi di imprevisti
    public void riempi() {
        listaImprevisti.clear(); // Cancella eventuali imprevisti precedenti
        listaImprevisti.add(new Imprevisti(TipoImprevisti.RADDOppia_VERDI));
        listaImprevisti.add(new Imprevisti(TipoImprevisti.DIMEZZA_ROSSE));
        listaImprevisti.add(new Imprevisti(TipoImprevisti.AUMENTA_DISPARI));
    }

    public ArrayList<Imprevisti> getListaImprevisti() {
        return listaImprevisti;
    }

    public void setListaImprevisti(ArrayList<Imprevisti> listaImprevisti) {
        this.listaImprevisti = listaImprevisti;
    }
}

//l'idea è quella di avere una classe che generi tutti gli imprevisti possibili, e li metta in un arraylist ordinato. 
//a quel punto, quando il gioco si avvia, in maniera random prende un numero da 1 a 10 e sceglie quinid uno degli imprevisti dell'arraylist