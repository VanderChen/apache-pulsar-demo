package org.vander.consumer;

import org.apache.pulsar.client.api.*;

import java.util.concurrent.TimeUnit;

public class ConsumerRunnable implements Runnable{

    private PulsarClient client;
    private Consumer<byte[]> consumer;

    private String url = null;
    private String topicName = null;
    private String subtopicName = null;
    private int topicNumberPerThread = 0;

    public ConsumerRunnable(String url, String topicName, String subtopicName, int topicNumberPerThread) {
        this.url = url;
        this.topicName = topicName;
        this.subtopicName = subtopicName;
        this.topicNumberPerThread = topicNumberPerThread;
    }

//    private void startConsumer(String topicName) throws PulsarClientException {
//        System.out.println(topicName + " " + "Start consume");
//        while (true) {
//            // Wait for a message
//            Message<byte[]> msg = consumer.receive();
//            try {
//                consumer.acknowledge(msg);
//            } catch (Exception e) {
//                System.err.printf("Unable to consume message: %s", e.getMessage());
//                consumer.negativeAcknowledge(msg);
//            }
//        }
//    }

    @Override
    public void run(){
        try {
            client = PulsarClient.builder()
                    .serviceUrl(url)
                    .build();

            int consumerCount = 100;

            while (true) {
                for (int topicIndex = 0; topicIndex < topicNumberPerThread; topicIndex++) {
                    consumer = client.newConsumer()
                            .topic(topicName + "-" + topicIndex)
                            .ackTimeout(30, TimeUnit.SECONDS)
                            .subscriptionName(subtopicName)
                            .subscriptionType(SubscriptionType.Shared)
                            .deadLetterPolicy(DeadLetterPolicy.builder()
                                    .maxRedeliverCount(10)
                                    .deadLetterTopic("dl-topic-name")
                                    .build())
                            .subscribe();
                    consumerCount = 100;
                    while (consumerCount > 0){
                        // Wait for a message
                        Message<byte[]> msg = consumer.receive();
                        try {
                            consumer.acknowledge(msg);
                        } catch (Exception e) {
                            System.err.printf("Unable to consume message: %s", e.getMessage());
                            consumer.negativeAcknowledge(msg);
                        }

                        consumerCount--;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
