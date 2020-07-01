package org.vander;

import org.vander.producer.ProducerRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerThreadPool {
    public static void main(String[] args) {
        PulsarConfig config = new PulsarConfig();

        int producerThreadNumber = config.getProducerThreadNumber();
        int topicNumber = config.getTopicNumber();

        String url = config.getUrl();
        String topicName = config.getTopicName();
        int size = config.getSize();   //byte

        ExecutorService pool = Executors.newFixedThreadPool(producerThreadNumber);
        for (int topicIndex = 0; topicIndex < topicNumber; topicIndex++) {
            pool.submit(new ProducerRunnable(url, topicName + Integer.toString(topicIndex), size));
        }
        pool.shutdown();
    }
}
