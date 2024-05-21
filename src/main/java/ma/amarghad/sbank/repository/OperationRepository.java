package ma.amarghad.sbank.repository;

import ma.amarghad.sbank.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findByAccountId(String accountId);

}
