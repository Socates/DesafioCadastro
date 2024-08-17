package com.br.cadastro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ClienteNaoEncontratoException.class)
    @ResponseBody
    public ResponseEntity<ErroResponse> clienteNaoEncontratoException(ClienteNaoEncontratoException ex) {
        ErroResponse errorResponse = new ErroResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CpfInvalidoException.class)
    @ResponseBody
    public ResponseEntity<ErroResponse> cpfInvalidoException(CpfInvalidoException ex) {
        ErroResponse errorResponse = new ErroResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErroResponse> genericoException(Exception ex) {
        ErroResponse errorResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno do servidor", LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public static class ErroResponse {

        private int status;
        private String message;
        private LocalDateTime timestamp;

        public ErroResponse(int status, String message, LocalDateTime timestamp) {
            this.status = status;
            this.message = message;
            this.timestamp = timestamp;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static class ClienteNaoEncontratoException extends RuntimeException {
        public ClienteNaoEncontratoException(String message) {
            super(message);
        }
    }

    public static class CpfInvalidoException extends RuntimeException {
        public CpfInvalidoException(String message) {
            super(message);
        }
    }

}