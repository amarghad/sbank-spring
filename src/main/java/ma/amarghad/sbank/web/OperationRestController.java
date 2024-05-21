package ma.amarghad.sbank.web;

import lombok.AllArgsConstructor;
import ma.amarghad.sbank.dto.OperationDto;
import ma.amarghad.sbank.service.OperationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts/{accountId}/operations")
@AllArgsConstructor
@CrossOrigin("*")
public class OperationRestController {

    private OperationService operationService;

    /**
     *
     * @param accountId
     * @return
     */
    @GetMapping()
    @PreAuthorize("hasAuthority('SCOPE_USER') || hasAuthority('SCOPE_ADMIN')")
    List<OperationDto> getHistory(@PathVariable String accountId) {
        return operationService.getAccountOperations(accountId);
    }

    /**
     *
     * @param accountId
     * @param amount
     * @return
     */
    @PostMapping("debit")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    OperationDto debit(@PathVariable String accountId, @RequestBody Double amount) {
        return operationService.debit(accountId, amount);
    }

    /**
     *
     * @param accountId
     * @param amount
     * @return
     */
    @PostMapping("credit")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    OperationDto credit(@PathVariable String accountId, @RequestBody Double amount) {
        return operationService.credit(accountId, amount);
    }

    /**
     *
     * @param accountId
     * @param payload
     */
    @PostMapping("transfer")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    void transfer(@PathVariable String accountId, @RequestBody Map<String, Object> payload) {
        String destinationId = (String) payload.get("destinationId");
        Object amountObj = payload.get("amount");
        Double amount = 0d;
        if (amountObj instanceof Integer amountInt) {
            amount = Double.valueOf(amountInt);
        } else if(amountObj instanceof Double) {
            amount = (Double) amountObj;
        }
        operationService.transfer(accountId, destinationId, amount);
    }

}
