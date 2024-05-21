package ma.amarghad.sbank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.amarghad.sbank.enums.OperationType;
import java.util.Date;

@Entity @Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class Operation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double amount;
    private OperationType type;
    @ManyToOne
    private BankAccount account;
    @ManyToOne
    private User createdBy;
}
