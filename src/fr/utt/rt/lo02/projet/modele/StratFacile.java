package fr.utt.rt.lo02.projet.modele;

import java.util.LinkedList;

public class StratFacile implements StrategieJoueur{

	@Override
	public Joueur choisirCarte() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void faireOffre(Joueur ceJoueur) {
		LinkedList<Carte> main = ceJoueur.getMain();
		ceJoueur.setOffreCache(main.pop());
		ceJoueur.setOffreVisible(main.pop());
		
	}



}
