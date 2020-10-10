package org.vander.pulsardemo.client;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.vander.pulsardemo.PulsarClientProperties;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan
@EnableConfigurationProperties(PulsarClientProperties.class)
public class PulsarClientAutoConfiguration {

    private final PulsarClientProperties pulsarClientProperties;

    public PulsarClientAutoConfiguration(PulsarClientProperties pulsarClientProperties){
        this.pulsarClientProperties = pulsarClientProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public PulsarClient pulsarClient() throws PulsarClientException{
        return PulsarClient.builder()
                .serviceUrl(pulsarClientProperties.getServiceUrl())
                .ioThreads(pulsarClientProperties.getIoThreads())
                .listenerThreads(pulsarClientProperties.getListenerThreads())
                .enableTcpNoDelay(pulsarClientProperties.isEnableTcpNoDelay())
                .keepAliveInterval(pulsarClientProperties.getKeepAliveIntervalSec(), TimeUnit.SECONDS)
                .connectionTimeout(pulsarClientProperties.getConnectionTimeoutSec(), TimeUnit.SECONDS)
                .operationTimeout(pulsarClientProperties.getOperationTimeoutSec(), TimeUnit.SECONDS)
                .startingBackoffInterval(pulsarClientProperties.getStartingBackoffIntervalMs(), TimeUnit.MILLISECONDS)
                .maxBackoffInterval(pulsarClientProperties.getMaxBackoffIntervalSec(), TimeUnit.SECONDS)
                .build();
    }
}
