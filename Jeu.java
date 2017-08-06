
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Jeu extends JFrame {

    //Les buttons de placement
    private PlacementButton PABtn;
    private PlacementButton annulerBtn;
    private PlacementButton contreTBtn;
    private PlacementButton croiseurBtn;
    private PlacementButton recommencerBtn;
    private PlacementButton aideBtn;
    private PlacementButton sousmarinBtn;
    private PlacementButton tourpilleurBtn;

    //Les jpanels
    private JPanel panel;
    private JPanel panelDroit;
    private JPanel panelGauche;
    private JPanel panelCentre;
    private JPanel panelMaGride;
    private JPanel panelgrideAdversaire;

    //les labels
    private JLabel grilleDeLAdversaireLbl;
    private JLabel maGrilleLbl;

    private JLabel dernierTirsLbl;
    private JLabel dernierTirsLbl2;
    private JLabel mesTiresLbl;
    private JLabel lesTireDuJA;
    //les zone de text
    public JTextField informationsTxt;

    public GrilleDeJeu grilleDeladversaire;
    public GrilleDeJeu maGrille;

    //les JScrollPane
    private JScrollPane magrilleSP;
    private JScrollPane adversaireSP;
    //
    private JoueurArtificiel unJoueurArtificiel;
    private JoueurHumain unJoueurHumain;


    //les variables protected
    protected int casesLimit;
    protected String nomDeNavire;

    public Jeu() {
        setSize( 800, 600 );
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initialiserLesVariables();
        construireLesButtons();
        constuireLayout();

    }

    public void initialiserLesVariables(){
        annulerBtn = new PlacementButton();
        recommencerBtn = new PlacementButton();
        aideBtn = new PlacementButton();
        PABtn = new PlacementButton();
        croiseurBtn = new PlacementButton();
        contreTBtn = new PlacementButton();
        sousmarinBtn = new PlacementButton();
        tourpilleurBtn = new PlacementButton();
        magrilleSP = new JScrollPane();
        grilleDeLAdversaireLbl = new JLabel();
        dernierTirsLbl = new JLabel();
        dernierTirsLbl.setText("5 Derniers Tirs");
        dernierTirsLbl2 = new JLabel();
        dernierTirsLbl2.setText("5 Derniers Tirs");
        mesTiresLbl = new JLabel();
        lesTireDuJA = new JLabel();
        adversaireSP = new JScrollPane();
        grilleDeladversaire = new GrilleDeJeu();
        maGrille = new GrilleDeJeu();
        maGrilleLbl = new JLabel();
        informationsTxt = new JTextField();
        informationsTxt.setText("Informations sur le jeu");
        unJoueurHumain = new JoueurHumain(maGrille);
        unJoueurArtificiel = new JoueurArtificiel(grilleDeladversaire);
    }
    public GrilleDeJeu getGrilledeLadversaire(){
    	return this.grilleDeladversaire;
    }

    private void construireLesButtons() {
        annulerBtn.setText("Annuler");
        annulerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unJoueurHumain.retierDernierTire();
                unJoueurArtificiel.retierDernierTire();
                afficherLesTiresDuJoueurArtificiel();
                afficherMesTires();
            }
        });

        recommencerBtn.setText("Recommencer");
        recommencerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //on detruit le jeu actuel en detruant le jframe
                Jeu.this.dispose();
                //on crﾃｩe un autre jeu
                new Jeu().setVisible(true);
            }
        });

        aideBtn.setText("Aide");
        aideBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=1;i<11;i++){
                    for(int j=1;j<11;j++){
                        Position unPosition = new Position(i,j);
                        if(!unJoueurArtificiel.estExist(unPosition)){
                            JOptionPane.showMessageDialog(null, "Nous vous proposons la case suivante: "+unPosition.nomDeLaCase());
                            return;
                        }
                    }
                }
            }
        });

        PABtn.setText("Placer P-A");
        PABtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PABtn.setClique(true);
                nomDeNavire="porte-avion";
                griserLesButtons(PABtn);
                casesLimit=5;
                
            }
        });

        croiseurBtn.setText("Placer Croiseur");
        croiseurBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nomDeNavire="croiseur";
                casesLimit=4;
                croiseurBtn.setClique(true);
                griserLesButtons(croiseurBtn);
            }
        });

        contreTBtn.setText("Placer Contre T");
        contreTBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contreTBtn.setClique(true);
                nomDeNavire="contre-torpilleurs";
                casesLimit=3;
                griserLesButtons(contreTBtn);

            }
        });

        sousmarinBtn.setText("Placer Sous Marin");
        sousmarinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nomDeNavire="sous-marin";
                casesLimit=3;
                sousmarinBtn.setClique(true);
                griserLesButtons(sousmarinBtn);
            }
        });

        tourpilleurBtn.setText("Placer tourpilleur");
        tourpilleurBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tourpilleurBtn.setClique(true);
                casesLimit=2;
                nomDeNavire="torpilleur";
                griserLesButtons(tourpilleurBtn);

            }
        });
        
       //ﾀ partir de la position cliquer, cela determine les position du bateaux pour l'humain
       maGrille.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!maGrille.isEnabled())
                    return;
                int ligne = maGrille.rowAtPoint(evt.getPoint());
                int col = maGrille.columnAtPoint(evt.getPoint());
                if(maGrille.isCellSelected(ligne,col)){
                    JOptionPane.showMessageDialog(null, "Cette Case est dﾃｩja sﾃｩlﾃｩctionnﾃｩ");
                    return;
                }
                if (ligne >= 0 && col >= 1) {
                    if( unJoueurHumain.getPositionsPossible().size()==0){
                        unJoueurHumain.setPremierePosision(new PositionPossible(col,ligne));
                        unJoueurHumain.getPositionsPossible().add(unJoueurHumain.getPremierePosision());
                        informationsTxt.setText(unJoueurHumain.buildPositionsPossible(col,ligne,casesLimit));
                    }
                    else{
                        unJoueurHumain.setDeuxiemePosision(new PositionPossible(col,ligne));
                        PositionPossible uneposition=Utilitaire.contient(unJoueurHumain.getPositionsPossible(),unJoueurHumain.getDeuxiemePosision());
                        if(uneposition==null)
                            JOptionPane.showMessageDialog(null, "Vous avez pas le droit de choisir cette case!");
                        else{
                            Position[] positions =  uneposition.possibles.toArray( new Position[uneposition.possibles.size()]);
                            Navire uneNavire = new Navire(nomDeNavire, positions);
                            unJoueurHumain.navires.add(uneNavire);
                            unJoueurHumain.selectCases();
                            unJoueurHumain.reinstallerLesPositions();
                            maGrille.clearSelection();
                            degriserlesButtons();
                        }
                    }

                }
                if(unJoueurHumain.navires.getSize()==5){
                    JOptionPane.showMessageDialog(null, "Le jeu ﾃ� commencer! vous pouvez commencer ﾃ� tirer sur l'adversaire!");
                    maGrille.setEnabled(false);
                    grilleDeladversaire.setEnabled(true);
                }
            }
        });
       
       //La methode qui determine les cliques de tire qu'on fait sur la grille adversaire
        grilleDeladversaire.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!grilleDeladversaire.isEnabled())
                    return;
                int ligne = grilleDeladversaire.rowAtPoint(evt.getPoint());
                int col = grilleDeladversaire.columnAtPoint(evt.getPoint());
                if (ligne >= 0 && col >= 1) {
                    unJoueurArtificiel.recoitTire(new Position(col,ligne));
                    unJoueurArtificiel.tire(unJoueurHumain);
                    afficherMesTires();
                    afficherLesTiresDuJoueurArtificiel();
                }
            }
        });
    }


    
    //Le main du jeu
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jeu().setVisible(true);
            }
        });
    }
    
    //Initialise les panneaux
    public void constuireLayout(){
        panel = new JPanel();
        panelGauche = new JPanel();
        panelCentre = new JPanel();
        panelDroit = new JPanel();


        JPanel panelDroitHaut =  new JPanel();
        JPanel panelDroitBas =  new JPanel();
        panelDroitBas.setPreferredSize(new Dimension(150,300));

        panelDroitBas.setLayout(new BoxLayout(panelDroitBas,BoxLayout.PAGE_AXIS));
        panelDroitHaut.setPreferredSize(new Dimension(150,300));

        panelDroitHaut.setLayout(new BoxLayout(panelDroitHaut,BoxLayout.PAGE_AXIS));

        panelgrideAdversaire = new JPanel();
        panelMaGride = new JPanel();
        panelgrideAdversaire.setMaximumSize(new Dimension(450,350));
        panelMaGride.setMaximumSize(new Dimension(450,350));
        BorderLayout layout = new BorderLayout();

        panel.setLayout(layout);
        panelGauche.setLayout( new BoxLayout(panelGauche, BoxLayout.PAGE_AXIS));
        panelGauche.add(PABtn);
        panelGauche.add(croiseurBtn);
        panelGauche.add(contreTBtn);
        panelGauche.add(sousmarinBtn);
        panelGauche.add(tourpilleurBtn);
        panelGauche.add(annulerBtn);
        panelGauche.add(aideBtn);
        panelGauche.add(recommencerBtn);
        panelCentre.setLayout(new BoxLayout(panelCentre, BoxLayout.PAGE_AXIS));


        grilleDeLAdversaireLbl.setText("Grille de l'adversaire");
        panelgrideAdversaire.add(grilleDeLAdversaireLbl);
        adversaireSP.setViewportView(grilleDeladversaire);
        panelgrideAdversaire.add(adversaireSP);
        panelCentre.add(panelgrideAdversaire);
        maGrilleLbl.setText("Ma Grille");
        magrilleSP.setViewportView(maGrille);
        panelMaGride.add(maGrilleLbl);

        panelMaGride.add(magrilleSP);
        panelCentre.add(panelMaGride);
        panelDroit.setLayout(new BoxLayout(panelDroit, BoxLayout.PAGE_AXIS));
        panelDroit.setPreferredSize(new Dimension(150,600));
        panelDroit.add(panelDroitHaut);
        panelDroit.add(panelDroitBas);
        panelDroitHaut.add(dernierTirsLbl);
        panelDroitHaut.add(mesTiresLbl);
        panelDroitBas.add(dernierTirsLbl2);
        panelDroitBas.add(lesTireDuJA);
        panel.add(panelDroit,BorderLayout.LINE_END);

        panel.add(panelCentre,BorderLayout.CENTER);
        panel.add(panelGauche,BorderLayout.LINE_START);
        panel.add(informationsTxt,BorderLayout.SOUTH);

        this.add(panel);
    }
    
    //Mettre en gris les bouttons apr鑚 avoir s駘ectionn� le placement d'un bateau
    void griserLesButtons(PlacementButton saufLeButton){
        PABtn.setEnabled(false);
        aideBtn.setEnabled(false);

        annulerBtn.setEnabled(false);
        contreTBtn.setEnabled(false);
        croiseurBtn.setEnabled(false);
        sousmarinBtn.setEnabled(false);
        tourpilleurBtn.setEnabled(false);
        saufLeButton.forceEnabled(true);
        maGrille.setEnabled(true);
    }
    //Remettre les bouttons � l'騁at normal
    void degriserlesButtons(){
        aideBtn.setEnabled(true);
        PABtn.setEnabled(true);
        annulerBtn.setEnabled(true);
        contreTBtn.setEnabled(true);
        croiseurBtn.setEnabled(true);
        sousmarinBtn.setEnabled(true);
        tourpilleurBtn.setEnabled(true);
        maGrille.setEnabled(false);

    }
    
    // Afficher les tires dans le c� droit
    void afficherMesTires(){
        String s="<html>";
        int limit = unJoueurHumain.getLesTires().size()>5 ? unJoueurHumain.getLesTires().size()-5:0;
        for(int i=unJoueurHumain.getLesTires().size()-1;i>=limit;i--){
            s+=unJoueurHumain.getLesTires().get(i).toString()+"<BR>";
        }
        s+="</html>";
        lesTireDuJA.setText(s);
    }
    void afficherLesTiresDuJoueurArtificiel(){
        String s="<html>";
        int limit = unJoueurArtificiel.getLesTires().size()>5?unJoueurArtificiel.getLesTires().size()-5:0;
        for(int i=unJoueurArtificiel.getLesTires().size()-1;i>=limit;i--){
            s+=unJoueurArtificiel.getLesTires().get(i).toString()+"<BR>";
        }
        s+="</html>";
        mesTiresLbl.setText(s);
    }
}
