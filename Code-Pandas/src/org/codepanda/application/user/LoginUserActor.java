package org.codepanda.application.user;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.user.User;

public class LoginUserActor implements CommandActor {
		private User  user=null;
		
	@Override
	public int executeCommand() {
		// TODO Auto-generated method stub
		if(this.user==null)
		{
			System.out.println("User Null!!!");
		}
		return 0;
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
