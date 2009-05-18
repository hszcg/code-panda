package org.codepanda.userinterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.codepanda.userinterface.utility.edit_paste;
import org.jvnet.flamingo.common.JCommandButton;
import org.jvnet.flamingo.common.icon.ImageWrapperResizableIcon;
import org.jvnet.flamingo.ribbon.*;

public class PhoneMeRibbon {
	private PhoneMeFrame mainFrame;
	private RibbonTask basicTask;
	private RibbonTask advancedTask;
	
	public PhoneMeRibbon( PhoneMeFrame mainFrame ){
		this.mainFrame = mainFrame;
		
		JRibbonBand userManagerBand = this.getUserManagerBand();
		JRibbonBand contactManagerBand = this.getContactManagerBand();
		JRibbonBand commonLabelManagerBand = this.getCommonLabelManagerBand();
		basicTask = new RibbonTask("��������", userManagerBand,
				contactManagerBand, commonLabelManagerBand);
		basicTask.setKeyTip("B");
		
		JRibbonBand contactExchangeBand = this.getContactExchangeBand();
		JRibbonBand contactSyncBand = this.getContactSyncBand();
		JRibbonBand otherFunctionBand = this.getOtherFunctionBand();
		advancedTask = new RibbonTask("�߼�����", contactExchangeBand,
				contactSyncBand, otherFunctionBand);
		advancedTask.setKeyTip("A");
	}
	
