package com.example.paymentservice.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentSuccess(Object event) {

        kafkaTemplate.send("payment-success-topic", event);

        System.out.println("Payment success event sent");
    }

    public void sendPaymentFailure(Object event) {

        kafkaTemplate.send("payment-failed-topic", event);

        System.out.println("Payment failed event sent");
    }
}