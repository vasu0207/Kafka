package com.example.consumer;

import static com.example.common.CommonUtil.MY_TOPIC_NAME;
import static com.example.common.CommonUtil.MY_TOPIC_NAME_1;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ConsumerSubscribeApplication {
  public static void main(String[] args) {
    Properties props = new Properties();
    props.put("bootstrap.servers","localhost:9092, localhost:9093, localhost:9094");
    props.put("key.deserializer", StringDeserializer.class);
    props.put("value.deserializer", StringDeserializer.class);
    props.put("group.id", "test1");

    KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer(props);
    kafkaConsumer.subscribe(Arrays.asList(MY_TOPIC_NAME, MY_TOPIC_NAME_1));
    try{
      while(true) {
        Duration dr = Duration.ofDays(1);
        ConsumerRecords<String, String > consumerRecords = kafkaConsumer.poll(dr);
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
