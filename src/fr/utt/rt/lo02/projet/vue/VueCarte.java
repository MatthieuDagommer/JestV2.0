package fr.utt.rt.lo02.projet.vue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fr.utt.rt.lo02.projet.modele.Carte;
import fr.utt.rt.lo02.projet.modele.Valeur;

public class VueCarte {

	private Carte carte;

	private JLabel image;

	private String chemin;

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

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public Carte getCarte() {
		return carte;
	}

	public JLabel getImage() {
		return image;
	}
}
