package com.credibanco.assessment.card.Dto;

import lombok.AllArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;

@AllArgsConstructor
@Valid
public class CardBankDto {
    @NotNull(message = "No debe ser nulo")
    @Size(min = 16, max = 19,message = "Debe contener entre 16 a 19 numeros")
    private String numberPan;

    @NotNull
    @Size(min = 2, max = 256)
    private String name;

    @NotNull
    @Size(min = 10, max = 15)
    private String documentNumber;


    @Min(value = 0)
    @Max(value = 1)
    private Integer cardType;

    @NotNull
    @Size(min = 9, max = 11)
    private String cellPhone;

    private Integer statusCard;

    public Integer getStatusCard() {
        return statusCard;
    }

    public void setStatusCard(Integer statusCard) {
        this.statusCard = statusCard;
    }

    public CardBankDto() {
    }

    public String getNumberPan() {
        return numberPan;
    }

    public void setNumberPan(String numberPan) {
        this.numberPan = numberPan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
}
