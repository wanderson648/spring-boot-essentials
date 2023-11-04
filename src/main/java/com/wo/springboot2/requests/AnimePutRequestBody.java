package com.wo.springboot2.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimePutRequestBody {
    private Long id;
    @NotBlank(message = "The anime cannot be empty or null")
    private String name;
}
