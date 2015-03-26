package fr.esiea.glpoo;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class EternityJFrame1 extends JFrame {
	public EternityJFrame1() {
		JFrame frame = new JFrame("Eternity");
		
		setTitle("Eternity");
		setPreferredSize(new Dimension(500, 400));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Menu
		ajoutDuMenu();
		
		pack();
	}
	
	private void ajoutDuMenu() {
		final JMenuBar menuBar = new JMenuBar();

		// Menu Fichier
		final JMenu menuFichier = new JMenu("Fichier");
		menuBar.add(menuFichier);

		// Sous-menus
		final JMenuItem menuOuvrir = new JMenuItem("Ouvrir");
		menuFichier.add(menuOuvrir);
		final JMenuItem menuSauver = new JMenuItem("Sauver");
		menuFichier.add(menuSauver);
		menuFichier.addSeparator();
		final JMenuItem menuQuitter = new JMenuItem(new QuitterAction("Quitter"));
		menuFichier.add(menuQuitter);

		// Menu Edition
		final JMenu menuEdition = new JMenu("Edition");
		menuBar.add(menuEdition);

		// Menu Affichage
		final JMenu menuAffichage = new JMenu("Affichage");
		menuBar.add(menuAffichage);

		// Menu ?
		final JMenu menuPointInterrogration = new JMenu("?");
		menuBar.add(menuPointInterrogration);
		final JMenuItem menuPreferences = new JMenuItem();
		menuPointInterrogration.add(menuPreferences);
		final JMenuItem menuAPropos = new JMenuItem();
		menuPointInterrogration.add(menuAPropos);

		// Ajout a la fenetre
		setJMenuBar(menuBar);
	}
	
	private class QuitterAction extends AbstractAction {
		public QuitterAction(String texte) {
			super(texte);
		}

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
