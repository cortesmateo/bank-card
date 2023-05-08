package com.credibanco.assessment.card.Service.Impl;

import com.credibanco.assessment.card.Dto.ActivateCardPayload;
import com.credibanco.assessment.card.Dto.CardBankPayload;
import com.credibanco.assessment.card.Dto.CardBankDto;
import com.credibanco.assessment.card.Exception.CardAlreadyActivateException;
import com.credibanco.assessment.card.Exception.CardBankExistException;
import com.credibanco.assessment.card.Exception.CardBankNotExists;
import com.credibanco.assessment.card.Exception.InvalidTokenException;
import com.credibanco.assessment.card.Model.CardBank;
import com.credibanco.assessment.card.Repository.CardRepository;
import com.credibanco.assessment.card.Repository.Mapper.CardBankMapper;
import com.credibanco.assessment.card.Service.CardService;
import org.springframework.stereotype.Service;


@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    @Override
    public CardBankPayload createCard(CardBankDto cardBankRequestDtoDto) {

        if(cardRepository.findByNumberPan(cardBankRequestDtoDto.getNumberPan()) != null) {
            throw new CardBankExistException("La Tarjeta ya existe en nuestra base de datos");
        }
        String maskedPan = maskPAN(cardBankRequestDtoDto.getNumberPan());
        CardBank cardBank = new CardBank();
        cardBank.setNumberPan(cardBankRequestDtoDto.getNumberPan());
        cardBank.setName(cardBankRequestDtoDto.getName());
        cardBank.setDocumentNumber(cardBankRequestDtoDto.getDocumentNumber());
        cardBank.setTokenCard(generateToken());
        cardBank.setStatusCard(0);
        cardBank.setCellPhone(cardBankRequestDtoDto.getCellPhone());
        cardBank.setTypeCard(cardBankRequestDtoDto.getCardType());
        cardRepository.save(cardBank);

        return new CardBankPayload("00","Exito",cardBank.getTokenCard(),maskedPan.toString());
    }


    @Override
    public ActivateCardPayload activateCard(String numberPan, Integer validationNumber) {
        CardBank cardBank = cardRepository.findByNumberPan(numberPan);
        if(cardBank == null){
            throw new CardBankNotExists("No se econtro la tarjeta");
        }
        if (cardBank.getStatusCard() == 1) {
            throw new CardAlreadyActivateException("La tarjeta ya se encuentra activa");
        }

        if (!validateValidationNumber(cardBank, validationNumber)) {
            throw new InvalidTokenException("Invalid validation number");
        }
        cardBank.setStatusCard(1);
        cardRepository.save(cardBank);

        String maskedPan = maskPAN(cardBank.getNumberPan());
        ActivateCardPayload activateCardPayload = new ActivateCardPayload();
        activateCardPayload.setResponseCode("00");
        activateCardPayload.setMessage("Exitoso");
        activateCardPayload.setMaskedNumberPan(maskedPan);
        return activateCardPayload;
    }


    @Override
    public ActivateCardPayload deleteCard(String numberPan, Integer validationNumber) {
        CardBank cardBank = cardRepository.findByNumberPan(numberPan);
        if(cardBank == null){
            throw new CardBankNotExists("No se econtro la tarjeta");
        }

        if (!validateValidationNumber(cardBank, validationNumber)) {
            throw new InvalidTokenException("Invalid validation number");
        }

        cardRepository.delete(cardBank);

        ActivateCardPayload activateCardPayload = new ActivateCardPayload();
        activateCardPayload.setResponseCode("00");
        activateCardPayload.setMessage("Exitoso se ha eliminado");
        return activateCardPayload;
    }

    @Override
    public CardBank findByNumberPan(String numberPan) {
        return cardRepository.findByNumberPan(numberPan);
    }

    private int generateToken() {
        return (int) (Math.random() * 100) + 1;
    }
    private String maskPAN(String pan) {
        return pan.substring(0, 6) + "****" + pan.substring(pan.length() - 4);
    }

    private boolean validateValidationNumber(CardBank cardBank, Integer validationNumber) {
        Integer cardValidationNumber = cardBank.getTokenCard();
        return validationNumber.equals(cardValidationNumber);
    }
}
