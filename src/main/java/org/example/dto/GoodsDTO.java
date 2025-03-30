package org.example.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@FieldDefaults(makeFinal = false, level= AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDTO {
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max = 150)
    private String name;

    @NotNull
    private Long goodsTypeId;

    @Builder.Default
    private Double cost = 0d;

    @Builder.Default
    private Integer count = 0;

    @Builder.Default
    private Boolean left = true;

    private String description;
}
