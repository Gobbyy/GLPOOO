package fr.esiea.glpoo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Chrono extends JPanel {

	private static final long serialVersionUID = 1L;

	private static Font f = new Font("Book Antiqua", Font.BOLD, 25);

	private Timer timer;

	private Color couleur = Color.orange;

	private int tempsRestant;

	private int temps;

	public Chrono(int N) {
		timer = createTimer();
		timer.start();
		setOpaque(false);
		setPreferredSize(new Dimension(72, 72));
		this.setTempsRestant(N);
		this.setTemps(N);
	}

	public Chrono(int N, Color couleur) {
		this.couleur = couleur;
		timer = createTimer();
		timer.start();
		setOpaque(false);
		setPreferredSize(new Dimension(72, 72));
		this.setTempsRestant(N);
		this.setTemps(N);
	}

	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	private Timer createTimer() {
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (tempsRestant > 0) {
					tempsRestant--;
					repaint();
				} else {
					timer.stop();
				}
			}
		};
		return new Timer(1000, action);
	}

	public void paintComponent(Graphics g) {
		this.drawCircle(g, 35, 35, 30);
	}

	public void drawCircle(Graphics cg, int xCenter, int yCenter, int r) {
		cg.setColor(Color.white);
		cg.fillOval(xCenter - r, yCenter - r, 2 * r, 2 * r);
		cg.setColor(couleur);
		cg.fillArc(xCenter - r, yCenter - r, 2 * r, 2 * r, 90,
				-(360 - tempsRestant * 360 / temps));
		cg.setColor(Color.black);
		cg.setFont(f);
		if (tempsRestant > 9) {
			cg.drawString("" + this.tempsRestant, 24, 42);
		} else {
			cg.drawString("0" + this.tempsRestant, 24, 42);
			if (tempsRestant == 0) {
				Object[] options = { "Rééssayer", "Quitter" };
				int n = JOptionPane
						.showOptionDialog(
								null,
								"Tu n'as pas terminé dans les temps !! Essaye encore !!",
								"Perdu", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.DEFAULT_OPTION, null, options,
								options[0]);
				System.out.println("ntimer : " + n);
				if (n == 0) {
					stop();
					AppFrameChrono test = null;
					try {
						test = new AppFrameChrono();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					test.fermer();
					JFrame f = null;
					try {
						f = new AppFrameChrono();

					} catch (IOException e1) {
						e1.printStackTrace();
					}
					f.setVisible(true);
				} else {
					AppFrameChrono test = null;
					try {
						test = new AppFrameChrono();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					test.fermer();
				}
			}
		}
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public int getTempsRestant() {
		return tempsRestant;
	}

	public void setTempsRestant(int tempsRestant) {
		this.tempsRestant = tempsRestant;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

}