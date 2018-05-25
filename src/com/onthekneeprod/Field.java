package com.onthekneeprod;


/**

 * 
 * This class represents one square in a minefield.
 * A Field can contain a mine or not.
 * Each Field has neighbours: the surrounding mines (horizontal, vertical and diagonal).
 * Only the content of the Field is visible to the player.
 */
public class Field {
	
	/**
	 * The "mine" variable is
	 *  false if the field is safe,
	 *  true if the field contains a mine. 
	 */
	private boolean mine;
	
	/**
	 * The content of a field can be a...
	 *  "?" - indicating an unknown field
	 *  "F" - a flagged field
	 *  "M" - a mine
	 *  a number ranging from 0 to 8 - indicating the number of surrounding mines
	 */
	private String content;
	
	/**
	 * The integer "neighbours" indicates the number of surrounding mines.
	 */
	private int neighbours;
	
	/**
	 * Field constructor.
	 * Every Field initially contains no mine.
	 * It's up to the Minefield class to allocate a certain number of fields mines.
	 * The content is initially unknown.
	 * 
	 * @post The mine attribute is set to false. | new.getMine() == false
	 * @post The content is set to unknown ("?"). | new.getContent().equals("?")
	 * @post The Field initially has no neighbours, so neighbours is set to 0. | new.getNeighbours() == 0
	 */
	public Field(){
		setMine(false);
		setContent("?");
		setNeighbours(0);
	}
	
	/**
	 * Getter for the mine attribute.
	 * 
	 * @return true if this field contains a mine; else false.
	 *  | result == this.mine
	 */
	public boolean getMine()
	{
		return mine;
	}
	
	/**
	 * Sets the mine attribute to <mine>.
	 * 
	 * @param mine
	 * @post mine is set. | new.getMine() == mine
	 */
	public void setMine(boolean mine)
	{
		this.mine = mine;
	}
	
	/**
	 * Getter for the content attribute.
	 * 
	 * @return The content String. | result == this.content
	 */
	public String getContent()
	{
		return content;
	}
	
	/**
	 * Sets content to <content>.
	 * 
	 * @param content
	 * @post content is set. | new.getMine() == content
	 */
	public void setContent(String content)
	{
		this.content = content;
	}
	
	/**
	 * Getter for the neighbours attribute.
	 * 
	 * @return The number of neighbouring fields containing a mine ("neighbours" integer).
	 *  | result == this.neighbours
	 */
	public int getNeighbours()
	{
		return neighbours;
	}
	
	/**
	 * Sets neighbours to <neighbours>.
	 * 
	 * @param neighbours
	 * @post neighbours is set. | new.getNeighbours() == neighbours
	 */
	public void setNeighbours(int neighbours)
	{
		this.neighbours = neighbours;
	}

}
