package ma.amarghad.sbank.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity @Data @Builder @AllArgsConstructor @NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
