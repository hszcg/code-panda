package org.codepanda.userinterface;

import java.util.ArrayList;

import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.ContactSectionType;
import org.codepanda.utility.data.DataPool;

public class AllContactPanel extends SearchResultPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3974845703930761485L;

	public AllContactPanel(PhoneMeFrame mainFrame,
			ArrayList<Integer> myISNList, ContactSectionType phone_number) {
		// TODO Auto-generated constructor stub
		super(mainFrame, myISNList, phone_number);
	}
	
	/**
	 * @param updateISN
	 * 支持更新、删除和新加
	 * 
	 */
	public void updateAllResult(int updateISN) {
		boolean isNewISN = true;
		ContactOperations c = DataPool.getInstance().getAllContactISNMap().get(
				updateISN);

		for (int i = 0; i < this.myISNList.size(); i++) {
			if (myISNList.get(i) == updateISN) {
				isNewISN = false;
				if (c == null) {
					myISNList.remove(i);
					resultPanel.remove(i);
				} else {
					((SingleResultPanel) resultPanel.getComponent(i))
							.updateDisplay(c, secondType);
				}
			}
		}

		if (isNewISN && c != null) {
			myISNList.add(updateISN);
			resultPanel.add(new SingleResultPanel(this.parentFrame, c,
					secondType));
		}
		
		searchResultLabel.setText(myISNList.size() + " Items Found");
	}

	/**
	 * @param updateISNList
	 * 支持更新、删除和新加
	 * 
	 */
	public void updateAllResult(ArrayList<Integer> updateISNList) {
		for (int updateISN : updateISNList) {
			updateAllResult(updateISN);
		}
	}

}
