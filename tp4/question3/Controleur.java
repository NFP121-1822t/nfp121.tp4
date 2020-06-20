package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur extends JPanel {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        add(donnee);
        actualiserInterface();

        donnee.addFocusListener(new FocusListener()
            {public void focusGained(FocusEvent a) {
                    donnee.setText("");
                }

                public void focusLost(FocusEvent a) {

                }
            });     

        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());

        class AL implements ActionListener
        {
            public void actionPerformed(ActionEvent ae){

                if(ae.getActionCommand()=="push"){ try{pile.empiler(operande());}catch(Exception e){}}
                if(ae.getActionCommand()=="+"||ae.getActionCommand()=="-"||ae.getActionCommand()=="*"||ae.getActionCommand()=="/") 
                {
                    try{int op1=pile.depiler();
                        int op2=pile.depiler();
                        if(ae.getActionCommand()=="+") { pile.empiler(op1+op2);}
                        if(ae.getActionCommand()=="-") { pile.empiler(op2-op1);}
                        if(ae.getActionCommand()=="*") { pile.empiler(op2*op1);}
                        if(ae.getActionCommand()=="/") { 
                            if(op1!=0){
                                pile.empiler(op2/op1);}
                            else{pile.empiler(op2);
                                pile.empiler(op1);}}}catch(Exception e){}}
                if(ae.getActionCommand()=="[]"){try{ pile.viderPile();}catch(Exception e){}}

                actualiserInterface();

            }}

        AL al = new AL();

        boutons.add(push);  push.addActionListener(al);
        boutons.add(add);   add.addActionListener(al);
        boutons.add(sub);   sub.addActionListener(al);
        boutons.add(mul);   mul.addActionListener(al);
        boutons.add(div);   div.addActionListener(al);
        boutons.add(clear);   clear.addActionListener(al);
        
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {
        // à compléter
        if(pile.estVide()) {add.setEnabled(false);clear.setEnabled(false);div.setEnabled(false);mul.setEnabled(false);sub.setEnabled(false);}
        if(pile.estPleine()) push.setEnabled(false);
        if(pile.taille()<2){add.setEnabled(false);div.setEnabled(false);mul.setEnabled(false);sub.setEnabled(false);}
        if(pile.taille()>=2){add.setEnabled(true);clear.setEnabled(true);div.setEnabled(true);mul.setEnabled(true);sub.setEnabled(true);}
        if(!pile.estPleine()) push.setEnabled(true);
        if(!pile.estVide()) clear.setEnabled(true);

    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }

    // à compléter
    // en cas d'exception comme division par zéro, 
    // mauvais format de nombre suite à l'appel de la méthode operande
    // la pile reste en l'état (intacte)

}
