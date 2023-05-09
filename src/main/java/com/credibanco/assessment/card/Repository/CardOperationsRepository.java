package com.credibanco.assessment.card.Repository;

import com.credibanco.assessment.card.Model.CardOperations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardOperationsRepository extends JpaRepository<CardOperations,Long> {
    List<CardOperations> findBynumberPan(String numberPan);
    void deleteAllByNumberPan(String numberPan);
}
