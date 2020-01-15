package fr.utt.rt.lo02.projet.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe qui permet de definir la strategie d'un joueur virtuel, ici difficile
 */
public class StratDifficile extends StrategieJoueur {

	/**
	 * Methode qui choisit une carte dans les offres disponible. Prend la carte
	 * visible du premier joueur dans la liste des offres disponible.
	 *
	 * @param ceJoueur le joueur qui joue
	 * @return le joueur chez qui la carte a ete prise
	 */
	public Joueur choisirCarte(Joueur ceJoueur) {
		String message = "";
		ArrayList<Joueur> joueurs = Partie.getInstance().getOffreDispo(ceJoueur);
		Joueur victime = joueurs.get(0);
		ceJoueur.addJest(victime.getOffreVisible());
		message = ceJoueur.getNom() + " prend la carte " + victime.getOffreVisible() + " de " + victime.getNom();
		// System.out.println(message);
		victime.setOffreVisible(null);
		setChanged();
		notifyObservers(victime);// On let a jour la vue de la victime
		setChanged();
		notifyObservers(message);
		return victime;
	}

	/**
	 * Permet de faire l'offre du joueur virtuel. Pour cette strategie difficile, la
	 * carte cache sera la carte noir de valeur la plus forte, sinon une carte
	 * rouge.
	 *
	 * @param ceJoueur le joueur qui joue
	 */
	@Override
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

	/**
	 * setter de victime, inutilise pour les joueur virtuel
	 *
	 * @param joueur the new victime
	 */
	@Override
	public void setVictime(Joueur joueur) {
		// TODO Auto-generated method stub

	}

	/**
	 * methode non utilise pour les joueurs virtuel.
	 *
	 * @param b      the b
	 * @param joueur the joueur
	 */
	@Override
	public void choix(boolean b, Joueur joueur) {
		// TODO Auto-generated method stub

	}

	/**
	 * methode non utilise pour les joueurs virtuel.
	 *
	 * @param actuelC the actuel C
	 */
	@Override
	public void cacher(Carte actuelC) {
		// TODO Auto-generated method stub

	}

}
