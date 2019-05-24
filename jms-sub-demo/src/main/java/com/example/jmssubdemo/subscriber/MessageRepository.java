package com.example.jmssubdemo.subscriber;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface MessageRepository extends JpaRepository<Message, UUID> {
}
