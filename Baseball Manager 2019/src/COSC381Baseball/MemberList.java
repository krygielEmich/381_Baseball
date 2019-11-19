package COSC381Baseball;

import java.util.ArrayList;
public class MemberList {

ArrayList<Member> memberList = new ArrayList<Member>();
	
	//Constructor
	public MemberList(Member member) {
		memberList.add(member);
	}
	//Getter
	public Member getMember(String name) {
		for(int i = 0; i<=memberList.size();i++) {
			if(memberList.get(i).getName().equalsIgnoreCase(name))return memberList.get(i);
		}
		System.out.print("Player not found");
		return null;
	}
	//Adds a Member to the end of the list
	public void addMember(Member member) {
		memberList.add(member);
	}
}

