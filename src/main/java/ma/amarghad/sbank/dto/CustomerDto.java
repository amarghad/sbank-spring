package ma.amarghad.sbank.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
}
