package ma.amarghad.sbank.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import ma.amarghad.sbank.enums.AccountStatus;
import java.util.Date;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CurrentAccountDto.class, name = "CurrentAccount"),
        @JsonSubTypes.Type(value = SavingAccountDto.class, name = "SavingAccount")
})
public abstract class BankAccountDto {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDto customerDto;
    private String type;
}
