package com.kokonut.backend.bean;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class DetallesErrorBean {

    private Date timestamp;
    private String message;
    private String details;
    
}
