package com.api.water_sytem_management_java.controllers;

import com.api.water_sytem_management_java.controllers.dtos.BankAccountInput;
import com.api.water_sytem_management_java.controllers.dtos.BankAccountOutput;
import com.api.water_sytem_management_java.models.BankAccount;
import com.api.water_sytem_management_java.services.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/bank-accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public ResponseEntity<List<BankAccountOutput>> getAllBankAccounts() {
        List<BankAccountOutput> accounts = bankAccountService.getAllBankAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccountInput input) {
        BankAccount account = input.toBankAccount();
        BankAccount savedAccount = bankAccountService.createBankAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountOutput> updateBankAccount(@PathVariable UUID id, @RequestBody BankAccountInput input) {
        Optional<BankAccountOutput> updatedAccount = bankAccountService.updateBankAccount(id, input);
        return updatedAccount.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable UUID id) {
        bankAccountService.deleteBankAccount(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}