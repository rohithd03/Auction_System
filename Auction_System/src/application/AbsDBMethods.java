package application;

import java.sql.SQLException;
import java.util.ArrayList;

import dBConnection.DBConnect;

public abstract class AbsDBMethods {
	
	protected abstract String getAttributes();
	protected abstract String getValues();
	protected abstract String getWhere();
	
	public ArrayList<Object[]> getall(){
		DBConnect instance = DBConnect.getInstance();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String table = "";
		String ins = this.getClass().getSimpleName();
		if(ins.equals("members")) {
			table = "user";
		}
		if(ins.equals("Item")) {
			table = "item";
		}
		try {
			list = instance.select(table, "*");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void insertToDB() throws SQLException {
		DBConnect instance = DBConnect.getInstance();
		String table = "";
		//String attributes = getAttributes();
		String values = getValues();
		
		String ins = this.getClass().getSimpleName();
		System.out.println(ins);
		
		if(ins.equals("members")) {
			table = "user";
		}
		if(ins.equals("Item")) {
			table = "item";
		}
		
		instance.insert(table, values);
	}
	
	public void updateToDB() throws SQLException {
		DBConnect instance = DBConnect.getInstance();
		String table = "";
		String values = getValues();
		String where = "";
		
		String ins = this.getClass().getSimpleName();
		System.out.println(ins);
		
		if(ins.equals("members")) {
			table = "user";
		}
		if(ins.equals("Item")) {
			table = "item";
			where = getWhere();
		}
		
		instance.update(table, values, where);
	}
	
	public void delete(String email) throws SQLException {
		DBConnect instance = DBConnect.getInstance();
		String table = "";
		String where = "";
		String ins = this.getClass().getSimpleName();
		if(ins.equals("members")) {
			table = "user";
			where = " email = \"" + email + "\"";
		}
		if(ins.equals("Item")) {
			table = "item";
			where = getWhere();
		}
		
		instance.delete(table, where);
	}

}
