package org.poc.springboot.conf;

import org.ff4j.FF4j;
import org.ff4j.consul.ConsulConnection;
import org.ff4j.consul.store.FeatureStoreConsul;
import org.ff4j.consul.store.PropertyStoreConsul;
import org.ff4j.web.ApiConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.orbitz.consul.Consul;

@Configuration
@ConditionalOnClass({FF4j.class})
@ComponentScan(value = {"org.ff4j.spring.boot.web.api", "org.ff4j.services", "org.ff4j.aop", "org.ff4j.spring"})
public class FF4jConfiguration {

    @Value("${spring.cloud.consul.host:localhost}")
    private String consulHost;
    @Value("${spring.cloud.consul.port:8500}")
    private int consulPort;

    @Bean
    public FF4j ff4j() {
        FF4j ff4j = new FF4j().audit(true).autoCreate(true);

        ConsulConnection connection = new ConsulConnection(
                Consul.builder().withUrl("http://" + consulHost + ":" + consulPort).build());
        ff4j.setFeatureStore(new FeatureStoreConsul(connection));
        ff4j.setPropertiesStore(new PropertyStoreConsul(connection));

        return ff4j;
    }
}
