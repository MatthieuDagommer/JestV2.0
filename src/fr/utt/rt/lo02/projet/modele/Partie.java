package fr.utt.rt.lo02.projet.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

import fr.utt.rt.lo02.projet.controleur.PartieControleur;

/**
 * La classe partie est une classe public qui est observable. Cette classe est
 * observable afin de mettre a jour les vues en cas de changement. C'est le
 * moteur du jeu, C'est a l'interieur de cette classe que l'on va : cree la
 * partie avec les differents joueurs et leur strategie, definir la variante et
 * eventuellement l'extension de carte. Derouler les tours et compter les scores
 * a la fin du jeu. Cette classe utilise le singleton car on ne peut avoir
 * seulement une instance unique de partie lorsque l'on joue.
 */
@SuppressWarnings("deprecation")
public class Partie extends Observable {

	/**
	 * instance est l'unique instance de partie. Elle est definie a null avant de
	 * lancer la partie, des que l'utilisateur a chosisit le nombre de joueur, la
	 * variante et l'extension, l'instance de partie est creee.
	 */
	private static Partie instance = null;

	/**
	 * Cette liste definie les differents joueur lors de la partie. Elle est
	 * composee dans notre jeu de JEST de 3 ou 4 joueurs virtuels/physique
	 */
	private ArrayList<Joueur> joueurs;

	/**
	 * Cette collection comporte les trophes mis face visible au debut de la partie
	 */
	private LinkedList<Carte> trophes;

	/** C'est la pioche de type Deck */
	private Deck jeuDeCartes;

	/**
	 * Regle choisit par l'utilisateur. Patron Strategy, elle est soit de de type
	 * RegleStandard, Variante1 ou Variante 2
	 * 
	 */
	private Regle regle;

	/** C'est le joueur qui joue pendant le tour. */
	private Joueur joueurActuel;

	/**
	 * Entier qui represente l'extension. Il est egale a 0 ou 1
	 */
	private int extension;

	/**
	 * Getter de l'extension
	 *
	 * @return l extension
	 */
	public int getExtension() {
		return extension;
	}

	/**
	 * Setter qui permet de definir l'extension.
	 *
	 * @param extension C'est un entier sui represente l'extension
	 */
	public void setExtension(int extension) {
		this.extension = extension;
	}

	/**
	 * Getter du joueur actuel.
	 *
	 * @return le joueur actuel
	 */
	public Joueur getJoueurActuel() {
		return joueurActuel;
	}

	/**
	 * Setter du joueur actuel.
	 *
	 * @param joueurActuel le nouveau joueur actuel
	 */
	public void setJoueurActuel(Joueur joueurActuel) {
		this.joueurActuel = joueurActuel;
	}

	/**
	 * Getter de la regle.
	 *
	 * @return la regle actuel
	 */
	public Regle getRegle() {
		return regle;
	}

	/**
	 * setter de la regle.
	 *
	 * @param regle la nouvelle regle
	 */
	public void setRegle(Regle regle) {
		this.regle = regle;
	}

	/**
	 * Constructeur prive dans le cadre du patron singleton Instancie une nouvelle
	 * partie.
	 */
	private Partie() {
		joueurActuel = null;
		joueurs = new ArrayList<Joueur>();
		trophes = new LinkedList<Carte>();
	}

	/**
	 * Methode public qui permet de cree la partie si elle n'est pas deja creee ou
	 * d'obtenir les attributs de la partie en cours.
	 *
	 * @return instance l'unique instance en partie
	 */
	public static Partie getInstance() {
		if (instance == null) {
			instance = new Partie();
		}
		return instance;
	}

	/**
	 * Getter qui permet d'obtenir les differents trophees de la partie.
	 *
	 * @return Liste chainee de carte representant les trophees de la partie mis au
	 *         milieu du jeu.
	 */
	public LinkedList<Carte> getTrophes() {
		return trophes;
	}

