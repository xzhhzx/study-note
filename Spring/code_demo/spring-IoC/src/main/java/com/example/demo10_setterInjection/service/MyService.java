package com.example.demo10_setterInjection.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Getter
    private String mandatoryField;
    @Getter
    private String optionalField;

    /**
     * 1. Constructor injection (Usually for injecting mandatory fields. @Autowired can be omitted)
     */
//    @Autowired
    public MyService(String mandatoryField) {
        this.mandatoryField = mandatoryField;
    }

    /**
     * 2. Setter injection (Usually for injecting optional fields. @Autowired can NOT be omitted, if you want this optional field to be injected!)
     */
    @Autowired
    public void setOptionalField(String optionalField) {
        this.optionalField = optionalField;
    }
}
