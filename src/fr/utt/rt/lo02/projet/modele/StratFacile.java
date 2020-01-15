package fr.utt.rt.lo02.projet.modele;

import java.util.ArrayList;
import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * Classe qui permet de definir la strategie d'un joueur virtuel, ici facile
 */
public class StratFacile extends StrategieJoueur {

	/**
	 * Methode qui choisit une carte dans les offres disponible. Prend la carte
	 * visible du premier joueur dans la liste des offres disponible.
	 *
	 * @param ceJoueur le joueur qui joue
	 * @return le joueur chez qui la carte a ete prise
	 */
	@Override
	public Joueur choisirCarte(Joueur ceJoueur) {
		String message = "";
		ArrayList<Joueur> joueurs = Partie.getInstance().getOffreDispo(ceJoueur);
		Joueur victime = joueurs.get(0);
		ceJoueur.addJest(victime.getOffreVisible());
		message = ceJoueur.getNom() + " prend la carte " + victime.getOffreVisible() + " de " + victime.getNom();
		// System.out.println(message);
		setChanged();
		notifyObservers(message);
		victime.setOffreVisible(null);
		setChanged();
		notifyObservers(victime);

		return victime;
	}

	/**
	 * Permet de faire l'offre du joueur virtuel. Pour cette strategie facile, la
	 * carte cache sera la premiere carte de la main du joueur
	 *
	 * @param ceJoueur le joueur qui joue
	 */
	@Override
	public void faireOffre(Joueur ceJoueur) {
		LinkedList<Carte> main = ceJoueur.getMain();
		ceJoueur.setOffreCache(main.pop());
		ceJoueur.setOffreVisible(main.pop());
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
