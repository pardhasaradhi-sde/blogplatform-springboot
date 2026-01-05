package com.example.blog.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequestDto {
    @NotBlank(message = "category name is required")
    @Size(min = 2 , max = 20,message = "category name must be within {min} and {max} characters")
    private String name;

}
