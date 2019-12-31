package fr.utt.rt.lo02.projet.modele;

import java.util.Observable;

public abstract class StrategieJoueur extends Observable {
	
	public abstract Joueur choisirCarte(Joueur ceJoueur);
	
	public abstract void faireOffre(Joueur ceJoueur); 
}
