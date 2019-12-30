package fr.utt.rt.lo02.projet.modele;

public class Carte {
	private Valeur valeur;
	private Couleur couleur;
	private Trophee trophee;
	
	public Carte(Valeur v, Couleur c, Trophee t) {
		this.valeur = v;
		this.couleur = c;
		this.trophee = t;

	}
	
	public Carte(int v, Couleur c, Trophee t) {
		this.valeur = Valeur.values()[v];
		this.couleur = c;
		this.trophee = t;

	}
	
	public Valeur getValeur() {
		return valeur;
	}
	public void setValeur(Valeur valeur) {
		this.valeur = valeur;
	}
	public Couleur getCouleur() {
		return couleur;
	}
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
	public Trophee getTrophee() {
		return trophee;
	}
	public void setTrophee(Trophee trophee) {
		this.trophee = trophee;
	}

	@Override
	public String toString() {
		return "Carte [valeur=" + valeur + ", couleur=" + couleur + ", trophee=" + trophee + "]\n";
	}
	
	
	
}
