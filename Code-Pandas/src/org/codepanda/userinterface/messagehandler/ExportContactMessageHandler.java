package org.codepanda.userinterface.messagehandler;

import org.codepanda.application.CommandVisitor;

public class ExportContactMessageHandler implements MessageHandler{

	@Override
	public Object executeCommand(CommandVisitor commandVistor) {
		// TODO Auto-generated method stub
		commandVistor.getCommandActor().executeCommand();
		
		return null;
	}

}
