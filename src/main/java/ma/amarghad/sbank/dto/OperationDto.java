package ma.amarghad.sbank.dto;

import lombok.Data;
import ma.amarghad.sbank.enums.OperationType;

import java.util.Date;

@Data
public class OperationDto {
    private Long id;
    private Date date;
    private double amount;
    private OperationType type;
}
