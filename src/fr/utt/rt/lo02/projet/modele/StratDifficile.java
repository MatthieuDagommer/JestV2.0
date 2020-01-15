package fr.utt.rt.lo02.projet.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe permettant d'affecter a un joueur virtuel la strategie de type
 * difficile.
 * 
 */
public class StratDifficile extends StrategieJoueur {

	/**
	 * Permet de choisir la carte dans l'offre d'un joueur et de placer la carte
	 * choisit dans le JEST du joueur en cours. Dans cette strategie le joueur prend
	 * la premiere carte du premier joueur de la liste des offres disponible.
	 *
	 * @param ceJoueur Le joueur en cours.
	 * @return Le joueur chez qui l'offre a ete prise.
	 */
	@SuppressWarnings("deprecation")
	public Joueur choisirCarte(Joueur ceJoueur) {
		String message = "";
		ArrayList<Joueur> joueurs = Partie.getInstance().getOffreDispo(ceJoueur);
		Joueur victime = joueurs.get(0);
		ceJoueur.addJest(victime.getOffreVisible());
		message = ceJoueur.getNom() + " prend la carte " + victime.getOffreVisible() + " de " + victime.getNom();
		// System.out.println(message);
		victime.setOffreVisible(null);
		setChanged();
		notifyObservers(victime);// permet de mettre a jour la vue des offres de la victime
		setChanged();
		notifyObservers(message);
		return victime;
	}

	/**
	 * Methode qui permet au joueur de cacher sa carte noir de plus haute valeur. Si
	 * il ne possede pas de carte noir il cache n'importe quelle carte.
	 *
	 * @param ceJoueur Le joueur en cours
	 */
	@SuppressWarnings("deprecation")
	public void faireOffre(Joueur ceJoueur) {
		LinkedList<Carte> main = ceJoueur.getMain();

		Iterator<Carte> it = main.iterator();
		Carte c = it.next();
		if (c.getCouleur() == Couleur.PIC || c.getCouleur() == Couleur.TREFLE) {
			Carte c2 = it.next();
			if (c2.getCouleur() == Couleur.PIC || c2.getCouleur() == Couleur.TREFLE) {
				if (c2.getValeur().ordinal() > c.getValeur().ordinal()) {
					ceJoueur.setOffreCache(c2);
					main.remove(c2);
					ceJoueur.setOffreVisible(c);
					main.remove(c);
				} else {
					ceJoueur.setOffreCache(c);
					main.remove(c);
					ceJoueur.setOffreVisible(c2);
					main.remove(c2);
				}
			} else {
				ceJoueur.setOffreCache(c);
				main.remove(c);
				ceJoueur.setOffreVisible(c2);
				main.remove(c2);
			}

		} else {
			Carte c2 = it.next();
			if (c2.getCouleur() == Couleur.PIC || c2.getCouleur() == Couleur.TREFLE) {
				ceJoueur.setOffreCache(c2);
				main.remove(c2);
				ceJoueur.setOffreVisible(main.pop());
			} else {
				ceJoueur.setOffreVisible(main.pop());
				ceJoueur.setOffreCache(main.pop());
			}
		}

		setChanged();
		notifyObservers(ceJoueur);

	}


}
