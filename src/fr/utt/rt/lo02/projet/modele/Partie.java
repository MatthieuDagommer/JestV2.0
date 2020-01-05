package fr.utt.rt.lo02.projet.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

import fr.utt.rt.lo02.projet.controleur.PartieControleur;

// TODO: Auto-generated Javadoc
/**
 * La classe partie est une classe public qui est observable. Cette classe est observable afin de mettre à jour les instances qui la compose en cas de changement. C'est le cerveau du jeu, 
 * C'est à l'interieur de cette classe que l'on va : crée la partie avec les différents joueurs et leur stratégie, définir la variante et eventuellement l'extension de carte. Dérouler les tour et compter les scores à la fin du jeu.
 * Cette classe utilise le singleton car on ne peut avoir seulement une instance unique de partie lorsque l'on joue.
 */
public class Partie extends Observable {

	/** instance est l'unique instance de partie. Elle est définie à null avant de lancer la partie, dès que l'utilisateur à chosisit le nombre de joueur, la variante et l'extension, l'instance de partie est créee.
	 *  */
	private static Partie instance = null;

	/** Cette liste définie les différents joueur lors de la partie. Elle est composée dans notre jeu de JEST de 3 ou 4 joueurs virtuels/physique et dans un fonctionnement */
	private ArrayList<Joueur> joueurs;

	/** The trophes. */
	private LinkedList<Carte> trophes;

	/** The jeu de cartes. */
	private Deck jeuDeCartes;

	/** The regle. */
	private Regle regle;

	/** The joueur actuel. */
	private Joueur joueurActuel;

	/**
	 * Gets the joueur actuel.
	 *
	 * @return the joueur actuel
	 */
	public Joueur getJoueurActuel() {
		return joueurActuel;
	}

	/**
	 * Sets the joueur actuel.
	 *
	 * @param joueurActuel the new joueur actuel
	 */
	public void setJoueurActuel(Joueur joueurActuel) {
		this.joueurActuel = joueurActuel;
	}

	/**
	 * Gets the regle.
	 *
	 * @return the regle
	 */
	public Regle getRegle() {
		return regle;
	}

	/**
	 * Sets the regle.
	 *
	 * @param regle the new regle
	 */
	public void setRegle(Regle regle) {
		this.regle = regle;
	}

	/**
	 * Instantiates a new partie.
	 */
	private Partie() {
		joueurActuel = null;
		joueurs = new ArrayList<Joueur>();
		trophes = new LinkedList<Carte>();
	}

	/**
	 * Méthode public qui permet de crée la partie si elle n'est pas déjà créee ou d'obtenir les attributs de la 
	 * partie en cours.
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
	 * Getter qui permet d'obtenir les différents trophées de la partie.
	 *
	 * @return Liste chainée de carte représentant les trophées de la partie mis au milieu du jeu.
	 */
	public LinkedList<Carte> getTrophes() {
		return trophes;
	}

	/**
	 * Setter qui permet de définir les trophées.
	 *
	 * @param trophes the new trophes
	 */
	public void setTrophes(LinkedList<Carte> trophes) {
		this.trophes = trophes;
	}

	/**
	 * Méthode public qui permet d'ajouter un joueur à la partie.
	 *Remarque : Le joueur ne doit pas avoir déjà été ajouté.
	 * @param j le joueur que l'on souhaite ajouter.
	 */
	public void addJoueur(Joueur j) {
		if (this.joueurs.contains(j) == false) {
			this.joueurs.add(j);

		}
	}

	/**
	 * Méthode permettant d'enlever un joueur déjà présent dans la partie.
	 *
	 * @param j le joueur que l'on souhiate enlever de la partie si il est déjà présent.
	 */
	public void removeJoueur(Joueur j) {
		if (this.joueurs.contains(j) == true) {
			this.joueurs.remove(j);
		}
	}

	/**
	 * Getter permettant d'obtenir la liste des joueurs présents dans la partie.
	 *
	 * @return Un liste contenant les joueurs.
	 */
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	/**
	 * Setter permettant de définir les joueurs de la partie à l'aide d'une liste.
	 *
	 * @param joueurs, liste de joueurs que l'on souhaite ajouter.
	 */
	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	/**
	 * Méthode public qui permet de construire la pioche de jeu en fonction de l'extension choisie.
	 * En effet, selon l'extension choisie, le jeu comportera 17 ou 21 cartes.
	 *
	 * @param extension correspondant à l'extension (0 sans extension, 1 avec une extension)
	 * @return le jeu de carte de type Deck (Liste chainée et extension)
	 */
	public Deck buildJeuDeCarte(int extension) {
		Deck jeu = new Deck(extension);
		this.jeuDeCartes = jeu;
		return jeu;
	}

