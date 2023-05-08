package com.credibanco.assessment.card.Dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CardBankPayload {

    private String responseCode;

    private String message;

    private Integer validationNumber;

    private String maskedNumberPan;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getValidationNumber() {
        return validationNumber;
    }

    public void setValidationNumber(Integer validationNumber) {
        this.validationNumber = validationNumber;
    }

    public String getMaskedNumberPan() {
        return maskedNumberPan;
    }

    public void setMaskedNumberPan(String maskedNumberPan) {
        this.maskedNumberPan = maskedNumberPan;
    }
}
