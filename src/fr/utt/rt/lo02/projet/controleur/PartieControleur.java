package fr.utt.rt.lo02.projet.controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JLabel;

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
 * Constructeur du controleur de la partie. 
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
	 * Constructeur du controleur de la partie. On définit tout d'abbord la partie et sa vue.
	 * 
	 *
	 * @param partie    partie que l'on souhaite controler
	 * @param vuePartie Vue de la partie que l'on souhaite controler
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
	 * Cette méthode permet de lancer la fenêtre d'initilisation. On va alors recupérer les informations
	 * rentrées par l'utilisateur afin de définir les règles, l'extension ainsi que le nombre de joueurs de la partie.
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
		switch (dialog.getJoueurP()) {
		case 0:
			break;
		case 1:
			Joueur moi = new Joueur(dialog.getNom());
			Partie.getInstance().addJoueur(moi);
			break;
		case 2:
			Joueur moi2 = new Joueur(dialog.getNom());
			Partie.getInstance().addJoueur(moi2);
			Joueur moi3 = new Joueur("Louis");
			Partie.getInstance().addJoueur(moi3);
			break;
		case 3:
			Joueur moi4 = new Joueur(dialog.getNom());
			Partie.getInstance().addJoueur(moi4);
			Joueur moi5 = new Joueur("Louis");
			Partie.getInstance().addJoueur(moi5);
			Joueur moi6 = new Joueur("Maxime");
			Partie.getInstance().addJoueur(moi6);
			break;
		case 4:
			Joueur moi7 = new Joueur(dialog.getNom());
			Partie.getInstance().addJoueur(moi7);
			Joueur moi8 = new Joueur("Louis");
			Partie.getInstance().addJoueur(moi8);
			Joueur moi9 = new Joueur("Maxime");
			Partie.getInstance().addJoueur(moi9);
			Joueur moi10 = new Joueur("Lauris");
			Partie.getInstance().addJoueur(moi10);
			break;
		default:
			break;
		}
		switch (dialog.getJoueurV()) {
		case 0:
			break;
		case 1:
			Joueur ordi1 = new Joueur("ordi1", new StratFacile());
			Partie.getInstance().addJoueur(ordi1);
			break;
		case 2:
			Joueur ordi2 = new Joueur("ordi1", new StratFacile());
			Partie.getInstance().addJoueur(ordi2);
			Joueur ordi3 = new Joueur("ordi2", new StratDifficile());
			Partie.getInstance().addJoueur(ordi3);
			break;
		case 3:
			Joueur ordi4 = new Joueur("ordi1", new StratFacile());
			Partie.getInstance().addJoueur(ordi4);
			Joueur ordi5 = new Joueur("ordi2", new StratDifficile());
			Partie.getInstance().addJoueur(ordi5);
			Joueur ordi6 = new Joueur("ordi3", new StratDifficile());
			Partie.getInstance().addJoueur(ordi6);
			break;
		case 4:
			Joueur ordi7 = new Joueur("ordi1", new StratFacile());
			Partie.getInstance().addJoueur(ordi7);
			Joueur ordi8 = new Joueur("ordi2", new StratDifficile());
			Partie.getInstance().addJoueur(ordi8);
			Joueur ordi9 = new Joueur("ordi3", new StratDifficile());
			Partie.getInstance().addJoueur(ordi9);
			Joueur ordi10 = new Joueur("ordi4", new StratDifficile());
			Partie.getInstance().addJoueur(ordi10);
			break;
		default:
			break;
		}

	}

	/**
	 * Update J text area.
	 *
	 * @param message the message
	 */
	public static void updateJTextArea(String message) {
		getVuePartie().getLog().append(message + "\n");
		getVuePartie().getLog().setCaretPosition(getVuePartie().getLog().getDocument().getLength());
	}

	/**
	 * Cette méthode permet de définir la fenetre des offres pour un joueur physique rentré en paramètre.
	 *
	 * @param joueur Joueur physique pour lequel on souhaite obtenir la fenetre de choix. Celle-ci affichera les deux cartes 
	 * que le joueur a dans sa main.
	 */
	public static void fenetreChoixOffre(Joueur joueur) {
		FenetreChoixOffre fenetreChoix = new FenetreChoixOffre(joueur);

	}

	/**
	 * Getter de la partie.
	 *
	 * @return the partie partie courante du controleur.
	 */
	public static Partie getPartie() {
		return partie;
	}

	/**
	 * Setter de la partie
	 *
	 * @param partie partie que l'on souhiate définir.
	 */
	public static void setPartie(Partie partie) {
		PartieControleur.partie = partie;
	}

	/**
	 * Getter de la vue de la Partie de type VuePartie.
	 *
	 * @return the vue partie La vue de la partie courante.
	 */
	public static VuePartie getVuePartie() {
		return vuePartie;
	}

	/**
	 * Setter de la vue de la partie.
	 *
	 * @param vuePartie la vue de la partie que l'on souhaite définir
	 */
	public static void setVuePartie(VuePartie vuePartie) {
		PartieControleur.vuePartie = vuePartie;
	}

}
