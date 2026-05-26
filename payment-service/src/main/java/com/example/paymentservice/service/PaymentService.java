package com.example.paymentservice.service;
import com.example.paymentservice.producer.PaymentProducer;
import com.pratiksha.orderservice.event.PaymentFailedEvent;
import com.pratiksha.orderservice.event.PaymentProcessedEvent;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {

    private final PaymentProducer paymentProducer;

    public PaymentService(PaymentProducer paymentProducer) {
        this.paymentProducer = paymentProducer;
    }

    public void processPayment(String orderId, String productName) {

        Random random = new Random();

        boolean paymentSuccess = random.nextBoolean();

        if(paymentSuccess) {

            PaymentProcessedEvent event =
                    new PaymentProcessedEvent();

            event.setOrderId(orderId);
            event.setProductName(productName);
            event.setStatus("SUCCESS");

            paymentProducer.sendPaymentSuccess(event);

        } else {

            PaymentFailedEvent event =
                    new PaymentFailedEvent();

            event.setOrderId(orderId);
            event.setReason("Insufficient balance");

            paymentProducer.sendPaymentFailure(event);
        }
    }
}