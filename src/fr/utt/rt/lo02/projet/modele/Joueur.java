package fr.utt.rt.lo02.projet.modele;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * Classe publique qui definie un joueur (virtuel ou reel). Elle herite de la
 * classe observable dans le cadre du patron MVC.
 */
public class Joueur extends Observable {

	/** Cet attribut modelise le nom d'un joueur Il represente le nom du joueur. */
	private String nom;

	/** cet attribut constitue les cartes contenues dans la main d'un joueur La liste peut être nulle si il n'a plus de carte, contenir une ou deux cartes de types Carte. */
	private LinkedList<Carte> main;

	/**
	 * Cette carte definie l'offre visible d'un joueur sur le plateau de jeu. Elle
	 * est de type carte et en cas de valeur nulle cela signifie que le joueur n'a
	 * pas d'offre visible a presenter aux autres joueurs
	 */
	private Carte offreVisible;

	/**
	 * Cette carte definie l'offre cachee d'un joueur sur le plateau de jeu. Elle
	 * est de type carte et en cas de valeur nulle cela signifie que le joueur n'a
	 * pas d'offre cachee a presenter aux autres joueurs
	 */
	private Carte offreCache;

	/**
	 * Cette liste chainee correspond a un tas de carte representant le JEST d'un
	 * joueur Elle est consitutee de 0 carte avant le premier tour de jeu et ensuite
	 * elle est remplie d'une carte a chaque tour et de deux cartes au dernier tour.
	 */
	private LinkedList<Carte> jest;

	/**
	 * Cette liste chainee representer le JEST du joueur a la fin du jeu avec
	 * eventuellement les trophees que le joueur a recuperer apres le premier
	 * comptage de point et en fonction de ces cartes Il contient au minimum le JEST
	 * du joueur a la fin du jeu et eventuellement les trophees recuperes, il est
	 * NULL jusqu'au premier comptage des points du JEST.
	 */
	private LinkedList<Carte> jestAvecTrophes;

	/**
	 * Attribut de type Strategie qui definie la strategie d'un joueur. Les
	 * startegies possibles sont la strategie facile ou difficile pour un joueur
	 * virtuel ou la strategie Physique pour un joueur physique. Pour un deroulement
	 * normal du jeu, chaque joueur doit avoir une startegie differente de null des
	 * la creation de celui-ci.
	 */
	private StrategieJoueur strategie;

	/**
	 * Cette entier correspond au score d'un joueur. Il est null jusqu'a la phase de
	 * distribution des trophees.
	 */
	private int score;

	/**
	 * Booleen qui determine si dans un tour, un joueur a deja jouer, si c'est le
	 * cas. Il est null avant le premier tour de jeu puis il est remis a faux au
	 * debut de chaque tour et a vrai pour chaque tour quand un joueur a joueur.
	 */
	private boolean aJouer;

	/** Nombre de joueur dans la partie courrante Ce nombre varie entre 3 et 4 pour le jeu de JEST. */
	public static int NB_JOUEURS = 0;

	/**
	 * Methode issue du design pattern Visitor qui va permettre a la partie de
	 * visiter et donc d'obtenir les cartes du jest d'un joueur afin d'effectuer les
	 * operations qu'elles souhaites sur les cartes du JEST sans les modifier pour
	 * obtenir le score du joueur qu'elle va renvoyer et affecter a l'attribut
	 * score.
	 *
	 * @param p the p
	 */
	public void accept(Partie p) {
		this.score = p.visitJest(this.jest);
	}

	/**
	 * Constructeur d'un joueur physique avec une strategie physique definie de
	 * base. On cree des listes chainees vides pour le jest, la main et le jest avec
	 * les trophees lors de la creation d'un joueurs qui vont se remplir au fur et a
	 * mesure des tours. On incremente la variable du nombre de joueur lors de la
	 * creation d'un nouveau joueur
	 *
	 * @param nom le nom du joueur physique
	 */
	public Joueur(String nom) {
		this.nom = nom;
		this.strategie = new StratPhysique();
		this.jest = new LinkedList<Carte>();
		this.jestAvecTrophes = new LinkedList<Carte>();
		this.main = new LinkedList<Carte>();
		Joueur.NB_JOUEURS++;
	}

