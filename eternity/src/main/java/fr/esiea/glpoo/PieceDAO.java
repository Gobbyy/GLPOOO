package fr.esiea.glpoo;

import java.util.ArrayList;
import java.util.List;

public interface PieceDAO {
	List<Piece> findPiece(String path);
	List<Piece> findPiece();

}
