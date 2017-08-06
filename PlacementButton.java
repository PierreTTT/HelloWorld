import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class PlacementButton extends JButton  {
    private boolean clique=false;

    public boolean estClique() {
        return clique;
    }

    public void setClique(boolean estClique) {
        this.clique = estClique;
    }

    public PlacementButton(){
        super();
        this.setMaximumSize(new Dimension(250,29));
    }

    public void setEnabled(boolean enabled){
        if(this.estClique())
            super.setEnabled(false);
        else
            super.setEnabled(enabled);
    }

    public void forceEnabled(boolean enabled){
            super.setEnabled(enabled);
    }

}
