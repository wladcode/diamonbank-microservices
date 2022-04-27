package com.diamoncode.diamonbank.cards.repository;

import com.diamoncode.diamonbank.cards.model.JpaEntityCards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends CrudRepository<JpaEntityCards, Long> {


    List<JpaEntityCards> findByCustomerId(long customerId);

}