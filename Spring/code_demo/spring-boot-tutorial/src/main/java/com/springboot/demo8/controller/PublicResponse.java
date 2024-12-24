package com.springboot.demo8.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PublicResponse<T> {
    private String code;
    private String desc;
    private T obj;
}
