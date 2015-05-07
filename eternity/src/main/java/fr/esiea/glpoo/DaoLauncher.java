package fr.esiea.glpoo;
import java.util.List;
import java.util.Map;

import fr.esiea.glpoo.*;

public class DaoLauncher {

    public static void main(String[] args) {

        final PieceDAO pieceDao = new CsvPieceDAO();
        final List<Piece> faces = pieceDao.findPiece();
//        System.out.println("Liste Face");
//        for (Piece face : faces) {
//            System.out.println(face.getIdPiece());
//        }

    }
}