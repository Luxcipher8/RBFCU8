package com.matera.digitalwallet.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Random;

@Entity
@Getter
@Setter
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private int bankCode = new Random().nextInt(1000);
    private String bankName;
}
