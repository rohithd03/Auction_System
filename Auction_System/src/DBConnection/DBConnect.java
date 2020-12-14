package dBConnection;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {
	
	private static DBConnect instance;
	private String url = "jdbc:mysql://localhost:3306/oas";
	private String user = "root";
	private String password = "Rohith@03";
	private Connection con = null;
	private String query = null;
	private ArrayList<Object[]> list = null;
	
	private DBConnect() {
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DBConnect getInstance() {
		if(instance == null) {
			instance = new DBConnect();
		}
		return instance;
	}
	
	public void insert (String table, String values) throws SQLException {
		Statement st = con.createStatement();
		query = "INSERT INTO " + table + " VALUES( " + values + " )";
		st.executeUpdate(query);
	}
	
	public void delete (String table, String where) throws SQLException {
		Statement st = con.createStatement();
		query = "DELETE FROM " + table + " WHERE " + where;
		st.executeUpdate(query);
	}
	
	public void update (String table, String colvalue, String where) throws SQLException {
		Statement st = con.createStatement();
		query = "UPDATE " + table + " SET " + colvalue + " WHERE " + where;
		System.out.println(query);
		st.executeUpdate(query);
	}
	
	public ArrayList<Object[]> select (String table, String value) throws SQLException {
		Statement st = con.createStatement();
		query = "SELECT " + value + " FROM " + table;
		
		ResultSet rs = st.executeQuery(query);
		list = new ArrayList<>();
		while(rs.next()) {
			//int id = rs.getInt("userid");
			//String name = rs.getString("fname");
			int columns = rs.getMetaData().getColumnCount();
            Object[] array = new Object[columns];
            for(int i=0; i < columns; i++){
                array[i] = rs.getObject(i+1);
            }
            list.add(array);
			
			//System.out.print(id);
			//System.out.println(name);
		}
		
		return list;
	}

}
