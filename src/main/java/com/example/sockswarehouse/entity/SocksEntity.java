package com.example.sockswarehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class SocksEntity {

    @Id
    @GeneratedValue
    Long idSocks;

    String color;
    Integer cottonPart;
    Integer quantity;
}
