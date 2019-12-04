package COSC381Baseball;

public class Player {
	//Player	Team	Pos	OBP	SLG	OPS	AVG for Batters
	//Player	Team	Pos	ER	K	BB	ERA for Pitchers
	
	public boolean drafted = false;
	public boolean ignore = false;
	public String position, name, mlbTeam;
	public String team = "No Team";
	//stats tracked for ranking
	public double avg, obp, slg, ops, er, era, k, bb;
	private double rank = 0;
	
	//Constructor
	public Player() {
		
	}
	public Player(String name) {
		this.name = name;
	}
	//Will print out the player
	public String toString() {
		String x = "";
		x+=this.name+" | Team: "+this.team+" | Position: "+this.position+" | Rank: "+this.rank+"\n";
		return x;
	}
	public String toStringSave() {
		String x = "";
		if(this.position.contains("P"))
		{
			x+=this.name+" "+this.mlbTeam+" "+this.position+" "+this.er+" "+this.k+" "+this.bb+" "+this.era+"\n";
		}
		else 
		{
			x+=this.name+" "+this.mlbTeam+" "+this.position+" "+this.avg+" "+this.obp+" "+this.slg+" "+this.ops+"\n";
		}
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
