package ma.amarghad.sbank.mappers;

import lombok.AllArgsConstructor;
import ma.amarghad.sbank.dto.CustomerDto;
import ma.amarghad.sbank.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerMapperImpl implements CustomerMapper {


    @Override
    public CustomerDto toDto(Customer customer) {
        CustomerDto customerDTO = new CustomerDto();
        BeanUtils.copyProperties(customer,customerDTO);
        return customerDTO;
    }

    @Override
    public Customer toEntity(CustomerDto dto) {
        Customer customer=new Customer();
        BeanUtils.copyProperties(dto, customer);
        return  customer;
    }
}
