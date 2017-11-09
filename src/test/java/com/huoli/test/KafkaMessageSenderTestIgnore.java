package com.huoli.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huoli.kafka.KafkaMessageSender;

/**
 * kafka发送测试 <br>
 * 版权: Copyright (c) 2011-2016<br>
 * 公司: 北京活力天汇<br>
 * 
 * @author: 童凡<br>
 * @date: 2016年10月19日<br>
 */
public class KafkaMessageSenderTestIgnore {
	@SuppressWarnings("resource")
	@Test
	public void test1() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("app-kafka-sender.xml");
		KafkaMessageSender kafkaMessageSender = (KafkaMessageSender) applicationContext.getBean("kafkaMessageSender");

		for (int i = 0; i < 10; i++) {
			kafkaMessageSender
					.send("{\"userId\":\"16849625\",\"airlineCode\":\"CA\",\"departCity\":\"PEK\",\"departCityName\":\"北京首都\",\"arriveCity\":\"NGB\",\"arriveCityName\":\"宁波\",\"date\":\"2016-10-21 16:18:48.537\",\"flightNo\":\"CA1853\",\"flightDate\":\"2016-10-21 20:10\",\"ticketNo\":\"9998507221848\",\"cabin\":\"Y\",\"certNo\":\"330107196906080910\",\"certType\":\"NI\",\"ffpCardNo\":\"\",\"passengerName\":\"欧柯祥\",\"mobile\":\"13806636872\",\"seatId\":\"24L\",\"status\":1,\"canceledNum\":0,\"operation\":\"submit_seat\",\"action\":\"checkin\",\"intl\":0}");
		}
		// Thread.currentThread().join();
		Thread.sleep(10 * 1000);
	}
}
