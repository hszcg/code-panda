package org.codepanda.userinterface.messagehandler;

import org.codepanda.application.CommandVisitor;

public class SearchContactMessageHandler implements MessageHandler {

	@Override
	public Object executeCommand(CommandVisitor commandVistor) {
		// TODO Auto-generated method stub		
		return commandVistor.getCommandActor().executeCommand();
	}

}
