package com.matera.digitalwallet.model.entities;

import com.matera.digitalwallet.model.dto.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int agency;
    private int number = new Random().nextInt(100000);
    private BigDecimal balance = BigDecimal.ZERO;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime updateTime;
    @ManyToOne
    @JoinColumn(name = "name_holder_id")
    private Holder holder;
    @ManyToMany
    @JoinTable(name = "account_fares_type",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "fares_types_id")
    )
    private List<FareType> faresTypes = new ArrayList<>();

    public void credit(BigDecimal value) {
        this.validate(value);
        balance.add(value);
    }

    public void debit(BigDecimal value) {
        this.validate(value);

        if (value.compareTo(balance) > 0) {
            throw new RuntimeException();
        }

        balance.subtract(value);
    }

    private void validate(BigDecimal value) {

        if (value == null) {
            throw new RuntimeException();
        }

        if (this.incorrectValue(value)) {
            throw new RuntimeException();
        }
    }

    private boolean incorrectValue(BigDecimal value) {
        return value.compareTo(BigDecimal.ZERO) <= 0;
    }

    public AccountDto toAccountDto() {
        AccountDto dto = new AccountDto();
        dto.setAgency(this.getAgency());
        dto.setNumber(this.getNumber());
        dto.setBalance(this.getBalance());
        return dto;
    }
}
