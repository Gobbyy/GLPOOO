package fr.esiea.glpoo;

import java.util.ArrayList;
import java.util.List;

import fr.esiea.glpoo.Face;
import static fr.esiea.glpoo.CsvFileHelper.readCsvFile;
import static fr.esiea.glpoo.TypeFace.BORD;
import static fr.esiea.glpoo.TypeFace.FACE;
import static fr.esiea.glpoo.ColorFace.BLACK;
import static fr.esiea.glpoo.ColorFace.BLUE;
import static fr.esiea.glpoo.ColorFace.GREEN;
import static fr.esiea.glpoo.ColorFace.ROSE;
import static fr.esiea.glpoo.ColorFace.YELLOW;

public class CsvFaceDao implements FaceDAO {

	private final static char SEPARATOR = ';';
	private final static String DOC = "src/csv/face_01.csv";

	public List<Face> findFace() {

		final List<String[]> data = readCsvFile(DOC, SEPARATOR);

		final List<Face> eleves = dataToFace(data);

		return eleves;
	}

	private List<Face> dataToFace(List<String[]> data) {
		final List<Face> faces = new ArrayList<Face>();

		for (String[] oneData : data) {
			final String typestr = oneData[0];
			final String idstr = oneData[1];
			final String couleurstr = oneData[2];

			final TypeFace type = typestr.equalsIgnoreCase("B") ? BORD : FACE;
			final Integer id = (Integer.parseInt(idstr));
			ColorFace color = null;

			switch (couleurstr) {
			case "black":
				color = BLACK;
				break;
			case "blue":
				color = BLUE;
				break;
			case "green":
				color = GREEN;
				break;
			case "rose":
				color = ROSE;
				break;
			case "yellow":
				color = YELLOW;
				break;
			default:
				System.out.println("couleur inconnue !!");
				break;
			}

			final Face face = new Face(id, type, color);
			faces.add(face);
		}

		return faces;
	}
}
