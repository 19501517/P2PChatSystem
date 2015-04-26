package com.modelsystem.datatransport;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 数据交互中心，负责数据的交互处理。
 * @author LiuYeFeng<897908343@qq.com>
 * @date 2015-1-10 下午2:05:04
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class DataTransportCenter {
	
	private DatagramSocket socket;
	
	public DataTransportCenter() {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送数据
	 * @param data 数据
	 * @param targetIP 目标ip
	 * @param port 目标端口
	 * @return
	 * @throws Exception
	 */
	public boolean sendData(String data, String targetIP, int port) throws Exception {
		DatagramPacket dp = null;
		InetAddress address = InetAddress.getByName(targetIP);
		
		byte[] msg = new byte[1024];
		msg = data.getBytes();

		dp = new DatagramPacket(msg, msg.length, address, port);
		
		socket.send(dp);
		
		return true;
	}
}
