package com.itsweb.backend.post;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

@Service
public class PostValidationService {

    public String getValidationErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError error : errors) {
                errorMessage.append(error.getDefaultMessage()).append(" ");
            }
            return errorMessage.toString().trim();
        }
        return null; // 유효성 검사 오류가 없는 경우 null 반환
    }
}