package fr.utt.rt.lo02.projet.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utt.rt.lo02.projet.modele.Carte;
import fr.utt.rt.lo02.projet.modele.Joueur;

// TODO: Auto-generated Javadoc
/**
 * The Class FenetreChoixOffre.
 */
public class FenetreChoixOffre {

	/** The joueur. */
	private Joueur joueur;

	/** The main. */
	private LinkedList<Carte> main;

	/**
	 * Instantiates a new fenetre choix offre.
	 *
	 * @param joueur the joueur
	 */
	public FenetreChoixOffre(Joueur joueur) {
		JFrame fenetre = new JFrame("Choisir la carte a cacher " + joueur.getNom());
		fenetre.setPreferredSize(new Dimension(350, 200));
		fenetre.setLayout(new BorderLayout());
		fenetre.setResizable(true);

		JPanel panelMain = new JPanel();
		panelMain.setLayout(new GridLayout(0, 2));

		JPanel main = new JPanel();
		main.setLayout(new GridLayout(0, 2));

		this.main = joueur.getMain();
		Iterator<Carte> it = this.main.iterator();

		while (it.hasNext()) {
			Carte actuelC = it.next();
			VueCarte vc = new VueCarte(actuelC);
			JLabel c = vc.getImage();
			panelMain.add(c);
			c.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					joueur.getStrategie().cacher(actuelC);
					fenetre.dispose();
				}
			});
		}

		fenetre.add(panelMain);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);

	}
}
