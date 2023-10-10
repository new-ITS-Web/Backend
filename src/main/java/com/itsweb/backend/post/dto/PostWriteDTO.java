package com.itsweb.backend.post.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteDTO{

    @NotEmpty(message = "제목은 비면 안됩니다.")
    @Size(max = 20,message = "제목은 20자 이하여야 합니다.")
    private String title;

    @NotEmpty(message = "내용은 비면 안됩니다.")
    @Size(max = 1000, message = "내용은 1000자 이하여야 합니다.")
    private String content;
}
