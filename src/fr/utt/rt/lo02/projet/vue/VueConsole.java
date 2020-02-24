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

/**
 * Cette classe permet de jouer en console en meme temps que de jouer avec
 * l'interface graphique
 * 
 * Elle implemente observer car elle observe le modele dans le cadre de MVC, et
 * Runnable car c'est un thread concurent à l'interface graphique
 */
@SuppressWarnings("deprecation")
public class VueConsole implements Observer, Runnable {

	/** Definit QUITTER */
	public static String QUITTER = "Quit";

	/** Definit PROMT */
	public static String PROMPT = ">";

	/** la partie a observer */
	private Partie partie;

	/** les joueurs de la partie */
	private ArrayList<Joueur> joueurs;

	/**
	 * Constructeur de la classe, il execute un thread.
	 *
	 * @param partie la partie en cours
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
	 * Methode run qui s'execute tant que quitter est faux.
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
	 * @return la chaine de caractere lu
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
	 * Methode update dans le cadre de MVC.
	 * 
	 * elle met a jour la console avec les logs et affiche le joueurs qui doit jouer
	 *
	 * @param o   the o
	 * @param arg the arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String message = arg.toString();
			System.out.println(message + "\n" + PROMPT);
		} else if (o instanceof Partie && arg instanceof Joueur) {
			Joueur j = (Joueur) arg;
			System.out.println("Le joueur " + j.getNom() + " doit jouer");
		}

	}

}
