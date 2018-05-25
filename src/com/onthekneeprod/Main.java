package com.onthekneeprod;


/**
 * Main is part of the "Simple Java Minesweeper" (SJM) application.
 */
public class Main {

	/**
	 * Starts the application. :)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Game game = new Game();
		Gui gui = new Gui(game);
		gui.setVisible(true);

	}

}
