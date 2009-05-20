package org.codepanda.userinterface.messagehandler;

import org.codepanda.application.CommandVisitor;
import org.codepanda.userinterface.utility.LoginResultType;

public class LoginUserMessageHandler implements MessageHandler {
	@Override
	public Object executeCommand(CommandVisitor commandVistor) {
		commandVistor.getCommandActor().executeCommand();
		
		return LoginResultType.SUCCEED;
	}

}
