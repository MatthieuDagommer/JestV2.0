package fr.utt.rt.lo02.projet.modele;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * The Class Joueur.
 */
public class Joueur extends Observable {

	/**
	 * Cet attribut modélise le nom d'un joueur Il représente le nom du joueur
	 */
	private String nom;

	/**
	 * cet attribut constitue les cartes contenues dans la main d'un joueur La liste
	 * peut être nulle si il n'a plus de carte, contenir une ou deux cartes de types
	 * Carte
	 */
	private LinkedList<Carte> main;

	/**
	 * Cette carte définie l'offre visible d'un joueur sur le plateau de jeu. Elle
	 * est de type carte et en cas de valeur nulle cela signifie que le joueur n'a
	 * pas d'offre visible à présenter aux autres joueurs
	 */
	private Carte offreVisible;

	/**
	 * Cette carte définie l'offre cachée d'un joueur sur le plateau de jeu. Elle
	 * est de type carte et en cas de valeur nulle cela signifie que le joueur n'a
	 * pas d'offre cachée à présenter aux autres joueurs
	 */
	private Carte offreCache;

	/**
	 * Cette liste chainée correspond à un tas de carte représentant le JEST d'un
	 * joueur Elle est consitutée de 0 carte avant le premier tour de jeu et ensuite
	 * elle est remplie d'une carte à chaque tour et de deux cartes au dernier tour.
	 */
	private LinkedList<Carte> jest;

	/**
	 * Cette liste chainée représenter le JEST du joueur à la fin du jeu avec
	 * éventuellement les trophées que le joueur à récupérer après le premier
	 * comptage de point et en fonction de ces cartes Il contient au minimum le JEST
	 * du joueur à la fin du jeu et éventuellement les trophées récupérés, il est
	 * NULL jusqu'au premier comptage des points du JEST.
	 */
	private LinkedList<Carte> jestAvecTrophes;

	/**
	 * Attribut de type Stratégie qui définie la stratégie d'un joueur. Les
	 * startégies possibles sont la stratégie facile ou difficile pour un joueur
	 * virtuel ou la stratégie Physique pour un joueur physique. Pour un déroulement
	 * normal du jeu, chaque joueur doit avoir une startégie différente de nulle dès
	 * la création de celui-ci.
	 */
	private StrategieJoueur strategie;

	/**
	 * Cette entier correspond au score d'un joueur. Il est null jusqu'à la phase de
	 * distribution des trophées, une fois les scores comptés une première fois.
	 */
	private int score;

	/**
	 * Booléen qui détermine si dans un tour, un joueur à déjà jouer, si c'est le
	 * cas. Il est null avant le premier tour de jeu puis il est remis à faux au
	 * début de chaque tour et à vrai pour chaque tour quand un joueur à joueur.
	 */
	private boolean aJouer;

	/**
	 * Nombre de joueur dans la partie courrante Ce nombre varie entre 3 et 4 pour
	 * le jeu de JEST
	 */
	public static int NB_JOUEURS = 0;

	/**
	 * Méthode issue du design pattern Visitor qui va permettre à la partie de
	 * visiter et donc d'obtenir les cartes du jest d'un joueur afin d'effectuer les
	 * opérations qu'elles souhaites sur les cartes du JEST sans les modifier pour
	 * obtenir le score du joueur qu'elle va renvoyer et affecter à l'attribut
	 * score.
	 *
	 * @param p, partie qui est le "moteur" du jeu.
	 */
	public void accept(Partie p) {
		this.score = p.visitJest(this.jest);
	}

