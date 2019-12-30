package fr.utt.rt.lo02.projet.modele;

import java.util.Iterator;
import java.util.LinkedList;



public class RegleStandard implements Regle {

	@Override
	public int visitCarreau(LinkedList<Carte> jest) {
		int score = 0;
		int nbCarte = 0;
		Iterator<Carte> it = jest.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if (c.getValeur() != Valeur.JOKER && c.getCouleur() == Couleur.CARREAU) {
				score -= c.getValeur().ordinal();
				nbCarte += 1;
			}
		}
		if (nbCarte == 1 && jest.contains(new Carte(Valeur.AS, Couleur.CARREAU, Trophee.plus4))) {
			score -= 4;
		}
		return score;
	}

	@Override
	public int visitCoeur(LinkedList<Carte> jest) {
		int score = 0;
		int nbCoeur = 0;
		if(jest.contains(Joker.getInstance())) {
			Iterator<Carte> it = jest.iterator();
			while(it.hasNext()) {
				Carte c = it.next();
				if(c.getCouleur() == Couleur.COEUR) {
					score -= c.getValeur().ordinal();
					nbCoeur++;
				}
			}
			if(nbCoeur == 4) {
				score = 5;
			}
			if(nbCoeur == 1 && jest.contains(new Carte(Valeur.AS, Couleur.COEUR, Trophee.joker))) {
				score = 5;
			}
		}
		return score;
	}

	@Override
	public int visitTreflePic(LinkedList<Carte> jest) {
		int score = 0;
		int nbPic = 0;
		int nbTrefle = 0;
		Iterator<Carte> it = jest.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if (c.getCouleur() == Couleur.TREFLE) {
				score += c.getValeur().ordinal();
				nbTrefle++;
			} else if (c.getCouleur() == Couleur.PIC) {
				score += c.getValeur().ordinal();
				nbPic++;
			}
		}
		if (nbPic == 1 && jest.contains(new Carte(Valeur.AS, Couleur.PIC, Trophee.hauttrefle))) {
			score+=4;
		}
		if (nbTrefle == 1 && jest.contains(new Carte(Valeur.AS, Couleur.TREFLE, Trophee.hautpic))) {
			score+=4;
		}
		return score;
	}

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
