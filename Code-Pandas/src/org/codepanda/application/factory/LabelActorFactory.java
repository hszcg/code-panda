package org.codepanda.application.factory;

import org.codepanda.application.CommandActor;
import org.codepanda.application.CommandType;
import org.codepanda.application.label.EditCommonLabelActor;
import org.codepanda.application.label.NewCommonLabelActor;
import org.codepanda.application.xml.CommonLabelXML;
import org.codepanda.utility.label.CommonLabel;

public class LabelActorFactory extends CommandActorFactory {

	@Override
	public CommandActor creator(CommandType commandType, String commandDetail) {
		// TODO Auto-generated method stub
		if(commandType==CommandType.NEW_COMMON_LABEL)
		{
			NewCommonLabelActor newCommonLabelActor=new NewCommonLabelActor();
			CommonLabel currentCommonLabel=new CommonLabel();
			CommonLabelXML myCommandLabelXML=new  CommonLabelXML();
			myCommandLabelXML.labelParserXML(currentCommonLabel, "<NewCommonLabel>", "</NewCommonLabel>", commandDetail);
			newCommonLabelActor.setLabel(currentCommonLabel);
			return newCommonLabelActor;
			
		}
		if(commandType==CommandType.EDIT_COMMON_LABEL)
		{
			EditCommonLabelActor editCommonLabelActor=new EditCommonLabelActor();
			CommonLabel currentCommonLabel=new CommonLabel();
			CommonLabelXML myCommandLabelXML=new  CommonLabelXML();
			myCommandLabelXML.labelParserXML(currentCommonLabel, "<EditCommonLabel>", "</EditCommonLabel>", commandDetail);
			editCommonLabelActor.setLabel(currentCommonLabel);
			return editCommonLabelActor;
		}
		if(commandType==CommandType.DELETE_COMMON_LABEL)
		{
			
		}
		return null;
	}

}
