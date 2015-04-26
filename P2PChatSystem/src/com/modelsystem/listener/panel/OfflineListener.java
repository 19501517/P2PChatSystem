package com.modelsystem.listener.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.modelsystem.service.OnlineAndOffLineService;

/**
 * 面板监听，监听下线按钮。
 * @author LiuYeFeng<897908343@qq.com>
 * @date 2015-1-10 下午2:06:30
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class OfflineListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		new OnlineAndOffLineService().offline();
	}

}
