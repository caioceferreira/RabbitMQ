package org.empresa.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "demo.topic";
    public static final String ORDERS_QUEUE = "orders.q";
    public static final String ORDERS_DLQ = "orders.dlq.q";
    public static final String ORDERS_RK = "orders.created";
    public static final String DLX = "orders.dlx";
    public static final String DLQ_RK = "orders.dlq";

    @Bean
    TopicExchange exchange(){return new TopicExchange(EXCHANGE, true, false);
    }

    @Bean
    TopicExchange deadLetterExchange() {return new TopicExchange(DLX, true, false);}

    @Bean
    Queue ordersQueue(){
        return new Queue(ORDERS_QUEUE, true, false, false, Map.of("x-dead-letter-exchange", DLX, "x-dead-letter-routing-key", DLQ_RK));
    }

    @Bean
    Queue ordersDlq(){return new Queue(ORDERS_DLQ, true);}

    @Bean
    Binding ordersBinding() {
        return BindingBuilder.bind(ordersQueue()).to(exchange()).with(ORDERS_RK);
    }

    @Bean
    Binding dlqBinding(){return BindingBuilder.bind(ordersDlq()).to(deadLetterExchange()).with(DLQ_RK);}

    @Bean
    Jackson2JsonMessageConverter jackson2JsonMessageConverter(){return new Jackson2JsonMessageConverter();}

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jackson2JsonMessageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }


}
