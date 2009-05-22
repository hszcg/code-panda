package org.codepanda.userinterface.messagehandler;

import org.codepanda.application.CommandVisitor;

public class NewContactMessageHandler implements MessageHandler {

	@Override
	public Object executeCommand(CommandVisitor commandVistor) {
		// TODO Auto-generated method stub
		return commandVistor.getCommandActor().executeCommand();
	}

}
