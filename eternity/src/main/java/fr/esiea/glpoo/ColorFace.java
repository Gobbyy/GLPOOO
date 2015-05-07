package fr.esiea.glpoo;

public enum ColorFace {
	BLACK("black", 1),BLUE("blue",2), GREEN("green",3), ROSE("rose", 4), YELLOW("yellow",5);
	
	private String couleur;
	private int color_id;
	
	private ColorFace(String couleur, int color_id){
		this.couleur=couleur;
		this.color_id=color_id;
	}
	
	private String getColor(){
		return couleur;
	}
	
	private int getColorId(){
		return color_id;
	}
	
}
