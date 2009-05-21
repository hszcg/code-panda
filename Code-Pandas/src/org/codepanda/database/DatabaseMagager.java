package org.codepanda.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import org.codepanda.utility.contact.ContactData;
import org.codepanda.utility.user.User;
import org.hsqldb.*;
import org.hsqldb.jdbc.jdbcDataSource;

public class DatabaseMagager implements DatabaseManagerFacade{
//	private static final User NULL = null;
	Connection conn;
	DatabaseMagager db;

	public DatabaseMagager(String dbName) throws Exception{
		// connect to the database.   This will load the db files and start the
        // database if it is not already running.
        // db_name is used to open or create files that hold the state
        // of the db.
        // It can contain directory names relative to the
        // current working directory
        jdbcDataSource dataSource = new jdbcDataSource();
        dataSource.setDatabase("jdbc:hsqldb:" + dbName);
        
        conn = dataSource.getConnection("sa", "");
        
        System.out.println("Database Connected! Con!");
	}
	public DatabaseMagager() {
		// TODO Auto-generated constructor stub
	}
	
	//use for SQL commands CREATE TABLE, DROP, INSERT and UPDATE
    public synchronized void update(String expression) throws SQLException {

        Statement st = null;

        st = conn.createStatement();    // statements

        int i = 0;    // run the query
		try {
			i = st.executeUpdate(expression);
		} catch (Exception e) {
		}

        if (i == -1) {
            System.out.println("db error : " + expression);
        }

        st.close();
    }    // void update()
    
   
    
    // use for SHUTDOWN
    public void shutdown() throws SQLException {

        Statement st = conn.createStatement();

        // db writes out to files and performs clean shuts down
        // otherwise there will be an unclean shutdown
        // when program ends
        st.execute("SHUTDOWN");
        conn.close();    // if there are no other open connection
    }
	// use for a query that return a User object
    public synchronized User queryUserObject(String name) throws SQLException{
    	Statement st=null;
    	ResultSet rs=null;
    	User temp_user=null;
    	st=conn.createStatement();
    	rs=st.executeQuery("SELECT * FROM UserTable WHERE username = "+name);
    	temp_user=(User) rs.getObject(2);  // 2 or 3
    	st.close();
		return temp_user;
    }
    // use for a query that return a list of Contact
    public synchronized ArrayList<ContactData> queryContactData(String name) throws SQLException{
    	Statement st=null;
    	ResultSet rs=null;
    	ArrayList<ContactData> acd = null;
    	st=conn.createStatement();
    	rs=st.executeQuery("SELECT * FROM contactList WHERE username ="+name);
    	for(;rs.next();){
    		acd.add((ContactData) rs.getObject(3));
    	}
    	st.close();
		return acd;
    }
	public User getUser(String name) {
		try {
			return queryUserObject(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public int newUser(User user) {
		try {
			db.updateS("INSERT INTO UserTable(username,user) VALUES(?,?)", user);
		} catch (SQLException e) {
			return 0;
		}
		return 1;
		
	}
	@Override
	public int close() throws SQLException{
		try {
			db.shutdown();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public int open(String dbName) throws SQLException{
		// new database
		try {
			db=new DatabaseMagager(dbName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// create table
		try {
			// create user-table
			db.update(
					"CREATE TABLE UserTable (id INTEGER IDENTITY, username VARCHAR(256), user OTHER)");
			db.update(
					"CREATE TABLE contactList(username VARCHAR(256), contact OTHER)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	@Override
	public int checkExistUser(String userName) {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public int delUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int deleteContact(User user, ContactData contact) {
		return 0;
		// TODO Auto-generated method stub
		
	}
	@Override
	public int editUser(String userName, User user) {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public int editUser(User user, ContactData contact) {
		// TODO Auto-generated method stub
		return 0;
	}
	public ContactData getContactData(User user, ContactData contact) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int newContact(String userName, ContactData contact) {
		db.updateC("INSERT INTO contactList(username,contact) VALUES(?,?)",userName,contact);
		return 1;
	}
	private void updateC(String expression, String username, ContactData contact) {
		try {
			PreparedStatement ps=conn.prepareStatement(expression);
			ps.setString(1,username );
			ps.setObject(2, contact);
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	 // use for SQL commands INSERT, especially, for object INSERT 
    public synchronized void updateS(String expression,User user) throws SQLException{
    	PreparedStatement ps=conn.prepareStatement(expression);
//      ps.setObject(1, "hello world");
//      ps.setString(1,"hello world");
//      ps.setInt(1,100);
    	ps.setString(1, user.getUserName());
    	ps.setObject(2, user);
      
    	ps.executeUpdate();
    	ps.close();
    }
	@Override
	public int getContactData(String userName, ArrayList<ContactData> contactList) {
		try {
			contactList=queryContactData(userName);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int getUser(String userName, User user) {
		try {
			user=queryUserObject(userName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int loginUser(String userName, String password) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int newLabel(String labelname, String username) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