	/**
	 * Setter qui permet de definir les trophees.
	 *
	 * @param trophes the new trophes
	 */
	public void setTrophes(LinkedList<Carte> trophes) {
		this.trophes = trophes;
	}

	/**
	 * Methode public qui permet d'ajouter un joueur a la partie. Remarque : Le
	 * joueur ne doit pas avoir deja ete ajoute.
	 * 
	 * @param j le joueur que l'on souhaite ajouter.
	 */
	public void addJoueur(Joueur j) {
		if (this.joueurs.contains(j) == false) {
			this.joueurs.add(j);

		}
	}

	/**
	 * Methode permettant d'enlever un joueur deja present dans la partie.
	 *
	 * @param j le joueur que l'on souhiate enlever de la partie si il est deja
	 *          present.
	 */
	public void removeJoueur(Joueur j) {
		if (this.joueurs.contains(j) == true) {
			this.joueurs.remove(j);
		}
	}

	/**
	 * Getter permettant d'obtenir la liste des joueurs presents dans la partie.
	 *
	 * @return Un liste contenant les joueurs.
	 */
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	/**
	 * Setter permettant de definir les joueurs de la partie a l'aide d'une liste.
	 *
	 * @param joueurs, liste de joueurs que l'on souhaite ajouter.
	 */
	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	/**
	 * Methode public qui permet de construire la pioche de jeu en fonction de
	 * l'extension choisie. En effet, selon l'extension choisie, le jeu comportera
	 * 17 ou 21 cartes.
	 *
	 * @param extension correspondant a l'extension (0 sans extension, 1 avec une
	 *                  extension)
	 * @return le jeu de carte de type Deck (Liste chainee et extension)
	 */
	public Deck buildJeuDeCarte(int extension) {
		this.extension = extension;
		Deck jeu = new Deck(extension);
		this.jeuDeCartes = jeu;
		return jeu;
	}

	/**
	 * Methode qui determine le deroullement d'une partie une fois les joueurs
	 * l'extension et la variante definis. 1. On commence par melanger les cartes de
	 * la pioche (methode melanger). 2. On choisit le/les trophees pour le/les
	 * mettres au milieu du plateau. 3. Tant que la pioche n'est pas vide pour
	 * chaque tour : a. On melange les cartes b. On definie que les joueurs n'ont
	 * pas encore jouer c. On distribue deux cartes a chaque joueurs. d. Chaque
	 * joueur choisit sa carte a cacher (selon sa strategie) e. On definit le joueur
	 * qui a la meilleure offre pour sa carte visible. f. On parcours tous les
	 * joueurs, en notifiant tous les observers de partie, le joueur qui est en
	 * train de jouer g. On met a jour la partie h. Le joueur choisit la carte qu'il
	 * souhaite ajouter a son JEST (methode jouer) i. Une fois que le joueur a jouer
	 * on definie le joueur suivant a l'aide de la methode meilleure offre si il
	 * reste des joueurs j. sinon on ramasser les cartes restantes dans les offres
	 * des joueurs pour les distribuer au tour d'apres. 4. On notifie les observers
	 * de la partie que la pioche et vide 5. On compte les scores et on distribue
	 * les trophees.
	 * 
	 */
	public void lancerPartie() {
		String message = "";
		jeuDeCartes.melanger();
		choixTrophee();
		do {
			jeuDeCartes.melanger();
			razAJoue();
			distribuerJeu();
			offreJoueur();
			joueurActuel = meilleureOffre();
			for (int i = 0; i < Joueur.NB_JOUEURS; i++) {
				setChanged();
				notifyObservers(joueurActuel);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				joueurActuel = joueurActuel.jouer();
				if (joueurActuel.isaJouer() && i < Joueur.NB_JOUEURS - 1) {
					joueurActuel = meilleureOffre();
				}
			}
			if (!jeuDeCartes.estVide()) {
				rammaserCartesRestante();
			}
		} while (!jeuDeCartes.estVide());
		setChanged();
		notifyObservers("La pioche est vide");
		rammaserCartesRestanteJest();
		updateScore();
		distribuerTrophees();
		fusionJest();
		updateScore();
		message = "Fin de partie, le gagant est : " + bestJest(joueurs);
		setChanged();
		notifyObservers(message);
	}

