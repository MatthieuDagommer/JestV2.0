package fr.utt.rt.lo02.projet.vue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

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
public class VueJest implements Observer{
	
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
	 * @param o the o
	 * @param arg the arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Joueur) {
			Joueur joueur = (Joueur) arg;
			majJest(joueur);
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
		while(it.hasNext()) {
			VueCarte vc = new VueCarte(it.next());
			JLabel carte = vc.getImage();
			this.jest.add(carte);
		}
		
	}
}
