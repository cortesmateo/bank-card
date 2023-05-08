package com.credibanco.assessment.card.Service;

import com.credibanco.assessment.card.Dto.ActivateCardPayload;
import com.credibanco.assessment.card.Dto.CardBankPayload;
import com.credibanco.assessment.card.Dto.CardBankDto;
import com.credibanco.assessment.card.Model.CardBank;

public interface CardService {

    CardBankPayload createCard(CardBankDto cardBankRequestDtoDto);

    CardBank findByNumberPan(String numberPan);

    ActivateCardPayload activateCard(String numberPan, Integer validationNumber);

    ActivateCardPayload deleteCard(String numberPan, Integer validationNumber);

}
