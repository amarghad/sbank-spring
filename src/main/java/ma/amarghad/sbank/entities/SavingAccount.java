package ma.amarghad.sbank.entities;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
public class SavingAccount extends BankAccount {
    private double interestRate;
}
