package org.codepanda.database;

import java.sql.SQLException;

import org.codepanda.utility.user.User;

public interface DatabaseManager {
	public void newUser(User user);
	public User getUser(String name);
	public void open(String db_name) throws SQLException;
	public void close() throws SQLException;
	public boolean checkExistUser(String userName);
}
