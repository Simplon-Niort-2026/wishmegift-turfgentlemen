package co.simplon.wishmegift.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorEntity {
    private LocalDateTime timeStamp;
    private String message;
    private int httpStatusCode;

}
