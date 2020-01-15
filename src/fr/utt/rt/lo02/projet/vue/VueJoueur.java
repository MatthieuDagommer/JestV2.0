package fr.utt.rt.lo02.projet.vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utt.rt.lo02.projet.controleur.PartieControleur;
import fr.utt.rt.lo02.projet.modele.Joueur;
import fr.utt.rt.lo02.projet.modele.Partie;
import fr.utt.rt.lo02.projet.modele.StrategieJoueur;

/**
 * Classe permettant d'afficher l offre d'un joueur
 * 
 * Elle observe la partie, le joueur et sa strategie.
 */
@SuppressWarnings("deprecation")
public class VueJoueur implements Observer {

	/** le joueur. */
	private Joueur joueur;

	/** Panel de son offre. */
	private JPanel offre;

	/** Label de son offre visible. */
	private JLabel offreVisible;

	/** Label de son offre cache. */
	private JLabel offreCache;

	/** Le Label du nom du joueur. */
	private JLabel nom;

	/**
	 * Constructeur de la classe. Il initialise les attributs et la fenetre et
	 * ajoute les observers
	 *
	 * @param joueur le joueur dont on cree la vue
	 */
	public VueJoueur(Joueur joueur) {
		this.joueur = joueur;
		this.nom = new JLabel(joueur.getNom());
		Partie.getInstance().addObserver(this);
		this.joueur.addObserver(this);
		this.joueur.getStrategie().addObserver(this);

		offre = new JPanel();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		offre.setLayout(fl);

		drawOffre();
	}

	/**
	 * Methode qui affiche l'offre du joueur au debut de la partie
	 */
	private void drawOffre() {

		VueCarte carteVisible = new VueCarte(joueur.getOffreVisible());
		VueCarte carteCache = new VueCarte(joueur.getOffreCache());

		offre.add(nom);
		offreVisible = carteVisible.getImage();
		offre.add(offreVisible);

		offreCache = carteCache.getImage();
		offre.add(offreCache);

	}

	/**
	 * Methode qui met a jour l'offre du joueus
	 */
	private void majOffre() {
		VueCarte carteVisible = new VueCarte(joueur.getOffreVisible());
		VueCarte carteCache = new VueCarte(joueur.getOffreCache());

		offreVisible.setIcon(carteVisible.getIcon());
		offreCache.setIcon(carteCache.getIcon());

		offre.updateUI();
	}

	/**
	 * methode qui permet de cacher son offre non visible quand ce n'est pas son
	 * tour
	 */
	private void cacherOffre() {
		if (this.joueur.getOffreCache() != null)
			offreCache.setIcon(new ImageIcon("image/dosCarte.jpg"));
		else
			offreCache.setIcon(new ImageIcon("image/tasVide.png"));
		offre.updateUI();
	}

	/**
	 * Getter de offre visible.
	 *
	 * @return the offre visible
	 */
	public JLabel getOffreVisible() {
		return offreVisible;
	}

	/**
	 * Setter de offre visible.
	 *
	 * @param offreVisible la nouvelle offre visible
	 */
	public void setOffreVisible(JLabel offreVisible) {
		this.offreVisible = offreVisible;
	}

	/**
	 * Getter de offre cache.
	 *
	 * @return the offre cache
	 */
	public JLabel getOffreCache() {
		return offreCache;
	}

	/**
	 * Setter de offre cache.
	 *
	 * @param offreCache la nouvelle offre cache
	 */
	public void setOffreCache(JLabel offreCache) {
		this.offreCache = offreCache;
	}

	/**
	 * Getter de joueur.
	 *
	 * @return the joueur
	 */
	public Joueur getJoueur() {
		return joueur;
	}

	/**
	 * Setter de joueur.
	 *
	 * @param joueur le nouveau joueur
	 */
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	/**
	 * Getter de offre.
	 *
	 * @return the offre
	 */
	public JPanel getOffre() {
		return offre;
	}

	/**
	 * Setter de offre.
	 *
	 * @param offre la nouvelle offre
	 */
	public void setOffre(JPanel offre) {
		this.offre = offre;
	}

	/**
	 * Getter de nom.
	 *
	 * @return the nom
	 */
	public JLabel getNom() {
		return nom;
	}

	/**
	 * Setter de nom.
	 *
	 * @param nom le nouveau nom
	 */
	public void setNom(JLabel nom) {
		this.nom = nom;
	}

	/**
	 * Methode qui met a jour l'offre du joueur en fonction du modele. Cache son
	 * offre quand ce n'est pas son tour, met a jour si on pioche un carte chez lui,
	 * et affiche sont offre si c'est son tour
	 *
	 * @param o   the o
	 * @param arg the arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Joueur) {
			Joueur j = (Joueur) arg;
			if (o instanceof StrategieJoueur) {
				majOffre();
			} else {
				switchBorder(j);
				if (j == joueur) {
					majOffre();
				} else {
					majOffre();
					cacherOffre();
				}
			}
		} else {
			majOffre();
		}
		if (arg instanceof String && !(o instanceof Partie)) {
			String message = arg.toString();
			PartieControleur.updateJTextArea(message);
		}

	}

	/**
	 * Permet d'entourer le joueur qui joue
	 *
	 * @param j le joueur qui joue
	 */
	public void switchBorder(Joueur j) {
		if (j == this.joueur) {
			offre.setBorder(BorderFactory.createLineBorder(Color.black));
		} else {
			offre.setBorder(BorderFactory.createLineBorder(Color.white));
		}

	}

}
