package fr.utt.rt.lo02.projet.vue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fr.utt.rt.lo02.projet.modele.Carte;
import fr.utt.rt.lo02.projet.modele.Valeur;

// TODO: Auto-generated Javadoc
/**
 * The Class VueCarte.
 */
public class VueCarte {

	/** The carte. */
	private Carte carte;

	/** The image. */
	private JLabel image;

	/** The chemin. */
	private String chemin;

	/**
	 * Instantiates a new vue carte.
	 *
	 * @param carte the carte
	 */
	public VueCarte(Carte carte) {
		this.carte = carte;
		StringBuffer sb = new StringBuffer("image/");
		if (carte == null) {
			sb.append("tasVide");
		} else if (carte.getValeur() != Valeur.JOKER) {
			switch (carte.getValeur()) {
			case AS:
				sb.append("As");
				break;
			case DEUX:
				sb.append("Deux");
				break;
			case JOKER:
				break;
			case QUATRE:
				sb.append("Quatre");
				break;
			case SIX:
				sb.append("Six");
				break;
			case TROIS:
				sb.append("Trois");
				break;
			default:
				break;
			}
			switch (carte.getCouleur()) {
			case CARREAU:
				sb.append("Carreau");
				break;
			case COEUR:
				sb.append("Coeur");
				break;
			case PIC:
				sb.append("Pic");
				break;
			case TREFLE:
				sb.append("Trefle");
				break;
			default:
				break;
			}
		} else if (carte.getValeur() == Valeur.JOKER) {
			sb.append("Joker");
		}

		sb.append(".png");
		chemin = sb.toString();
		this.image = new JLabel(new ImageIcon(sb.toString()));
	}
	
	/**
	 * Gets the icon.
	 *
	 * @return the icon
	 */
	public ImageIcon getIcon() {
		return new ImageIcon(chemin);
	}

	/**
	 * Gets the chemin.
	 *
	 * @return the chemin
	 */
	public String getChemin() {
		return chemin;
	}

	/**
	 * Sets the chemin.
	 *
	 * @param chemin the new chemin
	 */
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	/**
	 * Gets the carte.
	 *
	 * @return the carte
	 */
	public Carte getCarte() {
		return carte;
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public JLabel getImage() {
		return image;
	}
}
