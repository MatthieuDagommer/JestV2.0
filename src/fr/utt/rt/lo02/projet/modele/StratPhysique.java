package fr.utt.rt.lo02.projet.modele;

import java.util.ArrayList;

import fr.utt.rt.lo02.projet.controleur.PartieControleur;

public class StratPhysique extends StrategieJoueur {

	@Override
	public void faireOffre(Joueur ceJoueur) {
		// TODO Auto-generated method stub

	}

	@Override
	public Joueur choisirCarte(Joueur ceJoueur) {
		ArrayList<Joueur> joueurs = Partie.getInstance().getOffreDispo(ceJoueur);
		Carte c = PartieControleur.choisirCarte(joueurs);
	}
}
