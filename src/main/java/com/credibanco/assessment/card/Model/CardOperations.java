package com.credibanco.assessment.card.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "CARD_TRANSACTIONS")
public class CardOperations {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_seq_tran")
    @SequenceGenerator(name = "card_seq_tran", sequenceName = "CARD_TRANSACTIONS_SEQ", allocationSize = 1)
    @Column(name = "ID_OPERATION")
    private Long id;

    @Column(name = "NUMBER_PAN")
    private String numberPan;

    @Column(name = "REFERENCE_NUMBER")
    private Integer referenceNumber;

    @Column(name = "TOTAL_AMOUNT")
    private Integer totalAmount;

    @Column(name = "ADRESS_OPERATION")
    private String adressOperation;

    @Column(name = "STATUS_OPERATION")
    private Integer statusOperation;

    @Column(name = "DATE_OPERATION")
    private String dateOperation;

}
