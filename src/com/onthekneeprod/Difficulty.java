package com.onthekneeprod;


public enum Difficulty {
    
	
	EASY {
		int NumberOfMines = 8;
                
                public int GlobalGetNumberOfMines(){
            
                    return NumberOfMines;
                }
                
		/**
		 * Returns the number of mines.
		 * 
		 * @return The Integer 8. | result == 8
		 */
		@SuppressWarnings("unused")
		public int getNumberOfMines()
		{
			return 8;
		}
		

		
		/**
		 * Returns the String "Easy".
		 * 
		 * @return The String "Easy". | result.equals("Easy")
		 */
		public String toString()
		{
			return "Easy";
		}
		
	},
	NORMAL {
            
		int NumberOfMines = 16;
                
                public int GlobalGetNumberOfMines(){
            
                    return NumberOfMines;
                }
                
		/**
		 * Returns the number of mines.
		 * 
		 * @return The Integer 16. | result == 16
		 */
		@SuppressWarnings("unused")
		public int getNumberOfMines()
		{
			return 16;
		}
		
		/**
		 * Returns the String "Normal".
		 * 
		 * @return The String "Normal". | result.equals("Normal")
		 */
		public String toString()
		{
			return "Normal";
		}
		
	},
	HARD {
            
            int NumberOfMines = 24;
		
            public int GlobalGetNumberOfMines(){
            
                return NumberOfMines;
            }
		/**
		 * Returns the number of mines.
		 * 
		 * @return The Integer 24. | result == 24
		 */
		@SuppressWarnings("unused")
		public int getNumberOfMines()
		{
			return 24;
		}
		
		
		/**
		 * Returns the String "Hard".
		 * 
		 * @return The String "Hard". | result.equals("Hard")
		 */
		public String toString()
		{
			return "Hard";
		}
		
	};
        
        
	abstract int getNumberOfMines();
        

}
