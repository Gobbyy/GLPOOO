package fr.esiea.glpoo;

import java.util.List;


public class Piece {
	private Face face_nord;
	private Face face_sud;
	private Face face_est;
	private Face face_ouest;
	private int id_piece;

	public Piece(int id_piece, Face face_nord, Face face_sud, Face face_est,
			Face face_ouest) {
		this.id_piece = id_piece;
		this.face_nord = face_nord;
		this.face_sud = face_sud;
		this.face_est = face_est;
		this.face_ouest = face_ouest;
	}

	public int getIdPiece(){
		return id_piece;
	}
	
	public Face getNord() {
		return face_nord;
	}

	public Face getSud() {
		return face_sud;
	}
	
	public Face getEst() {
		return face_est;
	}
	
	public Face getOuest() {
		return face_ouest;
	}
}
