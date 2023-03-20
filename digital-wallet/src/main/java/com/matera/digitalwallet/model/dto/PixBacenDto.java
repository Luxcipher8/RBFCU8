package com.matera.digitalwallet.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PixBacenDto {
    private int agency;
    private String cpf;
    private int number;
    private String key;

    @Override
    public String toString() {
        return "PixBacenDto{" +
                "agency=" + agency +
                ", cpf='" + cpf + '\'' +
                ", number=" + number +
                ", key='" + key + '\'' +
                '}';
    }
}
