package org.codepanda.application.factory;

import org.codepanda.application.CommandActor;
import org.codepanda.application.CommandType;
import org.codepanda.application.user.DeleteUserActor;
import org.codepanda.application.user.EditUserActor;
import org.codepanda.application.user.LoginUserActor;
import org.codepanda.application.user.NewUserActor;
import org.codepanda.application.xml.UserXML;
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
		if (commandType == CommandType.NEW_USER) {
			NewUserActor newUserActor = new NewUserActor();
			currentUser = new User();
			UserXML myParserXML=new UserXML();
			myParserXML.userParserXML(currentUser,"<NewUser>","</NewUser>", commandDetail);
			newUserActor.setNewUser(currentUser);
			return newUserActor;
		}
		if (commandType == CommandType.LOGIN_USER) {
			System.out.println("Login");
			LoginUserActor loginUserActor=new LoginUserActor();
			currentUser=new User();
			UserXML myParserXML=new UserXML();
			//System.out.println("LLLLLLLL");
			myParserXML.userParserXML(currentUser,"<LoginUser>","</LoginUser>", commandDetail);
			loginUserActor.setUser(currentUser);
			System.out.println("UserName------"+currentUser.getUserName());
			return loginUserActor;
		}
		if (commandType == CommandType.DELETE_USER) {
			System.out.println("Delete User");
			DeleteUserActor deleteUserActor = new DeleteUserActor();
			currentUser=new User();
			UserXML myParserXML=new UserXML();
			myParserXML.userParserXML(currentUser,"<DeleteUser>","</DeleteUser>", commandDetail);
			 deleteUserActor.setUser(currentUser);
			return deleteUserActor;
		}
		if (commandType == CommandType.EDIT_USER) {
			System.out.println("Edit User");
			EditUserActor editUserActor=new EditUserActor();
			currentUser=new User();
			UserXML myParserXML=new UserXML();
			myParserXML.userParserXML(currentUser,"<EditUser>","</EditUser>", commandDetail);
			editUserActor.setUser(currentUser);
			return editUserActor;
		}
		return null;
	}

	public void setUser(User user) {
		currentUser = user;
		
	}

	public User getUser() {
		return currentUser;
	}
	
	//用于测试使用
	/*public static void main(String argv[])
	{
		CommandType commandType=CommandType.LOGIN_USER;
		 String commandDetail=
		 "<com>"+
		 "<LoginUser>"+
		 "<UserName>"+"leilei"+"</UserName>"+
		 "<UserPassword>"+"hehe"+"</UserPassword>"+
		 "</LoginUser>"+
		 "</com>";
		UserActorFactory uaf=new UserActorFactory();
		uaf.creator(commandType,commandDetail);
	}*/

}
