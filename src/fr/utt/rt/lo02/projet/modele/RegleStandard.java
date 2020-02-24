package fr.utt.rt.lo02.projet.modele;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe qui implemente l'interface regle qui permet de compter les points avec
 * la regle "Standard"
 * 
 */
public class RegleStandard implements Regle {

	/**
	 * Methode qui permet pour un jest donnee en parametre de renvoyer le nombre de
	 * points des cartes de carreau du jest du joueur. Pour cela on itere sur les
	 * cartes du jest du joueur puis pour la regle standard, on compte en negatif
	 * chaque valeur de carte de carreau. Si le joueur n'a que l'as de carreau, son
	 * nombre de points en carte de carreau est -5.
	 * 
	 *
	 * @param jest le Jest du joueur dont on souhaite compter les points
	 * @return un entier qui correspond au nombre de points du joueur pour les
	 *         cartes de carreau.
	 */
	@Override
	public int visitCarreau(LinkedList<Carte> jest) {
		int score = 0;
		int nbCarte = 0;
		boolean asCarreau = false;
		Iterator<Carte> it = jest.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if (c.getValeur() != Valeur.JOKER && c.getCouleur() == Couleur.CARREAU) {
				score -= c.getValeur().ordinal();
				nbCarte += 1;
				if (c.getValeur() == Valeur.AS)
					asCarreau = true;
			}
		}
		if (nbCarte == 1 && asCarreau) {
			score = -5;
		}
		return score;
	}

	/**
	 * Methode qui permet pour un jest donnee en parametre de renvoyer le nombre de
	 * points des cartes de Coeur du jest du joueur. Pour cela on itere sur les
	 * cartes du jest du joueur puis pour la regle standard on compte les points des
	 * cartes de coeur comme ceci : - Si le joueur n'a pas le Joker, il n'a pas de
	 * points pour les cartes de coeur - Si le joueur à le JOKER et entre 1 et 3
	 * cartes de coeur sans extension, on compte ses cartes en negatif - Si le
	 * joueur à le JOKER et entre 1 et 4 cartes de coeur avec extension, on compte
	 * ses cartes en negatif - Si le joueur à le JOKER et 4 cartes de coeur sans
	 * extension, le joker est un bonus de 4 et les cartes de coeurs sont comptes en
	 * positif.
	 *
	 * @param jest le Jest du joueur dont on souhaite compter les points
	 * @return un entier qui correspond au nombre de points du joueur pour les
	 *         cartes de carreau.
	 */
	@Override
	public int visitCoeur(LinkedList<Carte> jest) {
		int score = 0;
		int nbCoeur = 0;
		boolean asCoeur = false;
		if (jest.contains(Joker.getInstance())) {
			Iterator<Carte> it = jest.iterator();
			while (it.hasNext()) {
				Carte c = it.next();
				if (c.getCouleur() == Couleur.COEUR) {
					score -= c.getValeur().ordinal();
					nbCoeur++;
					if (c.getValeur() == Valeur.AS)
						asCoeur = true;
				}
			}
			if (nbCoeur == 0) {
				score += 4;
			} else if (nbCoeur == 4 && Partie.getInstance().getExtension() == 0) {
				score = 10;
			} else if (Partie.getInstance().getExtension() == 1 && nbCoeur == 5) {
				score = 16;
			} else if (nbCoeur == 1 && asCoeur) {
				score = -5;
			}
		}
		return score;
	}

	/**
	 * Methode qui permet pour un jest donnee en parametre de renvoyer le nombre de
	 * points pour les cartes de Pic et de Trefle Pour cela on itere sur les cartes
	 * du jest du joueur puis pour la regle standard, on compte chaque valeur de
	 * carte de pic et de trefle en positif. Si le joueur possede qu'un as de trefle
	 * (ou qu'un as de Pic), sa carte de trefle (ou de pic) vaut alors +5.
	 * 
	 * @param jest le Jest du joueur dont on souhaite compter les points
	 * @return un entier qui correspond au nombre de points du joueur pour les
	 *         cartes de carreau.
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
				if (c.getValeur() == Valeur.AS)
					asTrefle = true;
			} else if (c.getCouleur() == Couleur.PIC) {
				score += c.getValeur().ordinal();
				nbPic++;
				if (c.getValeur() == Valeur.AS)
					asPic = true;
			}
		}
		if (nbPic == 1 && asPic) {
			score += 4;
		}
		if (nbTrefle == 1 && asTrefle) {
			score += 4;
		}
		return score;
	}

	/**
	 * Methode qui permet pour un jest donnee en parametre de renvoyer le nombre de
	 * points bonus pour les cartes noires. Pour cela on itere sur les cartes du
	 * jest du joueur puis pour la regle standard, on compte un bonus de deux si le
	 * jest du joueur contient une carte de trefle et une carte de pic pour une
	 * valeur donnee.
	 *
	 * @param jest le Jest du joueur dont on souhaite compter les points
	 * @return un entier qui correspond au nombre de points du joueur pour les
	 *         cartes de carreau.
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
