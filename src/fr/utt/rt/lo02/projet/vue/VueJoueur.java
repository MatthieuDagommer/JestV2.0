package fr.utt.rt.lo02.projet.vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utt.rt.lo02.projet.controleur.PartieControleur;
import fr.utt.rt.lo02.projet.modele.Carte;
import fr.utt.rt.lo02.projet.modele.Joueur;
import fr.utt.rt.lo02.projet.modele.Partie;
import fr.utt.rt.lo02.projet.modele.StrategieJoueur;

// TODO: Auto-generated Javadoc
/**
 * The Class VueJoueur.
 */
public class VueJoueur implements Observer {

	/** The joueur. */
	private Joueur joueur;

	/** The offre. */
	private JPanel offre;

	/** The offre visible. */
	private JLabel offreVisible;
	
	/** The offre cache. */
	private JLabel offreCache;

	/** The carte graphique. */
	private LinkedList<VueCarte> carteGraphique;

	/** The nom. */
	private JLabel nom;

	/** The vue partie. */
	private VuePartie vuePartie;

	// controleur

	/**
	 * Instantiates a new vue joueur.
	 *
	 * @param joueur the joueur
	 * @param vuePartie the vue partie
	 */
	public VueJoueur(Joueur joueur, VuePartie vuePartie) {
		this.vuePartie = vuePartie;
		this.joueur = joueur;
		this.nom = new JLabel(joueur.getNom());
		Partie.getInstance().addObserver(this);
		this.joueur.addObserver(this);
		this.joueur.getStrategie().addObserver(this);

		carteGraphique = new LinkedList<VueCarte>();

		offre = new JPanel();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		offre.setLayout(fl);

		drawOffre();
	}

	/**
	 * Draw offre.
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
	 * Maj offre.
	 */
	private void majOffre() {
		// offre.removeAll();
		// drawOffre();
		VueCarte carteVisible = new VueCarte(joueur.getOffreVisible());
		VueCarte carteCache = new VueCarte(joueur.getOffreCache());

		offreVisible.setIcon(carteVisible.getIcon());
		// System.out.println(carteVisible.getChemin());
		offreCache.setIcon(carteCache.getIcon());

		offre.updateUI();
	}

	/**
	 * Cacher offre.
	 */
	private void cacherOffre() {
		// offre.removeAll();
		// VueCarte carteVisible = new VueCarte(joueur.getOffreVisible());
		if (this.joueur.getOffreCache() != null)
			offreCache.setIcon(new ImageIcon("image/dosCarte.jpg"));
		else
			offreCache.setIcon(new ImageIcon("image/tasVide.png"));
		// offre.add(nom);
		// JLabel carte = carteVisible.getImage();
		// offre.add(carte);

		// carte = new JLabel(new ImageIcon("image/dosCarte.jpg"));
		// offre.add(carte);
		offre.updateUI();
	}

	/**
	 * Gets the offre visible.
	 *
	 * @return the offre visible
	 */
	public JLabel getOffreVisible() {
		return offreVisible;
	}

	/**
	 * Sets the offre visible.
	 *
	 * @param offreVisible the new offre visible
	 */
	public void setOffreVisible(JLabel offreVisible) {
		this.offreVisible = offreVisible;
	}

	/**
	 * Gets the offre cache.
	 *
	 * @return the offre cache
	 */
	public JLabel getOffreCache() {
		return offreCache;
	}

	/**
	 * Sets the offre cache.
	 *
	 * @param offreCache the new offre cache
	 */
	public void setOffreCache(JLabel offreCache) {
		this.offreCache = offreCache;
	}

	/**
	 * Gets the joueur.
	 *
	 * @return the joueur
	 */
	public Joueur getJoueur() {
		return joueur;
	}

	/**
	 * Sets the joueur.
	 *
	 * @param joueur the new joueur
	 */
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	/**
	 * Gets the offre.
	 *
	 * @return the offre
	 */
	public JPanel getOffre() {
		return offre;
	}

	/**
	 * Sets the offre.
	 *
	 * @param offre the new offre
	 */
	public void setOffre(JPanel offre) {
		this.offre = offre;
	}

	/**
	 * Gets the carte graphique.
	 *
	 * @return the carte graphique
	 */
	public LinkedList<VueCarte> getCarteGraphique() {
		return carteGraphique;
	}

	/**
	 * Sets the carte graphique.
	 *
	 * @param carteGraphique the new carte graphique
	 */
	public void setCarteGraphique(LinkedList<VueCarte> carteGraphique) {
		this.carteGraphique = carteGraphique;
	}

	/**
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	public JLabel getNom() {
		return nom;
	}

	/**
	 * Sets the nom.
	 *
	 * @param nom the new nom
	 */
	public void setNom(JLabel nom) {
		this.nom = nom;
	}

	/**
	 * Update.
	 *
	 * @param o the o
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
	 * Switch border.
	 *
	 * @param j the j
	 */
	public void switchBorder(Joueur j) {
		if (j == this.joueur) {
			offre.setBorder(BorderFactory.createLineBorder(Color.black));
		} else {
			offre.setBorder(BorderFactory.createLineBorder(Color.white));
		}

	}

}
