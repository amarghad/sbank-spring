package ma.amarghad.sbank.service;

import ma.amarghad.sbank.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDto> getAll();
    UserDto createUser(UserDto userDto, String rawPassword);
    UserDto updateUser(UserDto userDetails);
    void changePassword(String oldPassword, String newPassword);
    void deleteUser(String username);
    boolean userExists(String username);


}
