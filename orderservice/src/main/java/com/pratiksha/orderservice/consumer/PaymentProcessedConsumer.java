package com.pratiksha.orderservice.consumer;

import com.pratiksha.orderservice.event.PaymentProcessedEvent;
import com.pratiksha.orderservice.model.EventStore;
import com.pratiksha.orderservice.model.OrderView;
import com.pratiksha.orderservice.repository.EventStoreRepository;
import com.pratiksha.orderservice.repository.OrderRepository;
import com.pratiksha.orderservice.repository.OrderViewRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PaymentProcessedConsumer {
    private final OrderRepository orderRepository;
    private final EventStoreRepository eventStoreRepository;
    private final OrderViewRepository orderViewRepository;

    public PaymentProcessedConsumer(OrderRepository orderRepository, EventStoreRepository eventStoreRepository, OrderViewRepository orderViewRepository) {
        this.orderRepository = orderRepository;
        this.eventStoreRepository = eventStoreRepository;
        this.orderViewRepository = orderViewRepository;
    }

    @KafkaListener(
            topics = "payment-success-topic",
            groupId = "order-group"
    )

    public void consume(PaymentProcessedEvent event) {

        EventStore eventStore =
                new EventStore();

        eventStore.setOrderId(
                Long.valueOf(event.getOrderId())
        );

        eventStore.setEventType(
                "PAYMENT_COMPLETED"
        );

        eventStore.setCreatedAt(
                LocalDateTime.now()
        );

        eventStoreRepository.save(
                eventStore
        );

        OrderView view =
                orderViewRepository.findById(
                        Long.valueOf(event.getOrderId())
                ).orElseThrow();


        view.setStatus(
                "PAID"
        );

        orderViewRepository.save(view);
    }

}
