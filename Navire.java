

public class Navire {
	private String nom;
	private Position[] positions;

	
	public Navire(String nom, Position[] _positions) {
		this.nom = nom;
		this.positions = _positions;
	}
	
	
	/**
	*Description : Le bateau est coul� si tout les positions sont touch�
	*@return  Si tous les positions sont touch� retouner boolean true
	*****************************************************/
	public boolean estCoule(){
		Position[] position = getPositions();
		int compteur = 0;
		for(int i = 0; i< position.length; i++){
			if(position[i].getTouchee()){
				compteur ++;
			}	
		}
		
		if(compteur == position.length){
			return false;
		}
		return true;
	}
	public String getNom() {
		return this.nom;
	}
	public void setNom(String value) {
		this.nom = value;
	}
	
	public Position[] getPositions() {
		return this.positions;
	}
	
	public void setPosition(Position[] _positions){
		this.positions = _positions;
	}

}
