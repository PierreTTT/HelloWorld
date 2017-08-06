import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class JoueurHumain extends AJoueur {

    private ArrayList<PositionPossible> positionsPossible;
    private PositionPossible premierePosision ;
    private PositionPossible deuxiemePosision ;


    public JoueurHumain(GrilleDeJeu grille){
        super(grille);
        positionsPossible = new ArrayList<PositionPossible>();
    }
    
    //Construire les positions possibles � partir d'une position s駘ectionn�
	//� partir des param鑼res
    String buildPositionsPossible(int colonne,int ligne,int casesLimit){
        String[] tab = {"A","B","C","D","E","F","G","H","I","J"};
        String infos="La position choisi: "+ tab[(colonne)-1]+":"+(ligne+1)+" et les positions possibles sont: ";

        boolean found=false;
        if(colonne+casesLimit<11){
            PositionPossible tmp = new PositionPossible(colonne+casesLimit-1,ligne);
            tmp.possibles.add(new Position(colonne,ligne));
            found=false;
            for(int i = colonne+1;i<colonne+casesLimit;i++){
                if(grille.isCellSelected(ligne,i))
                    found=true;
                tmp.possibles.add(new Position(i,ligne));
            }
            if(!found){
                positionsPossible.add(tmp);
                infos+=tab[(colonne+casesLimit)-2]+":"+(ligne+1);

            }
        }
        if(colonne-casesLimit>1){
            found=false;
            PositionPossible tmp =new PositionPossible(colonne-casesLimit+1,ligne);
            tmp.possibles.add(new Position(colonne,ligne));
            for(int i = colonne-1;i>(colonne-casesLimit);i--){
                if(grille.isCellSelected(ligne,i))
                    found=true;
                tmp.possibles.add(new Position(i,ligne));
            }
            if(!found){
                positionsPossible.add(tmp);
                infos+=", "+tab[(colonne-casesLimit)]+":"+(ligne+1);

            }

        }

        if(ligne+casesLimit<11){
            found=false;
            PositionPossible tmp =new PositionPossible(colonne,ligne+casesLimit-1);
            tmp.possibles.add(new Position(colonne,ligne));
            for(int i = ligne+1;i<ligne+casesLimit;i++){
                if(grille.isCellSelected(i,colonne))
                    found=true;

                tmp.possibles.add(new Position(colonne,i));
            }
            if(!found){
                positionsPossible.add(tmp);
                infos+=", "+tab[colonne-1]+":"+((ligne)+casesLimit);
            }
        }
        if(ligne-casesLimit>=0){
            found=false;
            PositionPossible tmp =new PositionPossible(colonne,ligne-casesLimit+1);
            tmp.possibles.add(new Position(colonne,ligne));
            for(int i = ligne-1;i>ligne-casesLimit;i--){
                if(grille.isCellSelected(i,colonne))
                    found=true;
                tmp.possibles.add(new Position(colonne,i));
            }
            if(!found){
                positionsPossible.add(tmp);
                infos+=", "+tab[colonne-1]+":"+(ligne-casesLimit+2);

            }

        }
        return infos;
    }


    public void selectCases(){
        grille.clearSelection();
        for(int i=0; i<navires.getSize();i++){
            Navire navire = navires.get(i);
            for (int j=0;j<navire.getPositions().length;j++) {
                Position uneposision = navire.getPositions()[j];
                grille.changeColor(uneposision.getLigne(),uneposision.getColonne());
            }
        }
    }


    
    public ArrayList<PositionPossible> getPositionsPossible() {
        return positionsPossible;
    }

    public void setPositionsPossible(ArrayList<PositionPossible> positionsPossible) {
        this.positionsPossible = positionsPossible;
    }

    public PositionPossible getPremierePosision() {
        return premierePosision;
    }

    public void setPremierePosision(PositionPossible premierePosision) {
        this.premierePosision = premierePosision;
    }

    public PositionPossible getDeuxiemePosision() {
        return deuxiemePosision;
    }

    public void setDeuxiemePosision(PositionPossible deuxiemePosision) {
        this.deuxiemePosision = deuxiemePosision;
    }
    
    //Remettre les positions � null
    public void reinstallerLesPositions(){
        premierePosision=null;
        deuxiemePosision=null;
        getPositionsPossible().clear();
    }
}
