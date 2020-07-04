package org.vander;

import org.apache.pulsar.shade.com.google.gson.Gson;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class PulsarConfig {

    private int producerThreadNumber;
    private int consumerThreadNumber;
    private int topicNumberPerThread;

    private String url;
    private String adminUrl;
    private final String topicName = "my-topic-";

    private int size;   //byte

    private String statsFolderName;

    public PulsarConfig(){
//        Read config from TestConfig json file
        try {
            Gson gson = new Gson();
            Reader jsonReader = Files.newBufferedReader(Paths.get("TestConfig.json"));
            Map<?, ?> jsonMap = gson.fromJson(jsonReader, Map.class);
            this.producerThreadNumber = Integer.parseInt((String) jsonMap.get("producerThreadNumber"));
            this.consumerThreadNumber = Integer.parseInt((String) jsonMap.get("consumerThreadNumber"));
            this.topicNumberPerThread = Integer.parseInt((String) jsonMap.get("topicNumberPerThread"));
            this.url = (String) jsonMap.get("url");
            this.adminUrl = (String) jsonMap.get("adminUrl");
            this.size = Integer.parseInt((String) jsonMap.get("payload-size"));
            this.statsFolderName = (String) jsonMap.get("statsFolderName");
        }catch (Exception e){
            e.printStackTrace();
        }

//        Check path available
        File folder = new File(statsFolderName);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
        }
    }

    //    Getter and Setter Methods

    public int getTopicNumberPerThread() {
        return topicNumberPerThread;
    }

    public String getStatsFolderName() {
        return statsFolderName;
    }

    public String getAdminUrl() {
        return adminUrl;
    }

    public int getProducerThreadNumber() {
        return producerThreadNumber;
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

    public int getSize() {
        return size;
    }
}
