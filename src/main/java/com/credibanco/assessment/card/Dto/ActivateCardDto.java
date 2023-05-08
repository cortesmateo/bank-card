package com.credibanco.assessment.card.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ActivateCardDto {

    @NotNull
    private String numberPan;

    @NotNull
    private Integer validationNumber;

}
