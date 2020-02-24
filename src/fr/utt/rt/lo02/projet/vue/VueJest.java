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

/**
 * Classe permettant d'afficher les JEST
 * 
 */
@SuppressWarnings("deprecation")
public class VueJest implements Observer {

	/** Panel des jest. */
	private JPanel jest;

	/** le nom du joueur a qui appartient le jest. */
	private JLabel nom;

	/**
	 * Constructeur de jest. Il s'ajoute dans la liste des observer de partie.
	 */
	public VueJest() {
		Partie.getInstance().addObserver(this);
		jest = new JPanel();
		jest.setLayout(new BoxLayout(jest, BoxLayout.Y_AXIS));
	}

	/**
	 * Getter de jest.
	 *
	 * @return le jest
	 */
	public JPanel getJest() {
		return jest;
	}

	/**
	 * Methode update qui met a jour le jest a afficher en fonction de l'état de la
	 * partie.
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

	/**
	 * Methode qui affiche tout les JEST en fin de partie et affiche le gagnant.
	 *
	 * @param joueurs les joueurs dont on affiche le jest
	 */
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
	 * Methode pour mettre a jour le jest a l'ecran.
	 *
	 * @param joueur le joueur dont on veut le jest
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
