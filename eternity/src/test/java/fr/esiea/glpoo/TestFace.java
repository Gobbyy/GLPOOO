package fr.esiea.glpoo;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import fr.esiea.glpoo.Face;
import fr.esiea.glpoo.CsvFaceDao;

public class TestFace {
	
	private final static String DOC="src/csv/face_01.csv";
	
	protected CsvFaceDao facedao;
	
	@Before
	public void doBefore(){

		facedao = new CsvFaceDao();

	}
	
	@Test
	public void testCinqFaces(){
		
		final int test=5;
		final List<Face> faces= facedao.findFace();
		System.out.println("Faces"+faces.size());

		Assert.assertEquals(test, faces.size());
	}

}
