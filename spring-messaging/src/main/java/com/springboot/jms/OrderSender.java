package com.springboot.jms;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.model.Order;

@Component
public class OrderSender {

    private final RabbitTemplate rabbit;

    OrderSender(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    /**
     *  Added for demonstration purposes only - it sends an Order object to new-order channel every 500 ms
     */
    @Scheduled(fixedRate = 500)
    public void sendTime() {

        var id = UUID.randomUUID().toString();
        var amount = ThreadLocalRandom.current().nextDouble(1000.00d);
        var order = new Order(id, BigDecimal.valueOf(amount));
        rabbit.convertAndSend("orders", "new-order", order);
    }

}
