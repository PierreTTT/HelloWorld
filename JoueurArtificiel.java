import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;


public class JoueurArtificiel extends AJoueur{
	
	
	
	//G駭駻er le necessaire pour l'adversaire
	public JoueurArtificiel(GrilleDeJeu grille){
		super(grille);
		grille.setCachee(true);

		genererCroiseur();
		genererContreTorpilleur();
		genererSousmarin();
		genererPA();
		genererTorpilleur();
		selectCases();

	}
	
	
	//G駭駻er les positions pour tirer
	public void tire(AJoueur cible) {
		int ligne = Utilitaire.genererUnChiffre(1,10);
		int colonne = Utilitaire.genererUnChiffre(1,10);
		cible.recoitTire(new Position(colonne,ligne));
	}



	//G駭駻er les bateaux de l'adversaire
    private void genererCroiseur(){
		Navire unNavire= new Navire("croiseur",genererLesPosistions(4));
		this.navires.add(unNavire);
	}
    private void genererSousmarin(){
		Navire unNavire= new Navire("sous-marin",genererLesPosistions(3));
		this.navires.add(unNavire);
	}
    private void genererContreTorpilleur(){
		Navire unNavire= new Navire("contre-torpilleur",genererLesPosistions(3));
		this.navires.add(unNavire);
	}
    private void genererTorpilleur(){
		Navire unNavire= new Navire("torpilleur",genererLesPosistions(2));
		this.navires.add(unNavire);
	}
	private void genererPA(){
		Navire unNavire= new Navire("porte-avion",genererLesPosistions(5));
		this.navires.add(unNavire);
	}
	
	//G駭駻er les positions occup� par les bateaux
	private Position[] genererLesPosistions(int casesLimit){
		while (true) {
				int ligne = Utilitaire.genererUnChiffre(1,9);
				int colonne = Utilitaire.genererUnChiffre(1,9);
				;

				boolean found=false;
				Position unePosistion = new Position(colonne,ligne);
				if (!estExist(unePosistion)) {

					if (colonne + casesLimit < 10) {
						Position[] tmp = new Position[casesLimit];
						tmp[0] = new Position(colonne, ligne);
						int count = 1;
						found = false;
						for (int i = colonne + 1; i < colonne + casesLimit; i++) {
							if (estExist(new Position(i, ligne)))
								found = true;
							tmp[count] = (new Position(i, ligne));
							count++;
						}
						if (!found) {
							return tmp;

						}
					}
					if (colonne - casesLimit > 1) {
						Position[] tmp = new Position[casesLimit];
						tmp[0] = new Position(colonne, ligne);
						int count = 1;
						found = false;
						for (int i = colonne-1 ; i > (colonne - casesLimit); i--) {
							if (estExist(new Position(i, ligne)))
								found = true;
							tmp[count] = (new Position(i, ligne));
							count++;
						}
						if (!found) {
							return tmp;


						}

					}

					if (ligne + casesLimit < 10) {
						Position[] tmp = new Position[casesLimit];
						tmp[0] = new Position(colonne, ligne);
						int count = 1;
						found = false;
						for (int i = ligne+1; i < ligne + casesLimit; i++) {
							if (estExist(new Position(colonne, i)))
								found = true;
							tmp[count] = (new Position(colonne, i));
							count++;
						}
						if (!found) {
							return tmp;
						}
					}
					if (ligne - casesLimit > 0) {
						Position[] tmp = new Position[casesLimit];
						tmp[0] = new Position(colonne, ligne);
						int count = 1;
						found = false;

						for (int i = ligne-1; i > ligne - casesLimit; i--) {
							if (estExist(new Position(colonne, i)))
								found = true;
							tmp[count] = (new Position(colonne, i));
							count++;
						}
						if (!found) {
							return tmp;
						}

					}
				}
			}
	}

	//V駻ifier si l'existance d'une position
	public boolean estExist(Position unePosision){
		if(navires.getSize()==0)
			return false;
		for(int i=0; i<navires.getSize();i++){
			Navire unenavire = navires.get(i);
			Position[] lesPositions = unenavire.getPositions();
			for (int j=0;j<unenavire.getPositions().length;j++) {
				if(lesPositions[j].getColonne()==unePosision.getColonne() && lesPositions[j].getLigne() == unePosision.getLigne())
					return true;

			}
		}
		return false;
	}
	
	//S駘ectionner une case pour la couleur
	private void selectCases(){
		grille.clearSelection();
		for(int i=0; i<navires.getSize();i++){
			Navire navire = navires.get(i);
			for (int j=0;j<navire.getPositions().length;j++) {
				Position uneposision = navire.getPositions()[j];
				grille.changeColor(uneposision.getLigne(),uneposision.getColonne());
			}
		}
	}
}