	/**
	 * Constructeur d'un joueur physique avec une stratégie physique définie de
	 * base. On crée des listes chainées vides pour le jest, la main et le jest avec
	 * les trophées lors de la création d'un joueurs qui vont se remplir au fur et a
	 * mesure des tours. On incrémente la variable du nombre de joueur lors de la
	 * création d'un nouveau joueur
	 *
	 * @param nom the nom
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
	 * Constructeur d'un joueur virtuel avec son nom et sa stratégie (soit facile,
	 * soit difficile) On crée des listes chainées vides pour le jest, la main et le
	 * jest avec les trophées lors de la création d'un joueurs qui vont se remplir
	 * au fur et a mesure des tours. On incrémente la variable du nombre de joueur
	 * lors de la création d'un nouveau joueur.
	 *
	 * @param nom       généralement une suite de caractère qui correspond au nom du
	 *                  joueur
	 * @param stratégie de type StratégieJoueur qui définie la stratégie du joueur
	 *                  virtuel (soit facile/ soit difficile)
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
	 * Cette méthode utilisée pour la distribution des trophées prends en paramètre
	 * une Valeur de carte (énumération valeur) Elle renvoi un entier qui correspond
	 * au nombres de cartes de cette valeur dans la jest du joueur sur lequel on
	 * applique la méthode. *
	 * 
	 * @param valeur Valeur dont on souhaite connaître le nombre de carte dans le
	 *               Jest du Joueur
	 * @return nbValeur (entier) qui correspond au nombre de carte de la Valeur
	 *         donnée en paramètre dans le JEST du joueur.
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
	 * Méthode qui renvoi un entier correspondant à la "force" d'une couleur pour
	 * une valeur donnée.
	 * 
	 * @param valeur dont l'on souhiate avoir la carte ayant la plus grande "force"
	 *               de couleur du Jest du joueur
	 * @return Entier qui correspond à la force de la carte la plus élevée pour une
	 *         valeur donnée La force de couleur pour une valeur donnée est de 0
	 *         pour le coeur,1 pour le carreau, 2 pour le trèfle, et 3 pour le Pic.
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
	 * Méthode qui renvoi la plus grande valeur de carte d'un Jest pour une couleur
	 * donnée en paramètre couleur de type Couleur indique pour quelle couleur on
	 * souhaite connaître la carte qui à la plus grande valeur dans le JEST du
	 * joueur Si le joueur n'a qu'un as pour une couleur donnée, son as vaudra 5.
	 * Sinon la plus grande valeur pour une couleur donnée en paramètre sera celle
	 * de la carte ayant la plus haute valeur dans le JEST ou 0 si il n'a pas de
	 * carte de cette couleur.
	 * 
	 * @return entier de 0 à 6 qui correspond à la plus haute valeur dans le JEST
	 *         pour une couleur donnée
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
	 * Méthode qui renvoi la plus basse valeur de carte d'un Jest pour une couleur
	 * donnée en paramètre couleur de type Couleur indique pour quelle couleur on
	 * souhaite connaître la carte qui à la plus basse valeur dans le JEST du joueur
	 * Si le joueur n'a qu'un as pour une couleur donnée, son as vaudra 5. Sinon la
	 * plus grande valeur pour une couleur donnée en paramètre sera celle de la
	 * carte ayant la plus haute valeur dans le JEST ou 0 si il n'a pas de carte de
	 * cette couleur.
	 * 
	 * @return entier de 0 à 6 qui correspond à la plus haute valeur dans le JEST
	 *         pour une couleur donnée
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
	 * Getter de la liste chainée de carte du Jest avec les éventuels trophées d'un
	 * joueur
	 *
	 * @return le Jest avec éventuellement les trophées d'un joueur
	 */
	public LinkedList<Carte> getJestAvecTrophes() {
		return jestAvecTrophes;
	}

	/**
	 * Setter qui permet de définir le Jest avec les éventuels trophées d'un joueur
	 *
	 * @param jestAvecTrophes, liste chainée contenant les cartes du Jest du joueur
	 *                         avec éventuellement les trophées qu'il a récupérer
	 */
	public void setJestAvecTrophes(LinkedList<Carte> jestAvecTrophes) {
		this.jestAvecTrophes = jestAvecTrophes;
	}

	/**
	 * Méthode qui permet à un joueur de choisir la carte qu'il souhaite ajouter à
	 * son Jest via la méthode choisir Carte de sa stratégie.
	 * 
	 * @return le joueur chez qui le joueur courant à pris la carte.
	 */
	public Joueur jouer() {
		this.aJouer = true;
		return strategie.choisirCarte(this);
	}

	/**
	 * Premet au joueur de faire une offre selon qu'il soit joueur physique ou
	 * virtuel à l'aide la méthode faireOffre
	 */
	public void faireOffre() {
		strategie.faireOffre(this);
	}

	/**
	 * Méthode permettant d'ajouter une carte au Jest du joueur
	 *
	 * @param c Carte que l'on souhaite ajouter au jest du joueur
	 */
	public void addJest(Carte c) {
		this.jest.add(c);
	}

	/**
	 * Méthode qui permer d'ajouter une carte passée en paramètre à la liste chainée
	 * de carte qui correspond à la main du joueur.
	 *
	 * @param c Carte que l'on souhaite ajouter à la main du joueur.
	 */
	public void addMain(Carte c) {
		this.main.add(c);
	}

