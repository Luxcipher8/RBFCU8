package com.matera.digitalwallet.repository;

import com.matera.digitalwallet.model.entities.FareType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FareTypeRepository extends JpaRepository<FareType, Long> {

    FareType name(String name);
    FareType findByName(String name);

    FareType findByNameContaining(String name);

}
