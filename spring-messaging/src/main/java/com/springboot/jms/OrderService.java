package com.springboot.jms;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.springboot.model.Order;
import com.springboot.model.OrderConfirmation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderService {
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = "orders", type = ExchangeTypes.TOPIC), 
            value = @Queue(name = "incoming-orders"), key = "new-order")
    )
    @SendTo("orders/order-confirmation")
    public OrderConfirmation handle(Order order) {
        log.info("[RECEIVED] - {}", order);
        return new OrderConfirmation(order.getId());
    }

}
