package org.codepanda.application.user;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.user.User;

public class EditUserActor implements CommandActor {
	public static final int NULL_USER = -1;
	public static final int FAILED= -2;
	public static final int SUCCEED = 0;
	private User user;
	@Override
	public int executeCommand() {
		// TODO Auto-generated method stub
		if(this.user==null)
		{
			System.out.println("this.user==null");
			return EditUserActor.NULL_USER;
		}
		int result=DataPool.getInstance().editUser(getUser().getUserName(), getUser());
		if(result==-2)
		{
			return EditUserActor.FAILED;
		}
		return EditUserActor.SUCCEED;
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
