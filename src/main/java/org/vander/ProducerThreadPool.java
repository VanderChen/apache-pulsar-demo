package org.vander;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerThreadPool {
    public static void main(String[] args) {
        int threadCount = 4;

        String url = "pulsar://localhost:6650";
        String topicName = "my-topic";
        int sleepTime = 1000;

        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
            pool.submit(new ProducerRunnable(url, topicName, sleepTime));
        }
        pool.shutdown();
    }
}
