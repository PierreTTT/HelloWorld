import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;



public class GrilleDeJeu extends JTable {

    private boolean[][] selectedRows = new boolean[13][13];
    private boolean[][] lesCellsRouge = new boolean[13][13];
    private boolean[][] lesCellBleus = new boolean[13][13];
    private boolean cachee ;
    
    //le model par defaut pour afficher un tableau de clonnes A ﾃ� J et les lignes de 1-10
    private TableModel tableModel = new DefaultTableModel(
            new Object [][] {
                    {"1", "", null, null, null, null, null, null, null, null, null},
                    {"2", null, null, null, null, null, null, null, null, null, null},
                    {"3", null, null, null, null, null, null, null, null, null, null},
                    {"4", null, null, null, null, null, null, null, null, null, null},
                    {"5", null, null, null, null, null, null, null, null, null, null},
                    {"6", null, null, null, null, null, null, null, null, null, null},
                    {"7", null, null, null, null, null, null, null, null, null, null},
                    {"8", null, null, null, null, null, null, null, null, null, null},
                    {"9", null, null, null, null, null, null, null, null, null, null},
                    {"10", null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                    "", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"
            }
    );

    //le constructeur qui met par defauls les parametres de notre jTable
    public GrilleDeJeu(){
        super();
        cachee=false;
        this.setModel(tableModel);
        this.setColumnSelectionAllowed(false);
        this.setRowSelectionAllowed ( false );
        this.setCellSelectionEnabled(true);
        this.setShowGrid(true);
        this.setDragEnabled(false);
        this.setEnabled(false);
        this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.setMaximumSize(new Dimension(400,100));

    }
    //j'ecrase la fonction de la page parent pour verifier la case
    public boolean isCellSelected(int ligne, int colonne){
        return selectedRows[colonne][ligne];
    }

    public void retirerLacouleur(int ligne, int colonne){
        lesCellsRouge[colonne][ligne]=false;
        lesCellBleus[colonne][ligne]=false;

    }
    public void setTouchee(int ligne, int colonne){
        lesCellsRouge[colonne][ligne]=true;
    }
    public void setRatee(int ligne, int colonne){
        lesCellBleus[colonne][ligne]=true;
    }

    //
    public void changeColor(int row,int colonne){
        selectedRows[colonne][row]=true;
    }

    /*
    * Modifier la couleur d'une cellule dans un Jtable
    * @source: http://stackoverflow.com/questions/9735007/how-to-set-color-to-a-certain-row-if-certain-conditions-are-met-using-java/9737299#9737299
    * le code a ﾃｩtﾃｩ copiﾃｩ du stackoverflow mais je l'ai changer pour l'adapter ﾃ� nos besoins
    */
    public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
        // get the current row
        Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
        // si la colonne est selectionne on affiche la couleur GRAY si non on affiche la couleur par default

        if (lesCellsRouge[Index_col][Index_row]){
            comp.setBackground(Color.RED);
        }
        else if (lesCellBleus[Index_col][Index_row]){
            comp.setBackground(Color.BLUE);
        }
        else if (selectedRows[Index_col][Index_row] && !cachee){
            comp.setBackground(Color.GRAY);
        }else {
            comp.setBackground(Color.white);
        }
        return comp;
    }


    public void setCachee(boolean val){
        this.cachee=val;
    }

    public boolean getCachee(){
        return cachee;
    }
}
