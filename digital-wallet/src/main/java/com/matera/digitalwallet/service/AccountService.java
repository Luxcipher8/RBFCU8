package com.matera.digitalwallet.service;

import com.matera.digitalwallet.exceptions.ExistingAccountException;
import com.matera.digitalwallet.exceptions.InvalidAccountException;
import com.matera.digitalwallet.exceptions.InvalidBankException;
import com.matera.digitalwallet.exceptions.InvalidOperationException;
import com.matera.digitalwallet.model.dto.PixBacenDto;
import com.matera.digitalwallet.model.dto.RequestAccountDto;
import com.matera.digitalwallet.model.entities.Account;
import com.matera.digitalwallet.model.entities.Bank;
import com.matera.digitalwallet.model.entities.Holder;
import com.matera.digitalwallet.repository.AccountRepository;
import com.matera.digitalwallet.repository.BankRepository;
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
    private final BankRepository bankRepository;
    private final PixBacenService pixBacenService;

    public Account createAccount(RequestAccountDto requestAccountDto) {

        int code = requestAccountDto.getCode();
        final Bank bank = bankRepository.findByBankCode(code).orElseThrow(
                () -> new InvalidBankException("Banco não encontrado: " + code)
        );

        final Holder holder = new Holder();
        holder.setCpf(requestAccountDto.getCpf());
        holder.setName(requestAccountDto.getName());
        holderRepository.save(holder);

        var account = new Account();
        account.setAgency(requestAccountDto.getAgency());
        account.setHolder(holder);
        account.setBank(bank);
        account.setPix(requestAccountDto.getPixKey());
        accountIsValid(account);

        Account savedAccount = accountRepository.save(account);
        pixBacenService.registerPixCentralBank(savedAccount.toBacenDto());

        return savedAccount;
    }

    private void accountIsValid(Account account) {
        Optional<Account> accountOptional =
                accountRepository.findByAgencyAndNumber(account.getAgency(), account.getNumber());

        if (accountOptional.isPresent()) {
            throw new ExistingAccountException();
        }
    }

    public List<Account> findAccounts() {

        List<Account> accountList = accountRepository.findAll();

        if (accountList.isEmpty()){
            throw new InvalidAccountException("Não encontramos contas cadastradas em nossa base de dados.");
        }

        return accountList;
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

    public static void validateTransfer(Account debitedAccount, Account creditedAccount) {
        if (debitedAccount.getBank().getBankCode() != creditedAccount.getBank().getBankCode()) {
            throw new InvalidOperationException();
        }
    }

    public void pix(Long debitedAccountId, String pixKey, BigDecimal value) {
        final Account debitedAccount = findAccount(debitedAccountId);

        PixBacenDto pixBacenDto = pixBacenService.findAccountCentralBank(pixKey);

        Account creditedAccount = accountRepository.findByAgencyAndNumber(pixBacenDto.getAgency(), pixBacenDto.getNumber())
                .orElseThrow(() -> new InvalidAccountException("Conta não encontrada: " + pixKey));

        debitedAccount.debit(value);
        creditedAccount.credit(value);

        List<Account> accounts = new ArrayList<>();
        accounts.add(creditedAccount);
        accounts.add(debitedAccount);
        accountRepository.saveAll(accounts);

    }
}
