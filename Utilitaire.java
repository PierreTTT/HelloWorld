import java.util.ArrayList;
import java.util.Random;

public class Utilitaire {
	
	//Vérification des positions possibles  
    public static PositionPossible  contient(ArrayList<PositionPossible> positionsPossibles,Position deuxiemePosision){
        for (PositionPossible positionsPossible:positionsPossibles) {
            if(positionsPossible.getColonne() == deuxiemePosision.getColonne()&& positionsPossible.getLigne()==deuxiemePosision.getLigne())
                return positionsPossible;
        }

        return  null;
    }

    
	//Générer un chiffre au hasard
    public static int genererUnChiffre(int min, int max){
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
