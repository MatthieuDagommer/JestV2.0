package fr.utt.rt.lo02.projet.modele;

import java.util.Iterator;
import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * Classe de variante qui change le comptage des points dans le jest des joueurs. Cette classe implemente la classe de regle dans 
 * laquelle les differentes methodes permettant d'obtenir le JEST de chaque joueur y sont present.
 */
public class Variante2 implements Regle{

	/**
	 * Methode qui pour un JEST de joueur donnee permet de compter les cartes de carreau du JEST. Dans cette variante, 
	 * Les cartes de carreau sont comptes en positif.
	 *Un bonus est accorde si le JEST contient uniquement l'AS, celui-ci vaut 5.
	 * @param jest Le Jest du joueur (liste chainee de cartes).
	 * @return int : le score du joueur pour les cartes de carreau dans son JEST.
	 * Remarque : Le JOKER etant arbitrairement fixe comme une carte de carreau, on ne le compte pas dans le nombre de carte de carreau du JEST. 
	 */
	public int visitCarreau(LinkedList<Carte> jest) {
		int score = 0;
		int nbCarte = 0;
		boolean asCarreau = false;
		Iterator<Carte> it = jest.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if (c.getValeur() != Valeur.JOKER && c.getCouleur() == Couleur.CARREAU) {
				score += c.getValeur().ordinal();
				nbCarte += 1;
				if(c.getValeur() == Valeur.AS)
					asCarreau = true;
			}
		}
		if (nbCarte == 1 && asCarreau) {
			score = 5;
		}
		return score;
	}

	/**
	 * Methode qui pour un JEST de joueur donnee permet de compter les cartes de coeur du JEST. Dans cette variante, 
	 * Les cartes de coeur sont comptes en positif si le joueur à le JOKER et entre 1 et 3 cartes de coeur sans extension ou entre 1 et 4 cartes de coeur avec extension.
	 * En négatif si il a toutes les cartes de coeur avec un bonus de 4 pour le JOKER. 
	 * Aucun point n'est accorde pour les cartes de coeur dans le cas ou le JEST ne contient pas le JOKER.
	 *
	 * @param jest Le Jest du joueur (liste chainee de cartes).
	 * @return int : le score du joueur pour les cartes de carreau dans son JEST.
	 */
	@Override
	public int visitCoeur(LinkedList<Carte> jest) {
		int score = 0;
		int nbCoeur = 0;
		boolean asCoeur = false;
		if(jest.contains(Joker.getInstance())) {
			Iterator<Carte> it = jest.iterator();
			while(it.hasNext()) {
				Carte c = it.next();
				if(c.getCouleur() == Couleur.COEUR) {
					score += c.getValeur().ordinal();
					nbCoeur++;
					if(c.getValeur() == Valeur.AS)
						asCoeur = true;
				}
			}
			if (nbCoeur == 0) {
				score = -4;
			} else if (nbCoeur == 4 && Partie.getInstance().getExtension() == 0) {
				score = -10;
			} else if (Partie.getInstance().getExtension() == 1 && nbCoeur == 5) {
				score = -16;
			}else if (nbCoeur == 1 && asCoeur) {
				score = 5;
			}
		}
		return score;
	}

	/**
	 * Methode qui pour un JEST de joueur donnee permet de compter les cartes de trefle et de Pic du JEST. Dans cette variante, 
	 * Les cartes de Pic et de Trefle sont comptees en positif. Un bonus est accorde si le JEST contient uniquement l'AS de PIC et/ou l'as de Trefle, celui-ci
	 * vaut alors 5.
	 * 
	 * @param jest Le Jest du joueur (liste chainee de cartes).
	 * @return int : le score du joueur pour les cartes de carreau dans son JEST.
	 */
	@Override
	public int visitTreflePic(LinkedList<Carte> jest) {
		int score = 0;
		int nbPic = 0;
		int nbTrefle = 0;
		boolean asPic = false, asTrefle = false;
		Iterator<Carte> it = jest.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if (c.getCouleur() == Couleur.TREFLE) {
				score += c.getValeur().ordinal();
				nbTrefle++;
				if(c.getValeur() == Valeur.AS)
					asTrefle = true;
			} else if (c.getCouleur() == Couleur.PIC) {
				score += c.getValeur().ordinal();
				nbPic++;
				if(c.getValeur() == Valeur.AS)
					asPic = true;
			}
		}
		if (nbPic == 1 && asPic) {
			score+=4;
		}
		if (nbTrefle == 1 && asTrefle) {
			score+=4;
		}
		return score;
	}

	/**
	 * Methode qui pour un JEST de joueur donnee permet de compter les bonus pour les cartes noires. Si un joueur
	 * possede deux cartes de la même valeur en PIC et en Trefle, un bonus de +2 lui est accorde pour chaque paire.
	 *
	 * @param jest Le Jest du joueur (liste chainee de cartes).
	 * @return int : le score du joueur pour les cartes de carreau dans son JEST.
	 */
	@Override
	public int visitNoir(LinkedList<Carte> jest) {
		int score = 0;
		Iterator<Carte> it = jest.iterator();
		Iterator<Carte> it2 = jest.iterator();
		
		while (it.hasNext()) {
			Carte c = (Carte) it.next();
			if (c.getCouleur() == Couleur.PIC || c.getCouleur() == Couleur.TREFLE) {
				it2 = jest.iterator();
				while (it2.hasNext()) {
					Carte c2 = (Carte) it2.next();
					if ((c2.getCouleur() == Couleur.PIC || c2.getCouleur() == Couleur.TREFLE)
							&& c2.getValeur() == c.getValeur() && !c2.equals(c)) {
						score += 1;
					}
				}
			}
		}
		return score;
	}
}
