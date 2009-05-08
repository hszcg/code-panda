package org.codepanda.database;

import java.sql.Connection;

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
	
	@Override
	public void getUser(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newUser(User user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void open(String db_name) {
		// TODO Auto-generated method stub
		try {
			db=new Database(db_name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
