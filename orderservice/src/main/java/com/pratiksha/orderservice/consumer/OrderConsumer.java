package com.pratiksha.orderservice.consumer;

import com.pratiksha.orderservice.event.InventoryUpdatedEvent;
import com.pratiksha.orderservice.event.OrderPlacedEvent;
import com.pratiksha.orderservice.model.EventStore;
import com.pratiksha.orderservice.model.OrderView;
import com.pratiksha.orderservice.repository.EventStoreRepository;
import com.pratiksha.orderservice.repository.OrderRepository;
import com.pratiksha.orderservice.repository.OrderViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrderConsumer {
    private final EventStoreRepository eventStoreRepository;
    private final OrderViewRepository orderViewRepository;

    @KafkaListener(topics = "inventory-success-topic",
            groupId = "order-group"
    )
    public void consumeInventoryUpdated(
            InventoryUpdatedEvent event) {
        EventStore eventStore =new EventStore();
        eventStore.setOrderId(Long.valueOf(event.getOrderId()));
        eventStore.setEventType("INVENTORY_UPDATED");
        eventStore.setCreatedAt(LocalDateTime.now());
        eventStoreRepository.save(eventStore);

        EventStore eventStore1 =new EventStore();
        eventStore1.setOrderId(Long.valueOf(event.getOrderId()));
        eventStore1.setEventType("ORDER_COMPLETED");
        eventStore1.setCreatedAt(LocalDateTime.now());
        eventStoreRepository.save(eventStore1);

    }

    @KafkaListener(topics = "order-topic",
            groupId = "order-group"
    )
    public void handleOrderCreated(
            OrderPlacedEvent event) {

        OrderView view =
                new OrderView();

        view.setOrderId(
                Long.valueOf(event.getOrderId())
        );

        view.setStatus(
                "CREATED"
        );

        orderViewRepository.save(view);
    }

}
