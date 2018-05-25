package com.onthekneeprod;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 
 * Gui is the "Graphical User Interface" of SJM.
 * This class handles all graphical stuff and checks moves for some logic.
 */
public class Gui extends JFrame implements MouseListener{
    
        public static String recMove = "";
        public static int NotLogicalMoves = 0;
        public int GlobalMarkedMines = 0;
        public int NumberOfMines = 0;
	
        private CheckLogic CheckLogicOfMove;
        private static Automove CheckLogicForAutomove;
        
        
        private Minefield minefield;
        
        private Difficulty difficulty;
        

	// The game and it's settings.
	private Game game;
	
	// The buttons.
	private JButton[][] buttons = new JButton[10][10];
	
	private static JLabel Logic;
	private Thread Refresh;
	
	
	// Frame settings
	private final String FRAME_TITLE = "Java Minesweeper(Modified to check logic of moves) v 2.0";
	private final int FRAME_WIDTH = 700;
	private final int FRAME_HEIGHT = 500;
	private final int FRAME_LOC_X = 100;
	private final int FRAME_LOC_Y = 100;
	
	/**
	 * Gui constructor
	 * A Game object needs to be specified.
	 * The frame is initialized, but it's still up to the user (Main) to set it visible. 
	 * 
	 * @param minefield
	 */
	public Gui(Game game)
	{
                
		// Set the game.
		setGame(game);
		
		// Initialize the logic label.
		setLogic( new JLabel(Integer.toString(NotLogicalMoves) + " Not Logical Moves") );
		
		
		// The layout of the frame:
		Container contentPane;
		JPanel gameBoard;
		JPanel controlPanel;
		JPanel LogicPanel;
		JPanel buttonPanel;
                JPanel automovePanel;
		
		// Set frame.
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle(FRAME_TITLE);
		setLocation(FRAME_LOC_X, FRAME_LOC_Y);
		
		// Set layout.
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(10,0));
		
