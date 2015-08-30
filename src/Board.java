//Board class for 8 puzzle using A*
//Daniel Olvera danog99@gmail.com
//Dante Serna dsercam93@gmail.com



public class Board {
	
	private int[] myBoardArray;
	private int myBoardInt;
	private int movesSoFar;
	private int myHeur;
	
	//Constructor for receiving an array of integers representation of the board
	public Board(int[] myBoard, int moves, int heur){
		this.myBoardArray = myBoard;
		this.myBoardInt = encode(myBoard);
		this.movesSoFar = moves;
		this.myHeur = heur;
	}
	
	//Constructor for receiving an integer representation of the board
	public Board(int myBoard, int moves, int heur){
		this.myBoardArray = decode(myBoard);
		this.myBoardInt = myBoard;
		this.movesSoFar = moves;
		this.myHeur = heur;
	}
	
	public static int encode(int[] board){
		int result = 0;
		int maxFact = board.length -1;
		for(int i = 1; i<board.length; i++){
			int index = findIndexOnBoard(board, i);
			//System.out.println(index+"*"+maxFact+"!");
			result += index * factorial(maxFact);
			maxFact--;
		}
		return result;
	}
	
	public static int[] decode(int board){
		int[] decBoard = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
		int maxDecFact = decBoard.length - 1;
		for(int i = 1; i<decBoard.length; i++){
			int index = board/factorial(maxDecFact);
			setIndexOnBoard(decBoard, i, index);
			board -= (index*factorial(maxDecFact));
			maxDecFact--;
			
		}
		return decBoard;
	}
	
	
	//Getters for the class
	public int[] getMyBoardArray(){
		return this.myBoardArray;
	}
	
	public int getArrayValue(int index){
		return this.myBoardArray[index];
	}
	
	public int getInt(){
		return this.myBoardInt;
	}
	
	public int getMyHeur(){
		return this.myHeur;
	}
	
	public int getMoves(){
		return this.movesSoFar;
	}
	
	//Methods for encoding and decoding
	public static int factorial(int number){
		int result = 1;
		for(int i=1; i<=number; i++){
			result *= i;
		}
		return result;
	}
	
	public static int findIndexOnBoard(int[] board, int number){
		int[] temp = board.clone();
		int count = 0;
		for(int i=0; i<temp.length; i++){
			if(temp[i]==number) {
				temp[i] = -1;
				break;
			}
			if(temp[i] > -1) count++;
		}
		return count;
	}
	
	public static void setIndexOnBoard(int[] decBoard, int number, int index){
		int index1 = 0;
		int count = 0;
		while(index != index1){
			if(decBoard[count]==-1)index1++;
			count++;
		}
		while(decBoard[count]!=-1){
			count++;
		}
		decBoard[count] = number;
	}
	
	
}
