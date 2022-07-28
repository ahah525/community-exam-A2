package com.ll.exam;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseData<T> {
    private String resultCode;
    private final String msg;
    private T data;
}
