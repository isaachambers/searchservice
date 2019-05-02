package com.isaachambers.search.config;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.isaachambers.search.repository.es")
public class ElasticConfiguration {

	@Value("${elastic.search.host}")
	private String host;

	@Value("${elastic.search.tcp.port}")
	private int tcpPort;

	@Value("${spring.data.elasticsearch.cluster-name}")
	private String clusterName;

	@Bean
	public Client client() throws Exception {
		Settings settings = Settings.builder().put("cluster.name", clusterName).build();
		TransportClient client = new PreBuiltTransportClient(settings);
		client.addTransportAddress(new TransportAddress(InetAddress.getByName(host), tcpPort));
		return client;
	}

}
