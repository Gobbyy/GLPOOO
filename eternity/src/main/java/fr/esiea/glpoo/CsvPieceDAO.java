package fr.esiea.glpoo;

import java.util.ArrayList;
import java.util.List;

import fr.esiea.glpoo.Piece;
import static fr.esiea.glpoo.CsvFileHelper.readCsvFile;

public class CsvPieceDAO implements PieceDAO {

	private final static char SEPARATOR = ',';
	private final static String DOC = "src/csv/piece.csv";

	final FaceDAO faceDao = new CsvFaceDao();
	final List<Face> faces = faceDao.findFace();

	public List<Piece> findPiece() {

		final List<String[]> data = readCsvFile(DOC, SEPARATOR);

		final List<Piece> pieces = dataToPiece(data);

		return pieces;
	}

	private List<Piece> dataToPiece(List<String[]> data) {
		Face face_nord = null;
		Face face_sud = null;
		Face face_est = null;
		Face face_ouest = null;
		final List<Piece> pieces = new ArrayList<Piece>();

		for (String[] oneData : data) {
			final String type = oneData[0];
			final String id_piecestr = oneData[1];
			final String id_fnstr = oneData[2];
			final String id_festr = oneData[3];
			final String id_fstr = oneData[4];
			final String id_fostr = oneData[5];

			final Integer id_piece = (Integer.parseInt(id_piecestr));

			for (Face face : faces) {
				if (Integer.parseInt(id_fnstr) == face.getId()) {
					face_nord = new Face(Integer.parseInt(id_fnstr),
							face.getType(), face.getColor());
				}
				if (Integer.parseInt(id_fstr) == face.getId()) {
					face_nord = new Face(Integer.parseInt(id_fstr),
							face.getType(), face.getColor());
				}
				if (Integer.parseInt(id_fostr) == face.getId()) {
					face_nord = new Face(Integer.parseInt(id_fostr),
							face.getType(), face.getColor());
				}
				if (Integer.parseInt(id_festr) == face.getId()) {
					face_nord = new Face(Integer.parseInt(id_festr),
							face.getType(), face.getColor());
				}
			}

			final Piece piece = new Piece(id_piece, face_nord, face_sud,
					face_est, face_ouest);
			//test
			pieces.add(piece);
		}

		return pieces;
	}
}
