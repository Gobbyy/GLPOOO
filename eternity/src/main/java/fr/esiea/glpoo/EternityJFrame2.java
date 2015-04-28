package fr.esiea.glpoo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class EternityJFrame2 extends JFrame {
	public JPanel Panelcontenu;
	
	public EternityJFrame2() {
		JFrame frame = new JFrame("Eternity");
		
		setTitle("Eternity");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 1280, 900);
	    Panelcontenu = new JPanel();
	    Panelcontenu.setBorder(new EmptyBorder(5, 5, 5, 5));
	    Panelcontenu.setLayout(new BorderLayout(0, 0));
	    setContentPane(Panelcontenu);
	    
	    JLabel label3 = new JLabel("c");
	    JSplitPane splitPane = new JSplitPane();
	    splitPane.setResizeWeight(0.5);
	    Panelcontenu.add(splitPane, BorderLayout.CENTER);

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
	    ImageIcon icon2bis = new ImageIcon("/Users/Maxime/Desktop/1_2.png");
	    ImageIcon icon3bis = new ImageIcon("/Users/Maxime/Desktop/2_1.png");
	    ImageIcon icon4bis = new ImageIcon("/Users/Maxime/Desktop/2_2.png");
	    
	    String[] columnNamesbis = {"Section 1","Section 2"};
	    Object[][] databis =
	    {
	    		{icon1bis , icon2bis},
		        {icon3bis,icon4bis}
	    };

	    DefaultTableModel modelbis = new DefaultTableModel(databis, columnNamesbis);
	    JTable tablebis = new JTable( modelbis )

	    {
	            public Class getColumnClass(int column)
	        {
	            return getValueAt(0, column).getClass();
	        }
	    };


	    //table.setPreferredScrollableViewportSize(table.getPreferredSize());

	    //table.setBackground(new Color(255, 255, 204));
	    //JScrollPane pane = new JScrollPane();
	    //JScrollPane scrollPane = new JScrollPane( table );
	    //getContentPane().add( scrollPane );

	    table.setRowHeight(160);
	    tablebis.setRowHeight(160);
	    //tablebis.setRowHeight(160);
	    //pane.setViewportView(table);
	    table.getColumnModel().getColumn(0).setResizable(false);
	    table.getColumnModel().getColumn(1).setResizable(false);
	   
	    splitPane.setLeftComponent(table);
	    Box container = Box.createVerticalBox();
	    Box currentLine = null;  

	    currentLine = Box.createHorizontalBox();
	    currentLine.add(new Chrono(60));
	    container.add(currentLine);
	    
	    currentLine = Box.createHorizontalBox();
	    currentLine.add(tablebis);
	    container.add(currentLine);
	    
	    //splitPane.setRightComponent(new Chrono(60));
	    splitPane.setRightComponent(container);
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
