package org.empresa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.empresa.OrderCreated;
import org.empresa.configuration.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody OrderCreated payload){
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ORDERS_RK, payload);
        log.info("✅ Order accepted: {}", payload.id());
        return ResponseEntity.accepted().body("published");
    }
}
