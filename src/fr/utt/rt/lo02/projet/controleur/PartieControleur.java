package fr.utt.rt.lo02.projet.controleur;



import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import fr.utt.rt.lo02.projet.modele.Carte;
import fr.utt.rt.lo02.projet.modele.Joueur;
import fr.utt.rt.lo02.projet.modele.Partie;
import fr.utt.rt.lo02.projet.modele.RegleStandard;
import fr.utt.rt.lo02.projet.modele.StratFacile;
import fr.utt.rt.lo02.projet.vue.MenuInitialisation;
import fr.utt.rt.lo02.projet.vue.VuePartie;

public class PartieControleur implements MouseListener {

	
	private static Partie partie;
	
	private static VuePartie vuePartie;
	
	public PartieControleur(Partie partie, VuePartie vuePartie) {
		setPartie(partie);
		setVuePartie(vuePartie);
		
		
	}
	
	
	public static void initialisationJest() {
		MenuInitialisation dialog = new MenuInitialisation(null, "JEST menu", true);
		dialog.setVisible(true);
		Partie.getInstance().buildJeuDeCarte(dialog.getExtension());
		switch (dialog.getVariante()) {
		case 0 :
			Partie.getInstance().setRegle(new RegleStandard());
			break;
		 default :
			 Partie.getInstance().setRegle(new RegleStandard());
			break;
		}
		Joueur ordi1 = new Joueur("ordi1", new StratFacile());
		Joueur ordi2 = new Joueur("ordi2", new StratFacile());
		Joueur ordi3 = new Joueur("ordi3", new StratFacile());
		//Joueur moi = new Joueur("moi");

		Partie.getInstance().addJoueur(ordi1);
		Partie.getInstance().addJoueur(ordi2);
		Partie.getInstance().addJoueur(ordi3);
		//Partie.getInstance().addJoueur(moi);
		
	}
	
	public static void choisirCarte(ArrayList<Joueur> joueurs, Joueur ceJoueur) {
		vuePartie.ajouterCarteClicable(joueurs, ceJoueur);
	}
	
	public static void updateJTextArea(String message) {
		getVuePartie().getLog().append(message + "\n");
		//Pour placer le curseur à la fin, afin d'émuler un scroll automatique
		getVuePartie().getLog().setCaretPosition(getVuePartie().getLog().getDocument().getLength());
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


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
