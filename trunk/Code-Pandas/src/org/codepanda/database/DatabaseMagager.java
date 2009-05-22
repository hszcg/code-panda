package org.codepanda.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.codepanda.utility.contact.ContactData;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.contact.PersonalContact;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.user.User;
import org.hsqldb.*;
import org.hsqldb.jdbc.jdbcDataSource;

public class DatabaseMagager implements DatabaseManagerFacade {
	// private static final User NULL = null;
	Connection conn;
//	DatabaseMagager db;

	public DatabaseMagager(String dbName) throws Exception {
		// connect to the database. This will load the db files and start the
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

	public DatabaseMagager() {}

	







	@Override
	public int newUser(final User user) {
		try {
			this.updateS(
					"INSERT INTO UserTable(username,user) VALUES(?,?)",user);
		} catch (SQLException e) {
			return 0;
		}
		return 1;
	}

	@Override
	public int close() throws SQLException {
		try {
			this.shutdown();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	// use for SHUTDOWN
	public void shutdown() throws SQLException {

		Statement st = conn.createStatement();

		// db writes out to files and performs clean shuts down
		// otherwise there will be an unclean shutdown
		// when program ends
		st.execute("SHUTDOWN");
		conn.close(); // if there are no other open connection
	}

	@Override
	public int open(String dbName) throws SQLException {
	/*	// new database
		try {
//			db = new DatabaseMagager(dbName);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		// create table
//		try {
			// create user-table
	//		this.update("CREATE TABLE UserTable (username VARCHAR(256), user OTHER)");
			this.update("CREATE TABLE UserTable (username VARCHAR(256), user OTHER)");
			this.update("CREATE TABLE contactList (username VARCHAR(256), contact OTHER)");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return 0;
	}
	// use for SQL commands CREATE TABLE, DROP, INSERT and UPDATE
	public synchronized void update(String expression) throws SQLException {

		Statement st = null;

		st = conn.createStatement(); // statements
		int i = 0; // run the query
		try {
			i = st.executeUpdate(expression);
		} catch (Exception e) {
		}

		if (i == -1) {
			System.out.println("db error : " + expression);
		}

		st.close();
	} // void update()

	@Override
	public int checkExistUser(String userName) {
		// TODO Auto-generated method stub
		Statement st=null;
		ResultSet rs=null;
		try {
			st=conn.createStatement();
			rs = st.executeQuery("SELECT * FROM UserTable WHERE username = '" + userName+"'");
			rs.next();
			if(rs.isFirst()==false)
				return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	//		return 0;
			e.printStackTrace();
		}
		return 1;
	}

	

	@Override
	public int newContact(String userName, final PersonalContact contact) {
		this.updateC("INSERT INTO contactList(username,contact) VALUES(?,?)",
				userName, contact);
		return 1;
	}

	private void updateC(String expression, String username, PersonalContact contact) {
		try {
			PreparedStatement ps = conn.prepareStatement(expression);
			ps.setString(1, username);
			ps.setObject(2, contact);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// use for SQL commands INSERT, especially, for object INSERT
	public synchronized void updateS(String expression, User user)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(expression);
		// ps.setObject(1, "hello world");
		// ps.setString(1,"hello world");
		// ps.setInt(1,100);
		ps.setString(1, user.getUserName());
		ps.setObject(2, user);
		ps.executeUpdate();
		ps.close();
	}
	
	public synchronized void updateD(String expression, User user) throws SQLException{
		PreparedStatement ps = conn.prepareStatement(expression);
		
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public int getContactData(String userName,
			ArrayList<ContactOperations> contactList) {
		// TODO 你这里修改了contactList指向的内存地址区域，应该用contactList.add(XXX)
		try {
			queryContactData(userName,contactList);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	// use for a query that return a list of Contact
	public synchronized void queryContactData(String name, ArrayList<ContactOperations> contactList)
			throws SQLException {
		Statement st = null;
		ResultSet rs = null;
//		ArrayList<ContactOperations> acd = null;	
		st = conn.createStatement();
		rs = st.executeQuery("SELECT * FROM contactList WHERE username = '" + name+"'");
//		rs.next();
		for (; rs.next();) {
			contactList.add((ContactOperations) rs.getObject(2));
//			acd.add((ContactOperations) rs.getObject(2));
		}
		st.close();
	}
	
	
	
	

	@Override
	public int getUser(String userName, User user) {
		// TODO 同样，不能修改user指向的内存地址区域
		try {
			queryUserObject(userName,user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	// use for a query that return a User object
	public synchronized void queryUserObject(String name, User user) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
	//	User temp_user = null;
		st = conn.createStatement();
//		rs = st.executeQuery("SELECT * FROM UserTable WHERE username = "+ name);
//		rs = st.executeQuery("SELECT * FROM UserTable WHERE username > 0    ");
		rs = st.executeQuery("SELECT * FROM UserTable WHERE username = '"+name+"'");
//		rs = st.executeQuery("SELECT * FROM UserTable WHERE UserTable.COL1=leilei");
//		rs = st.executeQuery("SELECT username = "+name+" FROM UserTable");
//		rs = st.executeQuery("SELECT * FROM UserTable WHERE username=username");
		rs.next();
		User tempuser = (User) rs.getObject(2); // 2 or 3
		user.setPassword(tempuser.getPassword());
		user.setUserName(tempuser.getUserName());
		user.setUserContactData(tempuser.getUserContactData());
		st.close();
	//	return temp_user;
	}

	@Override
	public int loginUser(String userName, String password) {
		
		if(this.checkExistUser(userName)==0)
			return 0;
		
		User user = new User();
//		try {
//			Statement st = null;
//			ResultSet rs = null;
//			st = conn.createStatement();
//			System.out.println(conn.toString());
//			rs = st.executeQuery("SELECT * FROM UserTable WHERE username = '"
//					+ userName + "'");
//			System.out.println(rs.toString());
//			rs.next();
//			user = (User) rs.getObject(2); // 2 or 3
//			st.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}

		// It's a stupid implement......
		// I could also ......
		getUser(userName,user);
		if(user.getPassword().equals(password))
			return 1;
		return 0;
	}

	
	@Override
	public int delUser(final User user) {
		// the process of checking process is outside the DataBase
//		User tempuser=new User();
		try {
//			this.getUser(user.getUserName(), tempuser);
			this.updateD(
					"DELETE FROM UserTable WHERE username = '"+user.getUserName()+"'", user);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteContact(final User user, final  PersonalContact contact) {
		int isn = contact.getISN();
		try {
			if(queryContact(user.getUserName(), isn)==1){
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public synchronized int queryContact(String name, int isn) throws SQLException{
		Statement st = null;
		ResultSet rs = null;
		st = conn.createStatement();
		rs = st.executeQuery("SELECT * FROM contactList WHERE username = '"+name+"'");
		for (; rs.next();){
			if(((PersonalContact)rs.getObject(2)).getISN() == isn){
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int editUser(String userName, final User user) {
		this.delUser(user);
		this.newUser(user);
		return 1;
	}

	@Override
	public int editContact(final User user, final PersonalContact contact){
		// TODO Auto-generated method stub
		this.deleteContact(user, contact);
		this.newContact(user.getUserName(), contact);
		return 0;
	}
}