	/**
	 * Methode public qui permet de remettre a 0 le fait qu'un joueur est jouer a
	 * chaque debut de tour
	 * 
	 */
	public void razAJoue() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			j.setaJouer(false);
		}
	}

	/**
	 * Methode qui parcours les joueurs et pour chacun leur demande de choisir
	 * parmis les 2 cartes qui leur sont proposees.
	 */
	public void offreJoueur() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = (Joueur) it.next();
			j.faireOffre();
		}
	}

	/**
	 * Dans cette methode, on ajoute dans le JEST courant, les eventuels trophees
	 * que le joueur a perçu.
	 */
	private void fusionJest() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			while (!j.getJestAvecTrophes().isEmpty()) {
				j.addJest(j.getJestAvecTrophes().pop());
			}
		}
	}

	/**
	 * Cette methode permet de ramasser les cartes restantes dans les offres des
	 * joueurs a chaque fin de tour et de les ajouter en haut de la pioche.
	 */
	private void rammaserCartesRestante() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (j.getOffreCache() != null) {
				jeuDeCartes.addCarte(j.getOffreCache());
				j.setOffreCache(null);
			}
			if (j.getOffreVisible() != null) {
				jeuDeCartes.addCarte(j.getOffreVisible());
				j.setOffreVisible(null);
			}
		}
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Lors du dernier tour, les cartes des offres des joueurs doivent etre
	 * transferer dans leur JEST. On ajouter les cartes restantes dans le JEST du
	 * joueurs. On notifie les differentes vues de la partie que les cartes
	 * restantes sont maintenant dans le JEST.
	 */
	private void rammaserCartesRestanteJest() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (j.getOffreCache() != null) {
				j.addJest(j.getOffreCache());
				j.setOffreCache(null);
			}
			if (j.getOffreVisible() != null) {
				j.addJest(j.getOffreVisible());
				j.setOffreVisible(null);
			}
		}
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Methode qui permet de determiner le joueur qui doit jouer dans un tour. On
	 * definit le prochain joueur comme un joueur n'ayant pas deja jouer.
	 * 
	 * On itere ensuite sur les differents joueurs, pour chacun on verifie si il a
	 * jouer et si il a une carte visible plus forte que celle du meilleur joueur
	 * courant. Si c'est le cas, il devient le joueur courant.
	 *
	 * @return le joueur qui doit jouer
	 */
	private Joueur meilleureOffre() {
		Iterator<Joueur> it = joueurs.iterator();
		Joueur bestOffre = joueurs.get(0), j;
		while (it.hasNext() && (bestOffre.getOffreVisible() == null || bestOffre.isaJouer() == true)) {
			bestOffre = it.next();
		}
		it = joueurs.iterator();
		while (it.hasNext()) {
			j = it.next();
			if (j.getOffreVisible() != null) {
				if (j.getOffreVisible().getValeur().ordinal() > bestOffre.getOffreVisible().getValeur().ordinal()
						&& !j.isaJouer()) {
					bestOffre = j;
				}
				if (j.getOffreVisible().getValeur().ordinal() == bestOffre.getOffreVisible().getValeur().ordinal() && j
						.getOffreVisible().getCouleur().ordinal() > bestOffre.getOffreVisible().getCouleur().ordinal()
						&& !j.isaJouer()) {
					bestOffre = j;
				}
			}
		}
		return bestOffre;
	}

	/**
	 * Cette methode public permet d'ajouter des cartes a la liste chainee trophee.
	 * Si le jeu possede 3 joueurs sans extension, il y a 2 cartes de trophee posees
	 * sur le plateau Si le jeu possede 4 joueurs il n'y a q'une sueule carte de
	 * trophee. Si le jeu possede 3 joueurs et 1 extension, on aura alors 3 cartes
	 * de torphees sur le plateau. Une fois les cartes de trophees ajouter a la
	 * liste chainee, on notifie les differentes vues qui observent la partie les
	 * trophees qui ont ete ajoute.
	 */
	public void choixTrophee() {
		String message = "";
		if (Joueur.NB_JOUEURS == 3 && jeuDeCartes.getExtension() == 0) {
			trophes.add(jeuDeCartes.piocherCarte());
			trophes.add(jeuDeCartes.piocherCarte());
		} else if (Joueur.NB_JOUEURS == 4) {
			trophes.add(jeuDeCartes.piocherCarte());
		} else if (Joueur.NB_JOUEURS == 3 && jeuDeCartes.getExtension() == 1) {
			trophes.add(jeuDeCartes.piocherCarte());
			trophes.add(jeuDeCartes.piocherCarte());
			trophes.add(jeuDeCartes.piocherCarte());
		}
		message = "Les troph�s sont " + trophes.toString();

		setChanged();
		notifyObservers(message);
	}

	/**
	 * Cette methode public permet la distribution de deux cartes de la pioche a la
	 * main de chaque joueurs.
	 */
	public void distribuerJeu() {
		for (int i = 0; i < 2; i++) {
			Iterator<Joueur> it = joueurs.iterator();
			while (it.hasNext()) {
				Joueur j = (Joueur) it.next();
				j.addMain(jeuDeCartes.piocherCarte());
			}
		}
	}

	/**
	 * Cette methode gere la distribution des trophees dans le jeu. Elle effectue
	 * different action en fonction du trophee de la carte a distribuee elle notifie
	 * la vue quand le trophe est distribue
	 * 
	 */
	public void distribuerTrophees() {
		String message = "";
		if (trophes.contains(Joker.getInstance())) {
			Carte joker = trophes.remove(trophes.indexOf(Joker.getInstance()));
			bestJest(joueurs).addJestAvecTrophes(joker);
			message = "Le Trophe Joker est distribu� � " + bestJest(joueurs);
			// System.out.println(message);
			setChanged();
			notifyObservers(message);
			setChanged();
			notifyObservers();
		}
		Iterator<Carte> it = trophes.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			switch (c.getTrophee()) {
			case bascarreau:
				lowestCarteInCouleur(Couleur.CARREAU).addJestAvecTrophes(c);
				message = "Le trophe " + c + " est distribu� � " + lowestCarteInCouleur(Couleur.CARREAU).getNom();
				break;
			case bascoeur:
				lowestCarteInCouleur(Couleur.COEUR).addJestAvecTrophes(c);
				message = "Le trophe " + c + " est distribu� � " + lowestCarteInCouleur(Couleur.COEUR).getNom();
				break;
			case baspic:
				lowestCarteInCouleur(Couleur.PIC).addJestAvecTrophes(c);
				message = "Le trophe " + c + " est distribu� � " + lowestCarteInCouleur(Couleur.PIC).getNom();
				break;
			case bastrefle:
				lowestCarteInCouleur(Couleur.TREFLE).addJestAvecTrophes(c);
				message = "Le trophe " + c + " est distribu� � " + lowestCarteInCouleur(Couleur.TREFLE).getNom();
				break;
			case bestJest:
				bestJest(joueurs).addJestAvecTrophes(c);
				message = "Le trophe " + c + " est distribu� � " + bestJest(joueurs).getNom();
				break;
			case hautcarreau:
				highestCarteInCouleur(Couleur.CARREAU).addJestAvecTrophes(c);
				message = "Le trophe " + c + " est distribu� � " + highestCarteInCouleur(Couleur.CARREAU).getNom();
				break;
			case hautcoeur:
				highestCarteInCouleur(Couleur.COEUR).addJestAvecTrophes(c);
				message = "Le trophe " + c + " est distribu� � " + highestCarteInCouleur(Couleur.COEUR).getNom();
				break;
			case hautpic:
				highestCarteInCouleur(Couleur.PIC).addJestAvecTrophes(c);
				message = "Le trophe " + c + " est distribu� � " + highestCarteInCouleur(Couleur.PIC).getNom();
				break;
			case hauttrefle:
				highestCarteInCouleur(Couleur.TREFLE).addJestAvecTrophes(c);
				message = "Le trophe " + c + " est distribu� � " + highestCarteInCouleur(Couleur.TREFLE).getNom();
				break;
			case joker:
				hasJoker().addJestAvecTrophes(c);
				message = "Le trophe " + c + " est distribu� � " + hasJoker().getNom();
				break;
			case nojoker:
				bestJestNoJoker().addJestAvecTrophes(c);
				message = "Le trophe " + c + " est distribu� � " + bestJestNoJoker().getNom();
				break;
			case plus2:
				plusValeur(Valeur.DEUX).addJestAvecTrophes(c);
				message = "Le trophe " + c + " est distribu� � " + plusValeur(Valeur.DEUX).getNom();
				break;
			case plus3:
				plusValeur(Valeur.TROIS).addJestAvecTrophes(c);
				message = "Le trophe " + c + " est distribu� � " + plusValeur(Valeur.TROIS).getNom();
				break;
			case plus4:
				plusValeur(Valeur.QUATRE).addJestAvecTrophes(c);
				message = "Le trophe " + c + " est distribu� � " + plusValeur(Valeur.QUATRE).getNom();
				break;
			default:
				break;

			}
			// System.out.println(message);
			setChanged();
			notifyObservers(message);

			message = "Les troph�s sont distribu�s";
			setChanged();
			notifyObservers(message);
		}
	}

	/**
	 * Permet de trouver le joueur qui possede le joker
	 *
	 * @return le joueur qui possede le joker
	 */
	public Joueur hasJoker() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (j.getJest().contains(Joker.getInstance()) || j.getJestAvecTrophes().contains(Joker.getInstance())) {
				return j;
			}
		}
		return null;
	}

	/**
	 * Chercher le joueur avec le plus de carte d'une valeur.
	 *
	 * @param valeur La valeur a compter
	 * @return le joueur avec le plus de carte de la valeur
	 */
	public Joueur plusValeur(Valeur valeur) {
		Joueur bestJoueur;
		Iterator<Joueur> it = joueurs.iterator();
		bestJoueur = it.next();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (bestJoueur.plusValeur(valeur) < j.plusValeur(valeur)) {
				bestJoueur = j;
			} else if (bestJoueur.plusValeur(valeur) == j.plusValeur(valeur)
					&& j.bestCouleur(valeur) > bestJoueur.bestCouleur(valeur)) {
				bestJoueur = j;
			}
		}
		return bestJoueur;
	}

	/**
	 * Cherche le joueur avec la valeur la plus haute dans une couleur donnee
	 *
	 * @param couleur la couleur ou l'on veut la plus haute valeur
	 * @return le joueur avec la plus haute carte
	 */
	public Joueur highestCarteInCouleur(Couleur couleur) {
		Joueur bestJoueur;
		Iterator<Joueur> it = joueurs.iterator();
		bestJoueur = it.next();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (j.highestValeurInCouleur(couleur) > bestJoueur.highestValeurInCouleur(couleur)) {
				bestJoueur = j;
			}
		}
		return bestJoueur;
	}

	/**
	 * Cherche le joueur avec la valeur la plus basse dans une couleur donnee
	 *
	 * @param couleur la couleur ou l'on veut la plus basse valeur
	 * @return le joueur avec la plus basse carte
	 */
	public Joueur lowestCarteInCouleur(Couleur couleur) {
		Joueur bestJoueur;
		Iterator<Joueur> it = joueurs.iterator();
		bestJoueur = it.next();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (j.highestValeurInCouleur(couleur) < bestJoueur.highestValeurInCouleur(couleur)) {
				bestJoueur = j;
			}
		}
		return bestJoueur;
	}

	/**
	 * met a jour les scores des joueurs graces aux visites du patron visitor
	 */
	public void updateScore() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			j.accept(this);
		}
	}

	/**
	 * Visit le jest d'un joueur pour compter les points avec la regle de la partie
	 * en cours.
	 *
	 * @param jest le JEST du joueur a compter
	 * @return le score du JEST
	 */
	public int visitJest(LinkedList<Carte> jest) {
		int score = 0;
		score += regle.visitCarreau(jest);
		score += regle.visitCoeur(jest);
		score += regle.visitNoir(jest);
		score += regle.visitTreflePic(jest);
		return score;
	}

	/**
	 * Cherche le joueur avec le meilleur jest
	 *
	 * @param joueurs la liste des joueurs a comparer
	 * @return le joueur avec le meilleur jest
	 */
	public Joueur bestJest(ArrayList<Joueur> joueurs) {
		updateScore();
		Iterator<Joueur> it = joueurs.iterator();
		Joueur bestJ = joueurs.get(0);
		it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j1 = (Joueur) it.next();
			if (j1.getScore() > bestJ.getScore()) {
				bestJ = j1;
			} else if (bestJ.getScore() == j1.getScore()) {
				Carte carteBestJoueur = bestJ.ComparaisonJest();
				Carte carteJoueur1 = j1.ComparaisonJest();
				int valeurBestJoueur = carteBestJoueur.getValeur().ordinal();
				int valeurJoueur1 = carteJoueur1.getValeur().ordinal();
				if (bestJ.highestValeurInCouleur(carteBestJoueur.getCouleur()) == 5) {
					valeurBestJoueur = 5;
				}
				if (j1.highestValeurInCouleur(carteJoueur1.getCouleur()) == 5) {
					valeurBestJoueur = 5;
				}
				if (valeurJoueur1 > valeurBestJoueur) {
					j1 = bestJ;
				} else if (valeurJoueur1 == valeurBestJoueur) {
					if (carteJoueur1.getCouleur().ordinal() > carteBestJoueur.getCouleur().ordinal()) {
						bestJ = j1;
					}
				}

			}
		}
		return bestJ;
	}

	/**
	 * On cherche le meilleur jest sans le joker. Si le meilleur jest continent le
	 * joker alors on cree une liste de joueur sans celui qui possede le joker
	 *
	 * @return le joueur avec le meilleur jest sans le joker
	 */
	private Joueur bestJestNoJoker() {
		updateScore();
		Joueur bestJ = bestJest(joueurs);
		if (bestJ.getJest().contains(Joker.getInstance()) || bestJ.getJestAvecTrophes().contains(Joker.getInstance())) {
			ArrayList<Joueur> noJoke = joueurs;
			noJoke.remove(bestJ);
			return bestJest(noJoke);
		} else {
			return bestJ;
		}
	}

	/**
	 * Permet d'obtenir les offre dans lesquelles le joueur peut choisir
	 *
	 * @param joueur le joueur dont on cherche les offre disponible pour jouer
	 * @return une liste de joueurs chez qui il peut piocher
	 */
	public ArrayList<Joueur> getOffreDispo(Joueur joueur) {
		ArrayList<Joueur> copyJoueurs = new ArrayList<Joueur>();
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (j.getOffreCache() != null && j.getOffreVisible() != null) {
				copyJoueurs.add(j);
			}
		}
		if (copyJoueurs.size() > 1) {
			copyJoueurs.remove(joueur);
		}
		return copyJoueurs;
	}

	/**
	 * Cette methode va permettre d'appeler la fenetre d'initialisation du JEST.
	 * Depuis le controlleur de la Partie.
	 */
	public void initialisation() {
		PartieControleur.initialisationJest();
	}

}
