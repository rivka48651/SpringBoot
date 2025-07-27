package com.mysite.myspringboot.dto;

import lombok.Data;

@Data
public class RoomDTO {
    private int roomNumber;
    private String type;
    private int capacity;
    private boolean hasJacuzzi;
}
