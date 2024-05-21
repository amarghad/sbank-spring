package ma.amarghad.sbank.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.amarghad.sbank.dto.CustomerDto;
import ma.amarghad.sbank.entities.Customer;
import ma.amarghad.sbank.entities.User;
import ma.amarghad.sbank.exceptions.NoSuchCustomerException;
import ma.amarghad.sbank.mappers.CustomerMapper;
import ma.amarghad.sbank.repository.CustomerRepository;
import ma.amarghad.sbank.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class RepositoryCustomerService implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private UserRepository userRepository;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDTO) {
        Customer customer = customerRepository.save(customerMapper.toEntity(customerDTO));
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        customer.setCreatedBy(user);
        return customerMapper.toDto(customer);
    }

    @Override
    public List<CustomerDto> getCustomers() {
        return getCustomers(null);
    }

    @Override
    public List<CustomerDto> getCustomers(String keyword) {

        List<Customer> customers = keyword != null && !keyword.isBlank() ?
                customerRepository.findByNameLike(keyword) :
                customerRepository.findAll();


        return customers.stream()
                .map(customerMapper::toDto)
                .toList();

    }

    @Override
    public CustomerDto getCustomer(Long id) throws NoSuchCustomerException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(NoSuchCustomerException::new);

        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto dto) {
        Customer customer = customerRepository.save(customerMapper.toEntity(dto));
        return customerMapper.toDto(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
