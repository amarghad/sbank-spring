package ma.amarghad.sbank.repository;

import ma.amarghad.sbank.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<BankAccount, String> {

    @Query("SELECT c FROM BankAccount c WHERE c.id LIKE %:q% OR c.customer.name LIKE %:q% ")
    List<BankAccount> findByKeyword(String q);
}
