package fr.utt.rt.lo02.projet.modele;

import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * Classe abstraite qui détermine les différentes méthodes permettant à une
 * joueur de jouer Cela inclus les méthodes de choix de cartes en début de
 * tour, le choix d'une carte chez un adversaire pendant le tour
 * 
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

}
