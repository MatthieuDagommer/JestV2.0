package fr.utt.rt.lo02.projet.modele;

public class Joker extends Carte {
	private static Joker INSTANCE = new Joker(Valeur.JOKER, Couleur.CARREAU, Trophee.bestJest);

	private Joker(Valeur valeur, Couleur couleur, Trophee trophee) {
		super(valeur, couleur, trophee);
	}

	public static Joker getInstance() {
		return INSTANCE;
	}
}
