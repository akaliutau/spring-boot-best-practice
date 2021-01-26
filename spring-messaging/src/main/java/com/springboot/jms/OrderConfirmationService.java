package com.springboot.jms;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.springboot.model.OrderConfirmation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderConfirmationService {
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = "orders", type = ExchangeTypes.TOPIC), 
            value = @Queue(name = "order-confirmations"), key = "order-confirmation")
    )
    public void handle(OrderConfirmation confirmation) {
        log.info("[RECEIVED] - {}", confirmation);
    }

}
