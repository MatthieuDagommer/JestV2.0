package fr.utt.rt.lo02.projet.vue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import fr.utt.rt.lo02.projet.modele.Joueur;
import fr.utt.rt.lo02.projet.modele.Partie;
import fr.utt.rt.lo02.projet.modele.StrategieJoueur;

// TODO: Auto-generated Javadoc
/**
 * The Class VueConsole.
 */
public class VueConsole implements Observer, Runnable {

	/** The quitter. */
	public static String QUITTER = "Quit";

	/** The prompt. */
	public static String PROMPT = ">";

	/** The partie. */
	private Partie partie;

	/** The joueurs. */
	private ArrayList<Joueur> joueurs;

	/**
	 * Instantiates a new vue console.
	 *
	 * @param partie the partie
	 */
	public VueConsole(Partie partie) {
		this.partie = partie;
		Partie.getInstance().addObserver(this);
		joueurs = Partie.getInstance().getJoueurs();
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			j.addObserver(this);
			j.getStrategie().addObserver(this);
		}

		Thread t = new Thread(this);
		t.start();
	}

	/**
	 * Run.
	 */
	@Override
	public void run() {

		String saisie = null;
		boolean quitter = false;

		System.out.println("Taper " + VueConsole.QUITTER + " pour quitter.");

		do {
			saisie = this.lireChaine();

			if (saisie != null) {
				Joueur jChoisit = null;
				if (saisie.equals(QUITTER)) {
					quitter = true;
				} else {
					Iterator<Joueur> it = joueurs.iterator();
					while (it.hasNext()) {
						Joueur j = it.next();
						if (saisie.equals(j.getNom())) {
							jChoisit = j;
						}
					}
					if (Partie.getInstance().getOffreDispo(Partie.getInstance().getJoueurActuel()).contains(jChoisit)) {
						System.out.println("Quel carte ? 0 - caché / 1 - visible");
						do {
							saisie = this.lireChaine();
							if (Integer.parseInt(saisie) == 0) {
								Partie.getInstance().getJoueurActuel().getStrategie().choix(false, jChoisit);
							} else if (Integer.parseInt(saisie) == 1) {
								Partie.getInstance().getJoueurActuel().getStrategie().choix(true, jChoisit);
							} else {
								System.out.println("Saisie invalide !");
							}
						} while (Integer.parseInt(saisie) != 0 && Integer.parseInt(saisie) != 1);
					} else {
						System.out.println("Saisie invalide !");
					}
				}
			}

		} while (quitter == false);
		System.exit(0);
	}

	/**
	 * Lire chaine.
	 *
	 * @return the string
	 */
	private String lireChaine() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String resultat = null;
		try {
			System.out.print(VueConsole.PROMPT);
			resultat = br.readLine();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return resultat;
	}

	/**
	 * Update.
	 *
	 * @param o   the o
	 * @param arg the arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String message = arg.toString();
			System.out.println(message + "\n" + PROMPT);
		} else if(o instanceof Partie && arg instanceof Joueur ) {
			Joueur j = (Joueur) arg;
			System.out.println("Le joueur "+ j.getNom() +" doit jouer");
		}

	}

}
