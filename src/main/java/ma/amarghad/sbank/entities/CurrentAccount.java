package ma.amarghad.sbank.entities;

import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class CurrentAccount extends BankAccount {
    private double overDraft;
}
