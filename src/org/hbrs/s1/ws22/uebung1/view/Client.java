package org.hbrs.s1.ws22.uebung1.view;

import org.hbrs.s1.ws22.uebung1.control.Factory;
import org.hbrs.s1.ws22.uebung1.control.Translator;

public class Client {
	private Translator translator;

	/*
	 * Referenz kann auch über den Client Konstruktur gegeben werden
	 * Vorteil: Konstruktor wird nur einmal aufgerufen
	 * die setter Methode kann mehrmals aufgerufen werden
	 * daher muss man sich überlegen was man machen möchte
	 */
	public Client(Translator translator){
		this.translator = translator;
	}


	/*
	 * Methode zur Ausgabe einer Zahl auf der Console (auch bezeichnet als CLI, Terminal)
	 */
	public void display( int aNumber ){
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung gegen das Interface Translator gewuenscht!
			/*
			 * das heißt die Methoden aus dem Interface Translator genommen werden
			 * heißt nicht "German Translator" sondern "Translator"
			 */

			//Das hier in der Factory Methode
//return "Übersetzung der Zahl " + number + " nicht möglich " + version
		try{
			translator = Factory.generateTranslator();
			String result = translator.translateNumber(aNumber);
			System.out.print("Das Ergebnis der Berechnung: " + result  );
		} catch (Exception e){
			System.out.print("Übersetzung der Zahl " + aNumber + " nicht möglich " + Translator.version);
		}

	}

	/*
	 * Objekt-Referenz wird von außen gesetzt
	 * dann brauche ich natürlich keine Factory mehr
	 */
	public void setTranslator(Translator translator){
		this.translator = translator;
	}

}





