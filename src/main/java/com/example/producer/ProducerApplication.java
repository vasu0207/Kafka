package com.example.producer;

import static com.example.common.CommonUtil.MY_TOPIC_NAME;

import java.util.Properties;
import java.util.stream.IntStream;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerApplication {

  public static void main(String[] args) {
    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092, localhost:9093");
    props.put("key.serializer", StringSerializer.class);
    props.put("value.serializer", StringSerializer.class);

    KafkaProducer<String, String> kafkaProducer = new KafkaProducer(props);

    IntStream.range(0,150).forEach(value -> {
      ProducerRecord producerRecord =
          new ProducerRecord(MY_TOPIC_NAME, "key"+Integer.toString(value), "This is my First Kafka Message "+Integer.toString(value));
      kafkaProducer.send(producerRecord);
    });
  }
}
