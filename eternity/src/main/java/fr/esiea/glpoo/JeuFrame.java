import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



public class JeuFrame extends JFrame implements ActionListener{
	
	 private JPanel contentPane;
	 public JTable table;
	 ImageIcon icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8, icon9, icon10, icon11, icon12, icon13, icon14, icon15, icon16;
	 JSplitPane splitPane;
	 DefaultTableModel model;
	 
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
    
    icon1 = new ImageIcon("/Users/Maxime/Desktop/1_1.png");
     icon2 = new ImageIcon("/Users/Maxime/Desktop/1_2.png");
     icon3 = new ImageIcon("/Users/Maxime/Desktop/1_3.png");
     icon4 = new ImageIcon("/Users/Maxime/Desktop/1_4.png");
     icon5 = new ImageIcon("/Users/Maxime/Desktop/2_1.png");
     icon6 = new ImageIcon("/Users/Maxime/Desktop/2_2.png");
     icon7 = new ImageIcon("/Users/Maxime/Desktop/2_3.png");
    icon8 = new ImageIcon("/Users/Maxime/Desktop/2_4.png");
     icon9 = new ImageIcon("/Users/Maxime/Desktop/3_1.png");
     icon10 = new ImageIcon("/Users/Maxime/Desktop/3_2.png");
     icon11 = new ImageIcon("/Users/Maxime/Desktop/3_3.png");
     icon12 = new ImageIcon("/Users/Maxime/Desktop/3_4.png");
     icon13 = new ImageIcon("/Users/Maxime/Desktop/4_1.png");
     icon14 = new ImageIcon("/Users/Maxime/Desktop/4_2.png");
     icon15 = new ImageIcon("/Users/Maxime/Desktop/4_3.png");
    icon16 = new ImageIcon("/Users/Maxime/Desktop/4_4.png");

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
    bouton.addActionListener(this); 
    splitPane.setRightComponent(bouton);
    contentPane.add(splitPane, BorderLayout.CENTER);
	}
	
	public void exchange(int departrow, int departcol, int arriverow, int arrivecol){
		Object test = table.getValueAt(arriverow, arrivecol);		
		table.setValueAt(table.getValueAt(departrow, departcol),arriverow,arrivecol);
		table.setValueAt(test,departrow,departcol);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
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
