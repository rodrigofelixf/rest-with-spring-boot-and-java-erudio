package br.com.erudio.restwithspringbootandjavaerudio.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDateTime timestamp;
    private String message;
    private String details;


    public ExceptionResponse(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
