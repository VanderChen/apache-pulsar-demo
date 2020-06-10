package org.vander;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerThreadPool {
    public static void main(String[] args) {
        int threadCount = 4;

        String url = "pulsar://localhost:6650";
        String topicName = "my-topic";
        String subtopicName = "my-subscription";

        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
            pool.submit(new ConsumerRunnable(url, topicName, subtopicName));
        }
        pool.shutdown();
    }
}
