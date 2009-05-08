package org.codepanda.database;

import org.codepanda.utility.user.User;

public interface DatabaseManager {
	public void newUser(User user);
	public void getUser(String name);
	public void open(String db_name);
	public void close();
}
