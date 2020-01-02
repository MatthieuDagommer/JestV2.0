package fr.utt.rt.lo02.projet.controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JLabel;

import fr.utt.rt.lo02.projet.modele.Carte;
import fr.utt.rt.lo02.projet.modele.Joueur;
import fr.utt.rt.lo02.projet.modele.Partie;
import fr.utt.rt.lo02.projet.modele.RegleStandard;
import fr.utt.rt.lo02.projet.modele.StratDifficile;
import fr.utt.rt.lo02.projet.modele.StratFacile;
import fr.utt.rt.lo02.projet.modele.StratPhysique;
import fr.utt.rt.lo02.projet.modele.Variante1;
import fr.utt.rt.lo02.projet.modele.Variante2;
import fr.utt.rt.lo02.projet.vue.FenetreChoixOffre;
import fr.utt.rt.lo02.projet.vue.MenuInitialisation;
import fr.utt.rt.lo02.projet.vue.VueJoueur;
import fr.utt.rt.lo02.projet.vue.VuePartie;

// TODO: Auto-generated Javadoc
/**
 * The Class PartieControleur.
 */
public class PartieControleur {

	/** The partie. */
	private static Partie partie;

	/** The vue partie. */
	private static VuePartie vuePartie;

	/** The pioche. */
	private JLabel pioche;

	/** The offres. */
	private LinkedList<JLabel> offres;

	/**
	 * Instantiates a new partie controleur.
	 *
	 * @param partie the partie
	 * @param vuePartie the vue partie
	 */
	public PartieControleur(Partie partie, VuePartie vuePartie) {
		setPartie(partie);
		setVuePartie(vuePartie);
		pioche = vuePartie.getPioche();
		offres = new LinkedList<JLabel>();
		pioche.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				System.out.println("Vous avez cliquer sur la pioche");
			}
		});

		ArrayList<VueJoueur> vueJoueurs = vuePartie.getVueJoueurs();
		Iterator<VueJoueur> it = vueJoueurs.iterator();
		while (it.hasNext()) {
			VueJoueur vueJ = it.next();
			offres.add(vueJ.getOffreCache());
			offres.add(vueJ.getOffreVisible());
		}

		Iterator<JLabel> itOffres = offres.iterator();
		while (itOffres.hasNext()) {
			JLabel c = itOffres.next();
			c.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					if (partie.getJoueurActuel() != null
							&& partie.getJoueurActuel().getStrategie() instanceof StratPhysique) {
						ArrayList<VueJoueur> vueJoueurs = vuePartie.getVueJoueurs();
						Iterator<VueJoueur> it = vueJoueurs.iterator();
						while (it.hasNext()) {
							VueJoueur vueJ = it.next();
							if (vueJ.getOffreVisible().equals(c) && Partie.getInstance()
									.getOffreDispo(partie.getJoueurActuel()).contains(vueJ.getJoueur())) {
								partie.getJoueurActuel().getStrategie().choix(true, vueJ.getJoueur());
							} else if (vueJ.getOffreCache().equals(c) && Partie.getInstance()
									.getOffreDispo(partie.getJoueurActuel()).contains(vueJ.getJoueur())) {
								partie.getJoueurActuel().getStrategie().choix(false, vueJ.getJoueur());
							}
						}
					}
				}
			});
		}
	}

	/**
	 * Initialisation jest.
	 */
	public static void initialisationJest() {
		MenuInitialisation dialog = new MenuInitialisation(null, "JEST menu", true);
		dialog.setVisible(true);
		Partie.getInstance().buildJeuDeCarte(dialog.getExtension());
		switch (dialog.getVariante()) {
		case 0:
			Partie.getInstance().setRegle(new RegleStandard());
			break;
		case 1:
			Partie.getInstance().setRegle(new Variante1());
			break;
		case 2:
			Partie.getInstance().setRegle(new Variante2());
			break;
		default:
			Partie.getInstance().setRegle(new RegleStandard());
			break;
		}
		Joueur ordi1 = new Joueur("ordi1", new StratFacile());
		Joueur ordi2 = new Joueur("ordi2", new StratDifficile());
		 Joueur ordi3 = new Joueur("ordi3", new StratDifficile());
		// Joueur ordi4 = new Joueur("ordi4", new StratFacile());
		//Joueur moi = new Joueur(dialog.getNom());
		//Joueur louis = new Joueur("Louis");

		Partie.getInstance().addJoueur(ordi1);
		Partie.getInstance().addJoueur(ordi2);
		Partie.getInstance().addJoueur(ordi3);
		// Partie.getInstance().addJoueur(ordi4);
		//Partie.getInstance().addJoueur(moi);
		//Partie.getInstance().addJoueur(louis);

	}

	/**
	 * Update J text area.
	 *
	 * @param message the message
	 */
	public static void updateJTextArea(String message) {
		getVuePartie().getLog().append(message + "\n");
		// Pour placer le curseur à la fin, afin d'émuler un scroll automatique
		getVuePartie().getLog().setCaretPosition(getVuePartie().getLog().getDocument().getLength());
	}
	
	/**
	 * Fenetre choix offre.
	 *
	 * @param joueur the joueur
	 */
	public static void fenetreChoixOffre(Joueur joueur) {
		FenetreChoixOffre fenetreChoix = new FenetreChoixOffre(joueur);
		
	}

	/**
	 * Gets the partie.
	 *
	 * @return the partie
	 */
	public static Partie getPartie() {
		return partie;
	}

	/**
	 * Sets the partie.
	 *
	 * @param partie the new partie
	 */
	public static void setPartie(Partie partie) {
		PartieControleur.partie = partie;
	}

	/**
	 * Gets the vue partie.
	 *
	 * @return the vue partie
	 */
	public static VuePartie getVuePartie() {
		return vuePartie;
	}

	/**
	 * Sets the vue partie.
	 *
	 * @param vuePartie the new vue partie
	 */
	public static void setVuePartie(VuePartie vuePartie) {
		PartieControleur.vuePartie = vuePartie;
	}

}
