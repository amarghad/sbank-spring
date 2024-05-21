package ma.amarghad.sbank.mappers;

import ma.amarghad.sbank.dto.RoleDto;
import ma.amarghad.sbank.entities.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleMapperImpl implements RoleMapper {
    @Override
    public RoleDto toDto(Role role) {
        return new RoleDto(role.getId(), role.getName());
    }

    @Override
    public Role toEntity(RoleDto roleDto) {
        return new Role(roleDto.getId(), roleDto.getName());
    }
}
