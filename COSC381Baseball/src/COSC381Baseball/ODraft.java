package COSC381Baseball;

public class ODraft {
	boolean validInput = true;
	
	public ODraft(Player player, Member member, PlayerList mlbList) {
		if(mlbList.exists(player)){
				if(!mlbList.getPlayer(player.getName()).getDrafted()) {
					if(player.getPosition()!="P"&&(member.playerList.getPos(player.getPosition())).getName()!=""||player.getPosition()=="P"&&member.P<5) {
						member.playerList.addPlayer(player);
						player.setTeam(member.getName());
						if(player.getPosition()=="P")member.P++;
						System.out.print(player.getName()+" drafted to "+member.getName()+"\n");
					}
					else {
						System.out.println("You already have a "+player.getPosition()+" drafted!");
					}
				}
				else {
					System.out.print("Player is already Drafted!\n");
					this.validInput=false;
				}
		}
		else {
			this.validInput=false;
		}
	}
	public boolean getValidInput() {
		return validInput;
	}
}
