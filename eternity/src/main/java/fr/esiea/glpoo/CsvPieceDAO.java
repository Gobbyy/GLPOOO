package fr.esiea.glpoo;

import java.util.ArrayList;
import java.util.List;

import fr.esiea.glpoo.Piece;
import static fr.esiea.glpoo.CsvFileHelper.readCsvFile;


public class CsvPieceDAO implements PieceDAO {

	private final static char SEPARATOR = ',';
	private final static String DOC = "src/csv/piece.csv";

	public List<Piece> findPiece() {

		final List<String[]> data = readCsvFile(DOC, SEPARATOR);

		final List<Piece> pieces = dataToPiece(data);

		return pieces ;
	}

	private List<Piece> dataToPiece(List<String[]> data) {
		final List<Piece> pieces = new ArrayList<Piece>();

		for (String[] oneData : data) {
//			final String typestr = oneData[0];
//			final String idstr = oneData[1];
//			final String couleurstr = oneData[2];
//
//			final TypeFace type = typestr.equalsIgnoreCase("B") ? BORD
//					: FACE;
//			final Integer id = (Integer.parseInt(idstr));
//			ColorFace color = null;
//			
//			switch (couleurstr) {
//			case "black":
//				color = BLACK;
//				break;
//			case "blue":
//				color = BLUE;
//				break;
//			case "green":
//				color = GREEN;
//				break;
//			case "rose":
//				color = ROSE;
//				break;
//			case "yellow":
//				color = YELLOW;
//				break;
//			default:
//				System.out.println("couleur inconnue !!");
//				break;
//			}
//
//			final Piece piece = new Piece();
//			pieces.add(piece);
		}

		return pieces;
	}
}
