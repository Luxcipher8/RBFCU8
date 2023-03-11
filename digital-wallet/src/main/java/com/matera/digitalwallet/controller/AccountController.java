package com.matera.digitalwallet.controller;

import com.matera.digitalwallet.model.dto.ResponseAccountDto;
import com.matera.digitalwallet.model.dto.RequestAccountDto;
import com.matera.digitalwallet.model.entities.Account;
import com.matera.digitalwallet.repository.AccountRepository;
import com.matera.digitalwallet.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/contas")
public class AccountController {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @PostMapping(value = "/cadastro")
    public ResponseAccountDto createAccount(@RequestBody RequestAccountDto requestAccountDto) throws InterruptedException {
        Account account = accountService.createAccount(requestAccountDto);
        account.setNumber(6544);
        return account.toAccountDto();
    }

    @GetMapping
    public List<Account> findAccounts() {
        return accountService.findAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAccountDto> findAccountById(@PathVariable Long id) {
        Account account = accountService.findAccount(id);
        return ResponseEntity.ok(account.toAccountDto());
    }

    @PostMapping("/{idAccount}/credito/{value}")
    public ResponseEntity<ResponseAccountDto> accountCredit(@PathVariable Long idAccount,
                                                            @PathVariable BigDecimal value) {
        Account account = accountService.creditAccount(idAccount, value);
        return ResponseEntity.ok(account.toAccountDto());
    }

    @PostMapping("/{idAccount}/debito/{value}")
    public ResponseEntity<ResponseAccountDto> accountDebit(@PathVariable Long idAccount,
                                                           @PathVariable BigDecimal value) {
        Account account = accountService.debitAccount(idAccount, value);
        return ResponseEntity.ok(account.toAccountDto());
    }

    @PostMapping("/{idAccountDebited}/{idAccountCredited}/{value}")
    public ResponseEntity transferAccount(@PathVariable Long idAccountDebited,
                                          @PathVariable Long idAccountCredited,
                                          @PathVariable BigDecimal value) {
        accountService.transfer(idAccountDebited, idAccountCredited, value);
        return ResponseEntity.ok("Transferência realizada com sucesso.");
    }
}
