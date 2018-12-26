package com.yl.conf;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.yl.properties.ZkClientProperties;

@Configuration
@ComponentScan(basePackages="com.yl")
public class ZkClientConfig {
	
	
	@Autowired
	private ZkClientProperties zkClientProperties;
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }
	
    @Bean
    ZkClient zkClient() {
    	return new ZkClient(zkClientProperties.getZkServers(),zkClientProperties.getConnectionTimeout());
    }
}
