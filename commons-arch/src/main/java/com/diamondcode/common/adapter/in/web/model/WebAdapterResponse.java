package com.diamondcode.common.adapter.in.web.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;
import java.time.LocalDateTime;

@PropertySource("classpath:messages.properties")
public abstract class WebAdapterResponse implements Serializable {
    protected final Log logger = LogFactory.getLog(this.getClass());

    /**
     *
     */
    private static final long serialVersionUID = 6291199890350305400L;

    @Value("${wlhomepay.response.header.code.success}")
    private String responseSucessCode;

    @Value("${wlhomepay.response.header.code.empty.data}")
    private String responseCodeEmptyData;

    @Value("${wlhomepay.response.header.code.general}")
    private String responseCodeGeneral;

    @Value("${wlhomepay.response.header.code.sql}")
    private String responseSQLCode;

    @Value("${wlhomepay.response.header.message.success}")
    private String reponseSucessMessage;

    @Value("${wlhomepay.response.header.message.error}")
    private String responseErrorMessage;

    private final HeaderDTO getSuccessHeader() {
        HeaderDTO header = new HeaderDTO();
        header.setCode(responseSucessCode);
        header.setMessage(reponseSucessMessage);
        header.setDate(LocalDateTime.now().toString());

        return header;
    }

    private final HeaderDTO getSuccessHeader(String message) {
        HeaderDTO header = new HeaderDTO();
        header.setCode(responseSucessCode);
        header.setMessage(message);
        header.setDate(LocalDateTime.now().toString());

        return header;
    }

    private final HeaderDTO getHeaderEmptyData() {
        HeaderDTO header = new HeaderDTO();
        header.setCode(responseCodeEmptyData);
        header.setMessage(responseErrorMessage);
        header.setDate(LocalDateTime.now().toString());

        return header;
    }

    private final HeaderDTO getHeaderCustomeError(String message) {
        HeaderDTO header = new HeaderDTO();
        header.setCode(responseCodeGeneral);
        header.setMessage(message);
        header.setDate(LocalDateTime.now().toString());

        return header;
    }

    private final HeaderDTO getErrorHeader(String code, String message) {
        HeaderDTO header = new HeaderDTO();
        header.setCode(code);
        header.setMessage(message);
        header.setDate(LocalDateTime.now().toString());

        return header;
    }

    public final ResponseDTO getSuccessResponse(String message, Object data) {

        ResponseDTO response = new ResponseDTO();
        response.setHeader(getSuccessHeader(message));
        response.setData(data);

        return response;

    }

    public final ResponseDTO getSuccessResponse(String message) {

        ResponseDTO response = new ResponseDTO();
        response.setHeader(getSuccessHeader(message));
        return response;
    }

    public final ResponseDTO getErrorResponse() {

        ResponseDTO response = new ResponseDTO();
        response.setHeader(getHeaderEmptyData());
        response.setData(null);

        return response;

    }

    public final ResponseDTO getErrorResponse(String message) {

        ResponseDTO response = new ResponseDTO();
        response.setHeader(getHeaderCustomeError(message));
        response.setData(null);

        return response;

    }


    public final ResponseDTO getResponse(String message, Object data) {

        if (data == null) {
            return getErrorResponse();
        }

        return getSuccessResponse(message, data);

    }

//	private String getSQLMessage(JDBCException e) {
//
//		return new StringBuilder()
//				.append(e.getMessage()).append(" - ")
//				.append(e.getSQLState()).append(" - ")
//				.append(e.getErrorCode()).toString();
//
//		Throwable cause = e.getCause().getCause();
//
//		if (cause instanceof SQLIntegrityConstraintViolationException) {
//			return ((SQLIntegrityConstraintViolationException) cause).getMessage();
//		} else if (cause instanceof MysqlDataTruncation) {
//			return ((MysqlDataTruncation) cause).getMessage();
//		} else if(cause instanceof SQLSyntaxErrorException) {
//			return ((SQLSyntaxErrorException) cause).getMessage();
//		}
//		else {
//			return "Error Generico no identificado";
//		}

//	}

	/*
	public final ResponseDTO getErrorResponseSQL(JDBCException e) {

		ResponseDTO response = new ResponseDTO();
		response.setHeader(getErrorHeader(responseSQLCode, getSQLMessage(e)));
		response.setData(null);

		return response;

	}

	 */

}
