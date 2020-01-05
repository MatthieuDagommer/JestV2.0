package fr.utt.rt.lo02.projet.vue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utt.rt.lo02.projet.modele.Carte;
import fr.utt.rt.lo02.projet.modele.Joueur;
import fr.utt.rt.lo02.projet.modele.Partie;

// TODO: Auto-generated Javadoc
/**
 * The Class VueJest.
 */
public class VueJest implements Observer {

	/** The jest. */
	private JPanel jest;

	/** The nom. */
	private JLabel nom;

	/**
	 * Instantiates a new vue jest.
	 */
	public VueJest() {
		Partie.getInstance().addObserver(this);
		jest = new JPanel();
		jest.setLayout(new BoxLayout(jest, BoxLayout.Y_AXIS));
	}

	/**
	 * Gets the jest.
	 *
	 * @return the jest
	 */
	public JPanel getJest() {
		return jest;
	}

	/**
	 * Update.
	 *
	 * @param o   the o
	 * @param arg the arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Joueur) {
			Joueur joueur = (Joueur) arg;
			majJest(joueur);
		} else if (arg instanceof String) {
			String message = arg.toString();
			if (message.contains("Fin de partie, le gagant est : ")) {
				afficheAllJest(Partie.getInstance().getJoueurs());
			}
		}
	}

	private void afficheAllJest(ArrayList<Joueur> joueurs) {
		jest.removeAll();
		Iterator<Joueur> it = joueurs.iterator();
		Joueur gagnant = Partie.getInstance().bestJest(joueurs);
		while (it.hasNext()) {
			Joueur j = it.next();
			JLabel nom = new JLabel(j.getNom());
			JLabel score = new JLabel(String.valueOf(j.getScore()));
			JPanel jest = new JPanel();
			jest.add(nom);
			LinkedList<Carte> jestJ = j.getJest();
			Iterator<Carte> itJ = jestJ.iterator();
			while (itJ.hasNext()) {
				VueCarte vc = new VueCarte(itJ.next());
				JLabel carte = vc.getImage();
				jest.add(carte);
			}
			jest.add(score);
			if (j == gagnant) {
				jest.setBorder(BorderFactory.createLineBorder(Color.black));
				jest.add(new JLabel("GAGNANT"));
			}
			this.jest.add(jest);
		}

	}

	/**
	 * Maj jest.
	 *
	 * @param joueur the joueur
	 */
	private void majJest(Joueur joueur) {
		jest.removeAll();
		nom = new JLabel(joueur.getNom());
		this.jest.add(nom);
		LinkedList<Carte> jest = joueur.getJest();
		Iterator<Carte> it = jest.iterator();
		while (it.hasNext()) {
			VueCarte vc = new VueCarte(it.next());
			JLabel carte = vc.getImage();
			this.jest.add(carte);
		}

	}
}
