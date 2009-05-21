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
		if (this.user == null) {
			System.out.println("User Null!!!");
			return LoginUserActor.NULL_USER;
		}
		
		int result = DataPool.getInstance().loginUser(this.user.getUserName(), this.user.getPassword());
		
		if(result == -1)
			return LoginUserActor.USERNAME_NOT_EXIST;
		else if(result == -2)
			return LoginUserActor.INVAILD_PASSWORD;
		
		return LoginUserActor.SUCCEED;
	}

	public void setUser(User user) {
		this.user = user;
	//	System.out.println("LoginUserActor...UserName------"+user.getUserName());
	}

	/**
	 * @return the newUser
	 */
	public User getNewUser() {
		return user;
	}
}
