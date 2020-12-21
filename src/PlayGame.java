package SandL;

import java.util.Scanner;

public class PlayGame {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		Game game = new Game();
		
		System.out.println("Enter the size of the board");
		int boardsize = sc.nextInt();
		
		System.out.println("enter the Snake number");
		int snakecount = sc.nextInt();
		
		System.out.println("Enter the ladder number");
		int laddercount = sc.nextInt();
		
		game.initBoard(boardsize, snakecount, laddercount);
		
		System.out.println("now enter the player number");
		int playercount = sc.nextInt();
		
		sc.nextLine();
		for(int i = 0; i < playercount; i ++) {
			System.out.println("Enter the player name");
			String name = sc.nextLine();
			Player p = new Player();
			p.Player(name);
			game.addPlayer(p);
		}
		
		game.playGame();
		

	}

}
