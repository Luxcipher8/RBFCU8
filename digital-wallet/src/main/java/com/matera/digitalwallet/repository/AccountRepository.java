package com.matera.digitalwallet.repository;

import com.matera.digitalwallet.model.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAgencyAndNumber(int agency, int number);

    Optional<Account> findByPix(String pixKey);
}
