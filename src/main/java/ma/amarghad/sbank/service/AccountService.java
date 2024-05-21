package ma.amarghad.sbank.service;

import ma.amarghad.sbank.dto.BankAccountDto;

import java.util.List;

public interface AccountService {

    List<BankAccountDto> getAccounts(String q);
    BankAccountDto getAccount(String accountId);

    BankAccountDto createAccount(BankAccountDto accountDto);

    void deleteAccount(String accountId);
}
