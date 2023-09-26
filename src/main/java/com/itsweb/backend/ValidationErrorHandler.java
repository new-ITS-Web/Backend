package com.itsweb.backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ValidationErrorHandler {
    public Map<String, String> handleError(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError error : errors) {
            errorMap.put(error.getField(), error.getDefaultMessage());
        }
        log.info("errorMap={}",errorMap);
        return errorMap;
    }

}
