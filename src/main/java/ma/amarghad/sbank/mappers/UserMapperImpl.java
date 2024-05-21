package ma.amarghad.sbank.mappers;

import lombok.AllArgsConstructor;
import ma.amarghad.sbank.dto.UserDto;
import ma.amarghad.sbank.entities.Role;
import ma.amarghad.sbank.entities.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserMapperImpl implements UserMapper {

    private RoleMapper roleMapper;

    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles().stream().map(roleMapper::toDto).toList())
                .build();
    }

    @Override
    public User toEntity(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .roles(userDto.getRoles().stream().map(roleMapper::toEntity).toList())
                .build();
    }
}
