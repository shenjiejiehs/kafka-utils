package com.huoli.kafka;

import java.util.UUID;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * kafka消息发送器 <br>
 * 版权: Copyright (c) 2011-2016<br>
 * 公司: 北京活力天汇<br>
 * 
 * @author: 童凡<br>
 * @date: 2016年10月16日<br>
 */
public class KafkaMessageSender {
	private static Logger logger = LoggerFactory.getLogger(KafkaMessageSender.class);
	private KafkaProducer<Integer, String> kafkaProducer;
	private String topic;

	/**
	 * 发送消息
	 * 
	 * @param object
	 */
	public void send(String message) {
		String key = UUID.randomUUID().toString();
		logger.debug("准备发送kafka消息,key:{},内容:{}", key, message);
		try {
			kafkaProducer.send(new ProducerRecord<Integer, String>(topic, message));
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		logger.debug("发送kafka消息完毕,key:{}", key);

	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public KafkaProducer<Integer, String> getKafkaProducer() {
		return kafkaProducer;
	}

	public void setKafkaProducer(KafkaProducer<Integer, String> kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

}
