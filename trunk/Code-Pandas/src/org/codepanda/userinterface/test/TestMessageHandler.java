package org.codepanda.userinterface.test;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.userinterface.messagehandler.MessageHandler;

/**
 * 仅供测试
 * 
 * @author hszcg
 *
 */
public class TestMessageHandler implements MessageHandler {
	public void testFunc(String xml){
		CommandVisitor c = new CommandVisitor(CommandType.LOGIN_USER, xml);
		this.executeCommand(c);		
	}

	@Override
	public Object executeCommand(CommandVisitor commandVistor) {
		// TODO 返回结果
		return null;
	}

}
