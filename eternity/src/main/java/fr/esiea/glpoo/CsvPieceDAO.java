package fr.esiea.glpoo;

import java.util.ArrayList;
import java.util.List;

import fr.esiea.glpoo.Piece;
import static fr.esiea.glpoo.CsvFileHelper.readCsvFile;

public class CsvPieceDAO implements PieceDAO {

	private final static char SEPARATOR = ',';
	private static String DOC = "src/csv/piece.csv";

	final FaceDAO faceDao = new CsvFaceDao();
	final List<Face> faces = faceDao.findFace();
	
	public List<Piece> findPiece(String path) {

		final List<String[]> data = readCsvFile(path, SEPARATOR);

		final List<Piece> pieces = dataToPiece(data);

		return pieces;
	}

	private List<Piece> dataToPiece(List<String[]> data) {
		Face id_face_nord = null;
		Face id_face_sud = null;
		Face id_face_est = null;
		Face id_face_ouest = null;
		final List<Piece> pieces = new ArrayList<Piece>();
		
//		System.out.println(faces.get(0).getColor());
		
		for (String[] oneData : data) {
//			System.out.println("debut");
			final String type = oneData[0];
//			System.out.println(type);
			final String id_piecestr = oneData[1];
//			System.out.println(id_piecestr);
			final String id_fnstr = oneData[2];
//			System.out.println(id_fnstr);
			final String id_festr = oneData[3];
//			System.out.println(id_festr);
			final String id_fstr = oneData[4];
//			System.out.println(id_fstr);
			final String id_fostr = oneData[5];
//			System.out.println(id_fostr);

			final Integer id_piece = (Integer.parseInt(id_piecestr));

			for (Face face : faces) {
				if (Integer.parseInt(id_fnstr) == face.getId()) {
//					System.out.println("test"+face.getId());
					id_face_nord = new Face(Integer.parseInt(id_fnstr),
							face.getType(), face.getColor());
				}
				if (Integer.parseInt(id_fstr) == face.getId()) {
					id_face_sud = new Face(Integer.parseInt(id_fstr),
							face.getType(), face.getColor());
				}
				if (Integer.parseInt(id_fostr) == face.getId()) {
					id_face_ouest = new Face(Integer.parseInt(id_fostr),
							face.getType(), face.getColor());
				}
				if (Integer.parseInt(id_festr) == face.getId()) {
					id_face_est = new Face(Integer.parseInt(id_festr),
							face.getType(), face.getColor());
				}
			}

			final Piece piece = new Piece(id_piece, id_face_nord, id_face_sud,
					id_face_est, id_face_ouest);
//			System.out.println("piece :");
//			System.out.println(piece.getIdPiece());
//			System.out.println(piece.getNord().getId());
//			System.out.println(piece.getEst().getId());
//			System.out.println(piece.getSud().getId());
//			System.out.println(piece.getOuest().getId());
			pieces.add(piece);
		}

		return pieces;
	}

	@Override
	public List<Piece> findPiece() {
		// TODO Auto-generated method stub
		return null;
	}

}
