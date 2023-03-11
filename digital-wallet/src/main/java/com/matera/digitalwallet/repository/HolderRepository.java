package com.matera.digitalwallet.repository;

import com.matera.digitalwallet.model.entities.Account;
import com.matera.digitalwallet.model.entities.Holder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolderRepository extends JpaRepository<Holder, Long> {
    //List<Account> findByHolderId(Long id);
}
