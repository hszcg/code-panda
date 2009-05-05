package org.codepanda.application.user;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.user.User;

public class NewUserActor implements CommandActor {
	@Override
	public int executeCommand() {
		// TODO Auto-generated method stub
		if( this.newUser == null ) {
			System.out.println("this.newUser == null");
		}

		// 要求Utility增加createNewUser(User userToBeAdded)，Utility的数据都在DataPool里面
		
		// 由于底层没有实现，样例到此为止
		
		// 剩下的事情就是Utility向数据库检查合法性checkExistUser()，和更新数据库 createNewUserData()	
		// 最后Utility更新DataPool并返回结果即可
			
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
