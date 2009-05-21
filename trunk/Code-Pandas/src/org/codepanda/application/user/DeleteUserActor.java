package org.codepanda.application.user;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.user.User;

public class DeleteUserActor implements CommandActor {

	private User user;
	private User temp=new User();
	public int executeCommand() {
		// TODO Auto-generated method stub
		if(this.user==null)
		{
			System.out.println("this user ====null");
		}
		user.deleteUser();
		return 0;
	}
	

	/**
	 * @return the newUser
	 */
	public User getNewUser() {
		return user;
	}
	public void setUser(User currentUser) {
		// TODO Auto-generated method stub
		this.user=currentUser;
	}
	
}
