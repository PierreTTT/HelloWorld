import java.util.ArrayList;


public abstract class AJoueur implements IJoueur {
	
	public ListeDynamique navires;
	private ArrayList<Position> lesTires;
	public GrilleDeJeu grille;
	
	public AJoueur(GrilleDeJeu grille){
		this.navires = new ListeDynamique();
		this.grille = grille;
		lesTires = new ArrayList<Position>();
	}
	
	
	
	/**
	 * L'ordinateur genere les positions pour tirer
	 * @param cible
	 */
	public void tire(AJoueur cible) {
		// TODO Auto-generated method stub
		int ligne = Utilitaire.genererUnChiffre(1,10);
		int colonne = Utilitaire.genererUnChiffre(1,10);
		Position unePosition = new Position(colonne,ligne);
		cible.recoitTire(unePosition);
	}
	

	/**
	 * Les tire envoy� sur les navires
	 * @param unePosition
	 */
	public void recoitTire(Position unePosision){
		boolean found=false;
		for(int i=0; i<navires.getSize();i++){
			Navire unenavire = navires.get(i);
			Position[] lesPositions = unenavire.getPositions();
			for (int j=0;j<unenavire.getPositions().length;j++) {
				if(lesPositions[j].getColonne()==unePosision.getColonne() && lesPositions[j].getLigne() == unePosision.getLigne()){
					grille.setTouchee(unePosision.getLigne(),unePosision.getColonne());
					navires.get(i).getPositions()[j].setTouche(true);
					unePosision.setTouche(true);
					found=true;
				}
			}
		}
		if(!found)
			grille.setRatee(unePosision.getLigne(),unePosision.getColonne());

		grille.clearSelection();
		grille.repaint();
		lesTires.add(unePosision);
	}
	
	public ArrayList<Position> getLesTires() {
		return lesTires;
	}

	public void setLesTires(ArrayList<Position> lesTires) {
		this.lesTires = lesTires;
	}
	public void retierDernierTire(){
		Position unePosition = lesTires.get(lesTires.size()-1);
		grille.retirerLacouleur(unePosition.getLigne(),unePosition.getColonne());
	}
}