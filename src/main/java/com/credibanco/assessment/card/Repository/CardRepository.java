package com.credibanco.assessment.card.Repository;

import com.credibanco.assessment.card.Model.CardBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardBank, Long> {

    CardBank findByNumberPan(String numberPAN);
}
