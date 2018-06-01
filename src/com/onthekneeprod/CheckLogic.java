/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onthekneeprod;

/**
 *  The class where logic of user's move is checked
 *
 */
public class CheckLogic implements Runnable {

    public CheckLogic(int x, int y, Field[][] minefield){
        this.minefield = minefield;
        this.x = x;
        this.y = y;
    }

    private int NotLogicalMoves;

    private int x;
    private int y;
    private Field[][] minefield;
    
    
    
    
    public void run() {
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
    }

    
    
    

    
    
    
}
