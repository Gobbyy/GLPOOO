package fr.esiea.glpoo;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class EternityJFrame3 extends JFrame {

	
	public EternityJFrame3() {
		JFrame frame = new JFrame("Eternity");
		
		setBounds(100, 100, 1280, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);
		getContentPane().add(splitPane_1, BorderLayout.SOUTH);
		
		 ImageIcon icon1 = new ImageIcon("/Users/Maxime/Desktop/1_1.png");
		    ImageIcon icon2 = new ImageIcon("/Users/Maxime/Desktop/1_2.png");
		    ImageIcon icon3 = new ImageIcon("/Users/Maxime/Desktop/2_1.png");
		    ImageIcon icon4 = new ImageIcon("/Users/Maxime/Desktop/2_2.png");

		    String[] columnNames = {"Section 1","Section 2"};
		    Object[][] data =
		    {
		        {icon1 , icon2},
		        {icon3,icon4},{}
		    };

		    DefaultTableModel model = new DefaultTableModel(data, columnNames);
		    JTable table = new JTable( model )

		    {
		            public Class getColumnClass(int column)
		        {
		            return getValueAt(0, column).getClass();
		        }
		    };
		    
		    ImageIcon icon1bis = new ImageIcon("/Users/Maxime/Desktop/1_1.png");
		    
		    String[] columnNamesbis = {"Section 1"};
		    Object[][] databis =
		    {
		        {icon1bis}
		    };

		    DefaultTableModel modelbis = new DefaultTableModel(databis, columnNamesbis);
		    JTable tablebis = new JTable( modelbis )

		    {
		            public Class getColumnClass(int column)
		        {
		            return getValueAt(0, column).getClass();
		        }
		    };
		    
		    table.setRowHeight(160);
		    tablebis.setRowHeight(160);
		    //tablebis.setRowHeight(160);
		    //pane.setViewportView(table);
		    table.getColumnModel().getColumn(0).setResizable(false);
		    table.getColumnModel().getColumn(1).setResizable(false);
		   
		    splitPane.setLeftComponent(table);
		    splitPane.setRightComponent(tablebis);
	    
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


