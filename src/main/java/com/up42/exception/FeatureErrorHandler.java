package com.up42.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * @author jalajchawla
 */
@RestControllerAdvice
@Slf4j
public class FeatureErrorHandler {

    @ExceptionHandler(FeatureNotFoundException.class)
    public  ResponseEntity<ErrorDetails> handleFeatureNotFound(FeatureNotFoundException tnf ){
        log.info("FeatureErrorHandler.handleFeatureNotFound()");
        ErrorDetails details=new ErrorDetails(LocalDateTime.now(),tnf.getMessage(),"404- Feature Not Found");
        return new ResponseEntity<ErrorDetails>(details,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ErrorDetails> handleAllProblems(Exception e){
        log.info("FeatureErrorHandler.handleAllProblems()");
        ErrorDetails details=new ErrorDetails(LocalDateTime.now(),e.getMessage(),"Problem in execution");
        return new ResponseEntity<ErrorDetails>(details,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
