package fr.utt.rt.lo02.projet.vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import fr.utt.rt.lo02.projet.controleur.PartieControleur;
import fr.utt.rt.lo02.projet.modele.Carte;
import fr.utt.rt.lo02.projet.modele.Joueur;
import fr.utt.rt.lo02.projet.modele.Partie;
import fr.utt.rt.lo02.projet.modele.Valeur;

public class VuePartie implements Observer {

	private JFrame fenetre;

	private JPanel panelJoueur;

	private JPanel panelTapis;

	private JLabel imageTapis;

	private Partie modele;

	private ArrayList<Joueur> joueurs;

	private JLabel pioche;
	
	private JTextArea log;

	// private JLabel tas;

	private JLabel trophe1, trophe2;

	private PartieControleur controleur;
	
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public VuePartie(final Partie modele) {

		this.modele = modele;
		modele.addObserver(this);

		joueurs = modele.getJoueurs();

		fenetre = new JFrame("JEST");
		fenetre.setLayout(new BorderLayout());
		fenetre.setResizable(true);

		panelJoueur = new JPanel();
		panelJoueur.setLayout(new BoxLayout(panelJoueur, BoxLayout.Y_AXIS));

		panelTapis = new JPanel();
		imageTapis = new JLabel(new ImageIcon("image/tapis.jpg"));

		pioche = new JLabel(new ImageIcon("image/dosCarte.jpg"));
		trophe1 = new JLabel(new ImageIcon("image/tasVide.png"));
		trophe2 = new JLabel(new ImageIcon("image/tasVide.png"));
		imageTapis.setLayout(new GridLayout());
		imageTapis.add(trophe1);
		imageTapis.add(trophe2);
		imageTapis.add(pioche);
		panelTapis.add(imageTapis);

		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			VueJoueur vueJoueur;
			vueJoueur = new VueJoueur((Joueur) it.next());
			panelJoueur.add(vueJoueur.getJpanel());
		}

		// TextArea des logs
		setLog(new JTextArea());
		getLog().setEditable(false);
		getLog().setRows(5);
		scrollPane = new JScrollPane(getLog());

		fenetre.add(scrollPane, BorderLayout.NORTH);
		fenetre.add(panelTapis, BorderLayout.EAST);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);
		;

	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Joueur) {

		} else if (arg instanceof String) {
			String message = ((String) arg.toString());
			if (message.contains("gagant")) {
				JestControleur.majLog(message.toString());
			}
		}

	}
	
	public void setTrophe(ArrayList<Carte> trophes) {
		Iterator<Carte> it = trophes.iterator();
		StringBuffer sb = new StringBuffer("image/");
		while(it.hasNext()) {
			Carte carte = it.next();
			sb = new StringBuffer("image/");
			if(carte.getValeur() != Valeur.JOKER) {
				switch(carte.getValeur()) {
				case AS:
					sb.append("As");
					break;
				case DEUX:
					sb.append("Deux");
					break;
				case JOKER:
					break;
				case QUATRE:
					sb.append("Quatre");
					break;
				case SIX:
					break;
				case TROIS:
					sb.append("Trois");
					break;
				default:
					break;
				}
				switch(carte.getCouleur()) {
				case CARREAU:
					sb.append("Carreau");
					break;
				case COEUR:
					sb.append("Coeur");
					break;
				case PIC:
					sb.append("Pic");
					break;
				case TREFLE:
					sb.append("Trefle");
					break;
				default:
					break;	}
			}
			else if(carte.getValeur() == Valeur.JOKER) {
				sb.append("Joker");
			}
			sb.append(".png");
		}
		setTrophe1(sb.toString());
	}
	
	public void setTrophe1(String chemin) {
		this.trophe1.setIcon(new ImageIcon(chemin));
	}
	
	public void setTrophe2(String chemin) {
		this.trophe2.setIcon(new ImageIcon(chemin));
	}
	
	public void majTrophe1(Carte carte) {
		StringBuffer sb = new StringBuffer("image/");
		if(carte.getValeur() != Valeur.JOKER) {
			switch(carte.getValeur()) {
			case AS:
				sb.append("As");
				break;
			case DEUX:
				sb.append("Deux");
				break;
			case JOKER:
				break;
			case QUATRE:
				sb.append("Quatre");
				break;
			case SIX:
				break;
			case TROIS:
				sb.append("Trois");
				break;
			default:
				break;
			}
			switch(carte.getCouleur()) {
			case CARREAU:
				sb.append("Carreau");
				break;
			case COEUR:
				sb.append("Coeur");
				break;
			case PIC:
				sb.append("Pic");
				break;
			case TREFLE:
				sb.append("Trefle");
				break;
			default:
				break;	}
		}
		else if(carte.getValeur() == Valeur.JOKER) {
			sb.append("Joker");
		}
		sb.append(".png");
		this.trophe1.setIcon(new ImageIcon(sb.toString()));
		
	}

	public void effacePioche() {
		this.pioche.setIcon(new ImageIcon("image/tasVide.png"));
	}

	public JLabel getTrophe1() {
		return trophe1;
	}

	public JLabel getTrophe2() {
		return trophe2;
	}

	public Partie getModele() {
		return modele;
	}

	public void setModele(Partie modele) {
		this.modele = modele;
	}

	public PartieControleur getControleur() {
		return controleur;
	}

	public void setControleur(PartieControleur controleur) {
		this.controleur = controleur;
	}
	
	public void setLog(JTextArea log) {
		this.log = log;
	}
	
	public JTextArea getLog() {
		return log;
	}

}
