package org.codepanda.application.user;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.user.User;

public class NewUserActor implements CommandActor {
	
	public static final int NULL_USER = -1;
	public static final int USER_EXIST = -2;
	public static final int SUCCEED = 0;
	public int executeCommand() {
		// TODO Auto-generated method stub
		if( this.newUser == null ) {
			System.out.println("this.newUser == null");
			return NewUserActor.NULL_USER;
		}
		//User currentUser = new User();
		
		// Ҫ��Utility����createNewUser(User userToBeAdded)��Utility�����ݶ���DataPool����
		System.out.println(this.newUser.getUserName());
		int result=DataPool.getInstance().createNewUser(this.newUser);
		// ���ڵײ�û��ʵ�֣���������Ϊֹ
		
		// ʣ�µ��������Utility�����ݿ���Ϸ���checkExistUser()���͸������ݿ� createNewUserData()	
		// ���Utility����DataPool�����ؽ������
		if(result==-2)
		{
			System.out.println("USer Already Exist!!!");
			return NewUserActor.USER_EXIST;
		}
		return NewUserActor.SUCCEED;
	}

	private User newUser = null;

	/**
	 * @param newUser the newUser to set
	 */
	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}


	/**
	 * @return the newUser
	 */
	public User getNewUser() {
		return newUser;
	}

}
