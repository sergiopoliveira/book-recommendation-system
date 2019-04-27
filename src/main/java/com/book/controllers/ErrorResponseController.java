package com.book.controllers;

import com.book.exceptions.ErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorResponseController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<ErrorResponse> handleError() {
        //create CustomerErrorResponse
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "404 Not Found",
                "Request URL not found",
                System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}