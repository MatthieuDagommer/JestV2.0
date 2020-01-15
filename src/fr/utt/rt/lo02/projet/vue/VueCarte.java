package fr.utt.rt.lo02.projet.vue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fr.utt.rt.lo02.projet.modele.Carte;
import fr.utt.rt.lo02.projet.modele.Valeur;

/**
 * Classe qui represente une carte en vue graohique
 */
public class VueCarte {

	/** la carte. */
	private Carte carte;

	/** son image. */
	private JLabel image;

	/** le chemin vers l'image. */
	private String chemin;

	/**
	 * Constructeur de la classe, il permet de definir le chemin en fonction des
	 * atributs de la carte a cree
	 *
	 * @param carte la carte dont on veut une vue
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
	 * Getter de icon.
	 *
	 * @return the icon
	 */
	public ImageIcon getIcon() {
		return new ImageIcon(chemin);
	}

	/**
	 * Getter de chemin.
	 *
	 * @return the chemin
	 */
	public String getChemin() {
		return chemin;
	}

	/**
	 * Setter de chemin.
	 *
	 * @param chemin le nouveau chemin
	 */
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	/**
	 * Getter de carte.
	 *
	 * @return the carte
	 */
	public Carte getCarte() {
		return carte;
	}

	/**
	 * Getter de image.
	 *
	 * @return the image
	 */
	public JLabel getImage() {
		return image;
	}
}
