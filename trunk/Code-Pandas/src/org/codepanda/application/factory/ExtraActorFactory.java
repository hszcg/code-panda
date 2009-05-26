package org.codepanda.application.factory;

import org.codepanda.application.CommandActor;
import org.codepanda.application.CommandType;
import org.codepanda.application.extra.MergeContactActor;
import org.codepanda.application.xml.MergeContactXML;

public class ExtraActorFactory extends CommandActorFactory {

	@Override
	public CommandActor creator(CommandType commandType, String commandDetail) {
		// TODO Auto-generated method stub
		if(commandType==CommandType.MERGE_CONTACT)
		{
			MergeContactActor mergeContactActor=new MergeContactActor();
			MergeContactXML mergeContactXML=new MergeContactXML();
			String result=mergeContactXML.MergeContact("MergeContact", "/MergeContact", commandDetail);
			mergeContactActor.setInfo(result);
			return mergeContactActor;
		}
		return null;
	}

}
