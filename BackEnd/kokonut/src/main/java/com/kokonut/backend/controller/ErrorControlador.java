package com.kokonut.backend.controller;

import java.util.Date;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kokonut.backend.bean.DetallesErrorBean;
import com.kokonut.backend.exception.AppException;
import com.kokonut.backend.exception.BadRequestException;
import com.kokonut.backend.exception.OperacionNoPermitidaExcepcion;

@ControllerAdvice
public class ErrorControlador extends ResponseEntityExceptionHandler {


    @ExceptionHandler({RuntimeException.class, AppException.class})
    public ResponseEntity<DetallesErrorBean> handle(RuntimeException e, WebRequest request) {
        return new ResponseEntity<>(DetallesErrorBean.builder().timestamp(new Date()).message(e.getMessage()).
                details(request.getDescription(true)).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<DetallesErrorBean> handle(ResourceNotFoundException e, WebRequest request) {
        return new ResponseEntity<>(DetallesErrorBean.builder().timestamp(new Date()).message(e.getMessage()).
                details(request.getDescription(true)).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<DetallesErrorBean> handle(BadRequestException e, WebRequest request) {
        return new ResponseEntity<>(DetallesErrorBean.builder().timestamp(new Date()).message(e.getMessage()).
                details(request.getDescription(true)).build(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({OperacionNoPermitidaExcepcion.class})
    public ResponseEntity<DetallesErrorBean> handle(OperacionNoPermitidaExcepcion e, WebRequest request) {
        return new ResponseEntity<>(DetallesErrorBean.builder().timestamp(new Date()).message(e.getMessage()).
                details(request.getDescription(true)).build(), HttpStatus.UNAUTHORIZED);
    }
}