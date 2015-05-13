package fr.esiea.glpoo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BeginAppFrame extends JFrame {
	

	
	public BeginAppFrame(){
		setBounds(00, 00, 1400, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblProjetGlpooEternity = new JLabel("Projet GLPOO Eternity 2014-2015");
		java.awt.Font myFont = new java.awt.Font ("Comic Sans MS ", Font.BOLD , 40 ) ; 
		lblProjetGlpooEternity.setFont ( myFont ) ; 
		lblProjetGlpooEternity.setForeground(new Color(44, 117, 255));
		lblProjetGlpooEternity.setBounds(0, 0, 1350, 50);
		lblProjetGlpooEternity.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblProjetGlpooEternity);
		
		JButton btnModeCalme = new JButton("Mode Calme");
		btnModeCalme.addActionListener(new BoutonListener());
		btnModeCalme.setBounds(0, 700, 500, 29);
		panel.add(btnModeCalme);
		
		JButton btnModeTimer = new JButton("Mode Timer");
		btnModeTimer.setBounds(750, 700 , 500, 29);
		panel.add(btnModeTimer);
		
		ImagePanel image = new ImagePanel("src/images/image_debut.png");
		image.setBounds(350, 60, image.getPreferredSize().width, image.getPreferredSize().height);
		panel.add(image);
	}
	
	class BoutonListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			dispose();
			JFrame f = null;
			try {
				f = new AppFrame();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			f.setVisible(true);
		}
	}
	
}


