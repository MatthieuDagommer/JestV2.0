package fr.utt.rt.lo02.projet.modele;

import java.util.ArrayList;
import java.util.LinkedList;

public class StratFacile extends StrategieJoueur {

	@Override
	public Joueur choisirCarte(Joueur ceJoueur) {
		String message = "";
		ArrayList<Joueur> joueurs = Partie.getInstance().getOffreDispo(ceJoueur);
		Joueur victime = joueurs.get(0);
		ceJoueur.addJest(victime.getOffreVisible());
		message = ceJoueur.getNom() + " prend la carte "+victime.getOffreVisible()+" de "+victime.getNom();
		System.out.println(message);
		victime.setOffreVisible(null);
		setChanged();
		notifyObservers(victime);

		return victime;
	}

	@Override
	public void faireOffre(Joueur ceJoueur) {
		LinkedList<Carte> main = ceJoueur.getMain();
		ceJoueur.setOffreCache(main.pop());
		ceJoueur.setOffreVisible(main.pop());
		setChanged();
		notifyObservers(ceJoueur);

	}

}
