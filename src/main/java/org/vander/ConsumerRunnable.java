package org.vander;

import org.apache.pulsar.client.api.*;

import java.util.concurrent.TimeUnit;

public class ConsumerRunnable implements Runnable{

    private static PulsarClient client;
    private static Consumer<byte[]> consumer;

    private String url = null;
    private String topicName = null;
    private String subtopicName = null;

    public ConsumerRunnable(String url, String topicName, String subtopicName) {
        this.url = url;
        this.topicName = topicName;
        this.subtopicName = subtopicName;
    }

    private static void startConsumer() throws PulsarClientException {

        while (true) {
            // Wait for a message
            Message<byte[]> msg = consumer.receive();
            try {
                System.out.printf("thread:" + Thread.currentThread().getName() + "   Message from: %s\n", new String(msg.getData()));
                consumer.acknowledge(msg);
            } catch (Exception e) {
                System.err.printf("Unable to consume message: %s", e.getMessage());
                consumer.negativeAcknowledge(msg);
            }
        }
    }

    @Override
    public void run(){
        try {
            client = PulsarClient.builder()
                    .serviceUrl(url)
                    .build();

            consumer = client.newConsumer()
                    .topic(topicName)
                    .ackTimeout(30, TimeUnit.SECONDS)
                    .subscriptionName(subtopicName)
                    .subscriptionType(SubscriptionType.Shared)
                    .deadLetterPolicy(DeadLetterPolicy.builder()
                            .maxRedeliverCount(10)
                            .deadLetterTopic("dl-topic-name")
                            .build())
                    .subscribe();

            startConsumer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
