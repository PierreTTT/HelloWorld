import java.util.ArrayList;

public class PositionPossible extends Position
{
	public ArrayList<Position> possibles =new ArrayList<Position>(); 

	public PositionPossible(int _colonne, int _ligne){
			super(_colonne,_ligne);

	}
}
