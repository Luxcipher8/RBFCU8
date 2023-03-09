package com.matera.digitalwallet.service;

import com.matera.digitalwallet.exceptions.ExistingAccountException;
import com.matera.digitalwallet.exceptions.InvalidAccountException;
import com.matera.digitalwallet.model.dto.RequestAccountDto;
import com.matera.digitalwallet.model.entities.Account;
import com.matera.digitalwallet.model.entities.Holder;
import com.matera.digitalwallet.repository.AccountRepository;
import com.matera.digitalwallet.repository.HolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final HolderRepository holderRepository;

    public Account createAccount(RequestAccountDto requestAccountDto) {

        final Holder holder = new Holder();
        holder.setCpf(requestAccountDto.getCpf());
        holder.setName(requestAccountDto.getName());
        holderRepository.save(holder);

        var account = new Account();
        account.setAgency(requestAccountDto.getAgency());
        account.setHolder(holder);
        accountIsValid(account);

        return accountRepository.save(account);
    }

    private void accountIsValid(Account account) {
        Optional<Account> accountOptional =
                accountRepository.findByAgencyAndNumber(account.getAgency(), account.getNumber());

        if (accountOptional.isPresent()) {
            throw new ExistingAccountException();
        }
    }

    public List<Account> findAccounts() {
        return accountRepository.findAll();
    }

    public Account findAccount(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);

        if (accountOptional.isEmpty()) {
            throw new InvalidAccountException("Essa conta não existe.");
        }
        return accountOptional.get();
    }

    public Account creditAccount(Long idAccount, BigDecimal value) {
        Account account = findAccount(idAccount);
        account.credit(value);

        return accountRepository.save(account);
    }

    public Account debitAccount(Long idAccount, BigDecimal value) {
        Account account = findAccount(idAccount);
        account.debit(value);

        return accountRepository.save(account);
    }

    public void transfer(Long idAccountDebited, Long idAccountCredited, BigDecimal value) {
        Account accountDebited = findAccount(idAccountDebited);
        Account accountCredited = findAccount(idAccountCredited);
        accountDebited.debit(value);
        accountCredited.credit(value);
        List<Account> accounts = new ArrayList<>();
        accounts.add(accountCredited);
        accounts.add(accountDebited);

        accountRepository.saveAll(accounts);
    }
}
