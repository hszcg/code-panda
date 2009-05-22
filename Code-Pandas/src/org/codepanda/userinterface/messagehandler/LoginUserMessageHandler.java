package org.codepanda.userinterface.messagehandler;

import org.codepanda.application.CommandVisitor;
import org.codepanda.application.user.LoginUserActor;
import org.codepanda.userinterface.utility.LoginResultType;

/**
 * @author hszcg
 *
 */
public class LoginUserMessageHandler implements MessageHandler {
	@Override
	
	public Object executeCommand(CommandVisitor commandVistor) {
		int result = commandVistor.getCommandActor().executeCommand();
		
		if(result == LoginUserActor.USERNAME_NOT_EXIST)
			return LoginResultType.USERNAME_NOT_EXIST;
		else if(result == LoginUserActor.INVAILD_PASSWORD)
			return LoginResultType.INVAILD_PASSWORD;
			
		return LoginResultType.SUCCEED;
	}

}
