package fr.utt.rt.lo02.projet.modele;

import java.util.LinkedList;

/**
 * Cette interface contient les differentes methodes qui vont permettre aux
 * differentes variantes de compter les points dans les Jests des joueurs.
 */
public interface Regle {

	/**
	 * Methode qui permet de renvoyer les points du jest donne en parametre pour
	 * les cartes de carreau.
	 *
	 * @param jest dans lequel on souhaite compter les points
	 * @return entier, nombre de points pour les cartes de carreau
	 */
	public int visitCarreau(LinkedList<Carte> jest);

	/**
	 * Methode qui permet de renvoyer les points du jest donne en parametre pour
	 * les cartes de coeur.
	 *
	 * @param jest dans lequel on souhaite compter les points
	 * @return entier, nombre de points pour les cartes de coeur
	 */
	public int visitCoeur(LinkedList<Carte> jest);

	/**
	 * Methode qui permet de renvoyer les points du jest donne en parametre pour
	 * les cartes de pic et de trefle
	 *
	 * @param jest dans lequel on souhaite compter les points
	 * @return entier, nombre de points pour les cartes de pic et de trefle
	 */
	public int visitTreflePic(LinkedList<Carte> jest);

	/**
	 * Methode qui permet de renvoyer les points du jest donnee en parametre pour
	 * les cartes consituant un bonus noir (carte de pic et de trefle de la même
	 * valeur dans un jest)
	 *
	 * @param jest dans lequel on souhaite compter les points
	 * @return entier, nombre de points pour les cartes noires consituant un bonus.
	 */
	public int visitNoir(LinkedList<Carte> jest);
}
