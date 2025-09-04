package com.api.water_sytem_management_java.services;

import com.api.water_sytem_management_java.controllers.dtos.BankAccountInput;
import com.api.water_sytem_management_java.controllers.dtos.BankAccountOutput;
import com.api.water_sytem_management_java.models.BankAccount;
import com.api.water_sytem_management_java.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccountOutput> getAllBankAccounts() {
        return bankAccountRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(BankAccount::toBankAccountOutput)
                .collect(Collectors.toList());
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        if (bankAccount.getBalance() < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        return bankAccountRepository.save(bankAccount);
    }

    @Transactional
    public Optional<BankAccountOutput> updateBankAccount(UUID id, BankAccountInput input) {
        return bankAccountRepository.findById(id).map(existingAccount -> {
            existingAccount.setAccountNumber(input.accountNumber());
            existingAccount.setAccountHolder(input.accountHolder());
            if (input.balance() < 0) {
                throw new IllegalArgumentException("Balance cannot be negative.");
            }
            existingAccount.setBalance(input.balance());
            existingAccount.setCreditLimit(input.creditLimit());
            existingAccount.setOpeningDate(input.openingDate());

            BankAccount updatedAccount = bankAccountRepository.save(existingAccount);
            return updatedAccount.toBankAccountOutput();
        });
    }

    public void deleteBankAccount(UUID id) {
        bankAccountRepository.deleteById(id);
    }
}