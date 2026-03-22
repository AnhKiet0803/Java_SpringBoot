package com.example.T2507e.dto.common;

import com.example.T2507e.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO <T>{
    private String code;
    private String messenge;
    private T data;

    public ResponseDTO(StatusCode statusCode, T data){
        this.code = statusCode.getCode();
        this.messenge = statusCode.getMessenge();
        this.data = data;
    }
}
