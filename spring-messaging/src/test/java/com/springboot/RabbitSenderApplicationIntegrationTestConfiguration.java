package com.springboot;

import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMq;
import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMqConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * Configuration class which needs to be loaded additionally to the
 * {@code @SpringBootApplication} annotated class. It will bootstrap a process
 * which will run activemq. Using an {@Configuration} class makes Spring manage
 * the starting and stopping of the embedded RabbitMQ. Another option is to use
 * the {@code @BeforeClass} and {@code @AfterClass} of a test. The configuration
 * includes the queue, exchange and binding definitions else it isn't possible
 * to receive the messages. 
 * 
 * See {@link <a href="https://github.com/AlejandroRivera/embedded-rabbitmq">Embedded RabbitMQ</a>}
 */
@TestConfiguration
public class RabbitSenderApplicationIntegrationTestConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    @Profile("embedded")
    public EmbeddedRabbitMq embeddedRabbitMq() {
        EmbeddedRabbitMqConfig config = new EmbeddedRabbitMqConfig.Builder().rabbitMqServerInitializationTimeoutInMillis(10000).build();
        return new EmbeddedRabbitMq(config);
    }

}
