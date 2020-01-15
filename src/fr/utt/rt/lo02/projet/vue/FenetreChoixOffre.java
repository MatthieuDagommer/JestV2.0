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

/**
 * Classe de la fenetre qui demande la carte a cacher pour un joueur physique
 */
public class FenetreChoixOffre {

	/** le joueur. */
	private Joueur joueur;

	/** la main du joueur */
	private LinkedList<Carte> main;

	/**
	 * Constructeur de la classe.
	 * Construit la fenetre avec les 2 carte de la main du joueur.
	 * Des qu'une carte est clique, on la cache dans l'offre du joueur.
	 *
	 * @param joueur le joueur qui joue
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
