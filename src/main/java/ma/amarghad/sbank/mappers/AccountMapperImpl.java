package ma.amarghad.sbank.mappers;

import lombok.AllArgsConstructor;
import ma.amarghad.sbank.dto.*;
import ma.amarghad.sbank.entities.BankAccount;
import ma.amarghad.sbank.entities.CurrentAccount;
import ma.amarghad.sbank.entities.Customer;
import ma.amarghad.sbank.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class AccountMapperImpl implements AccountMapper {

    private CustomerMapper customerMapper;

    @Override
    public BankAccountDto toDto(BankAccount bankAccount) {
        BankAccountDto bankAccountDto = bankAccount instanceof CurrentAccount ?
                new CurrentAccountDto() :
                new SavingAccountDto();

        BeanUtils.copyProperties(bankAccount, bankAccountDto);
        CustomerDto customerDto = customerMapper.toDto(bankAccount.getCustomer());
        bankAccountDto.setCustomerDto(customerDto);
        bankAccountDto.setType(bankAccount.getClass().getSimpleName());
        return bankAccountDto;
    }

    @Override
    public BankAccount toEntity(BankAccountDto accountDto) {
        BankAccount bankAccount = null;
        if (accountDto instanceof CurrentAccountDto currentAccountDto) {
            bankAccount = new CurrentAccount(currentAccountDto.getOverDraft());
        } else if (accountDto instanceof SavingAccountDto savingAccountDto) {
            bankAccount = new SavingAccount(savingAccountDto.getInterestRate());
        }

        assert bankAccount != null;

        Customer customer = customerMapper.toEntity(accountDto.getCustomerDto());
        bankAccount.setCustomer(customer);
        BeanUtils.copyProperties(accountDto, bankAccount);
        return bankAccount;
    }
}
