package com.huoli.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huoli.kafka.KafkaMessageHandler;

/**
 * 自定义的kafka消息处理器 <br>
 * 版权: Copyright (c) 2011-2016<br>
 * 公司: 北京活力天汇<br>
 * 
 * @author: 童凡<br>
 * @date: 2016年10月19日<br>
 */
public class CustomKafkaMessageHandler implements KafkaMessageHandler {
	private static Logger logger = LoggerFactory.getLogger(CustomKafkaMessageHandler.class);

	@Override
	public boolean handle(String message) {
		try {
			logger.debug("message:{}", message);

			return true;
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		return false;
	}

}
