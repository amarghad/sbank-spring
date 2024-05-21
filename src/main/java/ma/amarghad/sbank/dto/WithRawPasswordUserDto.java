package ma.amarghad.sbank.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WithRawPasswordUserDto extends UserDto {
    private String rawPassword;
}
