package ma.amarghad.sbank.web;

import lombok.AllArgsConstructor;
import ma.amarghad.sbank.dto.RoleDto;
import ma.amarghad.sbank.repository.RoleRepository;
import ma.amarghad.sbank.service.RoleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("roles")
public class RoleRestController {

    private RoleService roleService;

    @GetMapping
    public List<RoleDto> index() {
        return roleService.getAll();
    }

}
