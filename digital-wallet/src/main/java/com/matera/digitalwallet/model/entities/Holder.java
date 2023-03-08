package com.matera.digitalwallet.model.entities;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Holder {
    private String name;
    private String cpfOrCnpj;
    private List<Account> accounts;
    private String password;
}
