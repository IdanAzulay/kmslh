package com.kms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kms.entities.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
	
	Currency findByName(String name);
	
	boolean existsByName(String name);

}
