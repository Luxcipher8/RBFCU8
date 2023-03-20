package com.matera.digitalwallet.repository;

import com.matera.digitalwallet.model.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Optional<Bank> findByBankCode(int bankCode);
}
