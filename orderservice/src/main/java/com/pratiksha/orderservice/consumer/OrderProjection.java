package com.pratiksha.orderservice.consumer;

import com.pratiksha.orderservice.event.PaymentProcessedEvent;
import com.pratiksha.orderservice.model.OrderStatusMessage;
import com.pratiksha.orderservice.model.OrderView;
import com.pratiksha.orderservice.repository.OrderViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProjection {

  private final OrderViewRepository repository;
  private final SimpMessagingTemplate messagingTemplate;

  @KafkaListener(topics = "payment-success-topic",
  groupId = "order-group")
  public void handlePaymentSuccess(
          PaymentProcessedEvent event) {

    OrderView view = repository
            .findById(Long.valueOf(event.getOrderId()))
            .orElseThrow();

    view.setStatus("PAID");

    repository.save(view);

    OrderStatusMessage message =
            new OrderStatusMessage();

    message.setOrderId(
            Long.valueOf(event.getOrderId())
    );

    message.setStatus(
            "PAID"
    );

    messagingTemplate.convertAndSend(
            "/topic/order-status",
            message
    );
  }
}

