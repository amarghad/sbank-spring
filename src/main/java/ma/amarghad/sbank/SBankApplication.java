package ma.amarghad.sbank;

import ma.amarghad.sbank.dto.RoleDto;
import ma.amarghad.sbank.dto.UserDto;
import ma.amarghad.sbank.entities.Role;
import ma.amarghad.sbank.repository.RoleRepository;
import ma.amarghad.sbank.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SBankApplication {


    public static void main(String[] args) {
        SpringApplication.run(SBankApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository, UserService userService) {
        return args -> {
            List.of("ADMIN", "USER", "MANAGER").forEach(role -> {
                roleRepository.save(Role.builder().name(role).build());
            });
            userService.createUser(
                    UserDto.builder().username("marghad").roles(List.of(
                            RoleDto.builder().name("ADMIN").id(1L).build()
                    )).build(),
                    "123456"
            );
        };
    }
}
