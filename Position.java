
public class Position 
{
	


	private int colonne;

	public static String[] COLUMNS_TAB = {"A","B","C","D","E","F","G","H","I","J"};

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public int getLigne() {
		return ligne;
	}
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	private int ligne;
	private boolean touchee=false;
	

	public Position(int _colonne, int _ligne)
	{
		this.colonne=_colonne;
		this.ligne=_ligne;
	}
	
	public int getColonne(){
		return this.colonne;
	}


	public boolean getTouchee(){
		return this.touchee;
	}
	public void setTouche(boolean _value){
		this.touchee = _value;
	}

	public String toString(){
		return this.nomDeLaCase() +". "+ (this.touchee? "Touche":"Coule") ;
	}

	public String nomDeLaCase(){
		return COLUMNS_TAB[colonne-1]+"-"+(ligne+1);
	}
}
