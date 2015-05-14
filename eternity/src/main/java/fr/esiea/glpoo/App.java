package fr.esiea.glpoo;

import javax.swing.JFrame;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class App 
{

	public static void main(String[] args) throws IOException {

		final JFrame f = new BeginAppFrame();
//		final JFrame f = new EternityJFrame2();
		f.setVisible(true);
	}
}
