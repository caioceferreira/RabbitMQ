/*package org.empresa.consumer;

import org.empresa.OrderCreated;
import org.empresa.configuration.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = RabbitConfig.ORDERS_QUEUE)
    @SuppressWarnings("ConstantValue")
    public void handle(OrderCreated message){
        log.info("Consumed: id={}, amountCents={}", message.id(), message.amountCents());


        //message may come from other systems
        if(message.amountCents() < 0){
            throw new IllegalArgumentException("amountCents must be >= 0");
        }
    }
}
*/