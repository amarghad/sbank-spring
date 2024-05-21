package ma.amarghad.sbank.service;

import lombok.AllArgsConstructor;
import ma.amarghad.sbank.dto.RoleDto;
import ma.amarghad.sbank.mappers.RoleMapper;
import ma.amarghad.sbank.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RepositoryRoleService implements RoleService {
    private RoleRepository roleRepository;
    private RoleMapper roleMapper;
    @Override
    public List<RoleDto> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toDto).toList();
    }
}
