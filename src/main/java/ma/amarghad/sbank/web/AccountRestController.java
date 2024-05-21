package ma.amarghad.sbank.web;

import lombok.AllArgsConstructor;
import ma.amarghad.sbank.dto.BankAccountDto;
import ma.amarghad.sbank.service.AccountService;
import ma.amarghad.sbank.service.OperationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
@CrossOrigin("*")
public class AccountRestController {

    private AccountService accountService;

    /**
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_USER') || hasAuthority('SCOPE_ADMIN')")
    List<BankAccountDto> index(@RequestParam(required = false) String q) {
        return accountService.getAccounts(q);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public BankAccountDto add(@RequestBody BankAccountDto accountDto) {
        return accountService.createAccount(accountDto);
    }

    /**
     *
     * @param accountId
     * @return
     */
    @GetMapping("{accountId}")
    @PreAuthorize("hasAuthority('SCOPE_USER') || hasAuthority('SCOPE_ADMIN')")
    BankAccountDto view(@PathVariable String accountId ) {
        return accountService.getAccount(accountId);
    }

    /**
     *
     * @param accountId
     */
    @DeleteMapping("{accountId}")
    @PreAuthorize("hasAuthority('SCOPE_USER') || hasAuthority('SCOPE_ADMIN')")
    void delete(@PathVariable String accountId) {
        accountService.deleteAccount(accountId);
    }


}
