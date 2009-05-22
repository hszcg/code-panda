package org.codepanda.userinterface.test;

import java.sql.SQLException;

import org.codepanda.application.CommandType;
import org.codepanda.application.CommandVisitor;
import org.codepanda.application.user.DeleteUserActor;
import org.codepanda.application.user.LoginUserActor;
import org.codepanda.application.user.NewUserActor;
import org.codepanda.userinterface.messagehandler.MessageHandler;
import org.codepanda.userinterface.utility.DeleteUserResultType;
import org.codepanda.userinterface.utility.LoginResultType;
import org.codepanda.userinterface.utility.NewUserResultType;
import org.codepanda.utility.data.DataPool;

/**
 * ½ö¹©²âÊÔ
 * 
 * @author hszcg
 *
 */
public class TestMessageHandler implements MessageHandler {
	public void testFunc(String xml){
	/*	CommandVisitor c = new CommandVisitor(CommandType.NEW_USER, xml);*/
		CommandVisitor c = new CommandVisitor(CommandType.DELETE_USER, xml);
		this.executeCommand(c);		
	}
	@Override
	public Object executeCommand(CommandVisitor commandVistor) {
		int result = commandVistor.getCommandActor().executeCommand();
		
		if(result == DeleteUserActor.NULL_USER)
			return DeleteUserResultType.NULL_USER;
		else if(result == DeleteUserActor.INVAILD_PASSWORD)
			return DeleteUserResultType.INVAILD_PASSWORD;
			
		return DeleteUserResultType.SUCCEED;
	}
	public static void main(String argv[])
	{
		String DeleteUserxml="<com>"+
		"<DeleteUser>"+
		"<UserName>"+"zcg"+"</UserName>"+
		"<UserPassword>"+"fdjk"+"</UserPassword>"+
		"</DeleteUser>"+
		"</com>";
		String NewContactxml="<com>"+
		"<NewContact>"+
		"<ContactName>"+"zhang xin yu"+"</ContactName>"+
		"<Telepohone>"+"122489389"+"</Telephone>"+
		"<Email>"+"jdksd@dfjk"+"</Email>"+
		"</NewContact>"+
		"<com>";
		String NewUserxml="<com>"+
		"<NewUser>"+
		"<UserName>"+"xdq"+"</UserName>"+
		"<UserPassword>"+"fighting"+"</Userpassword>"+
		"<Telephone>"+"13699252256"+"<Telephone>"+
		"<Url>"+"www.leilei.org"+"</Url>"+
		"</NewUser>"+
		"</com>";
		
		String EditUserxml="<com>"+
		"<EditUser>"+
		"<UserName>"+"zcg"+"</UserName>"+
		"<Telephone>"+"478937"+"</Telephone>"+
		"<Email>"+"zcg@fjd"+"</Email>"+
		"</EditUser>"+
		"</com>";
		
		TestMessageHandler tmh=new TestMessageHandler();
		tmh.testFunc(DeleteUserxml);
		try {
			DataPool.getInstance().getDb().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
