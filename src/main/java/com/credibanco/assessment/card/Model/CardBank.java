package com.credibanco.assessment.card.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "CARD_BANK")
public class CardBank {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_seq")
    @SequenceGenerator(name = "card_seq", sequenceName = "CARD_BANK_SEQ", allocationSize = 1)
    @Column(name = "ID_CARD")
    private Long id;

    @Column(name = "NUMBER_PAN")
    private String numberPan;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;

    @Column(name = "TYPE_CARD")
    private Integer typeCard;

    @Column(name = "CELL_PHONE")
    private String cellPhone;

    @Column(name = "STATUS_CARD")
    private Integer statusCard;

    @Column(name = "TOKEN_CARD")
    private Integer tokenCard;

}
