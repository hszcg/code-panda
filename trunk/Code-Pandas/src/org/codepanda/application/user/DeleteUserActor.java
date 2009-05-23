package org.codepanda.application.user;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.data.DataPool;

/**
 * @author xdq
 *
 */
public class DeleteUserActor implements CommandActor {
	public static final int NULL_USER = -1;
	public static final int INVAILD_PASSWORD = -2;
	public static final int SUCCEED = 0;
	
	public Object executeCommand() {
		// TODO Auto-generated method stub
		String userName=DataPool.getInstance().getCurrentUser().getUserName();
		int result=DataPool.getInstance().deleteUser(userName);
		if(result==DeleteUserActor.INVAILD_PASSWORD)
		{
			System.out.println("Wrong  User Password ");
			return DeleteUserActor.INVAILD_PASSWORD;
		}
		return DeleteUserActor.SUCCEED;
	}
	
}
