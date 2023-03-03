package com.study.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

    /**
     * Kafka Listener
     * Kafka에서 메시지를 읽어들이는 역할
     *
     * <개념>
     * Topic: 메시지 데이터의 구분을 할 수 있는 논리적 개념
     * Offset: Kafka Message의 고유번호. consumer에서 메시지를 어디까지 읽었는지 확인하는 용도로 쓰임
     *
     * <log 예시>
     * ### record: ConsumerRecord(topic = dev-topic, partition = 0, leaderEpoch = 0, offset = 1, CreateTime = 1649170434791, serialized key size = -1, serialized value size = 13, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = kafka message)
     * ### topic: dev-topic, value: kafka message, offset: 1
     * @param record
     */
    @KafkaListener(topics = "dev-topic")
    public void messageListener(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        log.info("### record: " + record.toString());
        log.info("### topic: " + record.topic() + ", value: " + record.value() + ", offset: " + record.offset());

        // kafka 메시지 읽어온 곳까지 commit. (이 부분을 하지 않으면 메시지를 소비했다고 commit 된 것이 아니므로 계속 메시지를 읽어온다)
        acknowledgment.acknowledge();
    }
}
