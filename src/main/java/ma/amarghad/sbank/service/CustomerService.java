package ma.amarghad.sbank.service;

import ma.amarghad.sbank.dto.CustomerDto;
import ma.amarghad.sbank.exceptions.NoSuchCustomerException;

import java.util.List;

public interface CustomerService {

    CustomerDto saveCustomer(CustomerDto dto);
    List<CustomerDto> getCustomers();

    List<CustomerDto> getCustomers(String keyword);

    CustomerDto getCustomer(Long id) throws NoSuchCustomerException;
    CustomerDto updateCustomer(CustomerDto dto);
    void deleteCustomer(Long id);

}
