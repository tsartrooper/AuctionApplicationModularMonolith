package com.example.auction_application.shared;

import java.time.LocalDateTime;

public interface DomainEvent {
    
    public LocalDateTime getOccurredOn();
}
