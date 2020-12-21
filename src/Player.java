package SandL;

public class Player {
	String name;
	int currPosition;
	boolean isWon;
	
	public void Player(String name) {
		this.name = name;
		this.currPosition = 0;
		this.isWon = false;
	}
}
