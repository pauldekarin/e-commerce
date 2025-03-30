package org.example.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
public class GoodsTypeDTO {
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 150)
    private String name;
}