	private class ExpandActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(mainFrame,
					"Expand button clicked");
		}
	}
	
	private JRibbonBand getUserManagerBand() {
		JRibbonBand userManagerBand = new JRibbonBand("�û�����", new edit_paste(),
				new ExpandActionListener());

		JCommandButton newUserButton;
		try {
			newUserButton = new JCommandButton("�½��û�",
					ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass().getResource(
					"/userpic/user0.jpg")), new Dimension(32, 32)));
			
			newUserButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("�½��û�");
				}
			});

			userManagerBand.addCommandButton(newUserButton,
					RibbonElementPriority.MEDIUM);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		

		JCommandButton deleteUserButton = new JCommandButton("ɾ���û�",
				new edit_paste());

		deleteUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ɾ���û�");
			}
		});

		userManagerBand.addCommandButton(deleteUserButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton editUserButton = new JCommandButton("�޸��û���Ϣ",
				new edit_paste());

		editUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�޸��û���Ϣ");
			}
		});

		userManagerBand.addCommandButton(editUserButton,
				RibbonElementPriority.MEDIUM);

		// userManagerBand.setResizePolicies(CoreRibbonResizePolicies
		// .getCorePoliciesRestrictive(userManagerBand));

		return userManagerBand;
	}
	
	private JRibbonBand getContactManagerBand() {
		JRibbonBand contactManagerBand = new JRibbonBand("��ϵ�˹���",
				new edit_paste(), new ExpandActionListener());

		JCommandButton newContactButton = new JCommandButton("�½���ϵ��",
				new edit_paste());

		newContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�½���ϵ��");
			}
		});

		contactManagerBand.addCommandButton(newContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton deleteContactButton = new JCommandButton("ɾ����ϵ��",
				new edit_paste());

		deleteContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ɾ����ϵ��");
			}
		});

		contactManagerBand.addCommandButton(deleteContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton editContactButton = new JCommandButton("�޸���ϵ����Ϣ",
				new edit_paste());

		editContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�޸���ϵ��");
			}
		});

		contactManagerBand.addCommandButton(editContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton statContactButton = new JCommandButton("��ϵ��ͳ��",
				new edit_paste());

		statContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ϵ��ͳ��");
			}
		});

		contactManagerBand.addCommandButton(statContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton searchContactButton = new JCommandButton("��ϵ������",
				new edit_paste());

		searchContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ϵ������");
			}
		});

		contactManagerBand.addCommandButton(searchContactButton,
				RibbonElementPriority.MEDIUM);

		// contactManagerBand.setResizePolicies(CoreRibbonResizePolicies
		// .getCorePoliciesRestrictive(contactManagerBand));

		return contactManagerBand;
	}

	private JRibbonBand getCommonLabelManagerBand() {
		JRibbonBand commonLabelManagerBand = new JRibbonBand("��ͨ��ǩ����",
				new edit_paste(), new ExpandActionListener());

		JCommandButton newCommonLabelButton = new JCommandButton("�½���ͨ��ǩ",
				new edit_paste());

		newCommonLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�½���ͨ��ǩ");
			}
		});

		commonLabelManagerBand.addCommandButton(newCommonLabelButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton deleteCommonLabelButton = new JCommandButton("ɾ����ͨ��ǩ",
				new edit_paste());

		deleteCommonLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ɾ����ͨ��ǩ");
			}
		});

		commonLabelManagerBand.addCommandButton(deleteCommonLabelButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton editCommonLabelButton = new JCommandButton("�޸���ͨ��ǩ",
				new edit_paste());

		editCommonLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�޸���ͨ��ǩ");
			}
		});

		commonLabelManagerBand.addCommandButton(editCommonLabelButton,
				RibbonElementPriority.MEDIUM);

		return commonLabelManagerBand;
	}

	JRibbonBand getContactExchangeBand() {
		JRibbonBand contactExchangeBand = new JRibbonBand("���ݵ��뵼��",
				new edit_paste(), new ExpandActionListener());

		JCommandButton importContactButton = new JCommandButton("��ϵ�˵���",
				new edit_paste());

		importContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ϵ�˵���");
			}
		});

		contactExchangeBand.addCommandButton(importContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton exportContactButton = new JCommandButton("��ϵ�˵���",
				new edit_paste());

		exportContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ϵ�˵���");
			}
		});

		contactExchangeBand.addCommandButton(exportContactButton,
				RibbonElementPriority.MEDIUM);

		return contactExchangeBand;
	}

	JRibbonBand getContactSyncBand() {
		JRibbonBand contactSyncBand = new JRibbonBand("��ϵ��ͬ��",
				new edit_paste(), new ExpandActionListener());

		JCommandButton googleContactButton = new JCommandButton(
				"��Google Contactͬ��", new edit_paste());

		googleContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��Google Contactͬ��");
			}
		});

		contactSyncBand.addCommandButton(googleContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton bluetoothContactButton = new JCommandButton("���ֻ�����ͬ��",
				new edit_paste());

		bluetoothContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("���ֻ�����ͬ��");
			}
		});

		contactSyncBand.addCommandButton(bluetoothContactButton,
				RibbonElementPriority.MEDIUM);

		return contactSyncBand;
	}

	JRibbonBand getOtherFunctionBand() {
		JRibbonBand otherFunctionBand = new JRibbonBand("�����߼�����",
				new edit_paste(), new ExpandActionListener());

		JCommandButton remindBirthdayButton = new JCommandButton("��������",
				new edit_paste());

		remindBirthdayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��������");
			}
		});

		otherFunctionBand.addCommandButton(remindBirthdayButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton arrangeContactButton = new JCommandButton("��ϵ������",
				new edit_paste());

		arrangeContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ϵ������");
			}
		});

		otherFunctionBand.addCommandButton(arrangeContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton relationNetButton = new JCommandButton("������",
				new edit_paste());

		relationNetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("������");
			}
		});

		otherFunctionBand.addCommandButton(relationNetButton,
				RibbonElementPriority.MEDIUM);

		return otherFunctionBand;
	}
	
	public RibbonTask getBasicTask(){
		return basicTask;
	}
	
	public RibbonTask getAdvancedTask(){
		return advancedTask;
	}
	
	public void setMainFrame(PhoneMeFrame mainFrame){
		this.mainFrame = mainFrame;
	}
	
	public PhoneMeFrame getMainFrame(){
		return mainFrame;
	}
}
