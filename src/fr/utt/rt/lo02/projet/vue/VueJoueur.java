package fr.utt.rt.lo02.projet.vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utt.rt.lo02.projet.modele.Carte;
import fr.utt.rt.lo02.projet.modele.Joueur;
import fr.utt.rt.lo02.projet.modele.Partie;
import fr.utt.rt.lo02.projet.modele.StrategieJoueur;

public class VueJoueur implements Observer {

	private Joueur joueur;

	private JPanel offre;

	private LinkedList<VueCarte> carteGraphique;

	private JLabel nom;

	// controleur

	public VueJoueur(Joueur joueur) {
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

	private void drawOffre() {

		VueCarte carteVisible = new VueCarte(joueur.getOffreVisible());
		VueCarte carteCache = new VueCarte(joueur.getOffreCache());

		/*
		 * carteGraphique.add(carteVisible); carteGraphique.add(carteCache);
		 */
		offre.add(nom);
		JLabel carte = carteVisible.getImage();
		offre.add(carte);

		carte = carteCache.getImage();
		offre.add(carte);

	}

	private void majOffre() {
		offre.removeAll();
		drawOffre();
		offre.updateUI();
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public JPanel getOffre() {
		return offre;
	}

	public void setOffre(JPanel offre) {
		this.offre = offre;
	}

	public LinkedList<VueCarte> getCarteGraphique() {
		return carteGraphique;
	}

	public void setCarteGraphique(LinkedList<VueCarte> carteGraphique) {
		this.carteGraphique = carteGraphique;
	}

	public JLabel getNom() {
		return nom;
	}

	public void setNom(JLabel nom) {
		this.nom = nom;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Joueur) {
			Joueur j = (Joueur) arg;
			if (o instanceof StrategieJoueur) {
				majOffre();
			} else {
				switchBorder(j);
				if(j == joueur) {
					majOffre();
				} else {
					cacherOffre();
				}
			}
		}

	}

	private void cacherOffre() {
		offre.removeAll();
		VueCarte carteVisible = new VueCarte(joueur.getOffreVisible());

		offre.add(nom);
		JLabel carte = carteVisible.getImage();
		offre.add(carte);

		carte = new JLabel(new ImageIcon("image/dosCarte.jpg"));
		offre.add(carte);
	}

	public void switchBorder(Joueur j) {
		if (j == this.joueur) {
			offre.setBorder(BorderFactory.createLineBorder(Color.black));
		} else {
			offre.setBorder(BorderFactory.createLineBorder(Color.white));
		}

	}

}
