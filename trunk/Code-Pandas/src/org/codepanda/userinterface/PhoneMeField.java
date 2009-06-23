package org.codepanda.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.codepanda.userinterface.utility.BalloonBorder;

public class PhoneMeField extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7905237871318907598L;
	public static final int ADD_STATE = 0;
	public static final int EDIT_STATE = 1;
	
	public static final int phone_field = 10;
	public static final int email_field = 11;
	public static final int other_field = 12;
	
	StringBuffer phoneFormat = new StringBuffer("0123456789+-#()p");
	
	int state = ADD_STATE;

	private static final Color TIP_COLOR = new Color(255, 255, 225);
	private int limit = Integer.MAX_VALUE;
	private int filedFormat = other_field;
	private CoolToolTip numberTip;
	private CoolToolTip limitTip;
	private ImageIcon tipIcon;

	public PhoneMeField() {
		super();
		initComponents();
		initEventListeners();
	}

	public PhoneMeField(int size) {
		super(size);
		initComponents();
		initEventListeners();
	}

	public PhoneMeField(int size, int filedFormat) {
		super(size);
		this.filedFormat = filedFormat;
		initComponents();
		initEventListeners();
	}

	public void setState(int newstate) {
		state = newstate;
		if (state == ADD_STATE)
			setToolTipText("在此输入添加项，回车进行确认");
		if (state == EDIT_STATE)
			setToolTipText("在此输入进行编辑，回车进行确认");
	}

	public int getState() {
		return state;
	}

	private void initComponents() {
		// tipIcon = new ImageIcon(MyJTextField.class.getResource("tip.gif"));
		// System.out.println(super.getLocation().getX());
		// System.out.println(super.getLocation().getY());a
		// System.out.println("aaa");
		numberTip = new CoolToolTip(this, TIP_COLOR, getColumns(), 10);
		numberTip.setText("只能输入数字！");
		// numberTip.setIcon(tipIcon);
		numberTip.setIconTextGap(10);

		limitTip = new CoolToolTip(this, TIP_COLOR, getColumns(), 10);
		// limitTip.setIcon(tipIcon);
		limitTip.setIconTextGap(10);
	}

	private void initEventListeners() {
		addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				numberTip.determineAndSetLocation();
				if (getText().length() + 1 > limit) {
					deleteInputChar(e);
					limitTip.setVisible(true);
					return;
				} else {
					limitTip.setVisible(false);
				}
				
				char input = e.getKeyChar();
				boolean ignoreInput = input == (char) KeyEvent.VK_ESCAPE
                || input == (char) KeyEvent.VK_BACK_SPACE
                || input == (char) KeyEvent.VK_ENTER
                || input == (char) KeyEvent.VK_DELETE;
				if (ignoreInput) {
					limitTip.setVisible(false);
					numberTip.setVisible(false);
					return;
				}
				if (filedFormat == phone_field) {
					numberTip.setText("电话号码格式错误");
					if (phoneFormat.indexOf(String.valueOf(input)) == -1) {
						numberTip.setVisible(true);
						deleteInputChar(e);
					} else {
						numberTip.setVisible(false);
					}
				}
				
				if (filedFormat == email_field) {
					numberTip.setText("电子邮箱格式错误");
					if (!Character.isLetterOrDigit(input) &&
							! (input == '@') &&
							! (input == '.')) {
						numberTip.setVisible(true);
						deleteInputChar(e);
					} else {
						numberTip.setVisible(false);
					}
				}
			}

			private void deleteInputChar(KeyEvent source) {
				source.setKeyChar((char) KeyEvent.VK_CLEAR);
			}
		});
		
		addFocusListener(new FocusListener()
		{
		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			limitTip.setVisible(false);
			numberTip.setVisible(false);
		}

		});
	}

	private class CoolToolTip extends JPanel {
		private JLabel label = new JLabel();
		private boolean haveShowPlace;

		private Component attachedComponent;

		public CoolToolTip(Component attachedComponent, Color fillColor,
				int borderWidth, int offset) {
			this.attachedComponent = attachedComponent;

			label.setBorder(new EmptyBorder(borderWidth, borderWidth,
					borderWidth, borderWidth));
			label.setBackground(fillColor);
			label.setOpaque(true);
			label.setFont(new Font("system", 0, 12));

			setOpaque(false);
			this.setBorder(new BalloonBorder(fillColor, offset));
			this.setLayout(new BorderLayout());
			add(label);

			setVisible(false);

			// if the attached component is moved while the balloon tip is
			// visible, we need to move as well
			attachedComponent.addComponentListener(new ComponentAdapter() {
				public void componentMoved(ComponentEvent e) {
					if (isShowing()) {
						determineAndSetLocation();
					}
				}
			});

		}

		private void determineAndSetLocation() {
			
			Point location = attachedComponent.getLocationOnScreen();
			
			int x = location.x;
			int y = location.y;
			
			System.out.println(x);
			System.out.println(y);
			setBounds(x, y - getPreferredSize().height,
					getPreferredSize().width, getPreferredSize().height);
		}

		public void setText(String text) {
			label.setText(text);
		}

		public void setIcon(Icon icon) {
			label.setIcon(icon);
		}

		public void setIconTextGap(int iconTextGap) {
			label.setIconTextGap(iconTextGap);
		}

		public void setVisible(boolean show) {
			if (show) {
				determineAndSetLocation();
				findShowPlace();
			}
			super.setVisible(show);
		}

		private void findShowPlace() {
			if (haveShowPlace) {
				return;
			}
			// we use the popup layer of the top level container (frame or
			// dialog) to show the balloon tip
			// first we need to determine the top level container
			Container parent = attachedComponent.getParent();
			JLayeredPane layeredPane;
			while (true) {
				if (parent instanceof JFrame) {
					layeredPane = ((JFrame) parent).getLayeredPane();
					break;
				} else if (parent instanceof JDialog) {
					layeredPane = ((JDialog) parent).getLayeredPane();
					break;
				}
				parent = parent.getParent();
			}
			layeredPane.add(this, JLayeredPane.POPUP_LAYER);
			haveShowPlace = true;
		}
	}
}
