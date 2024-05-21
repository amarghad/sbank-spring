package ma.amarghad.sbank.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.amarghad.sbank.dto.OperationDto;
import ma.amarghad.sbank.entities.BankAccount;
import ma.amarghad.sbank.entities.Operation;
import ma.amarghad.sbank.entities.User;
import ma.amarghad.sbank.enums.OperationType;
import ma.amarghad.sbank.exceptions.NoSuckBankAccountException;
import ma.amarghad.sbank.exceptions.NoSufficientBalanceException;
import ma.amarghad.sbank.mappers.OperationMapper;
import ma.amarghad.sbank.repository.AccountRepository;
import ma.amarghad.sbank.repository.OperationRepository;
import ma.amarghad.sbank.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RepositoryOperationService implements OperationService {

    private AccountRepository accountRepository;
    private OperationRepository operationRepository;
    private OperationMapper operationMapper;
    private UserRepository userRepository;

    @Override
    public OperationDto credit(String accountId, double amount) {

        BankAccount account = accountRepository.findById(accountId)
                .orElseThrow(NoSuckBankAccountException::new);

        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);


        Operation operation = Operation.builder()
                .account(account)
                .date(new Date())
                .type(OperationType.CREDIT)
                .amount(amount)
                .createdBy(user)
                .build();

        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);

        return operationMapper.toDto(operationRepository.save(operation));
    }

    public OperationDto debit(String accountId, double amount) {

        BankAccount account = accountRepository.findById(accountId)
                .orElseThrow(NoSuckBankAccountException::new);

        if (account.getBalance() < amount) {
            throw new NoSufficientBalanceException();
        }

        User user = userRepository.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).orElse(null);

        Operation operation = Operation.builder()
                .account(account)
                .date(new Date())
                .type(OperationType.DEBIT)
                .amount(amount)
                .createdBy(user)
                .build();

        double newBalance = account.getBalance() - amount;
        account.setBalance( newBalance );

        return operationMapper.toDto(operationRepository.save(operation));

    }

    @Override
    public void transfer(String sourceId, String destinationId, double amount) {
        debit(sourceId, amount);
        credit(destinationId, amount);
    }

    @Override
    public List<OperationDto> getAccountOperations(String accountId) {

        return operationRepository.findByAccountId(accountId)
                .stream()
                .map(operationMapper::toDto)
                .toList();

    }
}
