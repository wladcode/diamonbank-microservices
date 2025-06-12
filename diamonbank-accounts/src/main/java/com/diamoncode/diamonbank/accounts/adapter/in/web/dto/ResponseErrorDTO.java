package com.diamoncode.diamonbank.accounts.adapter.in.web.dto;

import com.diamondcode.common.adapter.in.web.model.ResponseDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ResponseErrorDTO extends ResponseDTO {
    private Map<String, String> errors  ;

    public ResponseErrorDTO(int code, String message) {
        super();
        this.getHeader().setCode(String.valueOf(code));
        this.getHeader().setMessage(message);
        this.getHeader().setDate(LocalDateTime.now().toString());

    }

    public ResponseErrorDTO(int code, String message, Map<String, String> errors) {
        super();
        this.getHeader().setCode(String.valueOf(code));
        this.getHeader().setMessage(message);
        this.getHeader().setDate(LocalDateTime.now().toString());
        this.setErrors(errors);
    }
}
