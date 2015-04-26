package com.modelsystem.panel;

import java.util.List;

import com.modelsystem.control.ControlCenter;

/**
 * 聊天面板控制类，负责面板信息的更改。
 * @author LiuYeFeng<897908343@qq.com>
 * @date 2015-1-10 下午2:08:51
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class PanelControler {

	public static ChatPanel chatPanel = ControlCenter.chatPanel;
	
	/**
	 * 添加信息到聊天窗口。
	 * @param msg
	 */
	public static void addToChattingText(String msg) {
		chatPanel.getChattingMessage().append(msg);
	}
	
	/**
	 * 清空聊天输入框
	 */
	public static void clearEditText() {
		chatPanel.getEditMessage().setText("");
	}
	
	/**
	 * 得到聊天输入框的内容 
	 * @return
	 */
	public static String getEditText() {
		return chatPanel.getEditMessage().getText();
	}
	
	/**
	 * 得到目标ip框的内容
	 * @return
	 */
	public static String getTargetIP() {
		return chatPanel.getTargetIP().getText();
	}
	
	/**
	 * 设置在线人员信息
	 * @param persons
	 */
	public static void setOnlinePersonInfo(List<String> persons) {
		chatPanel.getOnlinePersonInfo().setText(listToString(persons));
	}
	
	/**
	 * 添加信息到上下线消息窗口
	 * @param msg
	 */
	public static void addToOnlineAndOfflineInfo(String msg) {
		chatPanel.getOnlineAndOfflineInfo().append(msg);
	}
	
	public static String listToString(List<String> list) {
		StringBuilder result = new StringBuilder();
		
		for (String s : list) {
			result.append(s);
			result.append("\n");
		}
		
		return result.toString();
	}
}
