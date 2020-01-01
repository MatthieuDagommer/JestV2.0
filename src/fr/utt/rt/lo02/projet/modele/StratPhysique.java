package fr.utt.rt.lo02.projet.modele;

import java.util.ArrayList;
import java.util.LinkedList;

import fr.utt.rt.lo02.projet.controleur.PartieControleur;

public class StratPhysique extends StrategieJoueur {
	
	private Joueur victime;

	@Override
	public void faireOffre(Joueur ceJoueur) {
		LinkedList<Carte> main = ceJoueur.getMain();
		ceJoueur.setOffreCache(main.pop());
		ceJoueur.setOffreVisible(main.pop());
		setChanged();
		notifyObservers(ceJoueur);
	}

	@Override
	public Joueur choisirCarte(Joueur ceJoueur) {
		ArrayList<Joueur> joueurs = Partie.getInstance().getOffreDispo(ceJoueur);
		PartieControleur.choisirCarte(joueurs, ceJoueur);
		while(victime == null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Joueur j = victime;
		victime = null;
		setChanged();
		notifyObservers(j);
		return j;
		
	}
	
	public void setVictime(Joueur joueur) {
		victime = joueur;
	}
	
	public Joueur getVictime() {
		return victime;
	}
	
	
	
}
