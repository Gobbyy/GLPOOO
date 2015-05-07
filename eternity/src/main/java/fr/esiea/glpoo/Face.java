package fr.esiea.glpoo;


public class Face {
	private TypeFace type;
	private ColorFace color;
	private int id;
	
	public Face(int id,TypeFace type, ColorFace color){
		this.id=id;
		this.type=type;
		this.color=color;
	}
	
	public TypeFace getType(){
		return type;
	}
	
	public int getId(){
		return id;
	}
	
	public ColorFace getColor(){
		return color;
	}
	
}
