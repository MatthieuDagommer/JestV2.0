package fr.utt.rt.lo02.projet.modele;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * The Class Deck.
 */
public class Deck {

	
	/** The tas de carte. */
	private LinkedList<Carte> tasDeCarte;
	
	/** The extension. */
	private int extension;
	
	/**
	 * Instantiates a new deck.
	 *
	 * @param extension the extension
	 */
	public Deck(int extension) {
		this.extension = extension;
		tasDeCarte = new LinkedList<Carte>();
		Trophee[] t = Trophee.values();
		int it = 0;

		for (Couleur c : Couleur.values()) { // pour toutes les couleurs
			if (extension == 1) {
				for (Valeur v : Valeur.values()) { // pour toutes les valeurs
					if (v != Valeur.JOKER) {
						if (c != Couleur.COEUR && v != Valeur.SIX) { // les coeurs ont le meme troph�e
							it++;
						}
						Carte carte = new Carte(v, c, t[it]); // création de la carte avec le trophée associé
						tasDeCarte.add(carte);
						// System.out.println(tasDeCarte);
					}
				}
			} else if (extension == 0) {
				for (int v = 0; v < 5; v++) { // pour toutes les valeurs
					if (v != 0) {
						if (c != Couleur.COEUR) { // les coeurs ont le meme troph�e
							it++;
						}
						Carte carte = new Carte(v, c, t[it]); // création de la carte avec le trophée associé
						tasDeCarte.add(carte);
					}
				}
			}
		}
		tasDeCarte.add(Joker.getInstance());
	}
	
	/**
	 * Est vide.
	 *
	 * @return true, if successful
	 */
	public boolean estVide() {
		return tasDeCarte.isEmpty();
	}

	/**
	 * Piocher carte.
	 *
	 * @return the carte
	 */
	public Carte piocherCarte() {
		return tasDeCarte.pop();
	}
	
	/**
	 * Melanger.
	 */
	public void melanger() {
		Collections.shuffle(tasDeCarte);
	}
	
	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public int getExtension() {
		return extension;
	}

	/**
	 * Sets the extension.
	 *
	 * @param extension the new extension
	 */
	public void setExtension(int extension) {
		this.extension = extension;
	}

	/**
	 * Gets the tas de carte.
	 *
	 * @return the tas de carte
	 */
	public LinkedList<Carte> getTasDeCarte() {
		return tasDeCarte;
	}

	/**
	 * Sets the tas de carte.
	 *
	 * @param tasDeCarte the new tas de carte
	 */
	public void setTasDeCarte(LinkedList<Carte> tasDeCarte) {
		this.tasDeCarte = tasDeCarte;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return tasDeCarte.toString();
	}
	
	/**
	 * Adds the carte.
	 *
	 * @param c the c
	 */
	public void addCarte(Carte c) {
		this.tasDeCarte.add(c);
	}
	

	
}
