package org.vander;

import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.common.policies.data.TopicStats;
import org.apache.pulsar.shade.com.google.gson.*;


import java.io.FileWriter;
import java.util.List;

public class PulsarTestAdmin {
    public static void main(String[] args) throws Exception{
        PulsarConfig config = new PulsarConfig();

        String adminUrl = config.getAdminUrl();

        PulsarAdmin admin = PulsarAdmin.builder()
                .serviceHttpUrl(adminUrl)
                .build();

//      Get Broker Metrics and save to result dir

        List<String> clusters = admin.clusters().getClusters();
        JsonArray brokerMetrics = admin.brokerStats().getMetrics();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(brokerMetrics);
        FileWriter writer = new FileWriter(config.getStatsFolderName() + "/broker_stats.json");
        writer.write(json);
        writer.close();

//      Get Topic Metrics

        List<String> namespaces = admin.namespaces().getNamespaces(admin.tenants().getTenants().get(0));
        List<String> topics = admin.topics().getList(namespaces.get(0));

        double totalRateIn = 0;
        double totalRateOut = 0;
        for (String topic: topics) {
            TopicStats topicStats = admin.topics().getStats(topic);
            totalRateIn += topicStats.msgRateIn;
            totalRateOut += topicStats.msgRateOut;
            String topicJson = gson.toJson(topicStats);
            writer = new FileWriter(config.getStatsFolderName() + "/" + topic.split("/")[4] + "_stats.json");
            writer.write(topicJson);
            writer.close();
        }

        System.out.println("totalRateIn: " + totalRateIn);
        System.out.println("totalRateOut: " + totalRateOut);

        System.exit(0);
    }
}