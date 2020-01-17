package fr.utt.rt.lo02.projet.vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.utt.rt.lo02.projet.controleur.PartieControleur;
import fr.utt.rt.lo02.projet.modele.Carte;
import fr.utt.rt.lo02.projet.modele.Joueur;
import fr.utt.rt.lo02.projet.modele.Partie;

/**
 * The Class VuePartie.
 */
@SuppressWarnings("deprecation")
public class VuePartie implements Observer {

	/** Partie unique  */
	private Partie modele;

	/** The controleur. */
	private PartieControleur controleur;

	/** The joueurs. */
	private ArrayList<Joueur> joueurs;

	/** The vue joueurs. */
	private ArrayList<VueJoueur> vueJoueurs;

	/** The panel joueur. */
	private JPanel panelJoueur;

	/** The panel jest. */
	private JPanel panelJest;

	/** The fenetre. */
	private JFrame fenetre;

	/** The trophe 1. */
	private JLabel trophe1;

	/** The trophe 2. */
	private JLabel trophe2;

	/** The trophe 3. */
	private JLabel trophe3;

	/** The pioche. */
	private JLabel pioche;

	/** The log. */
	private JTextArea log;

	/** The scroll pane. */
	private JScrollPane scrollPane;

	/**
	 * Constructeur qui permet de définir les différentes vues sur le jeu avec le
	 * tapis, les trophés ainsi que les vues des joueurs. Après avoir récuprérer les
	 * différents élements de notre partie, on les places sur la fenêtre.
	 *
	 * @param modele La partie unique qui représente le modèle.
	 */
	public VuePartie(final Partie modele) {

		this.setModele(modele);
		modele.addObserver(this);

		joueurs = modele.getJoueurs();

		fenetre = new JFrame("JEST");
		fenetre.setLayout(new BorderLayout());
		fenetre.setResizable(true);

		panelJoueur = new JPanel();
		panelJoueur.setLayout(new BoxLayout(panelJoueur, BoxLayout.X_AXIS));

		panelJest = new JPanel();
		panelJest.setLayout(new BoxLayout(panelJest, BoxLayout.Y_AXIS));

		Iterator<Joueur> it = joueurs.iterator();
		vueJoueurs = new ArrayList<VueJoueur>();
		while (it.hasNext()) {
			VueJoueur vueJoueur = new VueJoueur(it.next());
			vueJoueurs.add(vueJoueur);
			panelJoueur.add(vueJoueur.getOffre());

		}

		VueJest vueJest = new VueJest();
		panelJest.add(vueJest.getJest());

		JPanel panelTapis = new JPanel();
		JLabel imgTapis = new JLabel(new ImageIcon("image/tapis.jpg"));

		pioche = new JLabel(new ImageIcon("image/dosCarte.jpg"));

		trophe1 = new JLabel(new ImageIcon("image/tasVide.jpg"));
		trophe2 = new JLabel(new ImageIcon("image/tasVide.jpg"));
		trophe3 = new JLabel(new ImageIcon("image/tasVide.jpg"));

		imgTapis.setLayout(new GridLayout());
		imgTapis.add(pioche);
		imgTapis.add(trophe1);
		imgTapis.add(trophe2);
		imgTapis.add(trophe3);

		panelTapis.add(imgTapis);

		setLog(new JTextArea());
		getLog().setEditable(false);
		getLog().setRows(5);
		scrollPane = new JScrollPane(getLog());

		fenetre.add(scrollPane, BorderLayout.NORTH);
		fenetre.add(panelJoueur, BorderLayout.SOUTH);
		fenetre.add(panelJest, BorderLayout.WEST);
		fenetre.add(panelTapis, BorderLayout.EAST);
		afficherTrophes();

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);

	}

	/**
	 * Getter des joueurs qui permet de récupérer la liste chainée des joueurs de la
	 * partie
	 *
	 * @return the joueurs les joueurs de la partie
	 */
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	/**
	 * Setter des joueurs qui permet de définir les joueurs du jeu
	 *
	 * @param joueurs les joueurs de la partie
	 */
	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	/**
	 * Méthode qui permet d'effacer l'image de la pioche lorsqu'elle est vide
	 */
	public void effacerPioche() {
		this.pioche.setIcon(new ImageIcon("image/tasVide.png"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Méthode qui permet d'afficher les cartes de trophées au milieu du plateau de
	 * jeu en récup
	 */
	public void afficherTrophes() {
		LinkedList<Carte> listetrophes = Partie.getInstance().getTrophes();
		Iterator<Carte> it = listetrophes.iterator();
		int i = 0;
		while (it.hasNext()) {
			VueCarte trophe = new VueCarte(it.next());
			if (i == 0) {
				this.trophe1.setIcon(new ImageIcon(trophe.getChemin()));
			} else if (i == 1) {
				this.trophe2.setIcon(new ImageIcon(trophe.getChemin()));
			} else if (i == 2) {
				this.trophe3.setIcon(new ImageIcon(trophe.getChemin()));
			}
			i++;
		}

	}

	/**
	 * Méthode qui permet d'évacer les trophées sur le plateau de jeu.
	 */
	public void effacerTrophes() {
		this.trophe1.setIcon(new ImageIcon("image/tasVide.png"));
		this.trophe2.setIcon(new ImageIcon("image/tasVide.png"));
		this.trophe3.setIcon(new ImageIcon("image/tasVide.png"));
	}

	/**
	 * Getter qui renvoi le JLabel de la pioche
	 *
	 * @return the pioche. Le JLabel de la pioche
	 */
	public JLabel getPioche() {
		return pioche;
	}

	/**
	 * Sets the pioche.
	 *
	 * @param pioche the new pioche
	 */
	public void setPioche(JLabel pioche) {
		this.pioche = pioche;
	}

	/**
	 * Sets the log.
	 *
	 * @param log the new log
	 */
	public void setLog(JTextArea log) {
		this.log = log;
	}

	/**
	 * Gets the log.
	 *
	 * @return the log
	 */
	public JTextArea getLog() {
		return log;
	}

	/**
	 * Gets the modele.
	 *
	 * @return the modele
	 */
	public Partie getModele() {
		return modele;
	}

	/**
	 * Sets the modele.
	 *
	 * @param modele the new modele
	 */
	public void setModele(Partie modele) {
		this.modele = modele;
	}

	/**
	 * Update.
	 *
	 * @param o   the o
	 * @param arg the arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String message = arg.toString();
			if (message.equals("Les troph�s sont distribu�s")) {
				effacerTrophes();
			} else if (message.equals("La pioche est vide")) {
				effacerPioche();
			} else if (message.contains("Les troph�s sont ")) {
				afficherTrophes();
			} else {
				controleur.updateJTextArea(message);
			}
		}
	}

	/**
	 * Gets the controleur.
	 *
	 * @return the controleur
	 */
	public PartieControleur getControleur() {
		return controleur;
	}

	/**
	 * Getter qui permet d'obtenir la vue de chaque joueur sous forme de liste
	 *
	 * @return the vue joueurs La vue des joueurs.
	 */
	public ArrayList<VueJoueur> getVueJoueurs() {
		return vueJoueurs;
	}

	/**
	 * Setter qui permet de définir la liste de vue des joueurs.
	 *
	 * @param vueJoueurs la vue des différents joueurs sous forme de liste
	 */
	public void setVueJoueurs(ArrayList<VueJoueur> vueJoueurs) {
		this.vueJoueurs = vueJoueurs;
	}

}
