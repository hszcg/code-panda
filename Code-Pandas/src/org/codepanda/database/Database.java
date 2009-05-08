package org.codepanda.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codepanda.utility.user.User;
import org.hsqldb.*;
import org.hsqldb.jdbc.jdbcDataSource;

public class Database implements DatabaseManager{
//	private static final User NULL = null;
	Connection conn;
	Database db;

	public Database(String db_name) throws Exception{
		// connect to the database.   This will load the db files and start the
        // database if it is not already running.
        // db_name is used to open or create files that hold the state
        // of the db.
        // It can contain directory names relative to the
        // current working directory
        jdbcDataSource dataSource = new jdbcDataSource();
        dataSource.setDatabase("jdbc:hsqldb:" + db_name);
        
        conn = dataSource.getConnection("sa", "");
        
        System.out.println("Database Connected! Con!");
	}
	public Database() {
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
			// TODO: handle exception
		}

        if (i == -1) {
            System.out.println("db error : " + expression);
        }

        st.close();
    }    // void update()
    
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
    public synchronized User queryObject(String name) throws SQLException{
    	Statement st=null;
    	ResultSet rs=null;
    	User temp_user=null;
    	st=conn.createStatement();
    	rs=st.executeQuery("SELECT * FROM UserTable WHERE username = "+name);
//   	dumps(rs);
    	temp_user=(User) rs.getObject(3);
    	st.close();
		return temp_user;
    	
    }
	@Override
	public User getUser(String name) {
		// TODO Auto-generated method stub
		try {
			return queryObject(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void newUser(User user) {
		// TODO Auto-generated method stub
		try {
			db.updateS("INSERT INTO UserTable(username,user) VALUES(?,?)", user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void close() throws SQLException{
		// TODO Auto-generated method stub
		try {
			db.shutdown();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void open(String db_name) throws SQLException{
		// TODO Auto-generated method stub
		// new database
		try {
			db=new Database(db_name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// create table
		try {
			// create user-table
			db.update(
					"CREATE TABLE UserTable (id INTEGER IDENTITY, username VARCHAR(256), user OTHER)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public boolean checkExistUser(String userName) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
