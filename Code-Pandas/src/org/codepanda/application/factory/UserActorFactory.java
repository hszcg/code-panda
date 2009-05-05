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
		
		// ����Ϊ����
		if( commandType == CommandType.NEW_USER ){
			NewUserActor newUserActor = new NewUserActor();
			//��commandDetail�н������û���Ϣ
			User currentUser = new User();
			
			newUserActor.setNewUser(currentUser);
		}
		return null;
	}

}
