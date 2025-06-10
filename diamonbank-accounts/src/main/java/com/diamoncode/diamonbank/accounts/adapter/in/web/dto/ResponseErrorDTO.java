package com.diamoncode.diamonbank.accounts.adapter.in.web.dto;

import com.diamondcode.common.adapter.in.web.model.ResponseDTO;

import java.time.LocalDateTime;

public class ResponseErrorDTO extends ResponseDTO {

    public ResponseErrorDTO(int code, String message) {
        super();
        this.getHeader().setCode(String.valueOf(code));
        this.getHeader().setMessage(message);
        this.getHeader().setDate(LocalDateTime.now().toString());

    }

}
