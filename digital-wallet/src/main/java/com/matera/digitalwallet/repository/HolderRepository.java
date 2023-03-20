package com.matera.digitalwallet.repository;

import com.matera.digitalwallet.model.entities.Holder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolderRepository extends JpaRepository<Holder, Long> {
    //List<Account> findByHolderId(Long id);
}
