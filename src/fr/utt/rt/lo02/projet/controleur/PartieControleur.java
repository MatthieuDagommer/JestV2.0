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
import fr.utt.rt.lo02.projet.modele.StratFacile;
import fr.utt.rt.lo02.projet.modele.StratPhysique;
import fr.utt.rt.lo02.projet.vue.FenetreChoixOffre;
import fr.utt.rt.lo02.projet.vue.MenuInitialisation;
import fr.utt.rt.lo02.projet.vue.VueJoueur;
import fr.utt.rt.lo02.projet.vue.VuePartie;

public class PartieControleur {

	private static Partie partie;

	private static VuePartie vuePartie;

	private JLabel pioche;

	private LinkedList<JLabel> offres;

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

	public static void initialisationJest() {
		MenuInitialisation dialog = new MenuInitialisation(null, "JEST menu", true);
		dialog.setVisible(true);
		Partie.getInstance().buildJeuDeCarte(dialog.getExtension());
		switch (dialog.getVariante()) {
		case 0:
			Partie.getInstance().setRegle(new RegleStandard());
			break;
		default:
			Partie.getInstance().setRegle(new RegleStandard());
			break;
		}
		Joueur ordi1 = new Joueur("ordi1", new StratFacile());
		Joueur ordi2 = new Joueur("ordi2", new StratFacile());
		// Joueur ordi3 = new Joueur("ordi3", new StratFacile());
		// Joueur ordi4 = new Joueur("ordi4", new StratFacile());
		Joueur moi = new Joueur("moi");

		Partie.getInstance().addJoueur(ordi1);
		Partie.getInstance().addJoueur(ordi2);
		// Partie.getInstance().addJoueur(ordi3);
		// Partie.getInstance().addJoueur(ordi4);
		Partie.getInstance().addJoueur(moi);

	}

	public static void updateJTextArea(String message) {
		getVuePartie().getLog().append(message + "\n");
		// Pour placer le curseur à la fin, afin d'émuler un scroll automatique
		getVuePartie().getLog().setCaretPosition(getVuePartie().getLog().getDocument().getLength());
	}
	
	public static void fenetreChoixOffre(Joueur joueur) {
		FenetreChoixOffre fenetreChoix = new FenetreChoixOffre(joueur);
		
	}

	public static Partie getPartie() {
		return partie;
	}

	public static void setPartie(Partie partie) {
		PartieControleur.partie = partie;
	}

	public static VuePartie getVuePartie() {
		return vuePartie;
	}

	public static void setVuePartie(VuePartie vuePartie) {
		PartieControleur.vuePartie = vuePartie;
	}

}
