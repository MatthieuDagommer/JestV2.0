package fr.utt.rt.lo02.projet.vue;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.utt.rt.lo02.projet.modele.Joueur;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuInitialisation.
 */
public class MenuInitialisation extends JDialog {

	/** The joueur. */
	private Joueur joueur;

	/** The nom. */
	private JTextArea nom;

	/** The fenetre. */
	private JPanel fenetre;

	/** The ok. */
	private JButton ok;

	/** The nb. */
	private JTextArea nb;

	/** The extension. */
	private JTextArea extension;

	/** The variante. */
	private JTextArea variante;

	/**
	 * Instantiates a new menu initialisation.
	 *
	 * @param parent the parent
	 * @param title the title
	 * @param modal the modal
	 */
	public MenuInitialisation(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.init();
	}

	/**
	 * Inits the.
	 */
	private void init() {

		fenetre = new JPanel();
		fenetre.setLayout(new GridLayout(0, 2, 10, 10));

		JPanel pane = new JPanel();

		// Nom du joueur
		JLabel n = new JLabel("Votre nom :");
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

		// MouseListener qui vérifie que les champs sont remplis
		// avant de cacher la fenêtre
		ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (!nom.getText().equals("Nom"))
					setVisible(false);
				else
					ok.setText("!ok");

			}
		});

		fenetre.add(n);
		fenetre.add(nom);
		fenetre.add(var);
		fenetre.add(variante);
		fenetre.add(ext);
		fenetre.add(extension);
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
