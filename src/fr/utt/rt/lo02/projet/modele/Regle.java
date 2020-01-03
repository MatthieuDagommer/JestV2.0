package fr.utt.rt.lo02.projet.modele;

import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * Cette interface contient les différentes méthodes qui vont permettre aux différentes variantes 
 * de compter les points dans les Jests des joueurs.
 */
public interface Regle {
	
	/**
	 * Méthode qui permet de renvoyer les points du jest donné en paramètre pour les cartes de carreau.
	 *
	 * @param jest dans lequel on souhaite compter les points
	 * @return entier, nombre de points pour les cartes de carreau
	 */
	public int visitCarreau(LinkedList<Carte> jest);
	
	/**
	 * Méthode qui permet de renvoyer les points du jest donné en paramètre pour les cartes de coeur.
	 *
	 * @param jest dans lequel on souhaite compter les points
	 * @return entier, nombre de points pour les cartes de coeur
	 */
	public int visitCoeur(LinkedList<Carte> jest);
	
	/**
	 * Méthode qui permet de renvoyer les points du jest donné en paramètre pour les cartes de pic et de trèfle
	 *
	 * @param jest dans lequel on souhaite compter les points
	 * @return entier, nombre de points pour les cartes de pic et de trèfle
	 */
	public int visitTreflePic(LinkedList<Carte> jest);
	
	/**
	 * Méthode qui permet de renvoyer les points du jest donnée en paramètre pour les cartes consituant
	 * un bonus noir (carte de pic et de trèfle de la même valeur dans un jest)
	 *
	 * @param jest dans lequel on souhaite compter les points
	 * @return entier, nombre de points pour les cartes noires consituant un bonus.
	 */
	public int visitNoir(LinkedList<Carte> jest);
}
