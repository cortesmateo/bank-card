package com.credibanco.assessment.card.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CardOperationsPayload {

    private String responseCode;

    private String message;

    private String status;

    private Integer numberReference;



}
