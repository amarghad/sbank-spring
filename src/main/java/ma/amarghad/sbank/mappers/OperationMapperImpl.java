package ma.amarghad.sbank.mappers;

import ma.amarghad.sbank.dto.OperationDto;
import ma.amarghad.sbank.entities.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OperationMapperImpl implements OperationMapper {
    @Override
    public OperationDto toDto(Operation entity) {
        OperationDto dto = new OperationDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public Operation toEntity(OperationDto dto) {
        Operation operation = new Operation();
        BeanUtils.copyProperties(dto, operation);
        return operation;
    }
}
