package com.carros.carros_api.controller.common;

import com.carros.carros_api.controller.dto.ErroCampo;
import com.carros.carros_api.controller.dto.ErroResposta;
import com.carros.carros_api.exceptions.RegistroDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ErroResposta handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> listErros = fieldErrors
                .stream()
                .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .toList();
        return new ErroResposta(
                HttpStatus.UNPROCESSABLE_CONTENT.value(),
                "Erro de validação!",
                listErros
        );
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handlerRegistroDuplicadoException(RegistroDuplicadoException e) {
        return new ErroResposta(
                HttpStatus.CONFLICT.value(),
                "Registro Duplicado: " + e.getMessage(),
                List.of()
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                "Erro: '" + e.getValue() + "' não é um valor válido para o campo '" + e.getName() + "'",
                List.of()
        );
    }


}
