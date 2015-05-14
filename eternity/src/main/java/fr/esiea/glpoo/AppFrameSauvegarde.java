package fr.esiea.glpoo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DropMode;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class AppFrameSauvegarde extends JFrame {

	private JPanel contentPane;
	public JTable table;
	JSplitPane splitPane;
	DefaultTableModel model;
	JLabel label;
	int angle = 90;
	int counterRight = 0, counterLeft = 0;
	ImageIcon iconrot;
	String strdr, strdc, strar, strac, strrr, strdd;
	JTextField textField1, textField2, textField3, textField4, textField5,
			textField6;
	String path = "src/csv/sauvegarde.csv";
	final PieceDAO pieceDao = new CsvPieceDAO();
	final List<Piece> pieces = pieceDao.findPiece(path);
	Piece[][] piece_tab = null;

	public AppFrameSauvegarde() throws IOException {

		piece_tab = new Piece[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				piece_tab[i][j] = pieces.get(j + (4 * i));
			}
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400, 800);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.80);

		String[] columnNames = { "Section 1", "Section 2", "Section 1",
				"Section 2" };

		Object[][] data = {

		{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }

		};

		model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);

		table.setRowHeight(160);
		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 4; k++) {
				addpiece(i, k);
			}
		}

		splitPane.setLeftComponent(table);
		contentPane.add(splitPane, BorderLayout.CENTER);

		JButton bouton = new JButton("Modification");
		bouton.addActionListener(new BoutonListener());
		JButton sauvegarde = new JButton("sauvegarde");
		sauvegarde.addActionListener(new Bouton5Listener());
		JButton rotate2 = new JButton("Rotation Droite");
		rotate2.addActionListener(new Bouton3Listener());
		JButton verification = new JButton("Verification");
		verification.addActionListener(new Bouton4Listener());
		Box container = Box.createVerticalBox();
		Box currentLine = null;
		JLabel tes = new JLabel("MODIFICATION");
		container.add(tes);

		JLabel test = new JLabel("Entrez les coordonnées de l'image à déplacer");
		container.add(test);

		textField1 = new JTextField();
		textField1.setColumns(1);
		textField1.setMaximumSize(textField1.getPreferredSize());
		container.add(textField1);
		textField2 = new JTextField();
		textField2.setColumns(1);
		textField2.setMaximumSize(textField2.getPreferredSize());
		container.add(textField2);

		JLabel test1 = new JLabel(
				"Entrez les coordonnées ou vous souhaitez déplacer l'image");
		container.add(test1);

		textField3 = new JTextField();
		textField3.setColumns(1);
		textField3.setMaximumSize(textField3.getPreferredSize());
		container.add(textField3);
		textField4 = new JTextField();
		textField4.setColumns(1);
		textField4.setMaximumSize(textField4.getPreferredSize());
		container.add(textField4);

		currentLine = Box.createHorizontalBox();
		currentLine.add(bouton);
		container.add(currentLine);

		JLabel te = new JLabel("ROTATION");
		container.add(te);

		JLabel test2 = new JLabel("Entrez les coordonnées de l'image à tourner");
		container.add(test2);

		textField5 = new JTextField();
		textField5.setColumns(1);
		textField5.setMaximumSize(textField5.getPreferredSize());
		container.add(textField5);
		textField6 = new JTextField();
		textField6.setColumns(1);
		textField6.setMaximumSize(textField6.getPreferredSize());
		container.add(textField6);

		currentLine = Box.createHorizontalBox();
		currentLine.add(rotate2);
		container.add(currentLine);
		currentLine = Box.createHorizontalBox();
		currentLine.add(verification);
		container.add(currentLine);
		currentLine = Box.createHorizontalBox();
		currentLine.add(sauvegarde);
		container.add(currentLine);

		splitPane.setRightComponent(container);
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		return table;
	}

	public void exchange(int departrow, int departcol) {

		int cordy = 0, cordx = 0;
		JPanel jp = null, jp1 = null, jp2 = null, jp3 = null;
		BorderLayout a = new BorderLayout();
		JPanel panel = new JPanel(a);

		cordy = cordy + departrow * 160;
		cordx = cordx + departcol * 160;

		panel.setBounds(cordx, cordy, 160, 160);
		panel.setLayout(null);

		jp = new ImagePanel("src/images/ouest_"
				+ piece_tab[departrow][departcol].getOuest().getColor()
				+ ".png");
		jp.setBounds(0, -2, jp.getPreferredSize().width,
				jp.getPreferredSize().height);
		jp.setOpaque(false);
		jp1 = new ImagePanel("src/images/est_"
				+ piece_tab[departrow][departcol].getEst().getColor() + ".png");
		jp1.setBounds(80, -2, jp1.getPreferredSize().width,
				jp1.getPreferredSize().height);
		jp1.setOpaque(false);
		jp2 = new ImagePanel("src/images/nord_"
				+ piece_tab[departrow][departcol].getNord().getColor() + ".png");
		jp2.setBounds(0, -2, jp2.getPreferredSize().width,
				jp2.getPreferredSize().height);
		jp2.setOpaque(false);
		jp3 = new ImagePanel("src/images/sud_"
				+ piece_tab[departrow][departcol].getSud().getColor() + ".png");
		jp3.setBounds(0, 78, jp3.getPreferredSize().width,
				jp3.getPreferredSize().height);
		jp3.setOpaque(false);

		panel.add(jp1);
		panel.add(jp2);
		panel.add(jp3);
		panel.add(jp);
		panel.setBackground(Color.WHITE);
		getTableCellRendererComponent(table, panel, true, true, departrow,
				departcol);

		panel.repaint();

	}

	public void addpiece(int row, int col) {
		int cordx = 0, cordy = 0;
		JPanel jp = null, jp1 = null, jp2 = null, jp3 = null;
		BorderLayout a = new BorderLayout();
		JPanel panel = new JPanel(a);
		cordy = cordy + row * 160;
		cordx = cordx + col * 160;
		panel.setBounds(cordx, cordy, 160, 160);
		panel.setLayout(null);

		System.out.println(piece_tab[row][col].getNord().getColor());
		System.out.println(piece_tab[row][col].getEst().getColor());
		System.out.println(piece_tab[row][col].getSud().getColor());
		System.out.println(piece_tab[row][col].getOuest().getColor());

		jp = new ImagePanel("src/images/ouest_"
				+ piece_tab[row][col].getOuest().getColor() + ".png");
		jp.setBounds(0, -2, jp.getPreferredSize().width,
				jp.getPreferredSize().height);
		jp.setOpaque(false);
		jp1 = new ImagePanel("src/images/est_"
				+ piece_tab[row][col].getEst().getColor() + ".png");
		jp1.setBounds(80, -2, jp1.getPreferredSize().width,
				jp1.getPreferredSize().height);
		jp1.setOpaque(false);
		jp2 = new ImagePanel("src/images/nord_"
				+ piece_tab[row][col].getNord().getColor() + ".png");
		jp2.setBounds(0, -2, jp2.getPreferredSize().width,
				jp2.getPreferredSize().height);
		jp2.setOpaque(false);
		jp3 = new ImagePanel("src/images/sud_"
				+ piece_tab[row][col].getSud().getColor() + ".png");
		jp3.setBounds(0, 78, jp3.getPreferredSize().width,
				jp3.getPreferredSize().height);
		jp3.setOpaque(false);
		panel.add(jp1);
		panel.add(jp2);
		panel.add(jp3);
		panel.add(jp);
		panel.setBackground(Color.WHITE);
		getTableCellRendererComponent(table, panel, true, true, row, col);
		jp = null;
		jp1 = null;
		jp2 = null;
		jp3 = null;
	}

	// public void rotateLeft(int row, int col) {
	// int cordx = 0, cordy = 0;
	// JPanel jp = null, jp1 = null, jp2 = null, jp3 = null;
	// BorderLayout a = new BorderLayout();
	// JPanel panel = new JPanel(a);
	// cordy = cordy + row * 160;
	// cordx = cordx + col * 160;
	// panel.setBounds(cordx, cordy, 160, 160);
	// panel.setLayout(null);
	//
	// System.out.println(piece_tab[row][col].getNord().getColor());
	// System.out.println(piece_tab[row][col].getEst().getColor());
	// System.out.println(piece_tab[row][col].getSud().getColor());
	// System.out.println(piece_tab[row][col].getOuest().getColor());
	// switch (counterLeft) {
	// case 1:
	// jp = new ImagePanel("src/images/ouest_"
	// + piece_tab[row][col].getNord().getColor() + ".png");
	// jp.setBounds(0, -2, jp.getPreferredSize().width,
	// jp.getPreferredSize().height);
	// jp.setOpaque(false);
	// jp1 = new ImagePanel("src/images/est_"
	// + piece_tab[row][col].getSud().getColor() + ".png");
	// jp1.setBounds(80, -2, jp1.getPreferredSize().width,
	// jp1.getPreferredSize().height);
	// jp1.setOpaque(false);
	// jp2 = new ImagePanel("src/images/nord_"
	// + piece_tab[row][col].getEst().getColor() + ".png");
	// jp2.setBounds(0, -2, jp2.getPreferredSize().width,
	// jp2.getPreferredSize().height);
	// jp2.setOpaque(false);
	// jp3 = new ImagePanel("src/images/sud_"
	// + piece_tab[row][col].getOuest().getColor() + ".png");
	// jp3.setBounds(0, 78, jp3.getPreferredSize().width,
	// jp3.getPreferredSize().height);
	// jp3.setOpaque(false);
	//
	// panel.add(jp1);
	// panel.add(jp2);
	// panel.add(jp3);
	// panel.add(jp);
	// panel.setBackground(Color.WHITE);
	// getTableCellRendererComponent(table, panel, true, true, row, col);
	// panel.repaint();
	// jp = null;
	// jp1 = null;
	// jp2 = null;
	// jp3 = null;
	// break;
	// case 2:
	// jp = new ImagePanel("src/images/ouest_"
	// + piece_tab[row][col].getEst().getColor() + ".png");
	// jp.setBounds(0, -2, jp.getPreferredSize().width,
	// jp.getPreferredSize().height);
	// jp.setOpaque(false);
	// jp1 = new ImagePanel("src/images/est_"
	// + piece_tab[row][col].getOuest().getColor() + ".png");
	// jp1.setBounds(80, -2, jp1.getPreferredSize().width,
	// jp1.getPreferredSize().height);
	// jp1.setOpaque(false);
	// jp2 = new ImagePanel("src/images/nord_"
	// + piece_tab[row][col].getSud().getColor() + ".png");
	// jp2.setBounds(0, -2, jp2.getPreferredSize().width,
	// jp2.getPreferredSize().height);
	// jp2.setOpaque(false);
	// jp3 = new ImagePanel("src/images/sud_"
	// + piece_tab[row][col].getNord().getColor() + ".png");
	// jp3.setBounds(0, 78, jp3.getPreferredSize().width,
	// jp3.getPreferredSize().height);
	// jp3.setOpaque(false);
	//
	// panel.add(jp1);
	// panel.add(jp2);
	// panel.add(jp3);
	// panel.add(jp);
	// panel.setBackground(Color.WHITE);
	// getTableCellRendererComponent(table, panel, true, true, row, col);
	// panel.repaint();
	// jp = null;
	// jp1 = null;
	// jp2 = null;
	// jp3 = null;
	// break;
	// case 3:
	// jp = new ImagePanel("src/images/ouest_"
	// + piece_tab[row][col].getSud().getColor() + ".png");
	// jp.setBounds(0, -2, jp.getPreferredSize().width,
	// jp.getPreferredSize().height);
	// jp.setOpaque(false);
	// jp1 = new ImagePanel("src/images/est_"
	// + piece_tab[row][col].getNord().getColor() + ".png");
	// jp1.setBounds(80, -2, jp1.getPreferredSize().width,
	// jp1.getPreferredSize().height);
	// jp1.setOpaque(false);
	// jp2 = new ImagePanel("src/images/nord_"
	// + piece_tab[row][col].getOuest().getColor() + ".png");
	// jp2.setBounds(0, -2, jp2.getPreferredSize().width,
	// jp2.getPreferredSize().height);
	// jp2.setOpaque(false);
	// jp3 = new ImagePanel("src/images/sud_"
	// + piece_tab[row][col].getEst().getColor() + ".png");
	// jp3.setBounds(0, 78, jp3.getPreferredSize().width,
	// jp3.getPreferredSize().height);
	// jp3.setOpaque(false);
	//
	// panel.add(jp1);
	// panel.add(jp2);
	// panel.add(jp3);
	// panel.add(jp);
	// panel.setBackground(Color.WHITE);
	// getTableCellRendererComponent(table, panel, true, true, row, col);
	// panel.repaint();
	// jp = null;
	// jp1 = null;
	// jp2 = null;
	// jp3 = null;
	// break;
	// case 4:
	// jp = new ImagePanel("src/images/ouest_"
	// + piece_tab[row][col].getOuest().getColor() + ".png");
	// jp.setBounds(0, -2, jp.getPreferredSize().width,
	// jp.getPreferredSize().height);
	// jp.setOpaque(false);
	// jp1 = new ImagePanel("src/images/est_"
	// + piece_tab[row][col].getEst().getColor() + ".png");
	// jp1.setBounds(80, -2, jp1.getPreferredSize().width,
	// jp1.getPreferredSize().height);
	// jp1.setOpaque(false);
	// jp2 = new ImagePanel("src/images/nord_"
	// + piece_tab[row][col].getNord().getColor() + ".png");
	// jp2.setBounds(0, -2, jp2.getPreferredSize().width,
	// jp2.getPreferredSize().height);
	// jp2.setOpaque(false);
	// jp3 = new ImagePanel("src/images/sud_"
	// + piece_tab[row][col].getSud().getColor() + ".png");
	// jp3.setBounds(0, 78, jp3.getPreferredSize().width,
	// jp3.getPreferredSize().height);
	// jp3.setOpaque(false);
	//
	// panel.add(jp1);
	// panel.add(jp2);
	// panel.add(jp3);
	// panel.add(jp);
	// panel.setBackground(Color.WHITE);
	// getTableCellRendererComponent(table, panel, true, true, row, col);
	// panel.repaint();
	// jp = null;
	// jp1 = null;
	// jp2 = null;
	// jp3 = null;
	// break;
	//
	// default:
	// System.out.println("probleme rotation Gauche");
	// }
	// }

	public boolean verification() {
		boolean verif = true;

		for (int i = 0; i < 4; i++) {
			System.out.println("test [0][i] : "
					+ piece_tab[0][i].getNord().getColor().toString());
			if (piece_tab[0][i].getNord().getColor().toString() != "BLACK") {
				verif = false;
			}
		}
		for (int i = 0; i < 4; i++) {
			System.out.println("test [3][i] : "
					+ piece_tab[3][i].getSud().getColor().toString());
			if (piece_tab[3][i].getSud().getColor().toString() != "BLACK") {
				verif = false;
			}
		}
		for (int i = 0; i < 4; i++) {
			System.out.println("test [i][0] : "
					+ piece_tab[i][0].getOuest().getColor().toString());
			if (piece_tab[i][0].getOuest().getColor().toString() != "BLACK") {
				verif = false;
			}
		}
		for (int i = 0; i < 4; i++) {
			System.out.println("test [i][3] : "
					+ piece_tab[i][3].getEst().getColor().toString());
			if (piece_tab[i][3].getEst().getColor().toString() != "BLACK") {
				verif = false;
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.println("test [0][j+1,j] : "
						+ piece_tab[i][j + 1].getOuest().getColor().toString()
						+ piece_tab[i][j].getEst().getColor().toString());
				if (piece_tab[i][j + 1].getOuest().getColor().toString() != piece_tab[i][j]
						.getEst().getColor().toString()) {
					verif = false;
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.println("test [0][j+1,j] : "
						+ piece_tab[i][j].getSud().getColor().toString()
						+ piece_tab[i + 1][j].getNord().getColor().toString());
				if (piece_tab[i][j].getSud().getColor().toString() != piece_tab[i + 1][j]
						.getNord().getColor().toString()) {
					verif = false;
				}
			}
		}

		return verif;
	}

	public void rotateRight(int row, int col) {
		int cordx = 0, cordy = 0;
		JPanel jp = null, jp1 = null, jp2 = null, jp3 = null;
		BorderLayout a = new BorderLayout();
		JPanel panel = new JPanel(a);
		cordy = cordy + row * 160;
		cordx = cordx + col * 160;
		panel.setBounds(cordx, cordy, 160, 160);
		panel.setLayout(null);
		switch (counterRight) {
		case 1:
			jp = new ImagePanel("src/images/ouest_"
					+ piece_tab[row][col].getSud().getColor() + ".png");
			jp.setBounds(0, -2, jp.getPreferredSize().width,
					jp.getPreferredSize().height);
			jp.setOpaque(false);
			jp1 = new ImagePanel("src/images/est_"
					+ piece_tab[row][col].getNord().getColor() + ".png");
			jp1.setBounds(80, -2, jp1.getPreferredSize().width,
					jp1.getPreferredSize().height);
			jp1.setOpaque(false);
			jp2 = new ImagePanel("src/images/nord_"
					+ piece_tab[row][col].getOuest().getColor() + ".png");
			jp2.setBounds(0, -2, jp2.getPreferredSize().width,
					jp2.getPreferredSize().height);
			jp2.setOpaque(false);
			jp3 = new ImagePanel("src/images/sud_"
					+ piece_tab[row][col].getEst().getColor() + ".png");
			jp3.setBounds(0, 78, jp3.getPreferredSize().width,
					jp3.getPreferredSize().height);
			jp3.setOpaque(false);

			panel.add(jp1);
			panel.add(jp2);
			panel.add(jp3);
			panel.add(jp);
			panel.setBackground(Color.WHITE);
			getTableCellRendererComponent(table, panel, true, true, row, col);
			panel.repaint();
			jp = null;
			jp1 = null;
			jp2 = null;
			jp3 = null;
			break;
		case 2:
			jp = new ImagePanel("src/images/ouest_"
					+ piece_tab[row][col].getEst().getColor() + ".png");
			jp.setBounds(0, -2, jp.getPreferredSize().width,
					jp.getPreferredSize().height);
			jp.setOpaque(false);
			jp1 = new ImagePanel("src/images/est_"
					+ piece_tab[row][col].getOuest().getColor() + ".png");
			jp1.setBounds(80, -2, jp1.getPreferredSize().width,
					jp1.getPreferredSize().height);
			jp1.setOpaque(false);
			jp2 = new ImagePanel("src/images/nord_"
					+ piece_tab[row][col].getSud().getColor() + ".png");
			jp2.setBounds(0, -2, jp2.getPreferredSize().width,
					jp2.getPreferredSize().height);
			jp2.setOpaque(false);
			jp3 = new ImagePanel("src/images/sud_"
					+ piece_tab[row][col].getNord().getColor() + ".png");
			jp3.setBounds(0, 78, jp3.getPreferredSize().width,
					jp3.getPreferredSize().height);
			jp3.setOpaque(false);

			panel.add(jp1);
			panel.add(jp2);
			panel.add(jp3);
			panel.add(jp);
			panel.setBackground(Color.WHITE);
			getTableCellRendererComponent(table, panel, true, true, row, col);
			panel.repaint();
			jp = null;
			jp1 = null;
			jp2 = null;
			jp3 = null;
			break;
		case 3:
			jp = new ImagePanel("src/images/ouest_"
					+ piece_tab[row][col].getNord().getColor() + ".png");
			jp.setBounds(0, -2, jp.getPreferredSize().width,
					jp.getPreferredSize().height);
			jp.setOpaque(false);
			jp1 = new ImagePanel("src/images/est_"
					+ piece_tab[row][col].getSud().getColor() + ".png");
			jp1.setBounds(80, -2, jp1.getPreferredSize().width,
					jp1.getPreferredSize().height);
			jp1.setOpaque(false);
			jp2 = new ImagePanel("src/images/nord_"
					+ piece_tab[row][col].getEst().getColor() + ".png");
			jp2.setBounds(0, -2, jp2.getPreferredSize().width,
					jp2.getPreferredSize().height);
			jp2.setOpaque(false);
			jp3 = new ImagePanel("src/images/sud_"
					+ piece_tab[row][col].getOuest().getColor() + ".png");
			jp3.setBounds(0, 78, jp3.getPreferredSize().width,
					jp3.getPreferredSize().height);
			jp3.setOpaque(false);

			panel.add(jp1);
			panel.add(jp2);
			panel.add(jp3);
			panel.add(jp);
			panel.setBackground(Color.WHITE);
			getTableCellRendererComponent(table, panel, true, true, row, col);
			panel.repaint();
			jp = null;
			jp1 = null;
			jp2 = null;
			jp3 = null;
			break;
		case 4:
			jp = new ImagePanel("src/images/ouest_"
					+ piece_tab[row][col].getOuest().getColor() + ".png");
			jp.setBounds(0, -2, jp.getPreferredSize().width,
					jp.getPreferredSize().height);
			jp.setOpaque(false);
			jp1 = new ImagePanel("src/images/est_"
					+ piece_tab[row][col].getEst().getColor() + ".png");
			jp1.setBounds(80, -2, jp1.getPreferredSize().width,
					jp1.getPreferredSize().height);
			jp1.setOpaque(false);
			jp2 = new ImagePanel("src/images/nord_"
					+ piece_tab[row][col].getNord().getColor() + ".png");
			jp2.setBounds(0, -2, jp2.getPreferredSize().width,
					jp2.getPreferredSize().height);
			jp2.setOpaque(false);
			jp3 = new ImagePanel("src/images/sud_"
					+ piece_tab[row][col].getSud().getColor() + ".png");
			jp3.setBounds(0, 78, jp3.getPreferredSize().width,
					jp3.getPreferredSize().height);
			jp3.setOpaque(false);

			panel.add(jp1);
			panel.add(jp2);
			panel.add(jp3);
			panel.add(jp);
			panel.setBackground(Color.WHITE);
			getTableCellRendererComponent(table, panel, true, true, row, col);
			panel.repaint();
			jp = null;
			jp1 = null;
			jp2 = null;
			jp3 = null;
			break;

		default:
			System.out.println("probleme rotation droite");
		}
	}

	// is type of number
	public static boolean isInteger(String s) {
		boolean isInteger = true;
		for (int i = 0; i < s.length() && isInteger; i++) {
			char c = s.charAt(i);
			isInteger = isInteger & ((c >= '0' && c <= '3'));
		}
		return isInteger;
	}

	class BoutonListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {

			strdr = textField1.getText();
			strdc = textField2.getText();
			strar = textField3.getText();
			strac = textField4.getText();
			int departrow = 0;
			int departcol = 0;
			int arriverow = 0;
			int arrivecol = 0;
			if (isInteger(strdr) == true && isInteger(strdc) == true
					&& isInteger(strar) == true && isInteger(strac) == true) {
				departrow = Integer.parseInt(strdc);
				departcol = Integer.parseInt(strdr);
				arriverow = Integer.parseInt(strac);
				arrivecol = Integer.parseInt(strar);
				Piece[][] piece_tmp = new Piece[1][1];
				piece_tmp[0][0] = piece_tab[departrow][departcol];
				piece_tab[departrow][departcol] = piece_tab[arriverow][arrivecol];
				piece_tab[arriverow][arrivecol] = piece_tmp[0][0];
				exchange(departrow, departcol);
				exchange(arriverow, arrivecol);
				model.fireTableDataChanged();
			} else {

			}
		}
	}

	// class Bouton2Listener implements ActionListener {
	//
	// public void actionPerformed(ActionEvent e) {
	// strrr = textField5.getText();
	// strdd = textField6.getText();
	// int row, col;
	// row = Integer.parseInt(strdd);
	// col = Integer.parseInt(strrr);
	// if (isInteger(strrr) == true && isInteger(strdd)) {
	// angle = 90; // degrees clockwise
	// counterLeft = (counterLeft + 1) % 5;
	// if (counterLeft == 0) {
	// counterLeft++;
	// }
	// rotateLeft(row, col);
	// table.setValueAt(iconrot, row, col);
	// }
	// }
	// }

	class Bouton3Listener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			strrr = textField5.getText();
			strdd = textField6.getText();
			int row, col;
			row = Integer.parseInt(strdd);
			col = Integer.parseInt(strrr);
			if (isInteger(strrr) == true && isInteger(strdd)) {
				angle = -90; // degrees clockwise
				counterRight = (counterRight + 1) % 5;
				if (counterRight == 0) {
					counterRight++;
				}
				System.out.println("counter" + counterRight);
				rotateRight(row, col);
				table.setValueAt(iconrot, row, col);
			}
		}
	}

	class Bouton4Listener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			boolean a = verification();
			System.out.println(a);
			if (a == true) {
				Object[] options = { "Quitter" };
				int n = JOptionPane.showOptionDialog(null,
						"Bravo, tu as terminé !!", "Gagné",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.DEFAULT_OPTION, null, options, options[0]);
				System.out.println("n" + n);
				dispose();
			} else {
				Object[] options = { "Continuer" };
				int n = JOptionPane.showOptionDialog(null,
						"Perdu, essaye encore !", "Perdu",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.DEFAULT_OPTION, null, options, options[0]);
				System.out.println("n" + n);
				if (n == 0) {

				} else {
					System.out.println("Cancelled");
				}
			}
		}
	}

	class Bouton5Listener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

				Date maDate = new Date(); 
				String adressedufichier = "src/csv/sauvegarde.csv";

				try
				{
					PrintWriter pw =  new PrintWriter(new BufferedWriter(new FileWriter("src/csv/sauvegarde.csv", false)));
					FileWriter fw = new FileWriter(adressedufichier, true);
					pw.println("");
					
					BufferedWriter output = new BufferedWriter(fw);
					
					output.write("# P,id_piece,id_forme_nord,id_forme_est,id_forme_sud,id_forme_ouest\r");
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 4; j++) {
							output.write("P,");
							output.write(piece_tab[i][j].getIdPiece()+",");
							output.write(piece_tab[i][j].getNord().getId()+",");
							output.write(piece_tab[i][j].getEst().getId()+",");
							output.write(piece_tab[i][j].getSud().getId()+",");
							output.write(piece_tab[i][j].getOuest().getId()+"");
							
								output.write("\r");

							
						}
					}
					
					
					output.flush();
					
					
					output.close();
					
					System.out.println("fichier créé");
				}
				catch(IOException ioe){
					System.out.print("Erreur : ");
					ioe.printStackTrace();
					}

		}
			
			
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		table.add((Component) value, row, column);
		return this;
	}
	
}
