package application;

import java.sql.SQLException;
import java.util.ArrayList;

/*
 * usertypes:
 * 1->bidder
 * 0->seller
 */

public class members extends AbsDBMethods{
	protected int id, phone, usertype;
	protected String Fname, Lname, email, password;
	public ArrayList<members> memberslist = new ArrayList<members>();
	private ArrayList<Object[]> list;
	private ArrayList<String> emails;
	
	public members() {
		getinfo();
	}
	
	public members(int id, String Fname, String Lname, String email, String password, int phone, int usertype) {
		this.id = id;
		this.Fname = Fname;
		this.Lname = Lname;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.usertype = usertype;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public String getLname() {
		return Lname;
	}

	public void setLname(String lname) {
		Lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public members(int id, String Fname, String Lname, String email, int phone) {
		this.id = id;
		this.Fname = Fname;
		this.Lname = Lname;
		this.email = email;
		this.phone = phone;
	}
	
	public void Register(int id, String Fname, String Lname, String email, String password, int phone, int usertype) {
			
		this.setId(id);
		this.setFname(Fname);
		this.setLname(Lname);;
		this.setEmail(email);
		this.setPassword(password);
		this.setPhone(phone);
		this.setUsertype(usertype);
		
		try {
			this.insertToDB();
			System.out.println("Registered Successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int Login(String username, String password) {
		/*ArrayList<String> mails = new ArrayList<>();
		mails = getEmails();
		for(String i: mails) {
			System.out.println(i);
		}*/
		for(members i: memberslist) {
			if(i.email.equals(username)) {
				if(i.password.equals(password)) {
					this.setId(i.getId());
					this.setFname(i.getFname());
					this.setLname(i.getLname());
					this.setEmail(i.getEmail());
					this.setPassword(i.getPassword());
					this.setPhone(i.getPhone());
					this.setUsertype(i.getUsertype());
					System.out.println("Logged in Successful!!!!");
					return 0;
				}else {
					//System.out.println("Logging Failed!!, wrong password");
				}
			}else {
				//System.out.println("Logging Failed!!, wrong email");
			}
		}
		
		return -3;
		
	}
	
	public void getinfo() {
		list = getall();
		 for(Object[] user : list) {
	            memberslist.add(new members((int) user[0], (String) user[1], (String) user[2], (String) user[3],
	                    (String) user[4], (int) user[5], (int) user[6]));
	        }
	}
	
	public ArrayList<members> getmemberslist(){
		return memberslist;
	}
	
	public ArrayList<String> getEmails(){
		emails = new ArrayList<>();
		for (members user : memberslist) {
            emails.add(user.email);
        }
        return emails;
	}
	
	public void insertToDB() throws SQLException {
		super.insertToDB();
	}
	
	public void delete(String email) throws SQLException {
		super.delete(email);
	}

	@Override
	protected String getAttributes() {
		return "id,fname,lname,email,password,phone,usertype";
	}

	@Override
	protected String getValues() {
		return "null, \"" + Fname + "\" , \"" + Lname + "\" , \"" + email + "\" , \"" + password + "\" , " + phone + " , " + usertype;
	}

	@Override
	protected String getWhere() {
		return " ";
	}
	
}
