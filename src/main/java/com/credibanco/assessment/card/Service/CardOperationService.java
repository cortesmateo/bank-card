package com.credibanco.assessment.card.Service;

import com.credibanco.assessment.card.Dto.CardOperationsDto;
import com.credibanco.assessment.card.Dto.CardOperationsPayload;
import com.credibanco.assessment.card.Model.CardBank;
import com.credibanco.assessment.card.Model.CardOperations;

import java.util.List;

public interface CardOperationService {

    CardOperationsPayload createOperation(CardOperationsDto cardOperationsDto);
    List<CardOperations> findByNumberPan(String numberPan);

    void deleteCard(String numberPan);

}
