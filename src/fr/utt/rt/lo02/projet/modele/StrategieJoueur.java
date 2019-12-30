package fr.utt.rt.lo02.projet.modele;

public interface StrategieJoueur {
	
	public Joueur choisirCarte(Joueur ceJoueur);
	
	public void faireOffre(Joueur ceJoueur); 
}
