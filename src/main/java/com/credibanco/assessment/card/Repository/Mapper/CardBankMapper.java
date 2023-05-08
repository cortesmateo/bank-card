package com.credibanco.assessment.card.Repository.Mapper;

import com.credibanco.assessment.card.Dto.CardBankDto;
import com.credibanco.assessment.card.Model.CardBank;

public class CardBankMapper {

    public static CardBankDto toCardBankDto(CardBank cardBank){
        return new CardBankDto(cardBank.getNumberPan(), cardBank.getName(), cardBank.getDocumentNumber(), cardBank.getTypeCard(), cardBank.getCellPhone(), cardBank.getStatusCard());
    }
}
