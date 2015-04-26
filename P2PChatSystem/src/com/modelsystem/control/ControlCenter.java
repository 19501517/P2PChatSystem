package com.modelsystem.control;

import java.util.LinkedList;
import java.util.List;

import com.modelsystem.listener.port.PortListener;
import com.modelsystem.panel.ChatPanel;
import com.modelsystem.service.OnlineAndOffLineService;

/**
 * 控制中心，主函数所在类，包括启动聊天面板，启动监听程序和发送上线信息。
 * @author LiuYeFeng<897908343@qq.com>
 * @date 2015-1-10 下午2:03:30
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class ControlCenter {
	
	public static ChatPanel chatPanel = new ChatPanel();
	public static List<String> onlineIP = new LinkedList<String>();

	public static void main(String[] args) {
		chatPanel.setResizable(false);
		chatPanel.setLocationRelativeTo(null);
		chatPanel.setVisible(true);
		
		//启动监听程序
		new Thread(new PortListener()).start();
		//发送上线信息
		new OnlineAndOffLineService().online();
		
	}
}
