package org.vander;

import java.io.File;

public class PulsarConfig {

    private int producerThreadNumber = 3;
    private int consumerThreadNumber = 3;
    private int topicNumber = 3;

    private final String url = "pulsar://192.168.1.105:6650";
    private final String adminUrl = "http://192.168.1.105:8080";
    private final String topicName = "my-topic-";

    private int size = 1024;   //byte

    private final String statsFolderName = "results/standalone";

    public PulsarConfig() {
        File folder = new File(statsFolderName);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
        }
    }

    //    Getter and Setter Methods

    public String getStatsFolderName() {
        return statsFolderName;
    }

    public String getAdminUrl() {
        return adminUrl;
    }

    public int getProducerThreadNumber() {
        return producerThreadNumber;
    }

    public void setProducerThreadNumber(int producerThreadNumber) {
        this.producerThreadNumber = producerThreadNumber;
    }

    public int getTopicNumber() {
        return topicNumber;
    }

    public void setTopicNumber(int topicNumber) {
        this.topicNumber = topicNumber;
    }

    public String getUrl() {
        return url;
    }

    public String getTopicName() {
        return topicName;
    }

    public int getConsumerThreadNumber() {
        return consumerThreadNumber;
    }

    public void setConsumerThreadNumber(int consumerThreadNumber) {
        this.consumerThreadNumber = consumerThreadNumber;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
