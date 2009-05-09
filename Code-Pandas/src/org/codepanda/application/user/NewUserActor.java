package org.codepanda.application.user;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.user.User;

public class NewUserActor implements CommandActor {
	@Override
	public int executeCommand() {
		// TODO Auto-generated method stub
		if( this.newUser == null ) {
			System.out.println("this.newUser == null");
		}
		//User currentUser = new User();
		
		// Ҫ��Utility����createNewUser(User userToBeAdded)��Utility�����ݶ���DataPool����
		DataPool.getInstance().createNewUser(this.newUser);
		// ���ڵײ�û��ʵ�֣���������Ϊֹ
		
		// ʣ�µ��������Utility�����ݿ���Ϸ���checkExistUser()���͸������ݿ� createNewUserData()	
		// ���Utility����DataPool�����ؽ������
			
		return 0;
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
