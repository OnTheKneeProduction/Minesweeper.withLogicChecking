package com.onthekneeprod;

/**
 * Minefield is part of the "Simple Java Minesweeper" (SJM) application.
 * 
 * Minefield is a matrix of Field's.
 * I've chosen for a 10x10 matrix, but this can easily be adjusted (don't forget the GUI).
 * Minefield contains all functionality to generate a new "gameboard".
 */
public class Minefield {
	
	// The number of mines drastically changes the difficulty level.
	// This number shouldn't change once set.
	private int numberOfMines;
	
	// The minefield is a 10x10 matrix of Field's.
	private Field minefield[][] = new Field[10][10];
	
	
	/**
	 * Minefield constructor
	 * 
	 * @effect An array of "mine" coordinates is generated. | makeMines()
	 * @effect A new minefield is generated, based on the previous array of mines. | makeMinefield(makeMines())
	 * @post The current minefield is set. | new.getMinefield() == makeMinefield(makeMines())
	 */
	public Minefield(int numberOfMines)
	{
		setNumberOfMines(numberOfMines);
		setMinefield(makeMinefield(makeMines()));
	}
	
	/**
	 * Sets numberOfMines.
	 * 
	 * @param numberOfMines
	 * @post Sets this.numberOfMines to numberOfMines. | new.getNumberOfMines() == numberOfMines
	 */
	private void setNumberOfMines(int numberOfMines)
	{
		this.numberOfMines = numberOfMines;
	}
	
	/**
	 * Getter for the numberOfMines attribute.
	 * 
	 * @return The integer numberOfMines.
	 */
	public int getNumberOfMines()
	{
		return numberOfMines;
	}
	
	/**
	 * Sets the minefield.
	 * 
	 * @param minefield
	 * @post The minefield is set. | new.getMinefield() == <minefield>
	 */
	private void setMinefield(Field[][] minefield)
	{
		this.minefield = minefield;
	}
	
	/**
	 * Returns the minefield.
	 * 
	 * @return minefield | result == this.minefield
	 */
	public Field[][] getMinefield()
	{
		return minefield;
	}
	
	/**
	 * Generates a 2-dimensional array of "mine coordinates".
	 * The coordinates are random, but unique numbers between 0 and 9.
	 * 
	 * @return mines | result == mines[NUMBER_OF_MINES][2]
	 */
	private int[][] makeMines()
	{
		int[][] mines = new int[numberOfMines][2];
		int x,y;
		boolean isTaken;
		
		// Fill the mines array with (valid) coordinates
		for( int mine=0 ; mine<numberOfMines ; mine++ ) {
			
			// Generate a random x coordinate (between 0 and 9)
			x = (int)Math.floor(Math.random() * 10);
			// Generate a random y coordinate (between 0 and 9)
			y = (int)Math.floor(Math.random() * 10);
			
			x = 4;
			y = 4;
			
			// Make sure the coordinates aren't already taken.
			isTaken = true;
			while(isTaken) {
				isTaken = searchMine(mines, x, y);
				if(isTaken) {
					// Generate new x and y coordinates
					x = (int)Math.floor(Math.random() * 10);
					y = (int)Math.floor(Math.random() * 10);
				}
			}
			
			// add to mines array
			mines[mine][0] = x;
			mines[mine][1] = y;
			
			// Next coordinate
			
		}
		
		return mines;
	}
	
	/**
	 * Generates a new minefield.
	 * 
	 * @param mines
	 * @return minefield
	 */
	private Field[][] makeMinefield(int[][] mines)
	{	
		// Fill the matrix with Field's and distribute the mines (from the mines parameter).
		for( int x=0 ; x<10 ; x++ ) {
			for( int y=0 ; y<10 ; y++ ) {
				minefield[x][y] = new Field();
				if(searchMine(mines, x, y)) minefield[x][y].setMine(true);
			}
		}
		
		// Set the neighbours attribute of each Field.
		for( int x=0 ; x<10 ; x++ ) {
			for( int y=0 ; y<10 ; y++ ) {
				minefield[x][y].setNeighbours(calculateNeighbours(x,y));
			}
		}
		
		return minefield;
	}
	
	/**
	 * Checks if a coordinate (x,y) is in the "mines" array (return true) or not (return false).
	 * 
	 * @param mines
	 * @param x
	 * @param y
	 * @return isMine
	 */
	private boolean searchMine(int[][] mines, int x, int y)
	{
		boolean isMine = false;
		
		// Walk through the array.
		for( int i=0 ; i < numberOfMines ; i++ ) {
			// Check the x and y coordinates
			isMine = (mines[i][0] == x && mines[i][1] == y);
			// The coordinate was found, skip to the end.
			if(isMine) i = numberOfMines;
		}
		
		return isMine;
	}
	
	/**
	 * Calculates the number of surrounding mines ("neighbours").
	 * 
	 * @return neighbours
	 */
	private int calculateNeighbours(int xCo, int yCo)
	{
		int neighbours = 0;
		
		// Check the neighbours (the columns xCo - 1, xCo, xCo + 1).
		// makeValidCoordinate makes sure no non-existing columns are checked (eg. column 0-1 = -1).
		for( int x=makeValidCoordinate(xCo - 1) ; x<=makeValidCoordinate(xCo + 1) ; x++ ) {
			
			// Check the neighbours (the rows yCo - 1, yCo, yCo + 1).
			// makeValidCoordinate makes sure no non-existing rows are checked.
			for( int y=makeValidCoordinate(yCo - 1) ; y<=makeValidCoordinate(yCo + 1) ; y++ ) {
				
				// Skip (xCo, yCo), since that's no neighbour.
				// If the neighbour contains a mine, neighbours++.
				if(x != xCo || y != yCo) if(minefield[x][y].getMine()) neighbours++;
				
			}
			
		}
		
		return neighbours;
	}
	
	/**
	 * Simply makes a coordinate a valid one (within the boundries of the minefield).
	 * 
	 * @return i
	 */
	public int makeValidCoordinate(int i)
	{
		i=i<0?0:i;
		i=i>9?9:i;
		return i;
	}
	
	/**
	 * Sets the content of each Field in this.minefield to "?" (unknown content).
	 * 
	 * @effect minefield[x][y].setContent("?")
	 */
	public void resetAll()
	{
		for( int x=0 ; x<10 ; x++ ) {
			for( int y=0 ; y<10 ; y++ ) {
				minefield[x][y].setContent("?");
			}
		}
	}
	
}
