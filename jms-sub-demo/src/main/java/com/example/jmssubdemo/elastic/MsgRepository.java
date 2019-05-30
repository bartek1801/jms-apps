package com.example.jmssubdemo.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

interface MsgRepository extends ElasticsearchRepository<Message, UUID> {
}
