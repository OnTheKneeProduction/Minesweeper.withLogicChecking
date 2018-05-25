package com.onthekneeprod;

import java.awt.Color;
import javax.swing.*;

public class Game {
	
	// player (name)
	private String player;
	
	// "playing" indicates whether a game is running (true) or not (false).
	private boolean playing; 
	
	// The minefield
	private Minefield minefield;
	
	// Difficulty settings
	private Difficulty difficulty;
	
	// Logic settings
	private Thread Result;
	
	
	/**
	 * Game constructor
	 * Initializes the application; not an actual new game.
	 * 
	 * @param player
	 */
	public Game(String player){
		// Set player name.
		setPlayer(player);
		
		// Make sure a player has to press "new game" to start a new game.
		setPlaying(false);
		
		// Create a new minefield.
		setMinefield( new Minefield( Difficulty.NORMAL.getNumberOfMines() ) );
		
		// Set default difficulty settings.
		setDifficulty( Difficulty.NORMAL );
			
	}
	
	/**
	 * Game constructor for an anonymous player (startup of the app).
	 */
	public Game()
	{
		this("");
	}
	
	/**
	 * Sets the player name to player.
	 * 
	 * @param player
	 * @post Sets this.player to player. | new.getPlayer() == player
	 */
	public void setPlayer(String player)
	{
		this.player = player;
	}
	
	/**
	 * Getter for the player attribute.
	 * 
	 * @return The String player.
	 */
	public String getPlayer()
	{
		return player;
	}
	
	/**
	 * Sets this.playing to true or false.
	 * 
	 * @param playing
	 * @post Sets this.playing to playing. | new.getPlaying() == playing
	 */
	public void setPlaying(boolean playing)
	{
		this.playing = playing;
	}
	
	/**
	 * Getter for the playing attribute.
	 * 
	 * @return true if this.playing is true; else false
	 */
	public boolean isPlaying()
	{
		return playing;
	}
	
	/**
	 * Sets the minefield.
	 * 
	 * @param Minefield minefield
	 * @post Sets the minefield to minefield. | new.getMinefield() == minefield
	 */
	private void setMinefield(Minefield minefield)
	{
		this.minefield = minefield;
	}
	
	/**
	 * Getter for the minefield attribute.
	 * 
	 * @return The Minefield minefield.
	 */
	public Minefield getMinefield()
	{
		return minefield;
	}
	
	/**
	 * Sets difficulty.
	 * 
	 * @param Difficulty difficulty
	 * @post Sets this.difficulty to difficulty. | new.getDifficulty() == difficulty
	 */
	public void setDifficulty(Difficulty difficulty)
	{
		this.difficulty = difficulty;
	}
	
	/**
	 * Getter for the difficulty attribute.
	 * 
	 * @return Difficulty.
	 */
	public Difficulty getDifficulty()
	{
		return difficulty;
	}

	

	
	
	/**
	 * Initializes a new game.
	 */
	public void newGame(Difficulty difficulty)
	{
		// Create a new minefield.
		setMinefield( new Minefield( difficulty.getNumberOfMines() ) );
		
		// Set the difficulty.
		setDifficulty(difficulty);
		
		// Start the game.
		setPlaying(true);
	
	}
	
	/**
	 * If a player clicks on a zero, all surrounding fields ("neighbours") must revealed.
	 * This method is recursive: if a neighbour is also a zero, his neighbours must also be revealed...
	 * 
	 * @param buttons
	 * @param xCo
	 * @param yCo
	 * @pre The field (xCo, yCo) must be a "zero" field. 
	 * 	| minefield.getMinefield()[xCo][yCo].getNeighbours() == 0
	 */
	public void findZeroes(JButton[][] buttons, int xCo, int yCo)
	{
		int neighbours;            
                
                
                
		// Columns...
		for(int x=minefield.makeValidCoordinate(xCo - 1) ; x<=minefield.makeValidCoordinate(xCo + 1) ; x++) {
			
			// Rows...
			for(int y=minefield.makeValidCoordinate(yCo - 1) ; y<=minefield.makeValidCoordinate(yCo + 1) ; y++) {
				
				// Only unrevealed fields need to be revealed...
				if(minefield.getMinefield()[x][y].getContent().equals("?")) {
					
					// Get the # neighbours of the current (neighbouring) field.
					neighbours = minefield.getMinefield()[x][y].getNeighbours();
					
					// Reveal the # neighbours of the current (neighbouring) field
					minefield.getMinefield()[x][y].setContent(Integer.toString(neighbours));
					buttons[x][y].setText(Integer.toString(neighbours));
					
					// Is this (neighbouring) field a "zero" field itself?
					if(neighbours == 0){
						// Yes, give it a special color and recurse!
						buttons[x][y].setBackground(Color.lightGray);
						findZeroes(buttons, x, y);
					}else{
						// No, give it a boring gray color.
						buttons[x][y].setBackground(Color.gray);
					}
					
				}
				
			}
			
		}
	}
	
	/**
	 * Checks the game to see if it is finished (true) or not (false).
	 * 
	 * @return true if the game is over, else false
	 */
	public boolean isFinished()
	{
		boolean isFinished = true;
		String fieldSolution;
		
		// Check all columns.
		for( int x=0 ; x<10 ; x++ ) {
			
			// Check all rows
			for( int y=0 ; y<10 ; y++ ) {
			
				// fieldContent contains the solution of a field
				// If a game is solved, the content of each field on the board must match fieldContent.
				fieldSolution = Integer.toString(minefield.getMinefield()[x][y].getNeighbours());
				if(minefield.getMinefield()[x][y].getMine()) fieldSolution = "F";
			
				// Compare the player's "answer" to the solution.
				if(!minefield.getMinefield()[x][y].getContent().equals(fieldSolution)) {
					// This field is not solved yet!
					isFinished = false;
					// No need to check the rest of the minefield: stop the for's.
					x = 10;
					y = 10;
				}
			
			}
			
		}
		
		return isFinished;
	}
	
	/**
	 * Reset player info in case the player chooses not to start a new game after a finished one.
	 * 
	 * @post player (name) is erased. | new.getPlayer() == null
	 * @post difficulty (level) is erased. | new.getDifficulty() == null
	 */
	public void gameOver()
	{
		setPlayer(null);
		setDifficulty(null);
	}
	
	/**
	 * Ends the game.
	 * 
	 * @post playing is set to false. | new.isPlaying() == false
	 */
	public void endGame()
	{
		setPlaying(false);
	}
	
	/**
	 * Quits the application.
	 * 
	 * @effect The current game is ended. | endGame()
	 * @effect The application is shut down. | System.exit(0)
	 */
	public void quitGame()
	{
		endGame();
		System.exit(0);
	}

}
