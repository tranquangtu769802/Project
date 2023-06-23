//package com.vti.Validate;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.validation.ConstraintViolationException;
//import java.util.HashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        Map<String, String> map = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach(objectError -> {
//            String field = ((FieldError) objectError).getField();
//            String msg = objectError.getDefaultMessage();
//            map.put(field, msg);
//        });
//        return new ResponseEntity<>(map, status);
//    }
//
//
//    @ExceptionHandler({ConstraintViolationException.class})
//    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception){
//        Map<String,String> map = new HashMap<>();
//        exception.getConstraintViolations().forEach(constraintViolation -> {
//            String field = constraintViolation.getPropertyPath().toString();
//            String msg = constraintViolation.getMessage();
//            map.put(field, msg);
//        });
//        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
//    }
//}
