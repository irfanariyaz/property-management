package com.mycompany.propertymanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<List<ErrorModel>>  handleFieldValidation(MethodArgumentNotValidException manv){
        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel = null;
        List<FieldError> fieldErrorList = manv.getBindingResult().getFieldErrors();

        for(FieldError fe : fieldErrorList){
            logger.debug("Inside field validation: {} -{}",fe.getField(),fe.getDefaultMessage());
            logger.info("Inside field validation: {} -{}",fe.getField(),fe.getDefaultMessage());
                    errorModel = new ErrorModel();
            errorModel.setMessage(fe.getDefaultMessage());
            errorModel.setCode(fe.getCode());
            errorModelList.add(errorModel);
        }
       return new ResponseEntity<List<ErrorModel>>(errorModelList, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>>  handleBusinessException(BusinessException bex){
        for(ErrorModel em :bex.getErrors()){
            logger.debug("Inside field validation: {} -{}",em.getCode(),em.getMessage());
            logger.info("Inside field validation: {} -{}",em.getCode(),em.getMessage());

        }
         return new ResponseEntity<List<ErrorModel>>(bex.getErrors(), HttpStatus.BAD_REQUEST);

    }
}
