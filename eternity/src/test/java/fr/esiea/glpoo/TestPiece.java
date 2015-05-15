package fr.esiea.glpoo;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;


import static org.junit.Assert.*;

import org.junit.Test;

import fr.esiea.glpoo.Face;

public class TestPiece {
	
		Face face1a=new Face(1,TypeFace.BORD,ColorFace.BLACK);
		Face face1b=new Face(1,TypeFace.BORD,ColorFace.BLACK);
		Face face2=new Face(3,TypeFace.FACE,ColorFace.GREEN);
		Face face3=new Face(4,TypeFace.FACE,ColorFace.YELLOW);
		Face face4=new Face(5,TypeFace.FACE,ColorFace.BLACK);
		
		Piece piece1a=new Piece(1,face1a,face2,face3,face4);
		Piece piece1b=new Piece(1,face1a,face2,face3,face4);
		
		
		@Test
		public void testFaceSameColor(){
			assertEquals(face1a.getColor(), face1b.getColor());
		}
		@Test
		public void testFaceSameId(){
			assertEquals(face1a.getId(), face1b.getId());
		}
		@Test
		public void testFaceSameType(){
			assertEquals(face1a.getType(), face1b.getType());
		}
		@Test
		public void testFaceDifferent(){
			assertNotSame(face1a,face2);
			
		}
		@Test
		public void testPieceIdentiquePosNord(){
			assertEquals(piece1a.getNord(),piece1b.getNord());
		}
		@Test
		public void testPieceIdentiquePosSud(){
			assertEquals(piece1a.getSud(),piece1b.getSud());
		}
		@Test
		public void testPieceIdentiquePosEst(){
			assertEquals(piece1a.getEst(),piece1b.getEst());
		}
		@Test
		public void testPieceIdentiquePosOuest(){
			assertEquals(piece1a.getOuest(),piece1b.getOuest());
		}
		@Test
		public void testPieceIdentiquePosIdPiece(){
			assertEquals(piece1a.getIdPiece(),piece1b.getIdPiece());
		}

}
