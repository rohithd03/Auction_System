package applicationusers;

import java.util.ArrayList;

import application.members;

public class Admin {
	
	private String uname;
	private String pword;
	private int sum;
	
	public Admin() {
		this.uname = "Admin";
		this.pword = "admin";
	}
	
	public String getusername() {
		return uname;
	}
	
	public String getpassword() {
		return pword;
	}
	
	public void setusername(String username) {
		this.uname = username;
	}
	
	public void setpassword(String password) {
		this.pword = password;
	}
	
	public int getbiddercount() {
		members user = new members();
		ArrayList<members> list = user.getmemberslist();
		for(members i : list) {
			if(i.getUsertype() == 1) {
				sum += 1;
			}
		}
		return sum;
	}
	public int getsellercount() {
		members user = new members();
		ArrayList<members> list = user.getmemberslist();
		sum = 0;
		for(members i : list) {
			if(i.getUsertype() == 0) {
				sum += 1;
			}
		}
		return sum;
	}
	
}
