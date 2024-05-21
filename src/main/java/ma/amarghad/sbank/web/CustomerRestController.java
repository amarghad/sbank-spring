package ma.amarghad.sbank.web;

import lombok.AllArgsConstructor;
import ma.amarghad.sbank.dto.CustomerDto;
import ma.amarghad.sbank.service.CustomerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin("*")
@AllArgsConstructor
public class CustomerRestController {

    private CustomerService customerService;

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_USER') || hasAuthority('SCOPE_ADMIN')")
    public List<CustomerDto> index(@RequestParam(required = false) String q) {
        //System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return customerService.getCustomers(q);
    }


    @GetMapping("{customerId}")
    @PreAuthorize("hasAuthority('SCOPE_USER') || hasAuthority('SCOPE_ADMIN')")
    public CustomerDto view(@PathVariable Long customerId) {
        return customerService.getCustomer(customerId);
    }

    /**
     *
     * @param customerDTO
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CustomerDto save(@RequestBody CustomerDto customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }


    /**
     *
     * @param customerId
     * @param name
     * @param email
     */
    @PutMapping("{customerId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CustomerDto update(@PathVariable Long customerId, String name, String email) {
        CustomerDto customerDTO = CustomerDto.builder()
                .id(customerId).name(name)
                .email(email)
                .build();

        return customerService.updateCustomer(customerDTO);

    }


    /**
     *
     * @param customerId
     */
    @DeleteMapping("{customerId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void delete(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }



}
