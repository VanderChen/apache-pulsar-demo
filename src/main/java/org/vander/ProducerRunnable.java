package org.vander;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;

import javax.swing.*;

public class ProducerRunnable implements Runnable{

    private PulsarClient client;
    private Producer<byte[]> producer;

    private String url = null;
    private String topicName = null;
    private int sleepTime = 0;

    public ProducerRunnable(String url, String topicName, int sleepTime) {
        this.url = url;
        this.topicName = topicName;
        this.sleepTime = sleepTime;
    }

    private void startProducer(String topicName) throws Exception {
        System.out.println(topicName + " " + "Start produce");
        while (true) {
            producer.newMessage()
                    .value((topicName + " " + Thread.currentThread().getName()).getBytes())
                    .send();


            Thread.sleep(1000);
        }
    }

    @Override
    public void run(){
        try {
            client = PulsarClient.builder()
                    .serviceUrl(url)
                    .build();

            producer = client.newProducer()
                    .topic(topicName)
                    .create();

            startProducer(topicName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
