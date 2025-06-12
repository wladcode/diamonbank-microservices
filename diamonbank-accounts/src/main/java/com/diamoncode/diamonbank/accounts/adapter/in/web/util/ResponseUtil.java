package com.diamoncode.diamonbank.accounts.adapter.in.web.util;

import com.diamondcode.common.adapter.in.web.model.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

public class ResponseUtil {

    public static ResponseEntity<ResponseDTO> buildResponseCreate(String message, String apiCreatedUrl,  Map<String, Object> params) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.getHeader().setDate(String.valueOf(LocalDateTime.now()));
        responseDTO.getHeader().setCode(String.valueOf(HttpStatus.OK.value()));
        responseDTO.getHeader().setMessage(message);

        URI uriCreated = UriComponentsBuilder.newInstance()
                .path(apiCreatedUrl)
                .buildAndExpand(params).toUri();

        return ResponseEntity.created(uriCreated)
                .body(responseDTO);
    }


    public static ResponseEntity<ResponseDTO> buildResponseLoad(String message, Object data) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.getHeader().setDate(String.valueOf(LocalDateTime.now()));
        responseDTO.getHeader().setCode(String.valueOf(HttpStatus.OK.value()));
        responseDTO.getHeader().setMessage(message);
        responseDTO.setData(data);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDTO);
    }


}
