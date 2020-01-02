package fr.utt.rt.lo02.projet.modele;

import java.util.Iterator;
import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * The Class Variante2.
 */
public class Variante2 implements Regle{

	/**
	 * Visit carreau.
	 *
	 * @param jest the jest
	 * @return the int
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
	 * Visit coeur.
	 *
	 * @param jest the jest
	 * @return the int
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
			if(nbCoeur == 4) {
				score = 5;
			}
			if(nbCoeur == 1 && asCoeur) {
				score = 5;
			}
		}
		return score;
	}

	/**
	 * Visit trefle pic.
	 *
	 * @param jest the jest
	 * @return the int
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
	 * Visit noir.
	 *
	 * @param jest the jest
	 * @return the int
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
