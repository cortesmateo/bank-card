package com.credibanco.assessment.card.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CardBankExistException extends RuntimeException{
    public CardBankExistException(String message) {
        super(message);
    }
}
