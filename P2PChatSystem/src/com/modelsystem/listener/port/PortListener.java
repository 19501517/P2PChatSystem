package com.modelsystem.listener.port;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import com.modelsystem.control.Configuration;
import com.modelsystem.service.MessageService;
import com.modelsystem.service.OnlineAndOffLineService;

/**
 * 端口监听程序，程序的核心所在。
 * @author LiuYeFeng<897908343@qq.com>
 * @date 2015-1-10 下午2:07:12
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class PortListener implements Runnable{
	
	private DatagramPacket datagramPacket;
	private MulticastSocket broadcastSocket;
	private String localIP;
	
	public PortListener() {
		datagramPacket = new DatagramPacket(new byte[1024], 1024);
		try {
			broadcastSocket = new MulticastSocket(Configuration.PORT);
			broadcastSocket.joinGroup(InetAddress.getByName(Configuration.BROADCAST_IP));
			localIP = InetAddress.getLocalHost().getHostAddress().toString();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		String[] message;
		
		while (true) {
			try {
				broadcastSocket.receive(datagramPacket);
				message = new String(datagramPacket.getData(), 0, datagramPacket.getLength()).split("@"); 
				
				System.out.println(message[0]);
				
				if (message[1].equals(localIP))
					continue; // 忽略自身消息
				
				if (message[0].equals("MESSAGE")) { // 聊天信息
					System.out.println("message!");
					new MessageService().messageReceiver(message);
					
				} else if (message[0].equals("ONLINE")) {// 主机上线通知
					new OnlineAndOffLineService().onlineReceiver(message);
					
				} else if (message[0].equals("ONLINE_REPLY")) { // 主机上线回复
					new OnlineAndOffLineService().onlineReply(message);
					
				} else if (message[0].equals("OFFLINE")) { // 主机离线
					new OnlineAndOffLineService().offlineReceiver(message);
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("监听程序出错！ " + e);
			}
		}
	}

}
