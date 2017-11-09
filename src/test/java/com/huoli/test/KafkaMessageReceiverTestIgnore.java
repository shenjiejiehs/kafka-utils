package com.huoli.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * kafka接收测试 <br>
 * 版权: Copyright (c) 2011-2016<br>
 * 公司: 北京活力天汇<br>
 * 
 * @author: 童凡<br>
 * @date: 2016年10月19日<br>
 */
public class KafkaMessageReceiverTestIgnore {
	@SuppressWarnings({ "resource", "unused" })
	@Test
	public void test1() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("app-kafka-receiver.xml");
		Thread.sleep(10 * 60 * 1000);

	}
}
