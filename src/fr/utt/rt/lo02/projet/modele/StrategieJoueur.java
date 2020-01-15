package fr.utt.rt.lo02.projet.modele;

import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * Classe abstraite qui determine les differentes methodes permettant a une
 * joueur de jouer Cela inclus les methodes de choix de cartes en debut de tour,
 * le choix d'une carte chez un adversaire pendant le tour.
 * Elle est observable car elle modifie les offres et jest des joueurs.
 */
public abstract class StrategieJoueur extends Observable {

	/**
	 * Choisir carte.
	 *
	 * @param ceJoueur le joueur en cours
	 * @return le joueur chez qui la carte a ete choisit
	 */
	public abstract Joueur choisirCarte(Joueur ceJoueur);

	/**
	 * Faire offre.
	 *
	 * @param ceJoueur le joueur en cours
	 */
	public abstract void faireOffre(Joueur ceJoueur);

	/**
	 * Sets the victime.
	 *
	 * @param joueur the new victime
	 */
	public abstract void setVictime(Joueur joueur);

	/**
	 * Cacher.
	 *
	 * @param actuelC the actuel C
	 */
	public abstract void cacher(Carte actuelC);

	/**
	 * Choix.
	 *
	 * @param b        the b
	 * @param jChoisit the j choisit
	 */
	public abstract void choix(boolean b, Joueur jChoisit);
}
