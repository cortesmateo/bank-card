package com.credibanco.assessment.card.Service.Impl;

import com.credibanco.assessment.card.Dto.CardOperationsDto;
import com.credibanco.assessment.card.Dto.CardOperationsPayload;
import com.credibanco.assessment.card.Exception.CardAlreadyActivateException;
import com.credibanco.assessment.card.Exception.CardBankNotExists;
import com.credibanco.assessment.card.Model.CardBank;
import com.credibanco.assessment.card.Model.CardOperations;
import com.credibanco.assessment.card.Repository.CardOperationsRepository;
import com.credibanco.assessment.card.Repository.CardRepository;
import com.credibanco.assessment.card.Service.CardOperationService;
import oracle.sql.DATE;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CardOperationServiceImpl implements CardOperationService {

 private CardOperationsRepository cardOperationsRepository;
 private CardRepository cardRepository;

    public CardOperationServiceImpl(CardOperationsRepository cardOperationsRepository, CardRepository cardRepository) {
        this.cardOperationsRepository = cardOperationsRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public CardOperationsPayload createOperation(CardOperationsDto cardOperationsDto) {
        CardBank cardBank = cardRepository.findByNumberPan(cardOperationsDto.getNumberPan());
        if(cardBank == null){
            return new CardOperationsPayload("02","fallida","No existe la tarjeta", null);
        }
        if (cardBank.getStatusCard() == 0) {
            CardOperations cardOperations = new CardOperations();
            cardOperations.setNumberPan(cardOperationsDto.getNumberPan());
            cardOperations.setReferenceNumber(cardOperationsDto.getNumberReference());
            cardOperations.setTotalAmount(cardOperationsDto.getTotalAmount());
            cardOperations.setAdressOperation(cardOperationsDto.getAdressOperation());
            cardOperations.setStatusOperation(0);
            cardOperations.setDateOperation("10-10-2023");
            cardOperationsRepository.save(cardOperations);
            return new CardOperationsPayload("03","FALLIDA,la tarjeta no esta activa","DENEGADA", cardOperations.getReferenceNumber());
        }
        CardOperations cardOperations = new CardOperations();
        cardOperations.setNumberPan(cardOperationsDto.getNumberPan());
        cardOperations.setReferenceNumber(cardOperationsDto.getNumberReference());
        cardOperations.setTotalAmount(cardOperationsDto.getTotalAmount());
        cardOperations.setAdressOperation(cardOperationsDto.getAdressOperation());
        cardOperations.setStatusOperation(1);
        cardOperations.setDateOperation("10-10-2023");
        cardOperationsRepository.save(cardOperations);
        return new CardOperationsPayload("00","Exito","Aprobada", cardOperations.getReferenceNumber());
    }

    @Override
    public List<CardOperations> findByNumberPan(String numberPan) {
       return cardOperationsRepository.findBynumberPan(numberPan);
    }

    @Override
    @Transactional
    public void deleteCard(String numberPan) {
        cardOperationsRepository.deleteAllByNumberPan(numberPan);
    }



}
