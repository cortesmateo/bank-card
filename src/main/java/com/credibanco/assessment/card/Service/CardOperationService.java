package com.credibanco.assessment.card.Service;

import com.credibanco.assessment.card.Dto.CardOperationsDto;
import com.credibanco.assessment.card.Dto.CardOperationsPayload;

public interface CardOperationService {

    CardOperationsPayload createOperation(CardOperationsDto cardOperationsDto);

    void deleteCard(String numberPan);

}
