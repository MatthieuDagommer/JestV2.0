package fr.utt.rt.lo02.projet.modele;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Classe permettant la creation d'une pioche pour le jeu de JEST
 * Cette classe est composee d'une liste chainee qui correspond aux cartes de la pioche 
 * et d'un entier (extension) qui correspond au choix de l'extension fait par l'utilisateur
 */
public class Deck {

	
	/** Attribut qui definie un tas de carte comme collection. Cet attribut correspond a  la pioche dans le jeu de JEST
	 * Cet attribut peut contenir de 0 a  21 cartes dans le cas de l'extension de cartes.
	 * En cas de valeur null, soit la pioche n'a pas encore ete cree, soit la partie est terminee */
	private LinkedList<Carte> tasDeCarte;
	
	/** Attribut correspondant au choix de l'extension de carte.
	 * Bornes Valides : 0 pour aucune extension de carte ou 1 pour une extension de carte
	 * Toute autre valeur ne creera pas la pioche voulue composee de 17 cartes sans extension (extension = 0) et de 21 cartes avec extension (extension a  1)
	 *  */
	private int extension;
	
	/**
	 * Constructeur d'un deck qui correspond a  la pioche du JEST
	 * Cette methode permet de cree un deck a  17 cartes (pas d'extension)
	 * ou un deck a  21 cartes avec extension
	 * @param extension a  0 pour aucune extension ou a  1 pour une extension de carte.
	 * Pour construire une pioche, on parcours toutes les couleurs de l'enumeration, puis toutes les valeurs de l'enumeration
	 * et on ajoute le trophee correspondant. 
	 */
	public Deck(int extension) {
		this.extension = extension;
		tasDeCarte = new LinkedList<Carte>();
		Trophee[] t = Trophee.values();
		int it = 0;

		for (Couleur c : Couleur.values()) { // pour toutes les couleurs
			if (extension == 1) {
				for (Valeur v : Valeur.values()) { // pour toutes les valeurs
					if (v != Valeur.JOKER) {
						if (c != Couleur.COEUR && v != Valeur.SIX) { // les coeurs ont le meme trophee
							it++;
						}
						Carte carte = new Carte(v, c, t[it]); // creation de la carte avec le trophee associe
						tasDeCarte.add(carte);
						// System.out.println(tasDeCarte);
					}
				}
			} else if (extension == 0) {
				for (int v = 0; v < 5; v++) { // pour toutes les valeurs
					if (v != 0) {
						if (c != Couleur.COEUR) { // les coeurs ont le meme trophee
							it++;
						}
						Carte carte = new Carte(v, c, t[it]); // creation de la carte avec le trophee associe
						tasDeCarte.add(carte);
					}
				}
			}
		}
		tasDeCarte.add(Joker.getInstance());
	}
	
	/**
	 * Methode permettant de savoir si la pioche est vide	 *
	 * @return vraie si la pioche est vide et faux sinon
	 */
	public boolean estVide() {
		return tasDeCarte.isEmpty();
	}

	/**
	 * Methode permettant de piocher une carte en haut du paquet 
	 * gra¢ce a  la methode pop de la collection liste chainee
	 *
	 * @return la carte du haut du paquet
	 */
	public Carte piocherCarte() {
		return tasDeCarte.pop();
	}
	
	/**
	 * Methode qui permet de melanger la pioche.
	 * Cette methode ne renvoi rien et ne prends rien en paramaetre.
	 */
	public void melanger() {
		Collections.shuffle(tasDeCarte);
	}
	
	/**
	 * Getter qui permet d'obtenir l'extension d'une pioche
	 *
	 * @return un entier a  0 pour aucune extension et un entier a  1 pour une extension de carte
	 * Pour toute autre valeur, le deck n'est pas bien defini pour notre jeu de JEST
	 */
	public int getExtension() {
		return extension;
	}

	/**
	 * Setter qui permet de definir la valeur de l'extension de jeu
	 * Cette valeur est un entier a  0 si il n'y a pas d'extension et un entier a  1 si il y a une extension
	 * Toute autre valeur n'est pas admise dans notre jeu de JEST
	 * @param extension Entrer 0 pour aucune extension et 1 pour une extension
	 */
	public void setExtension(int extension) {
		this.extension = extension;
	}

	/**
	 * Getter qui permet d'obtenir la pioche sous forme de liste chainee
	 *
	 * @return Cette methode retourne la picohe sous forme de liste chainee qu'elle soient vide ou non
	 */
	public LinkedList<Carte> getTasDeCarte() {
		return tasDeCarte;
	}

	/**
	 * Setter qui permet de definir un tas de carte sous forme de liste chainee
	 *
	 * @param tasDeCarte Une liste chainee de carte qui correspond a  la liste des cartes.
	 */
	public void setTasDeCarte(LinkedList<Carte> tasDeCarte) {
		this.tasDeCarte = tasDeCarte;
	}
	
	/**
	 * methode d'affichage de la pioche sous forme de liste chainee de carte
	 *
	 * @return Les cartes contenues dans la pioche
	 */
	public String toString() {
		return tasDeCarte.toString();
	}
	
	/**
	 * Methode permettant d'ajouter une carte a  l'attribut tas de carte qui correspond a  la pioche
	 *
	 * @param c, Carte (objet de la classe carte) que l'on souhaite ajouter a  la liste chainee.
	 */
	public void addCarte(Carte c) {
		this.tasDeCarte.add(c);
	}
	

	
}
