package org.codepanda.application.factory;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import org.codepanda.utility.contact.*;
import org.codepanda.utility.data.DataPool;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.codepanda.application.CommandActor;
import org.codepanda.application.CommandType;
import org.codepanda.application.user.DeleteUserActor;
import org.codepanda.application.user.EditUserActor;
import org.codepanda.application.user.LoginUserActor;
import org.codepanda.application.user.NewUserActor;
import org.codepanda.utility.user.User;

/**
 * @author hszcg
 *
 */
public class UserActorFactory extends CommandActorFactory {
	 private User currentUser;
	@Override
	public CommandActor creator(CommandType commandType, String commandDetail) {
		// TODO Auto-generated method stub
		commandType=CommandType.LOGIN_USER;
		commandDetail=
			"<com>"+
			"<LoginUser>"+
			"<UserName>"+"leilei"+"</UserName>"+
			"<UserPassword>"+"hehe"+"</UserPassword>"+
			"<Telephone>"+"13699252256"+"</Telephone>"+
			"<Telephone>"+"51531174"+"</Telephone>"+
			"<Office>"+"Tsinghua"+"</Office>"+
			"<Url>"+"www.leilei.com"+"</Url>"+
			"</LoginUser>"+
			"</com>";
		if( commandType == CommandType.NEW_USER ){
			NewUserActor newUserActor = new NewUserActor();			
			currentUser = new User();
			//Document document=db.parse(commandDetail);
			
			User_XML myparser_xml=new User_XML();
			myparser_xml.UserXML(currentUser,"<NewUser>","</NewUser>", commandDetail);
			newUserActor.setNewUser(currentUser);
			return newUserActor;
		}
		if(commandType==CommandType.LOGIN_USER)
		{
			System.out.println("Login");
			LoginUserActor loginUserActor=new LoginUserActor();
			currentUser=new User();
			User_XML myparser_xml=new User_XML();
			myparser_xml.UserXML(currentUser,"<LoginUser>","</LoginUser>", commandDetail);
			loginUserActor.setUser(currentUser);
			return loginUserActor;
		}
		if(commandType==CommandType.DELETE_USER)
		{
			System.out.println("Delete User");
			DeleteUserActor deleteUserActor=new DeleteUserActor();
			return deleteUserActor;
		}
		if(commandType==CommandType.EDIT_USER)
		{
			System.out.println("Edit User");
			EditUserActor editUserActor=new EditUserActor();
			User tempUser=this.getUser();
			User_XML myparser_xml=new User_XML();
			myparser_xml.UserXML(tempUser, "<EditUser>", "</EditUser>", commandDetail);
			editUserActor.setUser(tempUser);
			return  editUserActor;
		}
		return null;
	}
	public void setUser(User user)
	{
		currentUser=user;
	}
	public User getUser()
	{
		return currentUser;
	}
	public static void main(String argv[])
	{
		UserActorFactory uaf=new UserActorFactory();
		uaf.creator(CommandType.EDIT_USER,"fsjdl");
	}
}
		
