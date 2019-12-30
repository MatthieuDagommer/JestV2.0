package fr.utt.rt.lo02.projet.modele;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Deck {

	
	private LinkedList<Carte> tasDeCarte;
	private int extension;
	
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
	
	public boolean estVide() {
		return tasDeCarte.isEmpty();
	}

	public Carte piocherCarte() {
		return tasDeCarte.pop();
	}
	
	public void melanger() {
		Collections.shuffle(tasDeCarte);
	}
	
	public int getExtension() {
		return extension;
	}

	public void setExtension(int extension) {
		this.extension = extension;
	}

	public LinkedList<Carte> getTasDeCarte() {
		return tasDeCarte;
	}

	public void setTasDeCarte(LinkedList<Carte> tasDeCarte) {
		this.tasDeCarte = tasDeCarte;
	}
	
	public String toString() {
		return tasDeCarte.toString();
	}
	
	public void addCarte(Carte c) {
		this.tasDeCarte.add(c);
	}
	

	
}
