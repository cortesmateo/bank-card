package com.credibanco.assessment.card.Exception;

public class CardBankNotExists extends RuntimeException{
    public CardBankNotExists(String message) {
        super(message);
    }
}
