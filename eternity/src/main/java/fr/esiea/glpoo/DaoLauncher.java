package fr.esiea.glpoo;
import java.util.List;
import java.util.Map;

import fr.esiea.glpoo.*;

public class DaoLauncher {

    public static void main(String[] args) {

        final FaceDAO faceDao = new CsvFaceDao();
        final List<Face> faces = faceDao.findFace();

        System.out.println("Liste Face");
        for (Face face : faces) {
            System.out.println(face.getType());
            System.out.println(face.getColor());
            System.out.println(face.getId());
        }

    }
}