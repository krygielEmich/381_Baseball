package COSC381Baseball;

public class Player {
	public boolean drafted = false;
	//Just a couple of placeholders for now until we decide what to implement here.
	public double RBI;//Runs Batted in
	public double wl;//Win/Loss Ratio
	public String position;
	public String name;
	
	//Constructor
	public Player(double RBI, double wl, String position, String name) {
		this.RBI=RBI;
		this.wl=wl;
		this.position=position;
		this.name = name;
	}
	//Setters/Getters
	public void setDrafted(boolean drafted) {
		this.drafted=drafted;
	}
	public boolean getDrafted() {
		return this.drafted;
	}
	public double getRBI() {
		return this.RBI;
	}
	public double getwl() {
		return wl;
	}
	public String getPosition() {
		return this.position;
	}
	public String getName() {
		return this.name;
	}
}
