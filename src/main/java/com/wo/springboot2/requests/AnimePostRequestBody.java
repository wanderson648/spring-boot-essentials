package com.wo.springboot2.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimePostRequestBody {
    @NotBlank(message = "The anime cannot be empty or null")
    private String name;
}
