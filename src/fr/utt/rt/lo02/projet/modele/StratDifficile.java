package fr.utt.rt.lo02.projet.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class StratDifficile extends StrategieJoueur{

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
		
		Iterator<Carte> it = main.iterator();
		Carte c = it.next();
		if(c.getCouleur() == Couleur.PIC || c.getCouleur() == Couleur.TREFLE) {
			Carte c2 = it.next();
			if(c2.getCouleur() == Couleur.PIC || c2.getCouleur() == Couleur.TREFLE) {
				if(c2.getValeur().ordinal() > c.getValeur().ordinal()) {
					ceJoueur.setOffreCache(c2);
					main.remove(c2);
					ceJoueur.setOffreVisible(c);
					main.remove(c);
				} else {
					ceJoueur.setOffreCache(c);
					main.remove(c);
					ceJoueur.setOffreVisible(c2);
					main.remove(c2);
				}
			} else {
				ceJoueur.setOffreCache(c);
				main.remove(c);
				ceJoueur.setOffreVisible(c2);
				main.remove(c2);
			}
			
		} else {
			Carte c2 = it.next();
			if(c2.getCouleur() == Couleur.PIC || c2.getCouleur()==Couleur.TREFLE) {
				ceJoueur.setOffreCache(c2);
				main.remove(c2);
				ceJoueur.setOffreVisible(main.pop());
			}else {
				ceJoueur.setOffreVisible(main.pop());
				ceJoueur.setOffreCache(main.pop());
			}
		}
		
		
		setChanged();
		notifyObservers(ceJoueur);

	}

	@Override
	public void setVictime(Joueur joueur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void choix(boolean b, Joueur joueur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cacher(Carte actuelC) {
		// TODO Auto-generated method stub
		
	}

}
