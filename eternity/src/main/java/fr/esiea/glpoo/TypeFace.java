package fr.esiea.glpoo;

public enum TypeFace {
	BORD("B"), FACE("F");
	
	private String lettre; 
	
	private TypeFace(String lettre){
		this.lettre=lettre;
	}
	
	private String getLettre(){
		return lettre;
	}
	
}