	/**
	 * Méthode qui permet d'ajouter une carte au jest complet du joueur (Jest et
	 * éventuellement les trophées)
	 *
	 * @param c Carte que l'on souhaite ajouter au Jest complet du joueur.
	 */
	public void addJestAvecTrophes(Carte c) {
		this.jestAvecTrophes.add(c);
	}

	/**
	 * Getter qui permet d'obtenir le score d'un joueur à tout moment
	 *
	 * @return un entier qui correspond au score du joueur
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Setter qui permet de définir le score du joueur
	 *
	 * @param un entier qui permet de définir le score du joueur
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Getter qui permet d'obtenir le nom du joueur
	 *
	 * @return le nom du joueur
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter qui définie le nom du joueur
	 *
	 * @param nom du joueur
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter de la main du joueur qui permet d'obtenir les cartes dans le main du
	 * joueur Cette liste chainée peut être éventuellement null ou contenir jusqu'à
	 * 2 cartes
	 *
	 * @return Liste chainée des cartes contenues dans la main du joueur
	 */
	public LinkedList<Carte> getMain() {
		return main;
	}

	/**
	 * Setter de la main du joueur
	 *
	 * @param liste chainée de cartes qui définie ce que le joueur a dans sa main
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
	 * Getter de l'offre cachée d'un joueur
	 *
	 * @return la carte de l'offre cachée du joueur ou null si il n'a pas d'offre
	 *         cachée.
	 */
	public Carte getOffreCache() {
		return offreCache;
	}

	/**
	 * Setter de la carte qui correspond à l'offre cachée du joueur. Null si pas
	 * d'offre cachée, diff de null si une offre cachée
	 * 
	 * @param la carte que le joueur souhiate cachée dans un tour donnée
	 */
	public void setOffreCache(Carte offreCache) {
		this.offreCache = offreCache;
	}

	/**
	 * Getter du Jest du Joueur
	 *
	 * @return Jest du joueur sous forme de liste chainée de carte
	 */
	public LinkedList<Carte> getJest() {
		return jest;
	}

	/**
	 * Setter du Jest du joueur
	 *
	 * @param jest de type liste chainée de Cartes représentant le Jest du joueur.
	 */
	public void setJest(LinkedList<Carte> jest) {
		this.jest = jest;
	}

	/**
	 * Getter qui permet de connaître la stratégie du joueur.
	 *
	 * @return la stratégie du joueur sur lequel on effectue la méthode.
	 */
	public StrategieJoueur getStrategie() {
		return strategie;
	}

	/**
	 * Setter de la stratégie d'un joueur
	 *
	 * @param strategie permet de définir la stratégie d'un joueur (paramètre de
	 *                  type stratégie). Si null, le joueur ne pourra pas jouer
	 */
	public void setStrategie(StrategieJoueur strategie) {
		this.strategie = strategie;
	}

	/**
	 * Méthode qui permet de connaître dans chaque tour à tout moment si un joueur à
	 * jouer.
	 *
	 * @return le booleen est à "true" si dans un tour, le joueur à jouer et à
	 *         "false" si dans un tour le joueur n'a pas encore jouer.
	 */
	public boolean isaJouer() {
		return aJouer;
	}

	/**
	 * Setter qui permet de définir dans un tour si le joueur à jouer ou pas encore.
	 *
	 * @param aJouer est un booléen qui est placé "true" si le joueur à jouer et
	 *               "false" si le joueur n'a pas encore jouer dans chaque tour.
	 */
	public void setaJouer(boolean aJouer) {
		this.aJouer = aJouer;
	}

	/**
	 * Méthode qui renvoi une chaine de caractère contenant les différents attributs
	 * d'un joueur Cela coprends, son nom, sa main (une liste chainée de carte), son
	 * offre visible (une carte ou rien), son offre cachée (une carte ou rien), son
	 * JEST avec ou sans trophée (liste chainée null ou contenant des cartes de type
	 * Carte), sa stratégie (de type Stratégie), son score (un entier), si le joueur
	 * à jouer (booleen).
	 * 
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", main=" + main + ", offreVisible=" + offreVisible + ", offreCache=" + offreCache
				+ ", jest=" + jest + ", jestAvecTrophes=" + jestAvecTrophes + ", strategie=" + strategie + ", score="
				+ score + ", aJouer=" + aJouer + "]\n";
	}

}
