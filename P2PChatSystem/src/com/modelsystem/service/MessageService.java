package com.modelsystem.service;

import java.net.InetAddress;

import com.modelsystem.control.Configuration;
import com.modelsystem.control.PackageType;
import com.modelsystem.datatransport.DataTransportCenter;
import com.modelsystem.panel.PanelControler;

/**
 * 信息处理服务类
 * @author LiuYeFeng<897908343@qq.com>
 * @date 2015-1-10 下午2:11:57
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class MessageService {

	private String localIP;
	private DataTransportCenter dataTransportCenter;
	
	public MessageService() {
		try {
			localIP = InetAddress.getLocalHost().getHostAddress().toString();
			dataTransportCenter = new DataTransportCenter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送聊天信息
	 */
	public void sendMessage() {
		StringBuilder msg = new StringBuilder();
		StringBuilder sendMsg = new StringBuilder();
		String chatMessage = PanelControler.getEditText();
		String targetIP = PanelControler.getTargetIP();
		
		sendMsg.append(PackageType.MESSAGE);
		sendMsg.append("@");
		sendMsg.append(localIP);
		sendMsg.append("@");
		sendMsg.append(chatMessage);
		sendMsg.append("\n");
		
		try {
			dataTransportCenter.sendData(sendMsg.toString(), targetIP, Configuration.PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		msg.append("你对 ");
		msg.append(targetIP);
		msg.append(" 说\n");
		msg.append(chatMessage + "\n\n");
		
		PanelControler.clearEditText();
		PanelControler.addToChattingText(msg.toString());
	}
	
	/**
	 * 接受从端口监听程序传过来的数据，并处理数据，在面板上显示。
	 * @param message
	 */
	public void messageReceiver(String[] message) {
		StringBuilder msg = new StringBuilder();
		
		msg.append(message[1]);
		msg.append(" 对你说：\n");
		msg.append(message[2]);
		msg.append("\n");
		
		PanelControler.addToChattingText(msg.toString());
	}
}
