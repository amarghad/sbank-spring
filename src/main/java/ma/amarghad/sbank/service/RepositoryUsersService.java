package ma.amarghad.sbank.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.amarghad.sbank.dto.UserDto;
import ma.amarghad.sbank.entities.User;
import ma.amarghad.sbank.exceptions.NoRolesAddedException;
import ma.amarghad.sbank.exceptions.ShortPasswordValueException;
import ma.amarghad.sbank.mappers.UserMapper;
import ma.amarghad.sbank.repository.RoleRepository;
import ma.amarghad.sbank.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class RepositoryUsersService implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    private static final int MIN_PASSWORD_LENGTH = 6;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("No used found with username " + username)
        );
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto).toList();
    }

    @Override
    public UserDto createUser(UserDto userDto, String rawPassword) {

        if (userDto.getRoles() == null || userDto.getRoles().isEmpty()) {
            throw new NoRolesAddedException();
        }

        if (rawPassword.length() < MIN_PASSWORD_LENGTH) {
            throw new ShortPasswordValueException(MIN_PASSWORD_LENGTH);
        }

        String encoded = passwordEncoder.encode(rawPassword);
        User user = userMapper.toEntity(userDto);
        user.setPassword(encoded);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username "+username+" does not exists to delete")
        );

        userRepository.delete(user);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username "+username+" does not exists to delete")
        );

        if (newPassword.length() < MIN_PASSWORD_LENGTH)
            throw new ShortPasswordValueException(MIN_PASSWORD_LENGTH);

        if (passwordEncoder.matches(oldPassword, user.getPassword()))
            throw new RuntimeException("Password does not match");

        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        User user = userMapper.toEntity(userDto);
        userRepository.save(user);
        return userMapper.toDto(user);

    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }
}
