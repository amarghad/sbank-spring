package ma.amarghad.sbank.web;

import lombok.AllArgsConstructor;
import ma.amarghad.sbank.dto.UserDto;
import ma.amarghad.sbank.dto.WithRawPasswordUserDto;
import ma.amarghad.sbank.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("users")
public class UserRestController {

    private UserService userService;


    @GetMapping
    public List<UserDto> get() {
        return userService.getAll();
    }

    @PostMapping
    public UserDto register(@RequestBody WithRawPasswordUserDto userDto) {
        return userService.createUser(userDto, userDto.getRawPassword());
    }

    @DeleteMapping("{username}")
    public void delete(@PathVariable String username) {
        userService.deleteUser(username);
    }

}
