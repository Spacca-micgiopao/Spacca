package application;

import java.util.HashMap;

public class Datiaccesso {

	//temporaneamente uso un hashmap mi sarebbe piaciuto usare un database in sql ma non escludo una sua implementazione
	HashMap<String,String> accesso = new HashMap<String,String>();
	
	//creo istanze di "accesso" in hashmap
	Datiaccesso(){
		accesso.put("Gio", "1234");
		accesso.put("Sgro", "4567");
	}
	
	//metodo per ottenere i dati di accesso devo aggiungere funzioni dell'hashmap
	protected HashMap getAcceso(){
		return accesso;
	}
	
	
}
