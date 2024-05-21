package ma.amarghad.sbank.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.amarghad.sbank.dto.BankAccountDto;
import ma.amarghad.sbank.entities.BankAccount;
import ma.amarghad.sbank.entities.User;
import ma.amarghad.sbank.enums.AccountStatus;
import ma.amarghad.sbank.exceptions.NoSuckBankAccountException;
import ma.amarghad.sbank.mappers.AccountMapper;
import ma.amarghad.sbank.repository.AccountRepository;
import ma.amarghad.sbank.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class RepositoryAccountService implements AccountService {

    private AccountRepository accountRepository;
    private AccountMapper accountMapper;
    private UserRepository userRepository;


    public List<BankAccountDto> getAccounts(String q) {

        return accountRepository.findAll().stream()
                .map(accountMapper::toDto)
                .toList();
    }

    @Override
    public BankAccountDto getAccount(String accountId) {
        BankAccount account = accountRepository.findById(accountId).orElseThrow(NoSuckBankAccountException::new);
        return accountMapper.toDto(account);
    }

    @Override
    public BankAccountDto createAccount(BankAccountDto accountDto) {
        BankAccount bankAccount = accountMapper.toEntity(accountDto);
        String accountId = UUID.randomUUID().toString();
        bankAccount.setId(accountId);
        bankAccount.setStatus(AccountStatus.CREATED);
        bankAccount.setCreatedAt(new Date());

        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        bankAccount.setCreatedBy(user);

        return accountMapper.toDto(accountRepository.save(bankAccount));
    }

    @Override
    public void deleteAccount(String accountId) {
        accountRepository.deleteById(accountId);
    }


}
