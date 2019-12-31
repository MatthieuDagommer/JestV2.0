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
import fr.utt.rt.lo02.projet.modele.Joueur;
import fr.utt.rt.lo02.projet.modele.Partie;

public class VuePartie implements Observer{

	private Partie modele;
	
	private PartieControleur controleur;
	
	private ArrayList<Joueur> joueurs;
	
	private JFrame fenetre;
	
	private LinkedList<VueCarte> trophes;
	
	private JLabel pioche;
	
	private JTextArea log;
	
	private JScrollPane scrollPane;
	
	public VuePartie(final Partie modele) {
		
		trophes = new LinkedList<VueCarte>();
		this.setModele(modele);
		modele.addObserver(this);
		
		joueurs = modele.getJoueurs();
		
		fenetre = new JFrame("JEST");
		fenetre.setLayout(new BorderLayout());
		fenetre.setResizable(true);
		
		
		JPanel panelTapis = new JPanel();
		JLabel imgTapis = new JLabel(new ImageIcon("image/tapis.jpg"));
		
		pioche = new JLabel(new ImageIcon("image/dasCarte.jpg"));
		
		imgTapis.setLayout(new GridLayout());
		imgTapis.add(pioche);
		panelTapis.add(imgTapis);
		
		setLog(new JTextArea());
		getLog().setEditable(false);
		getLog().setRows(5);
		scrollPane = new JScrollPane(getLog());
		
		fenetre.add(scrollPane, BorderLayout.NORTH);
		//fenetre.add(scroll, BorderLayout.WEST);
		//fenetre.add(continuer, BorderLayout.SOUTH);
		fenetre.add(panelTapis, BorderLayout.EAST);
		
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);;
		
	}
	
	public void setLog(JTextArea log) {
		this.log = log;
	}
	
	public JTextArea getLog() {
		return log;
	}

	public Partie getModele() {
		return modele;
	}

	public void setModele(Partie modele) {
		this.modele = modele;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
