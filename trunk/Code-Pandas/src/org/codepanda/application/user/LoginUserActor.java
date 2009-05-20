package org.codepanda.application.user;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.user.User;

public class LoginUserActor implements CommandActor {
	public static final int NULL_USER = -1;
	public static final int USERNAME_NOT_EXIST = -2;
	public static final int INVAILD_PASSWORD = -3;
	public static final int SUCCEED = 0;
	
	private User user = null;

	@Override
	public int executeCommand() {
		// TODO Auto-generated method stub
		if (this.user == null) {
			System.out.println("User Null!!!");
			return LoginUserActor.NULL_USER;
		}
		
		DataPool.getInstance();
		
		return LoginUserActor.SUCCEED;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the newUser
	 */
	public User getNewUser() {
		return user;
	}
}
