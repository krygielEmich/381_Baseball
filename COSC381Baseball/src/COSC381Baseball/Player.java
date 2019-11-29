package COSC381Baseball;

public class Player {
	public boolean drafted = false;
	public boolean ignore = false;
	//Just a couple of placeholders for now until we decide what to implement here.
	public double RBI;//Runs Batted in
	public double wl;//Win/Loss Ratio
	public String position;
	public String team;
	public String name;
	//stats tracked for ranking
	public double avg, obp, slg, ops, er, era, k, bb;
	private double rank = 0;
	
	//Constructor
	public Player() {
		
	}
	public Player(String name) {
		this.name = name;
	}
	public Player(double RBI, double wl, String position, String name) {
		this.RBI=RBI;
		this.wl=wl;
		this.position=position;
		this.name = name;
	}
	//Will print out the player
	public String toString() {
		String x = "";
			x= x+this.name+" "+this.position+" W/L: "+wl+" RBI: "+RBI+"\n";
		return x;
	}
	public String toStringSave() {
		String x = "";
			x+= this.name+" "+this.position+" "+wl+" "+RBI+"\n";
		return x;
	}
	//Setters/Getters
	public void setTeam(String team) {
		this.team = team;
	}
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
	public void setName(String name) {
		this.name = name;
	}
	//stats
	public void setPositon(String pos) {
		position = pos;
	}
	public void setAvg(double a) {
		avg = a;
	}
	
	public void setObp(double o) {
		obp = o;
	}
	public void setSlg(double s) {
		slg = s;
	}
	public void setOps(double o) {
		ops = o;
	}
	
	public void setEra(double e) {
		era = e;
	}
	
	public void setRank(double r) {
		rank = r;
	}
	public void setIgnore(boolean x) {
		ignore = x;
	}
	
	public double getAvg() {
		return avg;
	}
	public double getObp() {
		return obp;
	}
	public double getSlg() {
		return slg;
	}
	public double getOps() {
		return ops;
	}
	public double getEra() {
		return era;
	}
	public double getRank() {
		return rank;
	}
	public String getTeam() {
		return team;
	}
	public double getEr() {
		return er;
	}
	public void setEr(double er) {
		this.er = er;
	}
	public double getK() {
		return k;
	}
	public void setK(double k) {
		this.k = k;
	}
	public double getBb() {
		return bb;
	}
	public void setBb(double bb) {
		this.bb = bb;
	}
	
	
	
}
