package com.example.backend.common.exception;

import com.example.backend.common.response.ErrorCode;

public class UploadException extends BaseException {

    public UploadException(String message) {
        super(message, ErrorCode.COMMON_SYSTEM_ERROR);
    }
}