	/**
	 * Constructeur d'un joueur virtuel avec son nom et sa strategie (soit facile,
	 * soit difficile) On cree des listes chainees vides pour le jest, la main et le
	 * jest avec les trophees lors de la creation d'un joueurs qui vont se remplir
	 * au fur et a mesure des tours. On incremente la variable du nombre de joueur
	 * lors de la creation d'un nouveau joueur.
	 *
	 * @param nom       le nom du joueur virtuel
	 * @param strategie strategie de type StrategieJoueur qui definie la strategie
	 *                  du joueur virtuel (soit facile/ soit difficile)
	 */
	public Joueur(String nom, StrategieJoueur strategie) {
		this.nom = nom;
		this.strategie = strategie;
		this.jest = new LinkedList<Carte>();
		this.jestAvecTrophes = new LinkedList<Carte>();
		this.main = new LinkedList<Carte>();
		Joueur.NB_JOUEURS++;
	}

	/**
	 * Cette methode utilisee pour la distribution des trophees prends en parametre
	 * une Valeur de carte (enumeration valeur) Elle renvoi un entier qui correspond
	 * au nombres de cartes de cette valeur dans la jest du joueur sur lequel on
	 * applique la methode. *
	 * 
	 * @param valeur Valeur dont on souhaite connaître le nombre de carte dans le
	 *               Jest du Joueur
	 * @return nbValeur (entier) qui correspond au nombre de carte de la Valeur
	 *         donnee en parametre dans le JEST du joueur.
	 */
	public int plusValeur(Valeur valeur) {
		int nbValeur = 0;
		Iterator<Carte> it = jest.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if (c.getValeur() == valeur) {
				nbValeur++;
			}
		}
		return nbValeur;
	}

	/**
	 * Methode qui renvoi un entier correspondant a la "force" d'une couleur pour
	 * une valeur donnee.
	 * 
	 * @param valeur Valeur dont l'on souhaite avoir la carte ayant la plus grande "force"
	 *               de couleur du Jest du joueur
	 * @return Entier qui correspond a la force de la carte la plus elevee pour une
	 *         valeur donnee La force de couleur pour une valeur donnee est de 0
	 *         pour le coeur,1 pour le carreau, 2 pour le trefle, et 3 pour le Pic.
	 */
	public int bestCouleur(Valeur valeur) {
		int valeurCouleur = 0;
		Iterator<Carte> it = jest.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if (c.getValeur() == valeur && c.getCouleur().ordinal() > valeurCouleur) {
				valeurCouleur = c.getCouleur().ordinal();
			}
		}
		return valeurCouleur;
	}

	/**
	 * Methode qui renvoi la plus grande valeur de carte d'un Jest pour une couleur
	 * donnee en parametre couleur de type Couleur indique pour quelle couleur on
	 * souhaite connaître la carte qui a la plus grande valeur dans le JEST du
	 * joueur Si le joueur n'a qu'un as pour une couleur donnee, son as vaudra 5.
	 * Sinon la plus grande valeur pour une couleur donnee en parametre sera celle
	 * de la carte ayant la plus haute valeur dans le JEST ou 0 si il n'a pas de
	 * carte de cette couleur.
	 * 
	 * @param couleur Couleur dans laquelle on veux la plus grande valeur
	 * 
	 * @return entier de 0 a 6 qui correspond a la plus haute valeur dans le JEST
	 *         pour une couleur donnee
	 */
	public int highestValeurInCouleur(Couleur couleur) {
		int valeur = 0, nbCarte = 0;
		boolean as = false;
		Iterator<Carte> it = jest.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if (c.getCouleur() == couleur) {
				nbCarte++;
				if (c.getValeur().ordinal() > valeur) {
					valeur = c.getValeur().ordinal();
				}
				if (c.getValeur() == Valeur.AS) {
					as = true;
				}
			}
		}
		if (as && nbCarte == 1) {
			valeur = 5;
		}
		return valeur;
	}

	/**
	 * En cas d'�galit� dans le comptage des point du Jest, on cherche la carte la plus forte en valeur et couleur combinne
	 *
	 * @return La carte la plus forte
	 */
	public Carte ComparaisonJest() {
		Iterator<Carte> it = jest.iterator();
		Carte bestCard = it.next();
		int valeurBest = bestCard.getValeur().ordinal();
		if (this.highestValeurInCouleur(bestCard.getCouleur()) == 5) {
			valeurBest = 5;
		}
		while (it.hasNext()) {
			Carte carte = it.next();
			int valeurCarte = carte.getValeur().ordinal();
			if (this.highestValeurInCouleur(carte.getCouleur()) == 5) {
				valeurCarte = 5;
			}
			if (valeurCarte > valeurBest) {
				bestCard = carte;
				valeurBest = valeurCarte;
			}
			if (valeurCarte == valeurBest) {
				if (carte.getCouleur().ordinal() > bestCard.getCouleur().ordinal()) {
					bestCard = carte;
				}
			}
		}
		return bestCard;
	}

	/**
	 * Methode qui renvoi la plus basse valeur de carte d'un Jest pour une couleur
	 * donnee en parametre couleur de type Couleur indique pour quelle couleur on
	 * souhaite connaître la carte qui a la plus basse valeur dans le JEST du
	 * joueur Si le joueur n'a qu'un as pour une couleur donnee, son as vaudra 5.
	 * Sinon la plus grande valeur pour une couleur donnee en parametre sera celle
	 * de la carte ayant la plus haute valeur dans le JEST ou 0 si il n'a pas de
	 * carte de cette couleur.
	 * 
	 * @param couleur Couleur dont on veut la plus petite valeur
	 * 
	 * @return entier de 0 a 6 qui correspond a la plus haute valeur dans le JEST
	 *         pour une couleur donnee
	 */
	public int lowestValeurInCouleur(Couleur couleur) {
		int valeur = 6, nbCarte = 0;
		boolean as = false;
		Iterator<Carte> it = jest.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if (c.getCouleur() == couleur) {
				nbCarte++;
				if (c.getValeur().ordinal() < valeur && c.getValeur() != Valeur.JOKER) {
					valeur = c.getValeur().ordinal();
				}
				if (c.getValeur() == Valeur.AS) {
					as = true;
				}
			}
		}
		if (as && nbCarte > 1) {
			return 1;
		} else {
			return valeur;
		}
	}

	/**
	 * Getter de la liste chainee de carte du Jest avec les eventuels trophees d'un
	 * joueur.
	 *
	 * @return le Jest avec eventuellement les trophees d'un joueur
	 */
	public LinkedList<Carte> getJestAvecTrophes() {
		return jestAvecTrophes;
	}

	/**
	 * Setter qui permet de definir le Jest avec les eventuels trophees d'un joueur.
	 *
	 * @param jestAvecTrophes the new jest avec trophes
	 */
	public void setJestAvecTrophes(LinkedList<Carte> jestAvecTrophes) {
		this.jestAvecTrophes = jestAvecTrophes;
	}

	/**
	 * Methode qui permet a un joueur de choisir la carte qu'il souhaite ajouter a
	 * son Jest via la methode choisir Carte de sa strategie.
	 * 
	 * @return le joueur chez qui le joueur courant a pris la carte.
	 */
	public Joueur jouer() {
		this.aJouer = true;
		return strategie.choisirCarte(this);
	}

	/**
	 * Premet au joueur de faire une offre selon qu'il soit joueur physique ou
	 * virtuel a l'aide la methode faireOffre.
	 */
	public void faireOffre() {
		strategie.faireOffre(this);
	}

	/**
	 * Methode permettant d'ajouter une carte au Jest du joueur.
	 *
	 * @param c Carte que l'on souhaite ajouter au jest du joueur
	 */
	public void addJest(Carte c) {
		this.jest.add(c);
	}

	/**
	 * Methode qui permer d'ajouter une carte passee en parametre a la liste chainee
	 * de carte qui correspond a la main du joueur.
	 *
	 * @param c Carte que l'on souhaite ajouter a la main du joueur.
	 */
	public void addMain(Carte c) {
		this.main.add(c);
	}

	/**
	 * Methode qui permet d'ajouter une carte au jest complet du joueur (Jest et
	 * eventuellement les trophees).
	 *
	 * @param c Carte que l'on souhaite ajouter au Jest complet du joueur.
	 */
	public void addJestAvecTrophes(Carte c) {
		this.jestAvecTrophes.add(c);
	}

	/**
	 * Getter qui permet d'obtenir le score d'un joueur a tout moment.
	 *
	 * @return un entier qui correspond au score du joueur
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Setter qui permet de definir le score du joueur.
	 *
	 * @param score un entier qui permet de definir le score du joueur
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Getter qui permet d'obtenir le nom du joueur.
	 *
	 * @return le nom du joueur
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter qui definie le nom du joueur.
	 *
	 * @param nom du joueur
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter de la main du joueur qui permet d'obtenir les cartes dans le main du
	 * joueur Cette liste chainee peut être eventuellement null ou contenir jusqu'a
	 * 2 cartes.
	 *
	 * @return Liste chainee des cartes contenues dans la main du joueur
	 */
	public LinkedList<Carte> getMain() {
		return main;
	}

	/**
	 * Setter de la main du joueur.
	 *
	 * @param main liste chainee de cartes qui definie ce que le joueur a dans sa
	 *             main
	 */
	public void setMain(LinkedList<Carte> main) {
		this.main = main;
	}

	/**
	 * Gets the offre visible.
	 *
	 * @return the offre visible
	 */
	public Carte getOffreVisible() {
		return offreVisible;
	}

	/**
	 * Sets the offre visible.
	 *
	 * @param offreVisible the new offre visible
	 */
	public void setOffreVisible(Carte offreVisible) {
		this.offreVisible = offreVisible;
	}

	/**
	 * Getter de l'offre cachee d'un joueur.
	 *
	 * @return la carte de l'offre cachee du joueur ou null si il n'a pas d'offre
	 *         cachee.
	 */
	public Carte getOffreCache() {
		return offreCache;
	}

	/**
	 * Setter de la carte qui correspond a l'offre cachee du joueur. Null si pas
	 * d'offre cachee, diff de null si une offre cachee
	 * 
	 * @param offreCache la carte que le joueur souhiate cachee dans un tour donnee
	 */
	public void setOffreCache(Carte offreCache) {
		this.offreCache = offreCache;
	}

	/**
	 * Getter du Jest du Joueur.
	 *
	 * @return Jest du joueur sous forme de liste chainee de carte
	 */
	public LinkedList<Carte> getJest() {
		return jest;
	}

	/**
	 * Setter du Jest du joueur.
	 *
	 * @param jest de type liste chainee de Cartes representant le Jest du joueur.
	 */
	public void setJest(LinkedList<Carte> jest) {
		this.jest = jest;
	}

	/**
	 * Getter qui permet de connaître la strategie du joueur.
	 *
	 * @return la strategie du joueur sur lequel on effectue la methode.
	 */
	public StrategieJoueur getStrategie() {
		return strategie;
	}

	/**
	 * Setter de la strategie d'un joueur.
	 *
	 * @param strategie permet de definir la strategie d'un joueur (parametre de
	 *                  type strategie). Si null, le joueur ne pourra pas jouer
	 */
	public void setStrategie(StrategieJoueur strategie) {
		this.strategie = strategie;
	}

	/**
	 * Methode qui permet de connaître dans chaque tour a tout moment si un joueur
	 * a jouer.
	 *
	 * @return le booleen est a "true" si dans un tour, le joueur a jouer et a
	 *         "false" si dans un tour le joueur n'a pas encore jouer.
	 */
	public boolean isaJouer() {
		return aJouer;
	}

	/**
	 * Setter qui permet de definir dans un tour si le joueur a jouer ou pas encore.
	 *
	 * @param aJouer est un booleen qui est place "true" si le joueur a jouer et
	 *               "false" si le joueur n'a pas encore jouer dans chaque tour.
	 */
	public void setaJouer(boolean aJouer) {
		this.aJouer = aJouer;
	}

	/**
	 * Methode qui renvoi une chaine de caractere contenant les differents attributs
	 * d'un joueur Cela coprends, son nom, sa main (une liste chainee de carte), son
	 * offre visible (une carte ou rien), son offre cachee (une carte ou rien), son
	 * JEST avec ou sans trophee (liste chainee null ou contenant des cartes de type
	 * Carte), son score (un entier), si le joueur
	 * a jouer (booleen).
	 * 
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", main=" + main + ", offreVisible=" + offreVisible + ", offreCache=" + offreCache
				+ ", jest=" + jest + ", jestAvecTrophes=" + jestAvecTrophes + ", score="
				+ score + ", aJouer=" + aJouer + "]\n";
	}

}
