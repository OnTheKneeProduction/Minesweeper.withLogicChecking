/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onthekneeprod;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import static sjm.Gui.NotLogicalMoves;



/**
 *
 * @author lucky
 */
public class CheckLogic implements Runnable {
    
    
    private static Gui getLogic;
    private static JLabel Logic;
    private Minefield minefield;
    private static Gui checkGame;
    private static int NotLogicalMoves;
    private static Game game;
//    private static int x;
//    private static int y;
    
    
    
    
    public CheckLogic(int x, int y, Field[][] minefield){
    
        
//        System.out.println("1");
//        JOptionPane.showMessageDialog(null, x + "||" + y);
        int LogicOfMove = 0;
        String temp = "";
        //Thread Check = new CheckLogic();
        for( int i = 0; Gui.getLogic().getText().charAt(i) != ' '; i++ )
            temp += Gui.getLogic().getText().charAt(i);
        
        NotLogicalMoves = Integer.parseInt(temp);
        
        for(int xCo = x - 1; xCo <= x + 1 && LogicOfMove == 0; xCo++) {

            if(xCo == -1 || xCo == 10)
                continue;

            for(int yCo = y - 1; yCo <= y + 1 && LogicOfMove == 0; yCo++) {


                if(yCo == -1 || yCo == 10)
                    continue;



                String Content;  

                Content = minefield[xCo][yCo].getContent();
//                JOptionPane.showMessageDialog(null, Content);
                String F = "F";
                String QuestionMark = "?";

                if(Content.equals(QuestionMark) || Content.equals(F)){
                    continue;
                }
        //            int SomeLogic = 0;
                    int NumberOfTickedMines;
                    int NumberOfMinesNearby = Integer.parseInt(Content);

                    NumberOfTickedMines = 0;

                    for(int xCoordinate = xCo - 1; xCoordinate <= xCo + 1; xCoordinate++) {

                        if(xCoordinate == -1 || xCoordinate == 10)
                             continue;

                            for(int yCoordinate = yCo - 1 ; yCoordinate <= yCo + 1; yCoordinate++) {

                                if(yCoordinate == -1 || yCoordinate == 10)
                                    continue;

                                    if( !minefield[xCoordinate][yCoordinate].getContent().equals(QuestionMark) )
                                        NumberOfTickedMines++;

                            }
                    }
                    
                    
                    
                    if(NumberOfTickedMines >= 8 - NumberOfMinesNearby)
                        LogicOfMove++;
                    
            }
        }
        
        
        if(LogicOfMove == 0)
            NotLogicalMoves++;
        


        Gui.getLogic().setText(NotLogicalMoves + " Not Logical Moves");
        
        
        LogicOfMove = 0;

    }
    
    
    
    
    public void run() {
        
    }

    
    
    

    
    
    
}
