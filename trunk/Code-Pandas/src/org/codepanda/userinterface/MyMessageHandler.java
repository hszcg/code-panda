package org.codepanda.userinterface;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;

public class MyMessageHandler implements MessageHandler {
	public void test(){
		CommandType commandType=CommandType.NEW_USER;
		String commandDetail=
			"<com>"+
			"<NewUser>"+"</NewUser>"+
			"<UserName>"+"leilei"+"</UserName>"+
			"<Telephone>"+"13699252256"+"</Telephone>"+
			"<Telephone>"+"51531174"+"</Telephone>"+
			"</com>";
		
		CommandVisitor commandVistor = new CommandVisitor(commandType, commandDetail);
	}

	@Override
	public void executeCommand(CommandVisitor commandVistor) {
		// TODO Auto-generated method stub
		commandVistor.getCommandActor().executeCommand();
		// 更新一下GUI
	}

}
