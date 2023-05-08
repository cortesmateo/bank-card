package com.credibanco.assessment.card.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActivateCardPayload {
    private String responseCode;

    private String message;

    private String maskedNumberPan;
}
