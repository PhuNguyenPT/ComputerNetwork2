package com.example.jpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDateTime createAt;
    private LocalDateTime lastModifiedAt;
    private String createdBy;
    private String lastModifyBy;
}
