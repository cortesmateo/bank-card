package com.credibanco.assessment.card.Controller;

import com.credibanco.assessment.card.Dto.*;
import com.credibanco.assessment.card.Exception.*;
import com.credibanco.assessment.card.Model.CardBank;
import com.credibanco.assessment.card.Service.CardOperationService;
import com.credibanco.assessment.card.Service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CardBankController {

    private CardService cardService;
    private CardOperationService cardOperationService;

    public CardBankController(CardService cardService, CardOperationService cardOperationService) {
        this.cardService = cardService;
        this.cardOperationService = cardOperationService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<CardBankPayload> createCard(@Valid @RequestBody CardBankDto cardBankRequestDto) {
        CardBank exists = cardService.findByNumberPan(cardBankRequestDto.getNumberPan());
        if(exists != null){
            throw new CardBankExistException("Ya existe la tarjeta, verifique los datos del PAN");
        }
        CardBankPayload response = cardService.createCard(cardBankRequestDto);
        HttpStatus status = response.getResponseCode().equals("00") ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/createOperation")
    public ResponseEntity<CardOperationsPayload> createOperation(@RequestBody CardOperationsDto cardOperationsDto) {
        CardOperationsPayload response = cardOperationService.createOperation(cardOperationsDto);
        HttpStatus status = response.getResponseCode().equals("00") ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }



    @PutMapping("/activate")
    public ResponseEntity<ActivateCardPayload> enrollCard(@RequestBody ActivateCardDto activateCardDto) {
        try{
            ActivateCardPayload cardBank = cardService.activateCard(activateCardDto.getNumberPan(), activateCardDto.getValidationNumber());
            HttpStatus status = cardBank.getResponseCode().equals("00") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(cardBank, status);
        }catch (CardBankException e){
            ActivateCardPayload activateCardPayload = new ActivateCardPayload("01","No se econtro la tarjeta",null);
            return new ResponseEntity<>(activateCardPayload,HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (CardAlreadyActivateException e){
            ActivateCardPayload activateCardPayload = new ActivateCardPayload("02","La tarjeta ya se encuentra Activa",null);
            return new ResponseEntity<>(activateCardPayload,HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (InvalidTokenException e){
            ActivateCardPayload activateCardPayload = new ActivateCardPayload("03","No coincide el token",null);
            return new ResponseEntity<>(activateCardPayload,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/card/{numberPan}")
    public ResponseEntity<CardBankDto> getCardBank(@PathVariable String numberPan){
        CardBank exists = cardService.findByNumberPan(numberPan);
        String maskedPan = maskPAN(exists.getNumberPan());
        CardBankDto cardBankDto = new CardBankDto();
        cardBankDto.setNumberPan(maskedPan);
        cardBankDto.setName(exists.getName());
        cardBankDto.setCardType(exists.getTypeCard());
        cardBankDto.setDocumentNumber(exists.getDocumentNumber());
        cardBankDto.setCellPhone(exists.getCellPhone());
        cardBankDto.setStatusCard(exists.getStatusCard());
        return new ResponseEntity<>(cardBankDto,HttpStatus.OK);
    }


    @DeleteMapping("/deletecard")
    public ResponseEntity<ActivateCardPayload> deleteCard(@RequestBody ActivateCardDto activateCardDto) {
        try {
            cardOperationService.deleteCard(activateCardDto.getNumberPan());
            ActivateCardPayload cardBank = cardService.deleteCard(activateCardDto.getNumberPan(), activateCardDto.getValidationNumber());
            HttpStatus status = cardBank.getResponseCode().equals("00") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(cardBank, status);
        } catch (CardBankNotExists e) {
            ActivateCardPayload activateCardPayload = new ActivateCardPayload("01", "No se encontró la tarjeta", null);
            return new ResponseEntity<>(activateCardPayload, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (InvalidTokenException e) {
            ActivateCardPayload activateCardPayload = new ActivateCardPayload("02", "No coincide el token", null);
            return new ResponseEntity<>(activateCardPayload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    private String maskPAN(String pan) {
        return pan.substring(0, 6) + "****" + pan.substring(pan.length() - 4);
    }
}
