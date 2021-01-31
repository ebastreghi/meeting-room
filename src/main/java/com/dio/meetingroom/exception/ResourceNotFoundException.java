package com.dio.meetingroom.exception;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(String message){
        super(message);
    }

}

