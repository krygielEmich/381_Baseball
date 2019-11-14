package COSC381Baseball;

import java.util.ArrayList;
public class MemberList {

ArrayList<Member> memberList = new ArrayList<Member>();
	
	//Constructor
	public MemberList(Member member) {
		memberList.add(member);
	}
	//Getter
	public Member getMember(int index) {
		return memberList.get(index);
	}
	//Adds a Member to the end of the list
	public void addMember(Member member) {
		memberList.add(member);
	}
}

