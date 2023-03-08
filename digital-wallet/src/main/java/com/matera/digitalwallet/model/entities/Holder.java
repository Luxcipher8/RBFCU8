package com.matera.digitalwallet.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Holder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    @OneToMany(mappedBy = "holder")
    private List<Account> accounts = new ArrayList<>();
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Address address;
}
