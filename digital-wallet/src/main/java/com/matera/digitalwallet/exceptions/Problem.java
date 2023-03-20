package com.matera.digitalwallet.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Problem {
    private LocalDateTime hourDate = LocalDateTime.now();
    private String message;

    public Problem(String message) {
        this.message = message;
    }
}
