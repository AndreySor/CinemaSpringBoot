package com.school21.cinemaspringboot.model.response;

import lombok.Data;

import java.util.List;

@Data
public class BaseResponse {

    private List<SessionsResponse> sessions;

    public BaseResponse() {
    }

    public BaseResponse(List<SessionsResponse> sessions) {
        this.sessions = sessions;
    }
}
