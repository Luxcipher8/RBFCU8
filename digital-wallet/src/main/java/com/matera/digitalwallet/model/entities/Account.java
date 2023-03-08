package com.matera.digitalwallet.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int agency;
    private int number;
    private BigDecimal balance;
    private Holder holder;
}
