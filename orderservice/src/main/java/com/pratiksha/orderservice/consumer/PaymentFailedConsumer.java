package com.pratiksha.orderservice.consumer;

import com.pratiksha.orderservice.event.PaymentFailedEvent;
import com.pratiksha.orderservice.model.EventStore;
import com.pratiksha.orderservice.model.Order;
import com.pratiksha.orderservice.model.OrderView;
import com.pratiksha.orderservice.repository.EventStoreRepository;
import com.pratiksha.orderservice.repository.OrderRepository;
import com.pratiksha.orderservice.repository.OrderViewRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentFailedConsumer {

    private final OrderRepository orderRepository;
    private final EventStoreRepository eventStoreRepository;
    private final OrderViewRepository orderViewRepository;

    public PaymentFailedConsumer(OrderRepository orderRepository, EventStoreRepository eventStoreRepository, OrderViewRepository orderViewRepository) {
        this.orderRepository = orderRepository;
        this.eventStoreRepository = eventStoreRepository;
        this.orderViewRepository = orderViewRepository;
    }

    @KafkaListener(
            topics = "payment-failed-topic",
            groupId = "order-group"
    )
    public void consume(PaymentFailedEvent event) {

        EventStore eventStore = new EventStore();
        eventStore.setOrderId(Long.valueOf(event.getOrderId()));
        eventStore.setEventType("PAYMENT_FAILED");
        eventStore.setCreatedAt(LocalDateTime.now());
        eventStoreRepository.save(eventStore);


        Order order =
                orderRepository.findById(event.getOrderId())
                        .orElseThrow();

        order.setStatus("CANCELLED");

        orderRepository.save(order);

        OrderView view =
                orderViewRepository.findById(
                        Long.valueOf(event.getOrderId())
                ).orElseThrow();
        view.setStatus(
                "CANCELLED"
        );

        orderViewRepository.save(view);

        System.out.println("Order cancelled due to payment failure");
    }


    public void handlePaymentFailed(
            PaymentFailedEvent event) {

        OrderView view =
                orderViewRepository.findById(
                        Long.valueOf(event.getOrderId())
                ).orElseThrow();
        view.setStatus(
                "CANCELLED"
        );

        orderViewRepository.save(view);
    }
}
