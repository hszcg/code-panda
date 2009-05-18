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
		basicTask = new RibbonTask("基本功能", userManagerBand,
				contactManagerBand, commonLabelManagerBand);
		basicTask.setKeyTip("B");
		
		JRibbonBand contactExchangeBand = this.getContactExchangeBand();
		JRibbonBand contactSyncBand = this.getContactSyncBand();
		JRibbonBand otherFunctionBand = this.getOtherFunctionBand();
		advancedTask = new RibbonTask("高级功能", contactExchangeBand,
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
		JRibbonBand userManagerBand = new JRibbonBand("用户管理", new edit_paste(),
				new ExpandActionListener());

		JCommandButton newUserButton;
		try {
			newUserButton = new JCommandButton("新建用户",
					ImageWrapperResizableIcon.getIcon(ImageIO.read(this.getClass().getResource(
					"/userpic/user0.jpg")), new Dimension(32, 32)));
			
			newUserButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("新建用户");
				}
			});

			userManagerBand.addCommandButton(newUserButton,
					RibbonElementPriority.MEDIUM);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		

		JCommandButton deleteUserButton = new JCommandButton("删除用户",
				new edit_paste());

		deleteUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("删除用户");
			}
		});

		userManagerBand.addCommandButton(deleteUserButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton editUserButton = new JCommandButton("修改用户信息",
				new edit_paste());

		editUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("修改用户信息");
			}
		});

		userManagerBand.addCommandButton(editUserButton,
				RibbonElementPriority.MEDIUM);

		// userManagerBand.setResizePolicies(CoreRibbonResizePolicies
		// .getCorePoliciesRestrictive(userManagerBand));

		return userManagerBand;
	}
	
	private JRibbonBand getContactManagerBand() {
		JRibbonBand contactManagerBand = new JRibbonBand("联系人管理",
				new edit_paste(), new ExpandActionListener());

		JCommandButton newContactButton = new JCommandButton("新建联系人",
				new edit_paste());

		newContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("新建联系人");
			}
		});

		contactManagerBand.addCommandButton(newContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton deleteContactButton = new JCommandButton("删除联系人",
				new edit_paste());

		deleteContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("删除联系人");
			}
		});

		contactManagerBand.addCommandButton(deleteContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton editContactButton = new JCommandButton("修改联系人信息",
				new edit_paste());

		editContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("修改联系人");
			}
		});

		contactManagerBand.addCommandButton(editContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton statContactButton = new JCommandButton("联系人统计",
				new edit_paste());

		statContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("联系人统计");
			}
		});

		contactManagerBand.addCommandButton(statContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton searchContactButton = new JCommandButton("联系人搜索",
				new edit_paste());

		searchContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("联系人搜索");
			}
		});

		contactManagerBand.addCommandButton(searchContactButton,
				RibbonElementPriority.MEDIUM);

		// contactManagerBand.setResizePolicies(CoreRibbonResizePolicies
		// .getCorePoliciesRestrictive(contactManagerBand));

		return contactManagerBand;
	}

	private JRibbonBand getCommonLabelManagerBand() {
		JRibbonBand commonLabelManagerBand = new JRibbonBand("普通标签管理",
				new edit_paste(), new ExpandActionListener());

		JCommandButton newCommonLabelButton = new JCommandButton("新建普通标签",
				new edit_paste());

		newCommonLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("新建普通标签");
			}
		});

		commonLabelManagerBand.addCommandButton(newCommonLabelButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton deleteCommonLabelButton = new JCommandButton("删除普通标签",
				new edit_paste());

		deleteCommonLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("删除普通标签");
			}
		});

		commonLabelManagerBand.addCommandButton(deleteCommonLabelButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton editCommonLabelButton = new JCommandButton("修改普通标签",
				new edit_paste());

		editCommonLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("修改普通标签");
			}
		});

		commonLabelManagerBand.addCommandButton(editCommonLabelButton,
				RibbonElementPriority.MEDIUM);

		return commonLabelManagerBand;
	}

	JRibbonBand getContactExchangeBand() {
		JRibbonBand contactExchangeBand = new JRibbonBand("数据导入导出",
				new edit_paste(), new ExpandActionListener());

		JCommandButton importContactButton = new JCommandButton("联系人导入",
				new edit_paste());

		importContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("联系人导入");
			}
		});

		contactExchangeBand.addCommandButton(importContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton exportContactButton = new JCommandButton("联系人导出",
				new edit_paste());

		exportContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("联系人导出");
			}
		});

		contactExchangeBand.addCommandButton(exportContactButton,
				RibbonElementPriority.MEDIUM);

		return contactExchangeBand;
	}

	JRibbonBand getContactSyncBand() {
		JRibbonBand contactSyncBand = new JRibbonBand("联系人同步",
				new edit_paste(), new ExpandActionListener());

		JCommandButton googleContactButton = new JCommandButton(
				"与Google Contact同步", new edit_paste());

		googleContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("与Google Contact同步");
			}
		});

		contactSyncBand.addCommandButton(googleContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton bluetoothContactButton = new JCommandButton("与手机蓝牙同步",
				new edit_paste());

		bluetoothContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("与手机蓝牙同步");
			}
		});

		contactSyncBand.addCommandButton(bluetoothContactButton,
				RibbonElementPriority.MEDIUM);

		return contactSyncBand;
	}

	JRibbonBand getOtherFunctionBand() {
		JRibbonBand otherFunctionBand = new JRibbonBand("其它高级功能",
				new edit_paste(), new ExpandActionListener());

		JCommandButton remindBirthdayButton = new JCommandButton("生日提醒",
				new edit_paste());

		remindBirthdayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("生日提醒");
			}
		});

		otherFunctionBand.addCommandButton(remindBirthdayButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton arrangeContactButton = new JCommandButton("联系人整理",
				new edit_paste());

		arrangeContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("联系人整理");
			}
		});

		otherFunctionBand.addCommandButton(arrangeContactButton,
				RibbonElementPriority.MEDIUM);

		JCommandButton relationNetButton = new JCommandButton("人立方",
				new edit_paste());

		relationNetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("人立方");
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
