package application;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.io.File;
import javafx.scene.image.*;
import java.io.FileInputStream;



public class Mazzo {
	private List<Carta> carte;

	public Mazzo() {
        carte = new ArrayList<>();
        mescolaMazzo();
    }
	
	public void aggiungiCarta(Carta carta) {
		carte.add(carta);
	}
	
	public void rimuoviCarta(Carta carta) {
		carte.remove(carta);
	}
	public boolean contieneCarta(Carta carta) {
		return carte.contains(carta);
	}
	//la creazione del mazzo completo di tutte le carte avviene attraverso l'analisi delle immagini
	//ognuna delle quali è associata ad una carta specifica("colore_valore.jpg")
	public void CreaMazzoCompleto() {
		
        //percorso della cartella "Immagini" 
        String percorsoCartella = "src\\Immagini";

        // Creazione un oggetto File per la cartella
        File cartella = new File(percorsoCartella);

        // Verificare che la cartella esista
        if (cartella.exists() && cartella.isDirectory()) {
            // Ottenere l'elenco dei file nella cartella
            File[] files = cartella.listFiles();

            // Verificare che l'elenco dei file non sia nullo
            if (files != null) {
                // Iterare attraverso i file
            	//File immagine denominati in  questo modo: "colore_valore.jpg"
                for (File file : files) {
                    try {
                        // Ottenere il nome del file
                        String nomeFile = file.getName();

                        // Verificare se il file è un'immagine jpg
                        if (nomeFile.toLowerCase().endsWith(".jpg")){
                            // Ottenere colore e valore dal nome del file
                            String[] partiNome = nomeFile.split("_");
                            if (partiNome.length == 2) {
                                String colore = partiNome[0];
                                int valore = Integer.parseInt(partiNome[1].replace(".jpg", ""));

                             // Creare l'oggetto Image utilizzando FileInputStream
                                FileInputStream fileInputStream = new FileInputStream(file);
                                Image immagine = new Image(fileInputStream);

                                // Creare e aggiungere la Carta al mazzo
                                aggiungiCarta(new Carta(valore, colore, immagine));
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("errore");
                    }
                }
           }
            
        }else {
            	System.out.println("cartella non esiste");
            }
         // Mescolare il mazzo alla fine del caricamento
        //System.out.println(" dimensione mazzo completo è"+ mazzoCompleto.getCarte().size());
        mescolaMazzo();
     }
	//pesca una carta casualmente, la carta pescata viene tolta dal mazzo
	public Carta pescaCartaCasuale() {
	    if (!carte.isEmpty()) {
	        Random rand = new Random();
	        int index = rand.nextInt(carte.size());
	        Carta cartaCasuale = carte.get(index);
	        carte.remove(cartaCasuale);
	        return cartaCasuale ;
	    } else {
	        System.out.println("Il mazzo è vuoto.");
	        return null;
	    }
	}
    //ritorna oggetto carta con indice specifico
    public Carta getCarta(int i) {
    	Carta c =carte.get(i);
    	return c;
    }
    
    public void mescolaMazzo() {
        Collections.shuffle(carte);
    }
    
    public int getNumeroCarte() {
        return carte.size();
    }
    public List<Carta> getCarte() {
        return carte;
    }
}

