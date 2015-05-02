package fr.esiea.glpoo;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*
;

public class JeuFrame extends JFrame{
	
	 private JPanel contentPane;
	 public JTable table;
	 ImageIcon icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8, icon9, icon10, icon11, icon12, icon13, icon14, icon15, icon16;
	 JSplitPane splitPane;
	 DefaultTableModel model;
	 JLabel label;
	 int angle = 90;
	 ImageIcon iconrot;
	 
	public static void main(String[] args) {
	        try {
	          JeuFrame frame = new JeuFrame();
	          frame.setVisible(true);
	        }
	        catch (Exception e) {
	          e.printStackTrace();
	        }
	}


	public JeuFrame() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0, 0, 1200, 675);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);
    
    splitPane = new JSplitPane();
    splitPane.setResizeWeight(0.5);
  
    icon1 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/1_1.png");
     icon2 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/1_2.png");
     icon3 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/1_3.png");
     icon4 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/1_4.png");
     icon5 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/2_1.png");
     icon6 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/2_2.png");
     icon7 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/2_3.png");
    icon8 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/2_4.png");
     icon9 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/3_1.png");
     icon10 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/3_2.png");
     icon11 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/3_3.png");
     icon12 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/3_4.png");
     icon13 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/4_1.png");
     icon14 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/4_2.png");
     icon15 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/4_3.png");
    icon16 = new ImageIcon("/Users/Maxime/git/GLPOOO/eternity/src/images/4_4.png");

    String[] columnNames = {"Section 1","Section 2","Section 1","Section 2"};
    Object[][] data =
    {
        /*{icon1, icon5, icon9,icon13},
        {icon2, icon6, icon10,icon14},
        {icon3, icon7, icon11,icon15},
        {icon4, icon8, icon12,icon16},*/
    		{icon1, icon2, icon3,icon4},
            {icon5, icon6, icon7,icon8},
            {icon9, icon10, icon11,icon12},
            {icon13, icon14, icon15,icon16},
        
    };

    model = new DefaultTableModel(data, columnNames);
    table = new JTable( model )
    {
            public Class getColumnClass(int column)
        {	
            return getValueAt(0, column).getClass();
        }
    };
    
    table.setRowHeight(160);
    table.getColumnModel().getColumn(0).setResizable(false);
    table.getColumnModel().getColumn(1).setResizable(false);
    table.getColumnModel().getColumn(2).setResizable(false);
    table.getColumnModel().getColumn(3).setResizable(false);
    
    splitPane.setLeftComponent(table);
    contentPane.add(splitPane, BorderLayout.CENTER);
    
    JButton bouton = new JButton("Modification");
    bouton.addActionListener(new BoutonListener()); 
    JButton rotate = new JButton("Rotation");
    rotate.addActionListener(new Bouton2Listener()); 
    Box container = Box.createVerticalBox();
    Box currentLine = null;  

    currentLine = Box.createHorizontalBox();
    currentLine.add(bouton);
    container.add(currentLine);
    
    currentLine = Box.createHorizontalBox();
    currentLine.add(rotate);
    container.add(currentLine);
    splitPane.setRightComponent(container);
	}
	
	public void exchange(int departrow, int departcol, int arriverow, int arrivecol){
		Object test = table.getValueAt(arriverow, arrivecol);		
		table.setValueAt(table.getValueAt(departrow, departcol),arriverow,arrivecol);
		table.setValueAt(test,departrow,departcol);
	}
	
	public void rotate(int row, int column){
		int w = ((ImageIcon) table.getValueAt(row, column)).getIconWidth();
        int h = ((ImageIcon) table.getValueAt(row, column)).getIconHeight();
        int type = BufferedImage.TYPE_INT_RGB;  // other options, see api
        BufferedImage image = new BufferedImage(h, w, type);
        Graphics2D g2 = image.createGraphics();
        double x = (h - w)/2.0;
        double y = (w - h)/2.0;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.rotate(Math.toRadians(angle), w/2.0, h/2.0);
        g2.drawImage(((ImageIcon) table.getValueAt(row, column)).getImage(), at, table);
        g2.dispose();
        iconrot = new ImageIcon(image);
        model.fireTableDataChanged();  
	}

	
	class BoutonListener implements ActionListener{
	    //Redéfinition de la méthode actionPerformed()
	    public void actionPerformed(ActionEvent arg0) {
	    	Scanner sc = new Scanner(System.in);
		    System.out.println("Veuillez saisir departrow :");
		    String strdr = sc.nextLine();
		    System.out.println("Vous avez saisi : " + strdr);
		    System.out.println("Veuillez saisir departcol :");
		    String strdc = sc.nextLine();
		    System.out.println("Vous avez saisi : " + strdc);
		    System.out.println("Veuillez saisir arrivecol :");
		    String strac = sc.nextLine();
		    System.out.println("Vous avez saisi : " + strac);
		    System.out.println("Veuillez saisir arriverow :");
		    String strar = sc.nextLine();
		    System.out.println("Vous avez saisi : " + strar);
		    int departrow; int departcol; int arriverow; int arrivecol;
		    departrow = Integer.parseInt(strdr);
		    departcol = Integer.parseInt(strdc);
		    arriverow = Integer.parseInt(strar);
		    arrivecol = Integer.parseInt(strac);
		    exchange(departrow, departcol, arriverow, arrivecol);
		    model.fireTableDataChanged();    
	    }
	  }
	      
	  //Classe écoutant notre second bouton
	  class Bouton2Listener implements ActionListener{
	    //Redéfinition de la méthode actionPerformed()
	    public void actionPerformed(ActionEvent e) {
	    	Scanner sc = new Scanner(System.in);
		    System.out.println("Veuillez saisir la ligne :");
		    String strdr = sc.nextLine();
		    System.out.println("Veuillez saisir la colonne :");
		    String strdc = sc.nextLine();
		    int row, col; 
		    row = Integer.parseInt(strdr);
		    col = Integer.parseInt(strdc);
	      String ac = e.getActionCommand();
	        if(ac.equals("CCW"))
	            angle = -90;
	        else
	            angle = 90;     // degrees clockwise
	        rotate(row,col);
	        table.setValueAt(iconrot,row,col);
	    }
	  }      
}