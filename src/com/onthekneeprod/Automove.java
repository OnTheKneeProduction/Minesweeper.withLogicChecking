
package com.onthekneeprod;

import java.awt.Point;
import javax.swing.JButton;


/**
 *
 * This class searches for any logical move
 */
public class Automove {
    
    public static String CheckLogicForAutomove(Field[][] minefield, Point locationOnScreen){
        
        String result = "";

        for ( int x = 0; x < 10; x++ ){
            for ( int y = 0; y < 10; y++ ){
                
                if( !minefield[x][y].getContent().equals("?") )
                    continue;
                
                for ( int i = x - 1; i <= x + 1; i++ ){
                    
                    if( i == -1 || i == 10 )
                        continue;
                    
                    for ( int j = y - 1; j <= y + 1; j++ ){
                        
                        if( j == -1 || j == 10 )
                            continue;
                        
                        if( minefield[i][j].getContent().equals("?") || minefield[i][j].getContent().equals("F") )
                            continue;
                        
                        int NumberOfMinesNearby = Integer.parseInt( minefield[i][j].getContent() );
                        
                        int FlagsNearby = 0;
                        
                        for ( int n = i - 1; n <= i + 1; n++ ){
                            
                            if( n == -1 || n == 10 )
                                continue;
                            
                            for ( int m = j - 1; m <= j + 1; m++ ){
                                
                                if( m == -1 || m == 10 )
                                    continue;
                                
                
                                if( minefield[n][m].getContent().equals("F") )
                                    FlagsNearby++;
                                
                            }
                        }
                        
                        if(FlagsNearby == NumberOfMinesNearby){
                            
                            result = Integer.toString(x) + "," + Integer.toString(y) + "L";
                            return result;
                        }
                    }
                }
            }
        }
        
        if(result.equals("")){
            
            for ( int x = 0; x < 10; x++ ){
                for ( int y = 0; y < 10; y++ ){

                    if( minefield[x][y].getContent().equals("?") || minefield[x][y].getContent().equals("F") || minefield[x][y].getContent().equals("0") )
                        continue;

                    int MinesNearby = Integer.parseInt( minefield[x][y].getContent() );
                    System.out.println(minefield[x][y].getContent() + "/" + MinesNearby);
                    int AvailableCells = 0;
                    
                    int CellsCoor[][];
                    CellsCoor = new int[8][2];
                    CellsCoor[0][0] = -1;
                    CellsCoor[0][1] = -1;

                    int flagedCells = 0;
                    
                    for ( int i = x - 1; i <= x + 1; i++ ){
                        if( i == -1 || i == 10 )
                            continue;

                        for ( int j = y - 1; j <= y + 1; j++ ){
                            if( j == -1 || j == 10 )
                                continue;

                            if (j == y && x == i)
                                continue;

                            
                            if(minefield[i][j].getContent().equals("?")){
                                
                                CellsCoor[AvailableCells][0]= i;
                                CellsCoor[AvailableCells][1]= j;
                                AvailableCells++;
                            }

                            if(minefield[i][j].getContent().equals("F")) {
                                System.out.print(i + " " + j + ';');
                                flagedCells++;
//                                AvailableCells++;
                            }
                            
                        }
                    }

                    if(AvailableCells <= MinesNearby - flagedCells){

                        if (CellsCoor[0][0] < 0)
                            continue;

                        result = CellsCoor[0][0] + "," + CellsCoor[0][1] + "R";

                        System.out.print(AvailableCells + "/" + MinesNearby + "/" + (flagedCells) + ' ' );
                        return result;
                    }
                    flagedCells = 0;
                }
            }
        }
        return result;
    }
    
    
}
