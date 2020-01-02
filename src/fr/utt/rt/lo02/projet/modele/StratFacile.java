package fr.utt.rt.lo02.projet.modele;

import java.util.ArrayList;
import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * The Class StratFacile.
 */
public class StratFacile extends StrategieJoueur {

	/**
	 * Choisir carte.
	 *
	 * @param ceJoueur the ce joueur
	 * @return the joueur
	 */
	@Override
	public Joueur choisirCarte(Joueur ceJoueur) {
		String message = "";
		ArrayList<Joueur> joueurs = Partie.getInstance().getOffreDispo(ceJoueur);
		Joueur victime = joueurs.get(0);
		ceJoueur.addJest(victime.getOffreVisible());
		message = ceJoueur.getNom() + " prend la carte "+victime.getOffreVisible()+" de "+victime.getNom();
		//System.out.println(message);
		setChanged();
		notifyObservers(message);
		victime.setOffreVisible(null);
		setChanged();
		notifyObservers(victime);

		return victime;
	}

	/**
	 * Faire offre.
	 *
	 * @param ceJoueur the ce joueur
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
	 * Sets the victime.
	 *
	 * @param joueur the new victime
	 */
	@Override
	public void setVictime(Joueur joueur) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Choix.
	 *
	 * @param b the b
	 * @param joueur the joueur
	 */
	@Override
	public void choix(boolean b, Joueur joueur) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Cacher.
	 *
	 * @param actuelC the actuel C
	 */
	@Override
	public void cacher(Carte actuelC) {
		// TODO Auto-generated method stub
		
	}

}
