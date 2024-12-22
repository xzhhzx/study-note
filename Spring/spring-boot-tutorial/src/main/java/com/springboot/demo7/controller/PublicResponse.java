package com.springboot.demo7.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PublicResponse<T> {
    private String code;
    private String desc;
    private T obj;
}
