package com.matera.digitalwallet;

import com.matera.digitalwallet.model.entities.Bank;
import com.matera.digitalwallet.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DigitalWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalWalletApplication.class);
	}

	@Autowired
	private BankRepository repository;
	@PostConstruct
	public void createBank(){
		Bank bank = new Bank();
		bank.setBankName("NewBank");
		repository.save(bank);
	}

}
