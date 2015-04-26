package com.modelsystem.service;

import java.net.InetAddress;
import java.net.MulticastSocket;

import com.modelsystem.control.Configuration;
import com.modelsystem.control.ControlCenter;
import com.modelsystem.control.PackageType;
import com.modelsystem.datatransport.DataTransportCenter;
import com.modelsystem.panel.PanelControler;

/**
 * 上下线信息服务类
 * @author LiuYeFeng<897908343@qq.com>
 * @date 2015-1-10 下午2:13:42
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class OnlineAndOffLineService {
	
	private String localIP;
	private MulticastSocket multicastSocket;
	private DataTransportCenter dataTransportCenter;
	
	public OnlineAndOffLineService() {
		try {
			localIP = InetAddress.getLocalHost().getHostAddress().toString();
			multicastSocket = new MulticastSocket(Configuration.PORT);
			dataTransportCenter = new DataTransportCenter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送上线信息
	 */
	public void online() {
		StringBuilder sendMsg = new StringBuilder();
		sendMsg.append(PackageType.ONLINE);
		sendMsg.append("@");
		sendMsg.append(localIP);
		
		try {
			multicastSocket.joinGroup(InetAddress.getByName(Configuration.BROADCAST_IP));
			dataTransportCenter.sendData(sendMsg.toString(), Configuration.BROADCAST_IP, Configuration.PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 接受到本机上线回复，添加对方ip到在线列表。
	 * @param message
	 */
	public void onlineReply(String[] message){
		StringBuilder msg = new StringBuilder();
		ControlCenter.onlineIP.add(message[1]);
		
		msg.append("搜索到在线主机：");
		msg.append(message[1]);
		msg.append("\n");
		
		PanelControler.setOnlinePersonInfo(ControlCenter.onlineIP);
		PanelControler.addToOnlineAndOfflineInfo(msg.toString());
	}
	
	/**
	 * 收到其他主机上线信息并回复
	 * @param message
	 */
	public void onlineReceiver(String[] message) {
		StringBuilder msg = new StringBuilder();
		StringBuilder replyMsg = new StringBuilder();
		
		replyMsg.append(PackageType.ONLINE_REPLY);
		replyMsg.append("@");
		replyMsg.append(localIP);
		replyMsg.append("@");
		replyMsg.append(message[1]);
		
		try {
			dataTransportCenter.sendData(replyMsg.toString(), message[1], Configuration.PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		msg.append("主机上线：");
		msg.append(message[1]);
		msg.append("\n");
		
		ControlCenter.onlineIP.add(message[1]);
		
		PanelControler.setOnlinePersonInfo(ControlCenter.onlineIP);
		PanelControler.addToOnlineAndOfflineInfo(msg.toString());
	}
	
	/**
	 * 收到其他主机下线消息。
	 * @param message
	 */
	public void offlineReceiver(String[] message) {
		StringBuilder msg = new StringBuilder();
		
		msg.append("主机下线：");
		msg.append(message[1]);
		msg.append("\n");
		
		ControlCenter.onlineIP.remove(message[1]);
		
		PanelControler.setOnlinePersonInfo(ControlCenter.onlineIP);
		PanelControler.addToOnlineAndOfflineInfo(msg.toString());
	}
	
	/**
	 * 本机下线
	 */
	public void offline() {
		StringBuilder sendMsg = new StringBuilder();
		
		sendMsg.append(PackageType.OFFLINE);
		sendMsg.append("@");
		sendMsg.append(localIP);
		
		try {
			dataTransportCenter.sendData(sendMsg.toString(), Configuration.BROADCAST_IP, Configuration.PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.exit(0);
	}
}