		// Build the "gameboard".
		gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(10,10));
		// Rows
		for( int y=0 ; y<10 ; y++ ) {
			// Columns
			for( int x=0 ; x<10 ; x++ ) {
				// Set button text.
				buttons[x][y] = new JButton(this.game.getMinefield().getMinefield()[x][y].getContent());
				// Set button name (x,y).
				buttons[x][y].setName(Integer.toString(x) + "," + Integer.toString(y));
				// Add mouseListener.
				buttons[x][y].addMouseListener(this);
				// Add this button to the gameboard.
				gameBoard.add(buttons[x][y]);
			}
		}
		
		// Build the "controlpanel".
		controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());
		
		// Build the "LogicPanel".
		LogicPanel = new JPanel();
		LogicPanel.setBorder(BorderFactory.createTitledBorder("Logic:"));
		LogicPanel.add(Logic);       //   NotLogicalMoves
		controlPanel.add(LogicPanel, BorderLayout.NORTH);
			
		// Build the "buttonpanel" (part of controlPanel).
		buttonPanel = new JPanel();
		
                automovePanel = new JPanel();
                
                //Add "AutoMove" button.
                JButton automoveButton = new JButton("Automove");
		automoveButton.setName("automoveButton");
		automoveButton.addMouseListener(this);
		automovePanel.add(automoveButton);
                
                
		// Add "new game" button.
		JButton newGameButton = new JButton("New game");
		newGameButton.setName("newGameButton");
		newGameButton.addMouseListener(this);
		buttonPanel.add(newGameButton);
		
		// Add "quit game" button.
		JButton quitButton = new JButton("Quit game");
		quitButton.setName("quitButton");
		quitButton.addMouseListener(this);
		buttonPanel.add(quitButton);
		
                controlPanel.add(automovePanel, BorderLayout.CENTER);
                
		controlPanel.add(buttonPanel, BorderLayout.SOUTH);
                
		
		// Add everything to the contentpane.
		contentPane.add(gameBoard, BorderLayout.CENTER);
		contentPane.add(controlPanel, BorderLayout.EAST);
		
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		
		// Don't allow the player to click on the gameboard (yet).
		disableAll();
	}
	
	/**
	 * Sets the game.
	 * 
	 * @param Game
	 * @post Game is set. | new.getGame() == <game>
	 */
	private void setGame(Game game)
	{
		this.game = game;
	}
	
	/**
	 * Getter for the game attribute.
	 * 
	 * @return Game
	 */
	public Game getGame(){
            return game;
	}
	
	/**
	 * Sets the Logic.
	 * 
	 * @param JLabel Logic
	 * @post Logic is set. | new.getLogic() == <Logic>
	 */
	private void setLogic(JLabel Logic){
            
            this.Logic = Logic;
	}
        
        // Search any logical move available
        private void doAutomove(){
            
            Point locationOnScreen = getLocationOnScreen();
            
            recMove = Automove.CheckLogicForAutomove( game.getMinefield().getMinefield(), locationOnScreen);
            
            
            
            if( !recMove.equals("") ){

                if(recMove.charAt(recMove.length() - 1) == 'L'){
                    int xCoor = Character.getNumericValue(recMove.charAt(0));
                    int yCoor = Character.getNumericValue(recMove.charAt(2));

                    int xTarget = buttons[xCoor][yCoor].getX();
                    int yTarget = buttons[xCoor][yCoor].getY();
                    System.out.println(recMove);
                    Robot robot;


                    try {
                        robot = new Robot();
                        robot.mouseMove(locationOnScreen.x + xTarget + 35, locationOnScreen.y + yTarget +35);
                        robot.mousePress(InputEvent.BUTTON1_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_MASK);

                    } catch (AWTException ex) {
                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if(recMove.charAt(recMove.length() - 1) == 'R'){
                    
                    int xCoor = Character.getNumericValue(recMove.charAt(0));
                    int yCoor = Character.getNumericValue(recMove.charAt(2));

                    int xTarget = buttons[xCoor][yCoor].getX();
                    int yTarget = buttons[xCoor][yCoor].getY();
                    System.out.println(recMove);
                    Robot robot;


                    try {
                        robot = new Robot();
                        robot.mouseMove(locationOnScreen.x + xTarget + 35, locationOnScreen.y + yTarget +35);
                        robot.mousePress(InputEvent.BUTTON3_MASK);
                        robot.mouseRelease(InputEvent.BUTTON3_MASK);

                    } catch (AWTException ex) {
                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }


//                            JOptionPane.showMessageDialog(null, point);
            }
            else
                JOptionPane.showMessageDialog(null, "No logical moves available");
//              JOptionPane.showMessageDialog(null, recMove);

            
            
        }
        
	
	/**
	 * Getter for the LogicLabel attribute.
	 * 
	 * @return JLabel LogicLabel
	 */
	public static JLabel getLogic()	{
            return Logic;
	}
	
	
	/**
	 * Initializes a new game.
	 * 
	 * @param Difficulty
	 * @effect Hides all fields (cf. sets the text to "?").
	 * 	| hideAll()
	 * @effect Enables all fields (cf. makes them clickable).
	 * 	| enableAll()
	 * @effect Starts the logic checking.
	 * 	| startLogic()
	 * @effect Starts the actual game with the given difficulty.
	 * 	| game.newGame(difficulty)
	 */
	private void initGame(Difficulty difficulty)
	{
//                NotLogicalMoves = 0;
            hideAll();
            enableAll();
            startLogic();
            game.newGame(difficulty);
	}
	
	/**
	 * Asks the player if he/she wants to start a new game.
	 * 
	 * @effect If yes: set the player name and difficulty level and initialize the new game.
	 * @effect If no or cancel: do nothing.
	 */
	private void newGame()
	{
                NotLogicalMoves = 0;
                Logic.setText(Integer.toString(NotLogicalMoves) + " Not Logical Moves");
		// Ask the player to start a new game.
		int startNew = JOptionPane.showConfirmDialog(null, "Are you sure you wish to start a new game?", 
				"New game?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		switch(startNew) {
		
			case JOptionPane.YES_OPTION: 
				NotLogicalMoves = 0;
				String player = game.getPlayer();
				Difficulty difficulty = game.getDifficulty();
				
				// Check if we should ask the player name and difficulty again.
				while( player == null || difficulty == null || player.equals("") ) {
					player = askName();
					difficulty = askDifficulty();
				}
				
				game.setPlayer(player);
				game.setDifficulty(difficulty);
				
				// Initialize the new game.
				initGame(difficulty);
				
				break;
				
		  case JOptionPane.NO_OPTION: break;
		  case JOptionPane.CLOSED_OPTION: break;
		  
		}
	}
	
	/**
	 * The player's name is asked and returned as a String
	 * 
	 * @return player
	 */
	private String askName()
	{
		// Ask for the player's name.
		String player = null;
		
		// The input may not be null or empty.
		while(player == null || player.equals("")) {
			player = JOptionPane.showInputDialog(null, "What is your name?", "Your name?", JOptionPane.QUESTION_MESSAGE);
		}
		
		return player;
	}
	
	/**
	 * Let the player choose a difficulty level.
	 * 
	 * @return Difficulty difficulty
	 */
	private Difficulty askDifficulty()
	{
		// Difficulty enumeration.
		Difficulty difficulty = null;
		
		// Possibilities...
		String[] list = { Difficulty.EASY.toString(), Difficulty.NORMAL.toString(), Difficulty.HARD.toString() };
		String answer = null;
		
		// Keep on showing this list, until the player chooses a valid level.
		while( answer == null ){
			answer = (String)JOptionPane.showInputDialog(null, "Choose a difficulty level.", 
				"Difficlulty", JOptionPane.INFORMATION_MESSAGE, null, list, list[1]);
		}
		
		// Check answer.
		if( answer.equals( list[0] ) )
			difficulty = Difficulty.EASY;
		else if( answer.equals( list[1] ) )
			difficulty = Difficulty.NORMAL;
		else if( answer.equals( list[2] ) )
			difficulty = Difficulty.HARD;
		
		return difficulty;
	}
	
	/**
	 * Checks the game to see if it's finished or not.
	 * 
	 * @effect If the game is finished, end it.
	 *  | if( game.isFinished() ) { endGame(); gameOver; }
	 */
	public void checkGame(){
            
//            Logic.setText(Integer.toString(NotLogicalMoves) + " Not Logical Moves");
                
            if( game.isFinished() ) {
			// The player has successfully ended the game.
			endGame();
			JOptionPane.showMessageDialog(null, "Congratulations, you have finished the game!");
			gameOver();
		}
            
        }
	/**
	 * Shows the "solution" of the game.
	 * 
	 * @effect Disables all buttons.
	 * 	| buttons[x][y].setEnabled(false)
	 * @effect Show all mines (give red back color).
	 * @effect Leave all flags (give good ones green color, bad ones orange color)
	 * @effect Show everything else.
	 */
	private void showAll()
	{
		String fieldSolution;
		
		// Columns...
		for( int x=0 ; x<10 ; x++ ) {
			
			// Rows ...
			for( int y=0 ; y<10 ; y++ ) {
				
				// Don't allow the player to click on the buttons.
				buttons[x][y].setEnabled(false);
				
				// Get the current content of the field first, check later.
				fieldSolution = this.game.getMinefield().getMinefield()[x][y].getContent();
				
				// Is the field still unrevealed?
				if( fieldSolution.equals("?") ) {
					
					// Yes, get it's # neighbours.
					fieldSolution = Integer.toString(this.game.getMinefield().getMinefield()[x][y].getNeighbours());
					
					// Is it a mine?
					if(this.game.getMinefield().getMinefield()[x][y].getMine()) {
						// Yes, color it.
						fieldSolution = "M";
						buttons[x][y].setBackground(Color.red);
					}else{
						// No, maybe it's a zero?
						if(fieldSolution.equals("0"))
							buttons[x][y].setBackground(Color.lightGray);
						else
							buttons[x][y].setBackground(Color.gray);
					}
				}
				
				// This field is already flagged!
				if( fieldSolution.equals("F") ) {
					// Is it correctly flagged?
					if(!this.game.getMinefield().getMinefield()[x][y].getMine()) {
						// No, color it orange!
						buttons[x][y].setBackground(Color.orange);
					}
				}
				
				// Set the text to the solution.
				buttons[x][y].setText(fieldSolution);
			}
			
		}
	}
	
	/**
	 * Enables all fields (cf. make them clickable).
	 * 
	 * @effect buttons[x][y].setEnabled(true)
	 */
	private void enableAll()
	{
		for( int x=0 ; x<10 ; x++ ) {
			for( int y=0 ; y<10 ; y++ ) {
				buttons[x][y].setEnabled(true);
			}
		}
	}
	
	/**
	 * Disables all fields (cf. make them unclickable).
	 * 
	 * @effect buttons[x][y].setEnabled(false)
	 */
	private void disableAll()
	{
		for( int x=0 ; x<10 ; x++ ) {
			for( int y=0 ; y<10 ; y++ ) {
				buttons[x][y].setEnabled(false);
			}
		}
	}
	
	private void startLogic()
	{
		Refresh = new Thread() {
			public void run()
			{
				// Show the amount of moves, that were not logical
				while(getGame().isPlaying()) {
					
					// Update the number of not logical moves.
					Logic.setText(Integer.toString(NotLogicalMoves) + " Not Logical Moves");
					
				}
			}
		};
	}
	
	/**
	 * Hides all fields (cf. make the content "?" and background white).
	 * 
	 * @effect Set the content of all fields to "?".
	 *  | game.getMinefield().resetAll()
	 * @effect Set the content of all "field buttons" to "?". 
	 *  | buttons[x][y].setText("?")
	 * @effect Set the background of all "field buttons" to white. 
	 *  | buttons[x][y].setBackground(Color.white)
	 */
	private void hideAll()
	{
		game.getMinefield().resetAll();
		for( int x=0 ; x<10 ; x++ ) {
			for( int y=0 ; y<10 ; y++ ) {
				buttons[x][y].setText("?");
				buttons[x][y].setBackground(Color.white);
			}
		}
	}
	
	/**
	 * End the game.
	 * 
	 * @effect game.endGame()
	 * @effect Shows all fields. | showAll()
	 * @effect Disables all fields. | disableAll()
	 */
	private void endGame()
	{
            
            String LogicResult = "";
            
            int i = 0;
            
            while(getLogic().getText().charAt(i) != ' '){
                
                LogicResult += getLogic().getText().charAt(i);
                i++;
            }
            
//            System.out.println(getLogic().getText());
            JOptionPane.showMessageDialog(null, "Number of not logical moves made by you: " + LogicResult);
            game.endGame();
            showAll();
            disableAll();
                
	}
	
	/**
	 * The game is finished, ask for a new game.
	 * 
	 * @effect If new game: set player name, set difficulty level and initialize the game.
	 * @effect If no new game: reset player info and do nothing else. | game.gameOver()
	 */
	private void gameOver()
	{
		// Game is over. Ask the player to start a new game.
		int startNew = JOptionPane.showConfirmDialog(null, "Game over. Do you want to start a new game?", 
				"New game?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		switch(startNew) {
		
			case JOptionPane.YES_OPTION: 
                                NotLogicalMoves = 0;
                                Logic.setText(0 + " Not Logical Moves");
				String player = game.getPlayer();
				Difficulty difficulty = game.getDifficulty();
				
				// Check if we should ask the player name and difficulty again.
				if( player.equals("") || player == null || difficulty == null ) {
					player = askName();
					difficulty = askDifficulty();
                                        
				}
				
				game.setPlayer(player);
				game.setDifficulty(difficulty);
				
				// Initialize the new game.
				initGame(difficulty);
				
				break;
			
		  case JOptionPane.NO_OPTION: game.gameOver(); break;
		  case JOptionPane.CLOSED_OPTION: game.gameOver(); break;
	  
		}
	}
	
	/**
	 * Quits the game.
	 * 
	 * @effect If confirmed: game.quitGame()
	 */
	private void quitGame()
	{
		int quit = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", 
				"Quit game?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		switch(quit) {
			case JOptionPane.YES_OPTION: 
				game.quitGame();
				break;
			case JOptionPane.NO_OPTION: break;
			case JOptionPane.CLOSED_OPTION: break;
		}
	}
	
	/**
	 * Handles clicks.
	 * 
	 * @param e
	 */
	public void mouseClicked(MouseEvent e){
            
		JButton button = (JButton)e.getSource();
                
                if( button.getName().equals("automoveButton") ){
                    
                    if(game.isPlaying()){
                        
                        doAutomove();                        
                    }
                }
                    
                
		// Which button is pressed?
		if( button.getName().equals("newGameButton") ) {
			
                    NotLogicalMoves = 0;
                    newGame();
                        
			
		} else if( button.getName().equals("quitButton") ) {
			
			quitGame();
			
		} else if( game.isPlaying() && !button.getName().equals("automoveButton") ) {
			// A field is pressed.
			
			// Get coordinates (button.getName().equals("x,y")).
			String[] co = button.getName().split(",");
			int x = Integer.parseInt(co[0]);
			int y = Integer.parseInt(co[1]);
			
			// Get field information.
			boolean isMine = game.getMinefield().getMinefield()[x][y].getMine();
			int neighbours = game.getMinefield().getMinefield()[x][y].getNeighbours();
			
			if ( SwingUtilities.isLeftMouseButton(e) ) {
                            
                                // LEFT CLICK
                                
                                //This part checks a move for a logic.
                                //...............................
//                                String a = Integer.toString(x) + "||" + Integer.toString(y);

                                
//                                System.out.println(NotLogicalMoves);
                                if( !button.getName().equals("automoveButton") ){
                                    
                                    CheckLogic t = new CheckLogic(x, y, game.getMinefield().getMinefield());  
                                    t.run();
                                }
                                
                                
//                                System.out.println("3");
//                                Logic = new Check();
//                                Check.start();
                            
                                
                                //........................
                                
				if( isMine ) {
					
					// boom!
					button.setText("M");
					button.setBackground(Color.red);
					
					// End the game
					endGame();
					JOptionPane.showMessageDialog(null, "You stepped on a mine! Game Over!");
					gameOver();

				} else {
					
					// The player has clicked on a number.
					game.getMinefield().getMinefield()[x][y].setContent(Integer.toString(neighbours));
					button.setText(Integer.toString(neighbours));
					
                                        
                                        
					// Did the player click on a 0?
					if( neighbours == 0 ) {
						// Show all surrounding fields.
						button.setBackground(Color.lightGray);
						game.findZeroes(buttons, x, y);
					} else {
						button.setBackground(Color.gray);
					}
					
				}
                                
                                
                                
                                
			} else if ( SwingUtilities.isRightMouseButton(e) ) {
            // RIGHT CLICK
				
				if( game.getMinefield().getMinefield()[x][y].getContent().equals("F") ) {
					
					// set unknown
					game.getMinefield().getMinefield()[x][y].setContent("?");
					button.setText("?");
					button.setBackground(Color.white);
                                        
                                        
                                        //If it is a mine - decrease the number of marked mines
                                        if (isMine){
                                            
                                            GlobalMarkedMines--;
                                        }
					
				} else if ( game.getMinefield().getMinefield()[x][y].getContent().equals("?") ) {
					
					// set flag
					game.getMinefield().getMinefield()[x][y].setContent("F");
					button.setText("F");
					button.setBackground(Color.green);				
                
                                        // If it is a mine - increase the number of marked mines
                                        if (isMine){
                                            
                                            GlobalMarkedMines++;
                                        }
                                    }
				}
			
			}
			checkGame();
                        
		}
	
	public void mousePressed(MouseEvent e)
	{


	}
	
	public void mouseReleased(MouseEvent e)
	{
	}
	
	public void mouseEntered(MouseEvent e)
	{
	}
	
	public void mouseExited(MouseEvent e)
	{
	}
}