package SandL;

import java.util.*;
import SandL.DashBoard;

public class Game {
	
	int snakeNumber;
	int ladderNumber;

	Deque<Player> playerlist;
	ArrayList<Snake> snakelist;
	ArrayList<Ladder> ladderlist;
		
	DashBoard board;
	Dice dice;
	
	public void initBoard(int sizeOfBoard, int snakeNumber, int ladderNumber) throws Exception {
		
		
		this.snakeNumber = snakeNumber;
		this.ladderNumber = ladderNumber;	
		
		snakelist = new ArrayList<Snake>(snakeNumber);
		ladderlist = new ArrayList<Ladder>(ladderNumber);		
		playerlist = new LinkedList<Player>();
		
		board = new DashBoard();
		board.DashBoard(sizeOfBoard);
		
		dice = new Dice();
		dice.Dice(1, 6);
		
		setSnakeandLadder();
	}
	
	public void setSnakeandLadder() throws Exception {
		
		Set<String> snakeladderheadSet = new HashSet<String>();
		
		if(snakeNumber > board.size) {
			throw new Exception("snake Number should not be greater then the size of the board");
		}
		
		if(ladderNumber> board.size) {
			throw new Exception("ladder Number should not be greater then the size of the board");
		}
		
		for(int i = 1; i <= snakeNumber; i ++) {
			while(true) {
				
				int snakeHead = (int) ((Math.random() * ((board.size - 1) - 2)) + 2);
				int snakeEnd = (int) ((Math.random() * ((board.size - 1) - 2)) + 2);
				
				if(snakeHead <= snakeEnd) {
					continue;
				}
				
				String headTail = snakeHead + snakeEnd + "";
				if(!snakeladderheadSet.contains(headTail)) {
					Snake snake = new Snake();
					snake.Snake(snakeHead, snakeEnd);
					snakeladderheadSet.add(headTail);
					snakelist.add(snake);
					break;
				}
			}
		}
		
		for(int i = 1; i <= ladderNumber; i ++) {
			while(true) {
				
				int ladderHead = (int)((Math.random() * ((board.size - 1) - 2)) + 2);
				int ladderEnd = (int)((Math.random() * ((board.size - 1) - 2)) + 2);
				
				if(ladderHead >= ladderEnd) {
					continue;
				}
				
				String headTail = ladderHead + ladderEnd + ""; 
				if(!snakeladderheadSet.contains(headTail)) {
					Ladder ladder = new Ladder();
					ladder.Ladder(ladderHead, ladderEnd);
					snakeladderheadSet.add(headTail);
					ladderlist.add(ladder);
					break;
				}
			}
		}
	}

	public void addPlayer(Player player) {
		playerlist.addLast(player);
	}
	
	public int getNewPosition(int value) {
		
		for(int i = 0; i < snakelist.size(); i ++) {
			if(snakelist.get(i).snakeStartPoint == value) {
				System.out.println("Snake Bite");
				return snakelist.get(i).snakeEndPoint;
			}
			else if(ladderlist.get(i).ladderStartPoint == value) {
				System.out.println("Ladder climbed");
				return ladderlist.get(i).ladderEndPoint;
			}
		}
		
		return value;
	}
	
	public void playGame() {
		
		while(true) {
			
			Player player = playerlist.poll();
			int currdicevalue = dice.roll();
			
			System.out.println("Player "+player.name + " hit "+ currdicevalue);
			
			if(player.currPosition == 0) {
				if(currdicevalue != 1) {
					playerlist.addLast(player);
					continue;
				}
			}
			
			if(currdicevalue + player.currPosition < board.size) {
				
				int newposition = getNewPosition(player.currPosition + currdicevalue);	
				System.out.println("Player "+player.name + "  " + player.currPosition + " --> "+newposition);
				player.currPosition = newposition;
			}
			else if( currdicevalue + player.currPosition == board.size) {
				player.isWon = true;
				System.out.println("Player "+player.name + "  " + player.currPosition + " --> "+( currdicevalue + player.currPosition));
				System.out.println("Player "+ player.name + "Won");
				break;
			}
			
			if(!player.isWon) {
				playerlist.addLast(player);
			}
			
			
		}
	}

}
