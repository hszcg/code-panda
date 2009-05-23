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
import org.codepanda.utility.user.User;

/**
 * ½ö¹©²âÊÔ
 * 
 * @author hszcg
 *
 */
public class TestMessageHandler implements MessageHandler {
	private User currentUser;
	public void testFunc(CommandType commandType,String xml){
	/*	CommandVisitor c = new CommandVisitor(CommandType.NEW_USER, xml);*/
		CommandVisitor c = new CommandVisitor(commandType, xml);
		this.executeCommand(c);		
	}
	@Override
	public Object executeCommand(CommandVisitor commandVistor) {
		int result = (Integer) commandVistor.getCommandActor().executeCommand();
		
		if(result == DeleteUserActor.NULL_USER)
			return DeleteUserResultType.NULL_USER;
		else if(result == DeleteUserActor.INVAILD_PASSWORD)
			return DeleteUserResultType.INVAILD_PASSWORD;
			
		return DeleteUserResultType.SUCCEED;
	}
	public static void main(String argv[])
	{
		String LoginUserxml="<com>"+
		"<LoginUser>"+
		"<UserName>"+"xdq"+"</UserName>"+
		"<UserPassword>"+"fighting"+"</UserPassword>"+
		"</LoginUser>"+
		"</com>";
		
		String DeleteUserxml="<com>"+
		"<DeleteUser>"+
		"<UserName>"+"xdq"+"</UserName>"+
		"<UserPassword>"+"fighting"+"</UserPassword>"+
		"</DeleteUser>"+
		"</com>";
		String NewContactxml1="<com>"+
		"<NewContact>"+
		"<ContactName>"+"zhang xin yu"+"</ContactName>"+
		"<Telephone>"+"122489389"+"</Telephone>"+
		"<Email>"+"jdksd@dfjk"+"</Email>"+
		"<Email>"+"fsdf@fdjk"+"</Email>"+
		"<Email>"+"fdk@fdjk"+"</Email>"+
		"<Birthday>"+"1996-02-01"+"</Birthday>"+
		"<RelationLabel>"+
		"<LabelName>"+"friends"+"</LabelName>"+
		"<DestISN>"+"1234"+"</DestISN>"+
		"</RelationLabel>"+
		"</NewContact>"+
		"</com>";
		String NewContactxml2="<com>"+
		"<NewContact>"+
		"<ContactName>"+"zhangchenguang"+"</ContactName>"+
		"<Telephone>"+"122489389"+"</Telephone>"+
		"<Email>"+"jdksd@dfjk"+"</Email>"+
		"<Email>"+"fsdf@fdjk"+"</Email>"+
		"<Email>"+"fdk@fdjk"+"</Email>"+
		"<Birthday>"+"1997-03-01"+"</Birthday>"+
		"<RelationLabel>"+
		"<LabelName>"+"friends"+"</LabelName>"+
		"<DestISN>"+"1234"+"</DestISN>"+
		"</RelationLabel>"+
		"</NewContact>"+
		"</com>";
		String NewContactxml3="<com>"+
		"<NewContact>"+
		"<ContactName>"+"xudanqing"+"</ContactName>"+
		"<Telephone>"+"122489389"+"</Telephone>"+
		"<Email>"+"jdksd@dfjk"+"</Email>"+
		"<Email>"+"fsdf@fdjk"+"</Email>"+
		"<Email>"+"fdk@fdjk"+"</Email>"+
		"<Birthday>"+"1998-11-10"+"</Birthday>"+
		"<RelationLabel>"+
		"<LabelName>"+"friends"+"</LabelName>"+
		"<DestISN>"+"1234"+"</DestISN>"+
		"</RelationLabel>"+
		"</NewContact>"+
		"</com>";
		String NewUserxml="<com>"+
		"<NewUser>"+
		"<UserName>"+"xdq"+"</UserName>"+
		"<UserPassword>"+"fighting"+"</UserPassword>"+
		"<Telephone>"+"13699252256"+"</Telephone>"+
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
		String StatContactxml="<com>"+
		"<StatContact>"+
		"<Birthday>"+"1995-05-11"+"</Birthday>"+
		"<Birthday>"+"1999-12-20"+"</Birthday>"+
		"</StatContact>"+
		"</com>";
		TestMessageHandler tmh=new TestMessageHandler();
		tmh.testFunc(CommandType.NEW_USER,NewUserxml);
		tmh.testFunc(CommandType.LOGIN_USER, LoginUserxml);
		//System.out.println("LLLLLLLLLLLLLL"+DataPool.getInstance().getCurrentUser().getUserName());
		tmh.testFunc(CommandType.NEW_CONTACT, NewContactxml1);
		tmh.testFunc(CommandType.NEW_CONTACT, NewContactxml3);
		tmh.testFunc(CommandType.NEW_CONTACT, NewContactxml2);
		tmh.testFunc(CommandType.STAT_CONTACT, StatContactxml);
		//tmh.testFunc(CommandType.DELETE_USER, DeleteUserxml);
		try {
			DataPool.getInstance().getDb().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
