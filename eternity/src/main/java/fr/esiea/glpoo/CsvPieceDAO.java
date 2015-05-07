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

		return pieces;
	}

	private List<Piece> dataToPiece(List<String[]> data) {
		final List<Piece> pieces = new ArrayList<Piece>();

		for (String[] oneData : data) {
			final String type = oneData[0];
			final String id_piecestr = oneData[1];
			final String id_fnstr = oneData[2];
			final String id_festr = oneData[3];
			final String id_fstr = oneData[4];
			final String id_fostr = oneData[5];

			
			final Integer id_piece = (Integer.parseInt(id_piecestr));
			
			final Face face_nord; 
			final Face face_sud;
			final Face face_est;
			final Face face_ouest;

			final Piece piece = new Piece();
			pieces.add(piece);
		}

		return pieces;
	}
}
