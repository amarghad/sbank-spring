package ma.amarghad.sbank.dto;

import lombok.*;
import ma.amarghad.sbank.entities.Role;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class UserDto {

    private Long id;
    private String username;
    private List<RoleDto> roles;

}

