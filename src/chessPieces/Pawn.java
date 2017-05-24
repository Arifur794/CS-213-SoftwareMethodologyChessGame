package chessPieces;

/**
 * Group 105
 * @author Arifur Rahman	
 * @author Monique Gordon
 *
 */

public class Pawn extends ChessPiece {
	
	public boolean move1;
	public boolean movedTwo;
	
	public Pawn(String pos, String pieceColor){
		
		move1 = true;
		movedTwo = false;
		currPos = pos;
		color = pieceColor;
	}
	
	public String toString(){
		if (color.equals("white")) return "wp";
		return "bp";
	}
	
	/**pawn path checker*/
	public boolean pathValid(String dest){
		int[] orderedPairCurrent = posStringToArray(this.currPos);
		int[] orderedPair = posStringToArray(dest);
		
		if(!validCoordinateCheck(orderedPair)){
			return false;
		}
		if(orderedPair[0] != orderedPairCurrent[0]){
			
			//when letter is different, illegal diagonal movement
			return false;
		}
		
		
		if(orderedPairCurrent[0] == orderedPair[0]){
			if(orderedPairCurrent[1] == orderedPair[1]){//same spot will not be valid
				
				System.out.println("Illegal move, try again");
				return false;
			}
			if(move1){
				if(orderedPair[1]-orderedPairCurrent[1] == -2 && color.equals("black")){
					
					//on pawns first move it can move two spots
					return true;
				} else if (orderedPair[1]-orderedPairCurrent[1] == 2 && color.equals("white")){
					return true;
				}
			}
			
			// one spot for all other moves
			if(orderedPair[1]-orderedPairCurrent[1] == -1 && color.equals("black")){
				return true;
			} else if (orderedPair[1]-orderedPairCurrent[1] == 1 && color.equals("white")){
				return true;
			}
		}
		
		return false;
	}
	
	public void setMove1Boolean(boolean move1Value){
		this.move1 = move1Value;
	}
	
	
	
}