	/**
	 * Méthode qui détermine le déroullement d'une partie une fois les joueurs l'extension et la variante définis.
	 * 1. On commence par mélanger les cartes de la pioche (méthode mélanger).
	 * 2. On choisit le/les trophées pour le/les mettres au milieu du plateau.
	 * 3. Tant que la pioche n'est pas vide pour chaque tour :
	 * 	a. On mélange les cartes
	 * 	b. On définie que les joueurs n'ont pas encore jouer
	 * 	c. On distribue deux cartes à chaque joueurs.
	 * 	d. Chaque joueur choisit sa carte à cacher (selon sa stratégie)
	 * 	e. On définit le joueur qui à la meilleure offre pour sa carte visible.
	 * 	f. On parcours tous les joueurs, en notifiant tous les observers de partie, le joueur qui est en train de jouer
	 * 	g.	On met à jour la partie
	 *  h. Le joueur choisit la carte qu'il souhaite ajouter à son JEST (méthode jouer)
	 *  i. Une fois que le joueur à jouer on définie le joueur suivant à l'aide de la méthode meilleure offre si il reste des joueurs
	 *  j. sinon on ramasser les cartes restantes dans les offres des joueurs pour les distribuer au tour d'après.
	 * 4. On notifie les observers de la partie que la pioche et vide
	 * 5. On compte les scores et on distribue les trophées.
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				joueurActuel = joueurActuel.jouer();
				if (joueurActuel.isaJouer() && i < Joueur.NB_JOUEURS - 1) {
					// System.out.println("Dans le tour");
					joueurActuel = meilleureOffre();
				}
			}
			if (!jeuDeCartes.estVide()) {
				rammaserCartesRestante();
			}
			// System.out.println("Fin du tour");
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
	 * Méthode public qui permet de remettre à 0 le fait qu'un joueur est jouer à chaque début de tour
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
	 * Méthode qui parcours les joueurs et pour chacun leur demande de choisir parmis les 2 cartes qui leur sont proposées.
	 */
	public void offreJoueur() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = (Joueur) it.next();
			j.faireOffre();
		}
	}

	/**
	 * Dans cette méthode, on ajoute dans le JEST courant, les éventuels trophées que le joueur à perçu.
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
	 * Cette méthode permet de ramasser les cartes restantes dans les offres des joueurs à chaque fin de tour
	 * et de les ajouter en haut de la pioche.
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
	 * Lors du dernier tour, les cartes des offres des joueurs doivent être transférer dans leur JEST.
	 * On ajouter les cartes restantes dans le JEST du joueurs. 
	 * On notifie les différentes vues de la partie que les cartes restantes sont maintenant dans le JEST.
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
	 * Méthode qui permet de déterminer le joueur qui doit jouer dans un tour.
	 * On définit le prochain joueur comme un joueur n'ayant pas déjà jouer.
	 * 
	 * On itère ensuite sur les différents joueurs, pour chacun on vérifie si il à jouer
	 * et si il a une carte visible plus forte que celle du meilleur joueur courant.
	 * Si c'est le cas, il devient le joueur courant.
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
	 * Cette méthode public permet d'ajouter des cartes à la liste chainée trophée. 
	 * Si le jeu possède 3 joueurs sans extension, il y a 2 cartes de trophée posées sur le plateau
	 * Si le jeu possède 4 joueurs il n'y a q'une sueule carte de trophée.
	 * Si le jeu possède 3 joueurs et 1 extension, on aura alors 3 cartes de torphées
	 * sur le plateau.
	 * Une fois les cartes de trophées ajouter a la liste chainée, on notifie les différentes vues qui observent la
	 * partie les trophées qui ont été ajouté.
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
		// System.out.println(message);

		setChanged();
		notifyObservers(message);
	}

	/**
	 * Cette méthode public permet la distribution de deux cartes de la pioche
	 * à la main de chaque joueurs.
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
	 * Cette méthode gère la distribution des trophées dans le jeu.
	 * 
	 * 
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
			case aucun:
				break;
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
	 * Checks for joker.
	 *
	 * @return the joueur
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
	 * Plus valeur.
	 *
	 * @param valeur the valeur
	 * @return the joueur
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
	 * Highest carte in couleur.
	 *
	 * @param couleur the couleur
	 * @return the joueur
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
	 * Lowest carte in couleur.
	 *
	 * @param couleur the couleur
	 * @return the joueur
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
	 * Update score.
	 */
	public void updateScore() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			j.accept(this);
		}
	}

	/**
	 * Visit jest.
	 *
	 * @param jest the jest
	 * @return the int
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
	 * Best jest.
	 *
	 * @param joueurs the joueurs
	 * @return the joueur
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
			}
		}
		return bestJ;
	}

	/**
	 * Best jest no joker.
	 *
	 * @return the joueur
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
	 * Gets the offre dispo.
	 *
	 * @param joueur the joueur
	 * @return the offre dispo
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
	 * Cette méthode va permettre d'appeler la fenêtre d'initialisation du JEST.
	 * Depuis le controlleur de la Partie.
	 */
	public void initialisation() {
		PartieControleur.initialisationJest();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Joueur ordi1 = new Joueur("ordi1", new StratFacile());
		Joueur ordi2 = new Joueur("ordi2", new StratFacile());
		Joueur ordi3 = new Joueur("ordi3", new StratFacile());
		Joueur ordi4 = new Joueur("ordi4", new StratFacile());

		Partie partie = Partie.getInstance();

		partie.addJoueur(ordi1);
		partie.addJoueur(ordi2);
		partie.addJoueur(ordi3);
		partie.addJoueur(ordi4);

		partie.buildJeuDeCarte(0);
		partie.setRegle(new RegleStandard());

		partie.lancerPartie();

		// System.out.println(partie.bestJest(partie.getJoueurs()));
	}
}
