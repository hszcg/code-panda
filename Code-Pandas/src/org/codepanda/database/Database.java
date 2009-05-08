package org.codepanda.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.codepanda.utility.user.User;
import org.hsqldb.*;
import org.hsqldb.jdbc.jdbcDataSource;

public class Database implements DatabaseManager{
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
	//use for SQL commands CREATE TABLE, DROP, INSERT and UPDATE
    public synchronized void update(String expression) throws SQLException {

        Statement st = null;

        st = conn.createStatement();    // statements

        int i = st.executeUpdate(expression);    // run the query

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
	
	@Override
	public void getUser(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newUser(User user) {
		// TODO Auto-generated method stub
		try {
			db.updateS("INSERT INTO UserTable(string,class) VALUES(?,?)", user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		try {
			db.shutdown();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void open(String db_name) {
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
	

}
