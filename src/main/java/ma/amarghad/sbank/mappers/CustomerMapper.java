package ma.amarghad.sbank.mappers;

import ma.amarghad.sbank.dto.CustomerDto;
import ma.amarghad.sbank.entities.Customer;

//@Mapper(componentModel = "spring")
public interface CustomerMapper extends Mapper<Customer, CustomerDto> {
}
