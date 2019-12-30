package fr.utt.rt.lo02.projet.modele;

import java.util.LinkedList;

public interface Regle {
	
	public int visitCarreau(LinkedList<Carte> jest);
	
	public int visitCoeur(LinkedList<Carte> jest);
	
	public int visitTreflePic(LinkedList<Carte> jest);
	
	public int visitNoir(LinkedList<Carte> jest);
}
