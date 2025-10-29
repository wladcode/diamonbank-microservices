package com.diamondcode.common.adapter.in.web.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HeaderDTO header = new HeaderDTO();
	private Object data;

    public ResponseDTO() {
        super();
    }

    public ResponseDTO(HeaderDTO header, Object data) {
        this.header = header;
        this.data = data;
    }


	

}
