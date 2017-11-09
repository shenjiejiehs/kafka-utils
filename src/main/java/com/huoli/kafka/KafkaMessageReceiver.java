package com.huoli.kafka;

import java.util.Arrays;
import java.util.Collections;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * kafka消息接收器 <br>
 * 版权: Copyright (c) 2011-2016<br>
 * 公司: 北京活力天汇<br>
 * 
 * @author: 童凡<br>
 * @date: 2016年10月19日<br>
 */
public class KafkaMessageReceiver {
	private static Logger logger = LoggerFactory.getLogger(KafkaMessageReceiver.class);
	private KafkaConsumer<Integer, String> kafkaConsumer;
	private String topic;
	/** 是否开启了自动提交 */
	private Boolean autoCommit = true;
	/** 消息处理 */
	private KafkaMessageHandler kafkaMessageHandler;

	/** 初始化方法 */
	public void init() {
		kafkaConsumer.subscribe(Arrays.asList(topic));
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (true) {
					ConsumerRecords<Integer, String> records = kafkaConsumer.poll(200);
					for (ConsumerRecord<Integer, String> record : records) {
						logger.debug("message received,offset:{},value:{}", record.offset(), record.value());
						String message = record.value();
						if (kafkaMessageHandler == null) {
							logger.warn("kafkaMessageHandler is null.");
							continue;
						}
						boolean success = kafkaMessageHandler.handle(message);
						if (success && !autoCommit) {
							kafkaConsumer.commitSync(Collections.singletonMap(new TopicPartition(topic, record.partition()), new OffsetAndMetadata(record.offset() + 1)));
						}
					}
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.setDaemon(true);
		thread.start();
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public KafkaConsumer<Integer, String> getKafkaConsumer() {
		return kafkaConsumer;
	}

	public void setKafkaConsumer(KafkaConsumer<Integer, String> kafkaConsumer) {
		this.kafkaConsumer = kafkaConsumer;
	}

	public KafkaMessageHandler getKafkaMessageHandler() {
		return kafkaMessageHandler;
	}

	public void setKafkaMessageHandler(KafkaMessageHandler kafkaMessageHandler) {
		this.kafkaMessageHandler = kafkaMessageHandler;
	}

	public Boolean getAutoCommit() {
		return autoCommit;
	}

	public void setAutoCommit(Boolean autoCommit) {
		this.autoCommit = autoCommit;
	}

}
