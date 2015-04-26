package com.modelsystem.panel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.modelsystem.listener.panel.OfflineListener;
import com.modelsystem.listener.panel.SendMessageListener;

/**
 * 聊天面板。
 * @author LiuYeFeng<897908343@qq.com>
 * @date 2015-1-10 下午2:08:23
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class ChatPanel extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2613494144670085375L;
	
	private JPanel onlinePanel;
	private JLabel onlinePerson;
	private JScrollPane jsForOnlinePersonInfo;
	private JTextArea onlinePersonInfo;
	private JLabel onlineAndOffline;
	private JScrollPane jsForOnlineAndOfflineInfo;
	private JTextArea onlineAndOfflineInfo;
	private JButton offline;
	
	private JPanel chatPanel;
	private JScrollPane jsForChattingMessage;
	private JTextArea chattingMessage;
	private JLabel sendMessageTo;
	private JTextField targetIP;
	private JScrollPane jsForEditMessage;
	private JTextArea editMessage;
	private JButton send;
	
	public ChatPanel() {
		initGUI();
		initListener();
	}
	
	private void initGUI() {
		try {
			setTitle("P2P即时通信系统");
			getContentPane().setLayout(null);
			setSize(1200, 600);
			
			onlinePanel = new JPanel();
			onlinePanel.setBounds(30, 10, 450, 550);
			onlinePanel.setLayout(null);
			onlinePanel.setBorder(BorderFactory.createTitledBorder("在线情况"));
			
			onlinePerson = new JLabel("在线人员");
			onlinePerson.setBounds(20, 20, 100, 20);
			onlinePanel.add(onlinePerson);
			
			onlinePersonInfo = new JTextArea();
			jsForOnlinePersonInfo = new JScrollPane(onlinePersonInfo);
			jsForOnlinePersonInfo.setBounds(20, 40, 420, 200);
			jsForOnlinePersonInfo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			onlinePersonInfo.setEditable(false);
			onlinePanel.add(jsForOnlinePersonInfo);
			
			onlineAndOffline = new JLabel("上下线消息");
			onlineAndOffline.setBounds(20, 260, 100, 20);
			onlinePanel.add(onlineAndOffline);
			
			onlineAndOfflineInfo = new JTextArea();
			jsForOnlineAndOfflineInfo = new JScrollPane(onlineAndOfflineInfo);
			jsForOnlineAndOfflineInfo.setBounds(20, 280, 420, 220);
			jsForOnlineAndOfflineInfo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			onlineAndOfflineInfo.setEditable(false);
			onlinePanel.add(jsForOnlineAndOfflineInfo);

			offline = new JButton("下线");
			offline.setBounds(350, 510, 70, 30);
			onlinePanel.add(offline);
			
			chatPanel = new JPanel();
			chatPanel.setBounds(510, 10, 660, 550);
			chatPanel.setLayout(null);
			chatPanel.setBorder(BorderFactory.createTitledBorder("聊天信息"));
			
			chattingMessage = new JTextArea();
			jsForChattingMessage = new JScrollPane(chattingMessage);
			jsForChattingMessage.setBounds(20, 20, 630, 320);
			jsForChattingMessage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			chattingMessage.setEditable(false);
			chatPanel.add(jsForChattingMessage);
			
			sendMessageTo = new JLabel("发送消息到：");
			sendMessageTo.setBounds(25, 365, 80, 20);
			chatPanel.add(sendMessageTo);
			
			targetIP = new JTextField();
			targetIP.setBounds(100, 360, 150, 30);
			chatPanel.add(targetIP);
			
			editMessage = new JTextArea();
			jsForEditMessage = new JScrollPane(editMessage);
			jsForEditMessage.setBounds(20, 400, 630, 100);
			chatPanel.add(jsForEditMessage);
			
			send = new JButton("发送");
			send.setBounds(560, 510, 70, 30);
			chatPanel.add(send);
			
			getContentPane().add(onlinePanel);
			getContentPane().add(chatPanel);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void initListener() {
		this.send.addActionListener(new SendMessageListener());
		this.offline.addActionListener(new OfflineListener());
	}
	
	public JTextArea getOnlinePersonInfo() {
		return onlinePersonInfo;
	}

	public JTextArea getOnlineAndOfflineInfo() {
		return onlineAndOfflineInfo;
	}

	public JTextArea getChattingMessage() {
		return chattingMessage;
	}

	public JTextArea getEditMessage() {
		return editMessage;
	}
	
	public JTextField getTargetIP() {
		return targetIP;
	}
}
