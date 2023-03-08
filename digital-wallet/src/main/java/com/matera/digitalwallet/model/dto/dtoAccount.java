package com.matera.digitalwallet.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class dtoAccount {
    private int agency;
    private int number;
    private BigDecimal balance;
}
