package com.example.jmssubdemo.crud.person;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Getter
@MappedSuperclass
@ToString
public abstract class BaseEntity {

    public enum ArchiveStatus {
        ACTIVE, ARCHIVE
    }

    @Id
    private UUID id;

    @Version
    private Long version;

    @Enumerated(EnumType.ORDINAL)
    private ArchiveStatus archiveStatus = ArchiveStatus.ACTIVE;

    public BaseEntity() {
        this.id = UUID.randomUUID();
    }

    public BaseEntity markAsRemoved() {
        archiveStatus = ArchiveStatus.ARCHIVE;
        return this;
    }
}
