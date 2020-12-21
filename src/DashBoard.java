package SandL;

public class DashBoard {
	int size;
	int startpoint;
	int endpoint;
	
	public void DashBoard(int size) {
		this.size = size;
		this.startpoint = 1;
		this.endpoint = startpoint + size - 1;
	}
}
