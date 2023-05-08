package com.credibanco.assessment.card.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class CardOperationsDto {

    @NotNull
    private String numberPan;

    @NotNull
    private Integer numberReference;

    @NotNull
    private Integer totalAmount;

    @NotNull
    private String adressOperation;


}
