package com.mysite.myspringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Room {
    @Id
    @GeneratedValue
    private int roomNumber;

    private String type; // למשל: "Standard", "Deluxe", "Jacuzzi"
    
    private int capacity;

    private boolean hasJacuzzi;
     

}
