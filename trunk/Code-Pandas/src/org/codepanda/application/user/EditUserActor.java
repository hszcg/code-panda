package org.codepanda.application.user;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.user.User;

public class EditUserActor implements CommandActor {
	private User user;
	@Override
	public int executeCommand() {
		// TODO Auto-generated method stub
		if(this.user==null)
		{
			System.out.println("this.user==null");
		}
		return 0;
	}
	public void setUser(User user)
	{
		this.user=user;
	}
	public User getUser()
	{
		return user;
	}
}
