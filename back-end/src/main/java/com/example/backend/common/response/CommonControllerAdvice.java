package com.example.backend.common.response;


import com.example.backend.common.exception.UploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@ControllerAdvice
public class CommonControllerAdvice {

    @ResponseBody
    @ExceptionHandler(UploadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse handleUploadException(UploadException e) {
        return CommonResponse.fail(e.getMessage(),  e.getErrorCode().name());
    }

}