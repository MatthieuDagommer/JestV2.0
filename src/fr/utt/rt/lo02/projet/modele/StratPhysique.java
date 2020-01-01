package fr.utt.rt.lo02.projet.modele;

import java.util.ArrayList;
import java.util.LinkedList;

import fr.utt.rt.lo02.projet.controleur.PartieControleur;

public class StratPhysique extends StrategieJoueur {

	private Joueur victime;

	private boolean carteVisibleVictime;
	
	private Carte cache;

	@Override
	public void faireOffre(Joueur ceJoueur) {
		LinkedList<Carte> main = ceJoueur.getMain();
		PartieControleur.fenetreChoixOffre(ceJoueur);
		while(cache == null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ceJoueur.setOffreCache(cache);
		main.remove(cache);
		ceJoueur.setOffreVisible(main.pop());
		cache = null;
		setChanged();
		notifyObservers(ceJoueur);
	}
	
	@Override
	public void cacher(Carte carte) {
		this.cache = carte;
	}

	@Override
	public Joueur choisirCarte(Joueur ceJoueur) {
		String message = "";
		// ArrayList<Joueur> joueurs = Partie.getInstance().getOffreDispo(ceJoueur);
		while (victime == null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if(this.carteVisibleVictime == true) {
			ceJoueur.addJest(victime.getOffreVisible());
			message = ceJoueur.getNom() + " prend la carte "+victime.getOffreVisible()+" de "+victime.getNom();

			victime.setOffreVisible(null);
			
		} else if (this.carteVisibleVictime == false) {
			ceJoueur.addJest(victime.getOffreCache());
			message = ceJoueur.getNom() + " prend la carte "+victime.getOffreCache()+" de "+victime.getNom();

			victime.setOffreCache(null);
		}
		Joueur j = victime;
		victime = null;
		System.out.println(message);
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

	public void choix(boolean visible, Joueur victime) {

		this.carteVisibleVictime = visible;

		this.victime = victime;
	}



}
