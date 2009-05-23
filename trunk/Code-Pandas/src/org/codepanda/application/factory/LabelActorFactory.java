package org.codepanda.application.factory;

import org.codepanda.application.CommandActor;
import org.codepanda.application.CommandType;
import org.codepanda.application.label.DeleteCommonLabelActor;
import org.codepanda.application.label.EditCommonLabelActor;
import org.codepanda.application.label.NewCommonLabelActor;
import org.codepanda.application.xml.CommonLabelXML;
import org.codepanda.application.xml.DeleteCommonLabelXML;
import org.codepanda.utility.label.CommonLabel;

public class LabelActorFactory extends CommandActorFactory {

	@Override
	public CommandActor creator(CommandType commandType, String commandDetail) {
		// TODO Auto-generated method stub
		if(commandType==CommandType.DELETE_COMMON_LABEL)
		{
			DeleteCommonLabelActor deleteCommonLabelActor=new DeleteCommonLabelActor();
			DeleteCommonLabelXML deleteCommonLabelXML=new DeleteCommonLabelXML();
			String result=deleteCommonLabelXML.labelParserXML("<DeleteCommonLabel>", "</DeleteCommonLabel>", commandDetail);
			deleteCommonLabelActor.setInfo(result);
			return deleteCommonLabelActor;
		}
		return null;
	}

}
