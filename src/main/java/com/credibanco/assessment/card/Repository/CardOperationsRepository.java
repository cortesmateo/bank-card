package com.credibanco.assessment.card.Repository;

import com.credibanco.assessment.card.Model.CardOperations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardOperationsRepository extends JpaRepository<CardOperations,Long> {
    void deleteAllByNumberPan(String numberPan);
}
