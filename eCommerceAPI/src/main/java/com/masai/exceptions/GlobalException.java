package com.masai.exceptions;


import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(AdminException.class)
    public ResponseEntity<MyError> adminExceptionHandler(AdminException adminException, WebRequest webRequest){

        MyError myError=new MyError();
        myError.setLocalDate(LocalDate.now());
        myError.setMessage(adminException.getMessage());
        myError.setDescription(webRequest.getDescription(false));

        return new ResponseEntity<>(myError, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<MyError> categoryExceptionHandler(CategoryException categoryException, WebRequest webRequest){

        MyError myError=new MyError();
        myError.setLocalDate(LocalDate.now());
        myError.setMessage(categoryException.getMessage());
        myError.setDescription(webRequest.getDescription(false));

        return new ResponseEntity<>(myError, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<MyError> productExceptionHandler(ProductException productException, WebRequest webRequest){

        MyError myError=new MyError();
        myError.setLocalDate(LocalDate.now());
        myError.setMessage(productException.getMessage());
        myError.setDescription(webRequest.getDescription(false));

        return new ResponseEntity<>(myError, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<MyError> userExceptionHandler(UserException userException, WebRequest webRequest){

        MyError myError=new MyError();
        myError.setLocalDate(LocalDate.now());
        myError.setMessage(userException.getMessage());
        myError.setDescription(webRequest.getDescription(false));

        return new ResponseEntity<>(myError, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(ImageException.class)
    public ResponseEntity<MyError>imageException(ImageException imageException,WebRequest webRequest){

        MyError myError=new MyError();
        myError.setLocalDate(LocalDate.now());
        myError.setMessage(imageException.getMessage());
        myError.setDescription(webRequest.getDescription(false));

        return new ResponseEntity<>(myError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MyError> runtimeExceptionHandler(RuntimeException runtimeException, WebRequest webRequest){

        MyError myError=new MyError();
        myError.setLocalDate(LocalDate.now());
        myError.setMessage(runtimeException.getMessage());
        myError.setDescription(webRequest.getDescription(false));

        return new ResponseEntity<>(myError, HttpStatus.BAD_REQUEST);

    }



    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<MyError> notFoundExceptionHandler(ChangeSetPersister.NotFoundException notFoundException, WebRequest webRequest){

        MyError myError=new MyError();
        myError.setLocalDate(LocalDate.now());
        myError.setMessage(notFoundException.getMessage());
        myError.setDescription(webRequest.getDescription(false));

        return new ResponseEntity<>(myError, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException, WebRequest webRequest){

        MyError myError=new MyError();
        myError.setLocalDate(LocalDate.now());
        myError.setMessage(methodArgumentNotValidException.getMessage());
        myError.setDescription(webRequest.getDescription(false));

        return new ResponseEntity<>(myError, HttpStatus.BAD_REQUEST);

    }



}
