package com.david.MyBooksApi.util;

import com.david.MyBooksApi.dto.response.Response;

import static com.david.MyBooksApi.util.ResponseCode.*;
import static com.david.MyBooksApi.util.ResponseMessage.*;

public class ResponseFormatter {
    public ResponseFormatter() {
    }
    public static <T> Response<T> getResponse(T model, String respCode){

        Response<T> response = new Response<T>();
        response.setData((T) model);
        if(respCode.equals(COMPLETED_SUCCESSFULLY)) {
            response.setResponseCode(COMPLETED_SUCCESSFULLY);
            response.setResponseMessage(COMPLETED_SUCCESSFULLY_MESSAGE);
        }else if(respCode.equals(NO_CONTENT)){
            response.setResponseCode(NO_CONTENT);
            response.setResponseMessage(NO_CONTENT_MESSAGE);
        }else if(respCode.equals(NOT_FOUND)) {
            response.setResponseCode(NOT_FOUND);
            response.setResponseMessage(NOT_FOUND_MESSAGE);
        }
        return response;
    }

}
