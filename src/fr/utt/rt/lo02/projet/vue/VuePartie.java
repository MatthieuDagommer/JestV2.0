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
 * Classe de la Vue partie qui gere toute les etats de la partie en graphique
 */
@SuppressWarnings("deprecation")
public class VuePartie implements Observer {

	/** La partie qui est le modele dans l'interface MVC */
	private Partie modele;

	/** Controleur de la partie */
	private PartieControleur controleur;

	/** Liste des joueurs du jeu */
	private ArrayList<Joueur> joueurs;

	/** Liste de la vue des joueurs */
	private ArrayList<VueJoueur> vueJoueurs;

	/** Le Jpanel des joueurs */
	private JPanel panelJoueur;

	/** Le JPanel du Jest */
	private JPanel panelJest;

	/** La fenetre */
	private JFrame fenetre;

	/** JLabel du trophee n°1 */
	private JLabel trophe1;

	/** JLabel du trophee n°2 */
	private JLabel trophe2;

	/** JLabel du trophee n°3 */
	private JLabel trophe3;

	/** JLabel de la pioche */
	private JLabel pioche;

	/** JTexteArea qui definit la console de log */
	private JTextArea log;

	/** Le JscorllPane pour la console de log. */
	private JScrollPane scrollPane;

	/**
	 * Constructeur qui permet de definir les differentes vues sur le jeu avec le
	 * tapis, les trophes ainsi que les vues des joueurs. Apres avoir recuprerer les
	 * differents elements de notre partie, on les places sur la fenetre.
	 *
	 * @param modele La partie unique qui represente le modele.
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
	 * Getter des joueurs qui permet de recuperer la liste chainee des joueurs de la
	 * partie
	 *
	 * @return the joueurs les joueurs de la partie
	 */
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	/**
	 * Setter des joueurs qui permet de definir les joueurs de la partie.
	 *
	 * @param joueurs les joueurs de la partie
	 */
	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	/**
	 * Methode qui permet d'effacer l'image de la pioche lorsqu'elle est vide
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
	 * Methode qui permet d'afficher les cartes de trophees au milieu du plateau de
	 * jeu
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
	 * Methode qui permet d'effacer les trophees sur le plateau de jeu.
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
	 * Setter de la pioche
	 *
	 * @param pioche La pioche sous forme d'un JLabel
	 */
	public void setPioche(JLabel pioche) {
		this.pioche = pioche;
	}

	/**
	 * Setter du panneau qui indique le deroulement de la partie.
	 *
	 * @param log JTexteArea qui permet de definir le panneau de deroulement du jeu.
	 */
	public void setLog(JTextArea log) {
		this.log = log;
	}

	/**
	 * Getter du panneau de Log, qui indique le deroulement de la partie.
	 *
	 * @return the log JTextArea qui permet d'afficher le deroulement de la partie.
	 */
	public JTextArea getLog() {
		return log;
	}

	/**
	 * Getter du modele (de type Partie).
	 *
	 * @return the modele Le modele qui est la partie
	 */
	public Partie getModele() {
		return modele;
	}

	/**
	 * Setter du modele, la Partie.
	 *
	 * @param modele Permet de definir la partie dans la vue.
	 */
	public void setModele(Partie modele) {
		this.modele = modele;
	}

	/**
	 * Methode update du design pattern Observer/Observable qui permet de : 1/ Soit
	 * effacer les trophees sur l'interface graphique si le message envoyer par la
	 * partie est " "Les trophees sont distribues" 2/ Soit effacer la pioche sur
	 * l'interface graphique si le message envoyer par la partie est "La pioche est
	 * vide" 3/ Soit afficher les trophees sur l'interface graphique si le message
	 * envoyer par la partie est "Les trophes sont" 4/ Soit afficher le message recu
	 * dans la console d'affichage. Pour cela il appel les methodes definient plus
	 * haut.
	 * 
	 * @param o   Objet observable qui est un partie
	 * @param arg arguement de l'objet, ici generalement un message de type String.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String message = arg.toString();
			if (message.equals("Les trophees sont distribues")) {
				effacerTrophes();
			} else if (message.equals("La pioche est vide")) {
				effacerPioche();
			} else if (message.contains("Les trophees sont ")) {
				afficherTrophes();
			} else {
				controleur.updateJTextArea(message);
			}
		}
	}

	/**
	 * Getter du controleur de la partie.
	 *
	 * @return the controleur Le controleur de la partie.
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
	 * Setter qui permet de definir la liste de vue des joueurs.
	 *
	 * @param vueJoueurs la vue des differents joueurs sous forme de liste
	 */
	public void setVueJoueurs(ArrayList<VueJoueur> vueJoueurs) {
		this.vueJoueurs = vueJoueurs;
	}

}
