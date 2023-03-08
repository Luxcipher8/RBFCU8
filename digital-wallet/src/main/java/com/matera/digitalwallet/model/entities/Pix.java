package com.matera.digitalwallet.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pix {
    private Account destinationAccount;
    private Account sourceAccount;
    private LocalDateTime transactionDate;
    private BigDecimal value;
}
