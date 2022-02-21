package com.example.consumer;

import static com.example.common.CommonUtil.MY_TOPIC_NAME;
import static com.example.common.CommonUtil.MY_TOPIC_NAME_1;

import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ConsumerAssignApplication {
  public static void main(String[] args) {
    Properties props = new Properties();
    props.put("bootstrap.servers","localhost:9092, localhost:9093, localhost:9094");
    props.put("key.deserializer", StringDeserializer.class);
    props.put("value.deserializer", StringDeserializer.class);

    KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer(props);
    TopicPartition partition0 = new TopicPartition(MY_TOPIC_NAME, 0);
    TopicPartition partition1 = new TopicPartition(MY_TOPIC_NAME_1, 1);
    kafkaConsumer.assign(Arrays.asList(partition0, partition1));
    try{
      while(true) {
        ConsumerRecords<String, String > consumerRecords = kafkaConsumer.poll(10);
        for (ConsumerRecord cr: consumerRecords) {
          System.out.println(
              String.format(
                  "Topic: %s, Partition: %d, offset:%d, key:%s, value:%s",
                  cr.topic(), cr.partition(), cr.offset(), cr.key(), cr.value()));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally{
       kafkaConsumer.close();
    }
  }
}
