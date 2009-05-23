package org.codepanda.application.contact;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.data.PhoneMeConstants;

public class ExportContactActor implements CommandActor {

	@Override
	public Object executeCommand() {
		// TODO Auto-generated method stub
		ContactOperations[] list = (ContactOperations[]) DataPool.getInstance()
				.getAllContactISNMap().values().toArray();
		String[] p = (String[]) PhoneMeConstants.getInstance()
				.getContactSectionList().values().toArray();

		return 0;
	}

}
