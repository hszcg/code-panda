package org.codepanda.application.user;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.user.User;

/**
 * @author xdq
 *
 */
public class DeleteUserActor implements CommandActor {
	public static final int NULL_USER = -1;
	public static final int INVAILD_PASSWORD = -2;
	public static final int SUCCEED = 0;
	private User user = null;
	public int executeCommand() {
		// TODO Auto-generated method stub
		if(getUser()==null)
		{
			System.out.println("User Null!!!");
			return DeleteUserActor.NULL_USER;
		}
		int result=DataPool.getInstance().deleteUser(getUser());
		if(result==DeleteUserActor.INVAILD_PASSWORD)
		{
			System.out.println("Wrong  User Password ");
			return DeleteUserActor.INVAILD_PASSWORD;
		}
		return DeleteUserActor.SUCCEED;
	}
	
	public User getUser()
	{
		return this.user;
	}
	public void setUser(User user)
	{
		this.user=user;
	}
	
}
