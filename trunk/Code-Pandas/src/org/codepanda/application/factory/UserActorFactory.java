package org.codepanda.application.factory;

import org.codepanda.application.CommandActor;
import org.codepanda.application.CommandType;
import org.codepanda.application.user.NewUserActor;
import org.codepanda.utility.user.User;

/**
 * @author hszcg
 *
 */
public class UserActorFactory extends CommandActorFactory {

	@Override
	public CommandActor creator(CommandType commandType, String commandDetail) {
		// TODO Auto-generated method stub
		
		// 仅作为样例
		if( commandType == CommandType.NEW_USER ){
			NewUserActor newUserActor = new NewUserActor();
			//从commandDetail中解析出用户信息
			User currentUser = new User();
			
			newUserActor.setNewUser(currentUser);
		}
		return null;
	}

}
