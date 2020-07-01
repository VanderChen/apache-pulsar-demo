package org.vander;


import org.vander.consumer.ConsumerRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerThreadPool {
    public static void main(String[] args) {
        PulsarConfig config = new PulsarConfig();

        int consumerThreadNumber = config.getConsumerThreadNumber();
        int topicNumber = config.getTopicNumber();

        String url = config.getUrl();
        String topicName = config.getTopicName();
        String subtopicName = config.getTopicName();

        ExecutorService pool = Executors.newFixedThreadPool(consumerThreadNumber);
        for (int topicIndex = 0; topicIndex < topicNumber; topicIndex++) {
            pool.submit(new ConsumerRunnable(url, topicName + Integer.toString(topicIndex), subtopicName));
        }
        pool.shutdown();
    }
}
