package SandL;

public class Dice {
	
	int minValue;
	int maxValue;
	
	public void Dice(int minValue, int maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	public int roll() {
		return (int) ((Math.random() * ((maxValue  + 1)- minValue)) + minValue);
	}
}
