package com.testePratico.agrotis.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class AgrotisExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String CONSTANT_VALIDATION_NOT_BLANK = "NotBlank";
    private static final String CONSTANT_VALIDATION_NOT_NULL = "NotNull";
    private static final String CONSTANT_VALIDATION_LENGTH = "Length";
    private static final String CONSTANT_VALIDATION_MIN = "Min";

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Object> handleRegraNegocioException(RegraNegocioException ex, WebRequest request){
        String msgError = ex.getMessage();
        return handleExceptionInternal(ex, msgError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> objectNotFound(NotFoundException e, HttpServletRequest request){
        String msgError = e.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msgError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<MenssagensErro> menssagensErros = getListErrors(ex.getBindingResult());
        return super.handleExceptionInternal(ex, menssagensErros, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<MenssagensErro> getListErrors(BindingResult bindingResult){
        List<MenssagensErro> menssagensErros = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String msgErro = MsgErrorUser(fieldError);
            menssagensErros.add(new MenssagensErro(msgErro));
        });
        return menssagensErros;
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
        String msgErro = ex.getMessage() + " não encontrado(a)";
        List<MenssagensErro> menssagensErros = Arrays.asList(new MenssagensErro(msgErro));
        return handleExceptionInternal(ex, menssagensErros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
        String msgErro = "Recurso não encontrado";
        List<MenssagensErro> menssagensErros = Arrays.asList(new MenssagensErro(msgErro));
        return handleExceptionInternal(ex, menssagensErros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private String MsgErrorUser(FieldError fieldError){
        if(fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_BLANK)){
            return fieldError.getDefaultMessage().concat(" é obrigatório");
        }

        if(fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_NULL)){
            return fieldError.getDefaultMessage().concat(" é obrigatório");
        }

        if(fieldError.getCode().equals(CONSTANT_VALIDATION_LENGTH)){
            return fieldError.getDefaultMessage().concat(String.format(" deve ter entre %s e %s caracteres",
                fieldError.getArguments()[2], fieldError.getArguments()[1]));
        }

        if(fieldError.getCode().equals(CONSTANT_VALIDATION_MIN)){
            return fieldError.getDefaultMessage().concat(String.format(" deve ser maior que %s",
                fieldError.getArguments()[1]));
        }
        return fieldError.getField();
    }
}