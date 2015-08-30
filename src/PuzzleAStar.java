//Solving 8 puzzle using A*
//Daniel Olvera danog99@gmail.com
//Dante Serna dsercam93@gmail.com


import java.util.*;

public class PuzzleAStar {
	private Board myStart;
	private Board myGoal;
	public Queue<Board> openList;
	public HashSet<Integer> closedList;
	
	public PuzzleAStar(Board start, Board goal){
		this.myStart = start;
		this.myGoal = goal;
		this.openList = new PriorityQueue<Board>(heurComparator);
		this.closedList = new HashSet<Integer>();
	}
	
	
	
	//Implementation for the priority queue
	public static Comparator<Board> heurComparator = new Comparator<Board>(){
		@Override
		public int compare(Board start, Board goal){
			return start.getMyHeur() - goal.getMyHeur();
		}
	};
	
	//Using hamming priority function which calculates number of blocks in wrong position
	//and moves made so far.
	public int heuristics(int[] first){
		int result = 0;
		int startSize = first.length;
		for(int i = 0; i< startSize; i++){
			if(first[i]!=this.myGoal.getArrayValue(i)){
				result++;
			}
		}
		return result;
	}
	
	public static int getZeroIndex(int[] board){
		int x = 0;
		int index = 0;
		while(x!=board[index]){
			index++;
		}
		return index;
	}
	
	public static void printBoard(int[] board1){
		for(int i=0; i<board1.length; i++){
			System.out.println(board1[i]);
		}
		System.out.println("\n");
	}
	//Solver for the goal
	public boolean solve(){
		this.openList.add(myStart);
		int index;
		while(openList.size()>0){
			Board start = openList.poll();
			index = getZeroIndex(start.getMyBoardArray());
			if(index-3>=0){
				int[] child = start.getMyBoardArray().clone();
				child[index] = child[index-3];
				child[index-3] = 0;
				printBoard(child);
				int encoded = Board.encode(child);
				if(encoded==this.myGoal.getInt()){
					//System.out.println("\nHEY"+start.getMoves());
					return true;
				}
				if(!closedList.contains(encoded)){
					this.closedList.add(encoded);
					//System.out.println("IN1 "+encoded+" "+ this.myGoal.getInt());
					int moves = start.getMoves()+1;
					this.openList.add(new Board(child,moves,this.heuristics(child)+moves));
				}
			}
			if(index+3<=8){
				int[] child = start.getMyBoardArray().clone();
				child[index] = child[index+3];
				child[index+3] = 0;
				printBoard(child);
				int encoded = Board.encode(child);
				if(encoded==this.myGoal.getInt()){
					//System.out.println("\nHEY"+start.getMoves());
					return true;
				}
				if(!this.closedList.contains(encoded)){
					this.closedList.add(encoded);
					int moves = start.getMoves()+1;
					this.openList.add(new Board(child,moves,this.heuristics(child)+moves));
				}
			}
			if(index+1<=8){
				int[] child = start.getMyBoardArray().clone();
				child[index] = child[index+1];
				child[index+1] = 0;
				printBoard(child);
				int encoded = Board.encode(child);
				if(encoded==this.myGoal.getInt()){
					//System.out.println("\nSteps to reach the goal: "+start.getMoves());
					return true;
				}
				if(!closedList.contains(encoded)){
					this.closedList.add(encoded);
					//System.out.println("IN3 "+encoded+" "+ this.myGoal.getInt());
					int moves = start.getMoves()+1;
					this.openList.add(new Board(child,moves,this.heuristics(child)+moves));
				}
			}
			if(index-1>=0){
				int[] child = start.getMyBoardArray().clone();
				child[index] = child[index-1];
				child[index-1] = 0;
				printBoard(child);
				int encoded = Board.encode(child);
				if(encoded==this.myGoal.getInt()){
					//System.out.println("\nHEY"+start.getMoves());
					return true;
				}
				if(!closedList.contains(encoded)){
					this.closedList.add(encoded);
					//System.out.println("IN4 "+encoded+" "+ this.myGoal.getInt());
					int moves = start.getMoves()+1;
					this.openList.add(new Board(child,moves,this.heuristics(child)+moves));
				}
			}
			
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[] start = {0,1,2,3,4,5,6,7,8};
		int[] goal = {1,2,3,4,5,6,7,8,0};
		Board testStart = new Board(start,0,0);
		Board testGoal = new Board(goal,0,0);
		PuzzleAStar test = new PuzzleAStar(testStart,testGoal);
		System.out.println(test.solve());
	}
}

