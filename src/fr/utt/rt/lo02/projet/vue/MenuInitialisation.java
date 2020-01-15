package fr.utt.rt.lo02.projet.vue;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;

import fr.utt.rt.lo02.projet.modele.Joueur;

// TODO: Auto-generated Javadoc
/**
 * Classe de la fenetre qui permet l'initialisation du jeu.
 * On peut y choisir le nombre de joueur, la variante, l'xtension et notre nom
 */
public class MenuInitialisation extends JDialog {

	/** le nom du joueur physique */
	private JTextArea nom;

	/** la fenetre */
	private JPanel fenetre;

	/** le bouton ok */
	private JButton ok;

	/** zone de texte pour l'extension */
	private JTextArea extension;

	/** zone de texte pour la variante */
	private JTextArea variante;
	
	/** Slider pour les joueurs physique */
	private JSlider joueurP;
	
	/** Slider pour les joueurs virtuels. */
	private JSlider joueurV;

	/**
	 * Constructeur de la classe.
	 * Il cree la fenetre avec tout les element permetant l'interaction avec l'utilisateur
	 *
	 * @param parent the parent
	 * @param title le titre de la fenetre
	 * @param modal the modal
	 */
	public MenuInitialisation(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.init();
	}

	/**
	 * Initialise la fenetre avec tous les elements
	 */
	private void init() {

		fenetre = new JPanel();
		fenetre.setLayout(new GridLayout(0, 2, 10, 10));

		JPanel pane = new JPanel();

		// Nom du joueur si il y a un joueur
		JLabel n = new JLabel("Combien de joueurs physiques ?");
		nom = new JTextArea();
		nom.setText("Nom");
		nom.setEditable(true);
		
		
		
		
		

		// extension
		JLabel ext = new JLabel("Quelle extension ?");
		extension = new JTextArea();
		extension.setText("0");
		extension.setEditable(true);

		// variante
		JLabel var = new JLabel("Quelle variante ?");
		variante = new JTextArea();
		variante.setText("0");
		variante.setEditable(true);

		ok = new JButton("Ok");
		
		// Permet de verifier que les champs ne sont pas vide et que le nombre de joueur est compatible
		ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (!nom.getText().equals("Nom") && joueurP.getValue()+joueurV.getValue()<5 && joueurP.getValue()+joueurV.getValue()>2) {
					setVisible(false);
				}
					
				else
					ok.setText("Erreur de saisie, recommencez");
				
			}
		});
		
		joueurP = new JSlider();
		joueurP.setPaintTicks(true);
		joueurP.setPaintLabels(true);
		joueurP.setRequestFocusEnabled(false);
		joueurP.setMaximum(4);
		joueurP.setMajorTickSpacing(1);
		      
		joueurV = new JSlider();
		joueurV.setPaintTicks(true);
		joueurV.setPaintLabels(true);
		joueurV.setRequestFocusEnabled(false);
		joueurV.setMaximum(4);
		joueurV.setMajorTickSpacing(1);
		      
		JLabel physique = new JLabel("Combien de joueurs virtuels ?");
		
		
		fenetre.add(joueurP);
		fenetre.add(n);
		fenetre.add(joueurV);
		fenetre.add(physique);
		fenetre.add(var);
		fenetre.add(variante);
		fenetre.add(ext);
		fenetre.add(extension);
		fenetre.add(nom);
		fenetre.add(ok);

		pane.add(fenetre);

		this.getContentPane().add(pane);
		this.pack();

	}
	
	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public int getExtension() {
		return Integer.parseInt(extension.getText());
	}

	/**
	 * Gets the joueur P.
	 *
	 * @return the joueur P
	 */
	public int getJoueurP() {
		return joueurP.getValue();
	}
	
	/**
	 * Gets the joueur V.
	 *
	 * @return the joueur V
	 */
	public int getJoueurV() {
		return joueurV.getValue();
	}
	
	
	/**
	 * Sets the extension.
	 *
	 * @param extension the new extension
	 */
	public void setExtension(JTextArea extension) {
		this.extension = extension;
	}

	/**
	 * Gets the variante.
	 *
	 * @return the variante
	 */
	public int getVariante() {
		return Integer.parseInt(variante.getText());
	}

	/**
	 * Sets the variante.
	 *
	 * @param variante the new variante
	 */
	public void setVariante(JTextArea variante) {
		this.variante = variante;
	}
	
	/**
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	public String getNom() {
		return nom.getText();
	}
}
