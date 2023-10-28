package com.producer.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ErrorDetails {
    private String message;
    private String description;
}
