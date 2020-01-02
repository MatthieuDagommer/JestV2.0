package fr.utt.rt.lo02.projet.vue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

import fr.utt.rt.lo02.projet.modele.Partie;


// TODO: Auto-generated Javadoc
/**
 * The Class VueTexte.
 */
public class VueTexte implements Observer, Runnable{

	    /** The partie. */
    	private Partie partie;
	    
    	/** The prompt. */
    	public static String PROMPT = ">";


	    /**
    	 * Instantiates a new vue texte.
    	 *
    	 * @param partie the partie
    	 */
    	public VueTexte(Partie partie) {
		this.partie = partie;

		Thread t = new Thread(this);
		t.start();
	    }

	    /**
    	 * Run.
    	 */
    	public void run() {
	    	

	
	    }
	    
	    
	    /**
    	 * Lire chaine.
    	 *
    	 * @return the string
    	 */
    	private String lireChaine() {
	    	BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
	    	String resultat = null;
	    	try {
	    	    System.out.print(VueTexte.PROMPT);
	    	    resultat = br.readLine();
	    	} catch (IOException e) {
	    	    System.err.println(e.getMessage());
	    	}
	    	return resultat;	
	        }
	    
	    
	    
	    
	    /**
    	 * Update.
    	 *
    	 * @param arg0 the arg 0
    	 * @param arg1 the arg 1
    	 */
    	@Override
	    public void update(Observable arg0, Object arg1) {
		
	    }

	}
	
	
	
	
	
	

