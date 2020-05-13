package org.vander.keyshared;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;

public class PulsarProducer {

    private static PulsarClient client;
    private static Producer<byte[]> producer;

    public static void main(String[] args) throws Exception {
        client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        producer = client.newProducer()
                .topic("my-topic")
                .create();

        startProducer();

    }

    private static void startProducer() throws Exception {
        producer.newMessage().key("key-1").value("message-1-1".getBytes()).send();
        producer.newMessage().key("key-1").value("message-1-2".getBytes()).send();
        producer.newMessage().key("key-1").value("message-1-3".getBytes()).send();
        producer.newMessage().key("key-2").value("message-2-1".getBytes()).send();
        producer.newMessage().key("key-2").value("message-2-2".getBytes()).send();
        producer.newMessage().key("key-2").value("message-2-3".getBytes()).send();
        producer.newMessage().key("key-3").value("message-3-1".getBytes()).send();
        producer.newMessage().key("key-3").value("message-3-2".getBytes()).send();
        producer.newMessage().key("key-4").value("message-4-1".getBytes()).send();
        producer.newMessage().key("key-4").value("message-4-2".getBytes()).send();
    }
}
