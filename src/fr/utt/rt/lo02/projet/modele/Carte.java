package fr.utt.rt.lo02.projet.modele;

/**
 * Chaque carte est composee d'une valeur (enumeration Valeur)
 * d'une couleur  (enumeration Couleur) et d'un trophee (enumration trophee)
 * Les objets de type Carte sont crees des le debut de la partie via la classe Deck
 */
public class Carte {
	
	/** Attribut qui defini la valeur d'une carte 
	 * les valeurs valides sont celles de l'enumeration Valeur
	 * en cas de valeur null pour la valeur la carte ne pourra pas etre utilise pour la suite */
	private Valeur valeur;
	
	/** Attribut qui defini la couleur d'une carte 
	 * les valeurs valides sont celles de l'enumeration Couleur
	 * en cas de valeur null pour la couleur la carte ne pourra pas etre utilise pour la suite */
	private Couleur couleur;
	
	/** Attribut qui defini le trophee d'une carte 
	 * les valeurs valides sont celles de l'enumeration Trophee
	 */
	private Trophee trophee;
	
	/**
	 * Constructeur de la classe carte prenant en compte les 3 parametres d'une carte 
	 * pour le jeu de Jest (valeur, couleur, trophee).
	 *
	 * @param v definie la Valeur lors de la creation d'une carte pour creer une carte avec l'enumeration Valeur
	 * @param c definie la Couleur lors de la creation d'une carte pour creer une carte avec l'enumeration Couleur
	 * @param t definie le Trophee lors de la creation d'une carte pour creer une carte avec l'enumeration Trophee
	 */
	public Carte(Valeur v, Couleur c, Trophee t) {
		this.valeur = v;
		this.couleur = c;
		this.trophee = t;

	}
	
	/**
	 * Autre constructeur de Carte pour utiliser un entier comme Valeur correspondant à l'indice 
	 * d'une valeur de carte dans l'enumeration Valeur
	 * 
	 *
	 * @param v definie la Valeur à l'aide d'un entier correspondant à l'indice de la valeur dans l'enumeration Valeur
	 * @param c definie la Couleur d'une carte à l'aide de l'enumeration Couleur
	 * @param t definie le Trophee d'une carte à l'aide de l'enumeration Trophee
	 */
	public Carte(int v, Couleur c, Trophee t) {
		this.valeur = Valeur.values()[v];
		this.couleur = c;
		this.trophee = t;

	}
	
	/**
	 * Getter permettant de retourner la Valeur d'une carte.
	 *
	 * @return La valeur d'une carte (valeur de l'enumeration Valeur).
	 */
	public Valeur getValeur() {
		return valeur;
	}
	
	/**
	 * Setter permettant de donnee à une carte sa valeur.
	 *Cette methode ne renvoie rien
	 * @param valeur Prends en paramètre d'entree une Valeur (valeur de l'enumeration Valeur)
	 */
	public void setValeur(Valeur valeur) {
		this.valeur = valeur;
	}
	/**
	 * Getter permettant de retourner la Couleur d'une carte.
	 *
	 * @return La couleur d'une carte (valeur de l'enumeration Couleur)
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
	/**
	 * Setter permettant de donnee à une carte sa Couleur.
	 *Cette methode ne renvoie rien
	 * @param couleur Prends en paramètre d'entree une Couleur(valeur de l'enumeration Couleur).
	 */
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * Getter permettant de retourner le trophee d'une carte.
	 *
	 * @return Le trophee d'une carte d'une valeur de l'enumeration Trophee.
	 */
	public Trophee getTrophee() {
		return trophee;
	}
	
	/**
	 * Setter permettant de donnee à une carte son Trophee.
	 * Cette methode ne renvoie rien
	 * @param trophee Prends en paramètre d'entree un Trophee(valeur de l'enumeration Trophee).
	 */
	public void setTrophee(Trophee trophee) {
		this.trophee = trophee;
	}

	/**
	 * Methode d'affichage d'une carte avec ses differents arguments 
	 * sa Valeur, sa Couleur, son trophee.
	 */
	@Override
	public String toString() {
		return "Carte [valeur=" + valeur + ", couleur=" + couleur + ", trophee=" + trophee + "]\n";
	}
	
	
	
}
