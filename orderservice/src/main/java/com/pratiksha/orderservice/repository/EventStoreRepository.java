package com.pratiksha.orderservice.repository;

import com.pratiksha.orderservice.model.EventStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventStoreRepository extends JpaRepository<EventStore,Long> {
    List<EventStore> findByOrderIdOrderByCreatedAt(
            Long orderId
    );
}
