package com.tqk.myapp.application;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

/**
 * @author Quang-Khai TRAN
 * @date 04/04/2025
 */

@Configuration
public class IbmQueueConfig {

    @Value("${ibm.mq.host}") private String host;
    @Value("${ibm.mq.port}") private int port;
    @Value("${ibm.mq.queue-manager}") private String queueManager;
    @Value("${ibm.mq.channel}") private String channel;
    @Value("${ibm.mq.user}") private String user;
    @Value("${ibm.mq.password}") private String password;

    @Bean
    public ConnectionFactory mqConnectionFactory() throws Exception {
        MQConnectionFactory factory = new MQConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setQueueManager(queueManager);
        factory.setChannel(channel);
        factory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
        factory.setStringProperty(WMQConstants.USERID, user);
        factory.setStringProperty(WMQConstants.PASSWORD, password);
        factory.setCCSID(1208);
        return factory;
    }


}
