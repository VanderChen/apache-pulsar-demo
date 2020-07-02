package org.vander;

import org.vander.producer.ProducerRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerThreadPool {
    public static void main(String[] args) {
        PulsarConfig config = new PulsarConfig();

        int producerThreadNumber = config.getProducerThreadNumber();
        int topicNumber = config.getTopicNumber();
        int topicNumberPerThread = config.getTopicNumberPerThread();

        String url = config.getUrl();
        String topicName = config.getTopicName();
        int size = config.getSize();   //byte

        ExecutorService pool = Executors.newFixedThreadPool(producerThreadNumber);
        for (int threadIndex = 0; threadIndex < producerThreadNumber; threadIndex++) {
            pool.submit(new ProducerRunnable(url, topicName + Integer.toString(threadIndex), size, topicNumberPerThread));
        }
        pool.shutdown();
    }
}
