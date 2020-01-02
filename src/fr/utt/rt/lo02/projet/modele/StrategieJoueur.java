package fr.utt.rt.lo02.projet.modele;

import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * The Class StrategieJoueur.
 */
public abstract class StrategieJoueur extends Observable {
	
	/**
	 * Choisir carte.
	 *
	 * @param ceJoueur the ce joueur
	 * @return the joueur
	 */
	public abstract Joueur choisirCarte(Joueur ceJoueur);
	
	/**
	 * Faire offre.
	 *
	 * @param ceJoueur the ce joueur
	 */
	public abstract void faireOffre(Joueur ceJoueur);

	/**
	 * Sets the victime.
	 *
	 * @param joueur the new victime
	 */
	public abstract void setVictime(Joueur joueur);


	/**
	 * Choix.
	 *
	 * @param b the b
	 * @param joueur the joueur
	 */
	public abstract void choix(boolean b, Joueur joueur);

	/**
	 * Cacher.
	 *
	 * @param actuelC the actuel C
	 */
	public abstract void cacher(Carte actuelC); 
}
