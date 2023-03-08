package com.matera.digitalwallet.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAccountDto {
    private int agency;
    private String name;
    private String cpf;
}
