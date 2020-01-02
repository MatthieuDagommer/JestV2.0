package fr.utt.rt.lo02.projet.vue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

import fr.utt.rt.lo02.projet.modele.Partie;


public class VueTexte implements Observer, Runnable{

	    private Partie partie;

	    public VueTexte(Partie partie) {
		this.partie = partie;

		Thread t = new Thread(this);
		t.start();
	    }

	    public void run() {


	
	    }
	    @Override
	    public void update(Observable arg0, Object arg1) {
		
	    }

	}
	
	
	
	
	
	

