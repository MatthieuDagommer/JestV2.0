package fr.utt.rt.lo02.projet.modele;

import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * The Interface Regle.
 */
public interface Regle {
	
	/**
	 * Visit carreau.
	 *
	 * @param jest the jest
	 * @return the int
	 */
	public int visitCarreau(LinkedList<Carte> jest);
	
	/**
	 * Visit coeur.
	 *
	 * @param jest the jest
	 * @return the int
	 */
	public int visitCoeur(LinkedList<Carte> jest);
	
	/**
	 * Visit trefle pic.
	 *
	 * @param jest the jest
	 * @return the int
	 */
	public int visitTreflePic(LinkedList<Carte> jest);
	
	/**
	 * Visit noir.
	 *
	 * @param jest the jest
	 * @return the int
	 */
	public int visitNoir(LinkedList<Carte> jest);
}
