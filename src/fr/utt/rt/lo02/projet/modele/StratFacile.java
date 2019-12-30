package fr.utt.rt.lo02.projet.modele;

import java.util.ArrayList;
import java.util.LinkedList;

public class StratFacile implements StrategieJoueur {

	@Override
	public Joueur choisirCarte(Joueur ceJoueur) {
		ArrayList<Joueur> joueurs = Partie.getInstance().getOffreDispo(ceJoueur);
		Joueur victime = joueurs.get(0);
		ceJoueur.addJest(victime.getOffreVisible());
		victime.setOffreVisible(null);
		return victime;
	}

	@Override
	public void faireOffre(Joueur ceJoueur) {
		LinkedList<Carte> main = ceJoueur.getMain();
		ceJoueur.setOffreCache(main.pop());
		ceJoueur.setOffreVisible(main.pop());

	}

}
