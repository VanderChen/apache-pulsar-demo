package org.vander;


import org.vander.consumer.ConsumerRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerThreadPool {
    public static void main(String[] args) {
        PulsarConfig config = new PulsarConfig();

        ExecutorService pool = Executors.newFixedThreadPool(config.getConsumerThreadNumber());
        for (int threadIndex = 0; threadIndex < config.getConsumerThreadNumber(); threadIndex++) {
            pool.submit(new ConsumerRunnable(config, threadIndex));
        }
        pool.shutdown();
    }
}
