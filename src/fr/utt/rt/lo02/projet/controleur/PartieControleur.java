package fr.utt.rt.lo02.projet.controleur;

import fr.utt.rt.lo02.projet.modele.Partie;
import fr.utt.rt.lo02.projet.vue.VuePartie;

public class PartieControleur {

	
	private static Partie partie;
	
	private static VuePartie vuePartie;
	
	public PartieControleur(Partie partie, VuePartie vuePartie) {
		setPartie(partie);
		setVuePartie(vuePartie);
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
