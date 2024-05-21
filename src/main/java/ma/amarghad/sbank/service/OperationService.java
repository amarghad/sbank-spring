package ma.amarghad.sbank.service;


import ma.amarghad.sbank.dto.OperationDto;
import ma.amarghad.sbank.entities.BankAccount;
import ma.amarghad.sbank.entities.Operation;
import ma.amarghad.sbank.enums.OperationType;

import java.util.List;

public interface OperationService {

    OperationDto credit(String accountId, double amount);
    OperationDto debit(String accountId, double amount);
    void transfer(String source, String destination, double amount);

    List<OperationDto> getAccountOperations(String accountId);
}
