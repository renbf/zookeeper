package com.yl.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value= {"classpath:properties/zkclient.properties"})
public class ZkClientProperties {
	
	@Value("${zk.addr}")
    private String zkServers;
    
    @Value("${zk.timeout}")
    private int connectionTimeout;
    
    public String getZkServers() {
		return zkServers;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

}
